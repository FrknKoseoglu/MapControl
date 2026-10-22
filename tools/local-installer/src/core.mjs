export const PACKAGE = 'com.mapcontrol';
export const ACCESSIBILITY = `${PACKAGE}/com.mapcontrol.service.GlobalBackService`;
export const LISTENER = `${PACKAGE}/com.mapcontrol.media.LauncherMediaNotificationListener`;
export const quote = value => "'" + String(value).replaceAll("'", "'\\''") + "'";
export function appendComponent(current, component) {
  const value = String(current).trim();
  const list = !value || value === 'null' ? [] : value.split(':').filter(Boolean);
  return [...new Set([...list, component])].join(':');
}
export const PERMISSIONS = ['READ_LOGS','QUERY_ALL_PACKAGES','PACKAGE_USAGE_STATS',
  'ACCESS_FINE_LOCATION','ACCESS_COARSE_LOCATION','ACCESS_BACKGROUND_LOCATION',
  'WRITE_SETTINGS','WRITE_SECURE_SETTINGS','READ_SECURE_SETTINGS','READ_SETTINGS',
  'FOREGROUND_SERVICE','RECORD_AUDIO','BLUETOOTH_CONNECT','ACCESS_WIFI_STATE',
  'CHANGE_WIFI_STATE','ACCESS_NETWORK_STATE','CHANGE_NETWORK_STATE','CAMERA','INTERNET',
  'READ_PHONE_STATE','WRITE_EXTERNAL_STORAGE','READ_EXTERNAL_STORAGE','RECEIVE_BOOT_COMPLETED',
  'INTERACT_ACROSS_USERS_FULL','DELETE_PACKAGES','BIND_ACCESSIBILITY_SERVICE','MEDIA_CONTENT_CONTROL'];

export async function grantPermissions(io) {
  const warnings = [];
  async function optional(label, command) {
    const result = await io.shell(command);
    const rejected = result.code !== 0 || /(?:exception|error:|unknown (?:operation|command)|not allowed|denied|failure)/i.test(result.output);
    if (rejected) warnings.push(label);
    io.log(`${rejected ? 'UYARI' : 'OK'} ${label}: ${result.output.trim() || `çıkış ${result.code}`}`);
  }
  await optional('Arka planda çalışma', `dumpsys deviceidle whitelist +${PACKAGE}`);
  for (const op of ['SYSTEM_ALERT_WINDOW','GET_USAGE_STATS','REQUEST_INSTALL_PACKAGES','MANAGE_EXTERNAL_STORAGE','WRITE_SETTINGS']) {
    await optional(op, `appops set ${PACKAGE} ${op} allow`);
  }
  await optional('Sistem overlay', 'settings put global overlay_show_system_window 1');
  for (const permission of PERMISSIONS) await optional(permission, `pm grant ${PACKAGE} android.permission.${permission}`);
  for (const [key, component] of [['enabled_accessibility_services', ACCESSIBILITY], ['enabled_notification_listeners', LISTENER]]) {
    const current = await io.shell(`settings get secure ${key}`);
    if (current.code !== 0 || /(?:exception|error:|denied)/i.test(current.output)) {
      warnings.push(key);
      io.log(`UYARI ${key} okunamadı; mevcut liste değiştirilmedi.`);
      continue;
    }
    await optional(key, `settings put secure ${key} ${quote(appendComponent(current.output, component))}`);
  }
  await optional('Erişilebilirlik', `cmd accessibility enable-service ${ACCESSIBILITY}`);
  await optional('Bildirim erişimi', `cmd notification allow_listener ${LISTENER}`);
  return warnings;
}

export async function install(io, { apk, mode, dex, id }) {
  if (!['standard', 'oem'].includes(mode)) throw new Error('Geçersiz kurulum yolu.');
  if (!apk || apk.size < 4 || apk.size > 1024 ** 3) throw new Error('Geçerli bir APK seçin (en fazla 1 GiB).');
  if (mode === 'oem' && !dex) throw new Error('OEM yolu için orijinal mapcontrol-silent-installer.dex dosyasını seçin.');
  if (!/^[a-f0-9]{16}$/.test(id)) throw new Error('Geçersiz işlem kimliği.');
  const base = `/data/local/tmp/mapcontrol-web-${id}`;
  const apkPath = `${base}.apk`, dexPath = `${base}.dex`;
  let attempted = false, packageVerified = false;
  try {
    await io.push(apk, apkPath);
    if (mode === 'oem') await io.push(dex, dexPath);
    else {
      const property = await io.shell('setprop persist.sys.sv.isl true');
      if (property.code !== 0) io.log(`UYARI Kurulum property: ${property.output}`);
    }
    const command = mode === 'standard'
      ? `pm install -r -g ${quote(apkPath)}`
      : `CLASSPATH=${quote(dexPath)} /system/bin/app_process64 /system/bin com.mapcontrol.adb.MapControlSilentInstaller ${quote(apkPath)} --grant-runtime`;
    attempted = true;
    const result = await io.shell(command, 180000);
    const success = mode === 'standard' ? /^Success\s*$/m.test(result.output) : /\bINSTALL_SUCCEEDED\b/.test(result.output);
    if (result.code !== 0 || !success || /(?:Failure\s*\[|INSTALL_FAILED_)/.test(result.output)) {
      throw new Error(`Kurulum başarısız: ${result.output.trim() || `çıkış ${result.code}`}`);
    }
    const installed = await io.shell(`pm path ${PACKAGE}`);
    if (installed.code !== 0 || !/^package:/m.test(installed.output)) throw new Error('Kurulum yanıtı alındı ancak MapControl paketi doğrulanamadı.');
    packageVerified = true;
    io.log('MapControl paketi kuruldu. İzinler uygulanıyor…');
    return { warnings: await grantPermissions(io) };
  } catch (error) {
    if (packageVerified) throw new Error(`APK kuruldu ancak izin adımları tamamlanamadı: ${error.message}`);
    if (attempted && /zaman aşımı|bağlantı/i.test(error.message)) {
      throw new Error(`${error.message} Araçtaki kurulum sonucu belirsiz; yeniden bağlanıp cihaz bilgisini kontrol edin.`);
    }
    throw error;
  } finally {
    try {
      const cleanup = await io.shell(`rm -f ${quote(apkPath)} ${quote(dexPath)}`);
      if (cleanup.code !== 0) io.log('UYARI Geçici dosyalar temizlenemedi.');
    } catch { io.log('UYARI Bağlantı kesildi; geçici dosyalar araçta kalmış olabilir.'); }
  }
}
