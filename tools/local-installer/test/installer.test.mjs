import test from 'node:test';
import assert from 'node:assert/strict';
import { readFile } from 'node:fs/promises';
import { zipSync } from 'fflate';
import { apkPackage, binaryManifestPackage } from '../src/apk.mjs';
import { install, grantPermissions, appendComponent, quote, ACCESSIBILITY } from '../src/core.mjs';

const options = { apk: { size: 100, name: "own'; reboot.apk" }, mode: 'standard', id: '0123456789abcdef' };
function mock(handler = () => undefined) {
  const commands = [], files = [], logs = [];
  return { commands, files, logs,
    log: message => logs.push(message),
    push: async (file, path) => { files.push(path); },
    shell: async command => { commands.push(command); return handler(command) ?? { code: 0, output: command.startsWith('pm install') ? 'Success\n' : command.startsWith('pm path') ? 'package:/data/app/com.mapcontrol/base.apk\n' : command.startsWith('settings get') ? 'other/component\n' : '' }; },
  };
}
test('normal install uses generated paths, checks success, preserves services and cleans up', async () => {
  const io = mock();
  const result = await install(io, options);
  assert.deepEqual(result.warnings, []);
  assert.equal(io.files[0], '/data/local/tmp/mapcontrol-web-0123456789abcdef.apk');
  assert(io.commands.some(c => c === `settings put secure enabled_accessibility_services 'other/component:${ACCESSIBILITY}'`));
  assert(!io.commands.some(c => c.includes(options.apk.name) || c.includes('set-device-owner') || c.includes('uninstall') || c.startsWith('pm install -r -d')));
  assert(io.commands.at(-1).startsWith('rm -f '));
});
test('failed installation never grants permissions and still removes uploaded files', async () => {
  const io = mock(c => c.startsWith('pm install') ? { code: 1, output: 'Failure [INSTALL_FAILED_UPDATE_INCOMPATIBLE]' } : undefined);
  await assert.rejects(install(io, options), /INSTALL_FAILED_UPDATE_INCOMPATIBLE/);
  assert(!io.commands.some(c => c.startsWith('pm grant')));
  assert(io.commands.at(-1).startsWith('rm -f '));
});
test('zero exit without Success is not installation success', async () => {
  const io = mock(c => c.startsWith('pm install') ? { code: 0, output: 'Pending user action' } : undefined);
  await assert.rejects(install(io, options), /Kurulum başarısız/);
});
test('Success text with failed exit is rejected', async () => {
  const io = mock(c => c.startsWith('pm install') ? { code: 1, output: 'Success' } : undefined);
  await assert.rejects(install(io, options), /Kurulum başarısız/);
});
test('package verification failure does not grant permissions', async () => {
  const io = mock(c => c.startsWith('pm path') ? { code: 1, output: '' } : undefined);
  await assert.rejects(install(io, options), /doğrulanamadı/);
  assert(!io.commands.some(c => c.startsWith('pm grant')));
});
test('OEM path requires a helper before writing anything', async () => {
  const io = mock();
  await assert.rejects(install(io, { ...options, mode: 'oem' }), /orijinal/);
  assert.equal(io.files.length, 0);
});
test('OEM path checks its own success marker and cleans both files', async () => {
  const io = mock(c => c.includes('app_process64') ? { code: 0, output: 'INSTALL_SUCCEEDED' } : undefined);
  await install(io, { ...options, mode: 'oem', dex: { size: 112 } });
  assert.equal(io.files.length, 2);
  assert(!io.commands.some(c => c.startsWith('pm install') || c.startsWith('setprop')));
  assert(io.commands.at(-1).includes('.dex'));
});
test('timeout after starting install reports uncertain result', async () => {
  const io = mock(c => { if (c.startsWith('pm install')) throw new Error('Zaman aşımı; bağlantı kapatıldı.'); });
  await assert.rejects(install(io, options), /sonucu belirsiz/);
});
test('transfer failure still attempts cleanup', async () => {
  const io = mock(); io.push = async () => { throw new Error('USB disconnected'); };
  await assert.rejects(install(io, options), /USB disconnected/);
  assert.equal(io.commands.length, 1);
  assert(io.commands[0].startsWith('rm -f '));
});
test('permission refusals stay visible rather than failing successful APK install', async () => {
  const io = mock(c => c.includes('WRITE_SECURE_SETTINGS') ? { code: 0, output: 'SecurityException: denied' } : undefined);
  const { warnings } = await install(io, options);
  assert.deepEqual(warnings, ['WRITE_SECURE_SETTINGS']);
});
test('connection failure during grants keeps known APK success in the message', async () => {
  const io = mock(c => { if (c.startsWith('pm grant')) throw new Error('Bağlantı kesildi'); });
  await assert.rejects(install(io, options), /APK kuruldu ancak izin adımları tamamlanamadı/);
});
test('unreadable secure list is not overwritten', async () => {
  const io = mock(c => c.startsWith('settings get') ? { code: 1, output: 'denied' } : undefined);
  await grantPermissions(io);
  assert(!io.commands.some(c => c.startsWith('settings put secure')));
});
test('components append without duplicates and null placeholders', () => {
  assert.equal(appendComponent('null\n', 'x/y'), 'x/y');
  assert.equal(appendComponent('a/b:x/y\r\n', 'x/y'), 'a/b:x/y');
  assert.equal(quote("a'; reboot"), "'a'\\''; reboot'");
});
test('invalid transaction id cannot become a shell path', async () => {
  const io = mock();
  await assert.rejects(install(io, { ...options, id: 'x; reboot' }), /kimliği/);
  assert.equal(io.commands.length, 0);
});

function manifest(pkg, utf8 = true) {
  const words = ['manifest', 'package', pkg];
  const enc = words.map(s => utf8 ? Uint8Array.from([s.length, s.length, ...new TextEncoder().encode(s), 0]) : Uint8Array.from([s.length, 0, ...Array.from(s).flatMap(c => [c.charCodeAt(0) & 255, c.charCodeAt(0) >> 8]), 0, 0]));
  const poolSize = 28 + 4 * words.length + enc.reduce((n, b) => n + b.length, 0);
  const bytes = new Uint8Array(8 + poolSize + 56), v = new DataView(bytes.buffer);
  v.setUint16(0, 3, true); v.setUint16(2, 8, true); v.setUint32(4, bytes.length, true);
  const p = 8; v.setUint16(p, 1, true); v.setUint16(p + 2, 28, true); v.setUint32(p + 4, poolSize, true);
  v.setUint32(p + 8, 3, true); v.setUint32(p + 16, utf8 ? 256 : 0, true); v.setUint32(p + 20, 40, true);
  let off = 0;
  enc.forEach((s, i) => { v.setUint32(p + 28 + i * 4, off, true); bytes.set(s, p + 40 + off); off += s.length; });
  const tag = p + poolSize, ext = tag + 16;
  v.setUint16(tag, 0x102, true); v.setUint16(tag + 2, 16, true); v.setUint32(tag + 4, 56, true);
  v.setUint32(ext + 4, 0, true); v.setUint16(ext + 8, 20, true); v.setUint16(ext + 10, 20, true); v.setUint16(ext + 12, 1, true);
  v.setUint32(ext + 24, 1, true); v.setUint32(ext + 28, 2, true);
  return bytes;
}
test('manifest reader supports UTF8 and UTF16 string pools', () => {
  assert.equal(binaryManifestPackage(manifest('com.mapcontrol')), 'com.mapcontrol');
  assert.equal(binaryManifestPackage(manifest('com.other', false)), 'com.other');
});
test('ZIP reader handles compressed and stored manifests', async () => {
  for (const level of [0, 6]) assert.equal(await apkPackage(new Blob([zipSync({ 'AndroidManifest.xml': manifest('com.mapcontrol') }, { level })])), 'com.mapcontrol');
});
test('missing and malformed manifest never pass validation', async () => {
  await assert.rejects(apkPackage(new Blob(['not a zip'])), /ZIP/);
  await assert.rejects(apkPackage(new Blob([zipSync({ 'other.txt': new Uint8Array([1]) })])), /bulunamadı/);
  assert.throws(() => binaryManifestPackage(new Uint8Array(8)), /geçersiz/);
});
test('out-of-bounds XML chunks fail closed', () => {
  const bytes = manifest('com.mapcontrol');
  new DataView(bytes.buffer).setUint32(12, 0xffffffff, true);
  assert.throws(() => binaryManifestPackage(bytes), /geçersiz/);
});
test('built project APK has expected package', async t => {
  const bytes = await readFile('../../app/build/outputs/apk/debug/app-debug.apk').catch(error => {
    if (error.code !== 'ENOENT') throw error;
    t.skip('Önce Android debug APK derlenmeli.');
  });
  if (!bytes) return;
  assert.equal(await apkPackage(new Blob([bytes])), 'com.mapcontrol');
});
