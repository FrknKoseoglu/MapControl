import { Adb, AdbDaemonTransport } from '@yume-chan/adb';
import { AdbDaemonWebUsbDeviceManager } from '@yume-chan/adb-daemon-webusb';
import AdbWebCredentialStore from '@yume-chan/adb-credential-web';
import { ReadableStream } from '@yume-chan/stream-extra';
import { apkPackage } from './apk.mjs';
import { install, PACKAGE, quote } from './core.mjs';

const $ = id => document.getElementById(id);
let adb, usb, busy = false, apk, dex, checkedDevice = false;
let apkRevision = 0, dexRevision = 0;
const supported = window.isSecureContext && !!AdbDaemonWebUsbDeviceManager.BROWSER;
function log(text) {
  const line = `[${new Date().toLocaleTimeString('tr-TR')}] ${text}\n`;
  $('log').textContent = ($('log').textContent + line).slice(-64000);
  $('log').scrollTop = $('log').scrollHeight;
}
function status(text, kind = '') { $('status').textContent = text; $('status').className = kind; }
function refresh() {
  $('connect').disabled = busy || !!adb || !supported;
  $('disconnect').disabled = !usb;
  $('info').disabled = busy || !adb;
  $('install').disabled = busy || !adb || !checkedDevice || !apk || !$('ready').checked || ($('mode').value === 'oem' && !dex);
  for (const id of ['mode','apk','dex','ready']) $(id).disabled = busy;
  $('dex-panel').hidden = $('mode').value !== 'oem';
  $('launch').disabled = busy || !adb || !$('launch').dataset.installed;
}
async function disconnect() {
  const oldAdb = adb, oldUsb = usb;
  adb = undefined; usb = undefined; checkedDevice = false;
  delete $('launch').dataset.installed;
  refresh();
  try { if (oldAdb) await oldAdb.close(); } catch {}
  try { if (oldUsb?.raw.opened) await oldUsb.raw.close(); } catch {}
}
async function deadline(task, milliseconds) {
  let timer;
  try {
    return await Promise.race([task, new Promise((_, reject) => {
      timer = setTimeout(() => { void disconnect(); reject(new Error('Zaman aşımı; bağlantı kapatıldı.')); }, milliseconds);
    })]);
  } finally { clearTimeout(timer); }
}
async function action(task) {
  if (busy) return;
  busy = true; refresh();
  try { await task(); }
  catch (error) { log(error.message || String(error)); status(error.message || 'İşlem başarısız.', 'error'); }
  finally { busy = false; refresh(); }
}
async function shell(command, timeout = 20000) {
  if (!adb) throw new Error('Araç bağlantısı yok.');
  const client = adb;
  const marker = `__MC_${crypto.randomUUID().replaceAll('-', '')}:`;
  return deadline((async () => {
    const socket = await client.createSocket(`shell:(${command}); rc=$?; printf '\\n${marker}%s\\n' "$rc"`);
    const reader = socket.readable.getReader(), decoder = new TextDecoder();
    let output = '';
    try {
      for (;;) {
        const { done, value } = await reader.read();
        if (done) break;
        output += decoder.decode(value, { stream: true });
        if (output.length > 65536) throw new Error('Araç yanıtı boyut sınırını aştı.');
      }
      output += decoder.decode();
      const match = output.match(new RegExp(`\\r?\\n${marker}(\\d+)\\r?\\n?\\s*$`));
      if (!match) throw new Error('Bağlantı tamamlanmadı; komut sonucu doğrulanamadı.');
      return { code: Number(match[1]), output: output.slice(0, match.index).replaceAll('\r', '') };
    } finally { reader.releaseLock(); await socket.close(); }
  })(), timeout);
}
async function push(file, filename) {
  if (!adb) throw new Error('Araç bağlantısı yok.');
  const client = adb;
  log(`${file.name} aktarılıyor (${(file.size / 1048576).toFixed(1)} MB)…`);
  $('progress').value = 0;
  await deadline((async () => {
    const sync = await client.sync();
    let offset = 0;
    const stream = new ReadableStream({
      async pull(controller) {
        if (offset === file.size) { controller.close(); return; }
        const end = Math.min(file.size, offset + 65536);
        const bytes = new Uint8Array(await file.slice(offset, end).arrayBuffer());
        offset = end; controller.enqueue(bytes);
        $('progress').value = Math.round(offset * 100 / file.size);
      },
    });
    // Match the official installer: the package service must be able to read the APK.
    try { await sync.write({ filename, file: stream, permission: 0o644 }); }
    finally { await sync.dispose(); }
  })(), 600000);
  $('progress').value = 100;
  log('Dosya aktarımı tamamlandı.');
}

$('connect').addEventListener('click', () => action(async () => {
  const manager = AdbDaemonWebUsbDeviceManager.BROWSER;
  const selected = await manager.requestDevice();
  if (!selected) { status('Cihaz seçimi iptal edildi.'); return; }
  usb = selected; refresh();
  try {
    status('Bağlanılıyor. Araç ekranında ADB onayı çıkarsa onaylayın.');
    const connection = await deadline(selected.connect(), 20000);
    const transport = await deadline(AdbDaemonTransport.authenticate({
      serial: selected.serial, connection, credentialStore: new AdbWebCredentialStore('MapControl Local Installer'),
    }), 90000);
    if (usb !== selected) { await transport.close(); return; }
    const client = new Adb(transport);
    adb = client;
    const lost = () => { if (adb === client) { void disconnect(); status('Araç bağlantısı kesildi.', 'error'); log('Araç bağlantısı kesildi.'); } };
    client.disconnected.then(lost, lost);
    status('Bağlandı. Önce cihaz bilgisini okuyun.', 'success');
    log('USB ADB bağlantısı kuruldu.');
  } catch (error) { await disconnect(); throw error; }
}));
$('disconnect').addEventListener('click', async () => { await disconnect(); status('Bağlantı kapatıldı. Devam eden kurulum varsa sonucu araçta kontrol edin.'); });
$('info').addEventListener('click', () => action(async () => {
  const result = await shell('getprop ro.product.model; getprop ro.build.version.release; pm path com.mapcontrol');
  $('device-info').textContent = result.output.trim() || 'Cihaz yanıtı boş.';
  // pm path returns nonzero when this is a first installation.
  const identity = await shell('getprop ro.product.model');
  if (identity.code !== 0 || !identity.output.trim()) throw new Error('Cihaz kimliği okunamadı.');
  checkedDevice = true;
  status('Cihaz bilgisi alındı. Seçtiğiniz aracın doğru olduğunu kontrol edin.', 'success');
}));
$('apk').addEventListener('change', async () => {
  const revision = ++apkRevision, file = $('apk').files[0];
  apk = undefined; refresh();
  if (!file) { $('apk-info').textContent = 'APK seçilmedi.'; return; }
  $('apk-info').textContent = 'APK paketi doğrulanıyor…';
  try {
    if (!/\.apk$/i.test(file.name) || file.size > 1024 ** 3) throw new Error('En fazla 1 GiB boyutunda bir .apk dosyası seçin.');
    const name = await apkPackage(file);
    if (name !== PACKAGE) throw new Error(`Bu APK MapControl değil: ${name}`);
    if (revision !== apkRevision) return;
    apk = file;
    $('apk-info').textContent = `${file.name} · ${(file.size / 1048576).toFixed(1)} MB · ${name}`;
    log('Seçilen APK: ' + file.name + ' (MapControl paketi doğrulandı).');
  } catch (error) { if (revision === apkRevision) $('apk-info').textContent = error.message; }
  refresh();
});
$('dex').addEventListener('change', async () => {
  const revision = ++dexRevision, file = $('dex').files[0]; dex = undefined; refresh();
  if (!file) { $('dex-info').textContent = ''; return; }
  const signature = new Uint8Array(await file.slice(0, 8).arrayBuffer());
  if (revision !== dexRevision) return;
  if (file.size < 112 || file.size > 8 * 1024 ** 2 || new TextDecoder().decode(signature).match(/^dex\n0\d\d\0$/) === null) {
    $('dex-info').textContent = 'Geçerli bir DEX dosyası seçin (en fazla 8 MiB).';
  } else { dex = file; $('dex-info').textContent = 'DEX seçildi. Dosyanın kaynağı ve bu araçla uyumu sizin tarafınızdan doğrulanmalıdır.'; }
  refresh();
});
for (const id of ['mode','ready']) $(id).addEventListener('change', refresh);
$('install').addEventListener('click', () => action(async () => {
  delete $('launch').dataset.installed;
  status('Kurulum sürüyor. Kabloyu çıkarmayın.');
  const id = Array.from(crypto.getRandomValues(new Uint8Array(8)), b => b.toString(16).padStart(2, '0')).join('');
  const result = await install({ shell, push, log }, { apk, dex, mode: $('mode').value, id });
  $('launch').dataset.installed = 'true';
  status(result.warnings.length ? `APK kuruldu; ${result.warnings.length} izin adımı uygulanamadı. Ayrıntılar işlem kaydında.` : 'APK kuruldu ve izin adımları tamamlandı.', result.warnings.length ? 'warning' : 'success');
}));
$('launch').addEventListener('click', () => action(async () => {
  const result = await shell(`am start -n ${quote('com.mapcontrol/com.mapcontrol.ui.activity.MainActivity')}`);
  log(result.output);
  if (result.code !== 0 || /(?:Error|Exception)/.test(result.output)) throw new Error('Uygulama açılamadı; işlem kaydını inceleyin.');
  status('Uygulamayı açma isteği gönderildi. Araç ekranını kontrol edin.', 'success');
}));
window.addEventListener('beforeunload', event => { if (busy) { event.preventDefault(); event.returnValue = ''; } });
if (!supported) status(!window.isSecureContext ? 'WebUSB için bu sayfayı HTTPS veya bu cihazın localhost adresinden açın. Başka cihazın HTTP adresi çalışmaz.' : 'Bu tarayıcı WebUSB desteklemiyor. Android telefonda veya bilgisayarda Chrome kullanın; iPhone desteklenmez.', 'error');
else status('Hazır. APK seçip aracın ADB bağlantısını açın.');
refresh();
