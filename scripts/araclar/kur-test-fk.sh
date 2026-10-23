#!/usr/bin/env bash
# MapControl test-fk / Linux Mint. Existing apps, their data and device owner are preserved.
set -Eeuo pipefail
umask 077
PACKAGE=com.mapcontrol.testfk
APK_NAME=MapControl-test-fk-1.2.4.apk
APK_SHA256=0a9edb1460e62834525f31265d9865260bf5049efa8c96c2e52e3aa2305c85ac
A11Y="$PACKAGE/com.mapcontrol.service.GlobalBackService"
NLS="$PACKAGE/com.mapcontrol.media.LauncherMediaNotificationListener"
ADMIN="$PACKAGE/com.mapcontrol.admin.MapControlDeviceAdminReceiver"
SCRIPT_DIR=$(cd -- "$(dirname -- "${BASH_SOURCE[0]}")" && pwd)
ROOT=${TESTFK_ROOT:-$(cd -- "$SCRIPT_DIR/../.." && pwd)}
SERIAL= USER_ID= DO_OWNER=0 LAUNCH=1 REPORT= OK=0 WARN=0
usage() {
    cat <<'EOF'
Kullanım: bash scripts/araclar/kur-test-fk.sh [seçenekler]
  --serial SERI       Birden çok ADB cihazı varsa hedefi seç.
  --user ID           Android kullanıcısı (varsayılan: etkin kullanıcı).
  --no-launch        Kurulumdan sonra uygulamayı açma.
  --device-owner     Yalnız mevcut cihaz sahibi YOKSA test-fk için atamayı dene.
  --help             Bu açıklamayı göster.
Mevcut uygulama kaldırılmaz; veri silinmez, mevcut cihaz sahibi değiştirilmez.
Çıkış: 0 doğrulandı, 2 kuruldu fakat bazı izinler verilemedi, 1 kurulum/bağlantı hatası.
EOF
}
fatal() { printf '❌ %s\n' "$*" >&2; [[ -z "$REPORT" ]] || printf '\n❌ %s\n' "$*" >> "$REPORT"; exit 1; }
while (($#)); do
    case "$1" in
        --serial|--user)
            (($# >= 2)) || fatal "$1 için değer gerekli."
            if [[ "$1" == --serial ]]; then SERIAL=$2; else USER_ID=$2; fi
            shift 2 ;;
        --no-launch) LAUNCH=0; shift ;;
        --device-owner) DO_OWNER=1; shift ;;
        --help|-h) usage; exit 0 ;;
        *) fatal "Bilinmeyen seçenek: $1 (--help)" ;;
    esac
done
[[ -z "$USER_ID" || "$USER_ID" =~ ^[0-9]+$ ]] || fatal 'Android kullanıcı ID sayısal olmalı.'
[[ -z "$SERIAL" || "$SERIAL" =~ ^[a-zA-Z0-9_.:-]+$ ]] || fatal 'Geçersiz cihaz seri numarası.'
APK="$ROOT/$APK_NAME"
[[ -f "$APK" ]] || fatal "APK bulunamadı: $APK"
command -v sha256sum >/dev/null || fatal 'sha256sum gerekli (Mint: coreutils).'
ACTUAL_HASH=$(sha256sum -- "$APK"); ACTUAL_HASH=${ACTUAL_HASH%% *}
[[ "$ACTUAL_HASH" == "$APK_SHA256" ]] || fatal 'APK özeti uyuşmuyor. Doğru APK ve ona ait kurulum betiğini birlikte kullan.'
ADB_BIN=${TESTFK_ADB:-}
if [[ -z "$ADB_BIN" ]]; then
    if command -v adb >/dev/null; then ADB_BIN=$(command -v adb)
    else
        for candidate in "${ANDROID_HOME:-$HOME/Android/Sdk}/platform-tools/adb" \
                         "${ANDROID_SDK_ROOT:-$HOME/Android/Sdk}/platform-tools/adb" "$HOME/Android/Sdk/platform-tools/adb"; do
            if [[ -x "$candidate" ]]; then ADB_BIN=$candidate; break; fi
        done
    fi
fi
[[ -n "$ADB_BIN" ]] && command -v "$ADB_BIN" >/dev/null || fatal 'ADB bulunamadı. Mint üzerinde sudo apt install adb komutunu çalıştır.'
DEVICES=$("$ADB_BIN" devices -l 2>&1) || fatal "ADB başlatılamadı: $DEVICES"
if [[ -z "$SERIAL" ]]; then
    mapfile -t READY < <(printf '%s\n' "$DEVICES" | tr -d '\r' | awk '$2 == "device" { print $1 }')
    ((${#READY[@]} == 1)) || fatal "Tek bir yetkili cihaz gerekli. Araçta USB hata ayıklamayı onayla; birden çok cihaz varsa --serial kullan. ADB: $DEVICES"
    SERIAL=${READY[0]}
    [[ "$SERIAL" != emulator-* ]] || fatal 'Yalnız emülatör bağlı. Aracı bağla; emülatör testi için --serial emulator-PORT kullan.'
fi
STATE=$("$ADB_BIN" -s "$SERIAL" get-state 2>&1) || fatal "Cihaza erişilemiyor: $STATE"
[[ "${STATE//$'\r'/}" == device ]] || fatal "Cihaz hazır değil: $STATE"
# Quote every argument for Android's POSIX shell; never evaluate values from the device.
adb_shell() {
    local command= argument
    for argument in "$@"; do command+="'${argument//\'/\'\\\'\'}' "; done
    "$ADB_BIN" -s "$SERIAL" shell "$command"
}
capture() {
    RESULT_RC=0
    RESULT=$(adb_shell "$@" 2>&1) || RESULT_RC=$?
    RESULT=${RESULT//$'\r'/}
}
if [[ -z "$USER_ID" ]]; then capture am get-current-user; USER_ID=$RESULT; ((RESULT_RC == 0)) || fatal 'Etkin Android kullanıcısı okunamadı.'; fi
[[ "$USER_ID" =~ ^[0-9]+$ ]] || fatal "Geçersiz Android kullanıcısı: $USER_ID"
capture getprop ro.build.version.sdk
SDK=$RESULT
[[ "$SDK" =~ ^[0-9]+$ ]] && ((SDK >= 29)) || fatal "Android 10/API29 veya üstü gerekli; okunan API: $SDK"
DAY=$(TZ=Europe/Istanbul date +%F); MONTH=$(TZ=Europe/Istanbul date +%Y/%m)
LOG_ROOT=${TESTFK_LOG_ROOT:-$ROOT/devices/omoda/logs}
mkdir -p -- "$LOG_ROOT/$MONTH"
REPORT="$LOG_ROOT/$MONTH/log-$DAY.md"
printf '\n## ⚙️ MapControl test-fk kurulumu — %s\n\n- Hedef: `%s`, kullanıcı `%s`, API `%s`\n- APK SHA-256: `%s`\n\n' \
    "$(TZ=Europe/Istanbul date -Iseconds)" "$SERIAL" "$USER_ID" "$SDK" "$APK_SHA256" >> "$REPORT"
record() {
    local status=$1 label=$2 detail=${3:-}
    detail=${detail//$'\n'/ }; detail=${detail:0:650}
    printf '%s %s%s\n' "$status" "$label" "${detail:+: $detail}"
    printf -- '- %s **%s**%s\n' "$status" "$label" "${detail:+: $detail}" >> "$REPORT"
}
pass() { OK=$((OK+1)); record '✅' "$@"; }
warn() { WARN=$((WARN+1)); record '⚠️' "$@"; }
printf '⚙️ Hedef: %s / Android kullanıcı %s / %s\n' "$SERIAL" "$USER_ID" "$PACKAGE"
INSTALL_RC=0
INSTALL=$("$ADB_BIN" -s "$SERIAL" install -r --user "$USER_ID" "$APK" 2>&1) || INSTALL_RC=$?
if ((INSTALL_RC != 0)) || ! printf '%s\n' "$INSTALL" | tr -d '\r' | grep -qx 'Success'; then
    fatal "Kurulum başarısız; izin işlemleri başlatılmadı. $INSTALL"
fi
capture pm path --user "$USER_ID" "$PACKAGE"
((RESULT_RC == 0)) && [[ "$RESULT" == package:* ]] || fatal 'Kurulumdan sonra hedef paket doğrulanamadı.'
pass 'APK kuruldu / güncellendi' "$PACKAGE"
refresh_permissions() {
    capture dumpsys package "$PACKAGE"
    ((RESULT_RC == 0)) && [[ "$RESULT" == *"$PACKAGE"* ]] || fatal 'Paket izinleri doğrulama için okunamadı.'
    PACKAGE_DUMP=$RESULT
}
is_granted() {
    printf '%s\n' "$PACKAGE_DUMP" | awk -v permission="$1:" -v target="$USER_ID" '
        BEGIN { current=-1; granted=0 }
        /^[[:space:]]*User [0-9]+:/ { current=$2; sub(/:.*/, "", current) }
        $1 == permission && (current == -1 || current == target) {
            if ($0 ~ /granted=true/) granted=1
            else if ($0 ~ /granted=false/) granted=0
        }
        END { exit !granted }'
}
refresh_permissions
# Runtime/development and requested OEM permissions. Already-granted normal permissions are not rewritten.
PERMISSIONS=(
    android.permission.INTERNET android.permission.ACCESS_NETWORK_STATE android.permission.REQUEST_INSTALL_PACKAGES
    ivi.vds.permission.SERVICE android.permission.READ_SECURE_SETTINGS android.permission.WRITE_SECURE_SETTINGS
    android.permission.READ_SETTINGS android.permission.WRITE_SETTINGS android.permission.FOREGROUND_SERVICE
    android.permission.READ_LOGS android.permission.ACCESS_COARSE_LOCATION android.permission.ACCESS_FINE_LOCATION
    android.permission.ACCESS_BACKGROUND_LOCATION android.permission.RECORD_AUDIO android.permission.MODIFY_AUDIO_SETTINGS
    android.permission.CAMERA android.permission.MEDIA_CONTENT_CONTROL android.permission.MANAGE_EXTERNAL_STORAGE
    android.permission.BIND_VPN_SERVICE android.permission.SYSTEM_ALERT_WINDOW android.permission.QUERY_ALL_PACKAGES
    android.permission.PACKAGE_USAGE_STATS android.permission.ACCESS_WIFI_STATE android.permission.CHANGE_WIFI_STATE
    android.permission.CHANGE_NETWORK_STATE android.permission.WAKE_LOCK android.permission.RECEIVE_BOOT_COMPLETED
    android.permission.BROADCAST_STICKY android.permission.READ_PHONE_STATE android.permission.ACCESS_COARSE_UPDATES
    android.permission.WRITE_EXTERNAL_STORAGE android.permission.READ_EXTERNAL_STORAGE android.permission.READ_INTERNAL_STORAGE
    android.permission.REQUEST_DELETE_PACKAGES android.permission.INTERACT_ACROSS_USERS_FULL android.permission.DELETE_PACKAGES
)
if ((SDK <= 30)); then PERMISSIONS+=(android.permission.BLUETOOTH android.permission.BLUETOOTH_ADMIN)
else PERMISSIONS+=(android.permission.BLUETOOTH_CONNECT android.permission.BLUETOOTH_SCAN); fi
for permission in "${PERMISSIONS[@]}"; do
    # Special access is verified through AppOps below, not pm grant.
    case "$permission" in
        android.permission.SYSTEM_ALERT_WINDOW|android.permission.PACKAGE_USAGE_STATS|android.permission.REQUEST_INSTALL_PACKAGES|android.permission.MANAGE_EXTERNAL_STORAGE|android.permission.WRITE_SETTINGS) continue ;;
    esac
    if is_granted "$permission"; then pass "$permission" 'zaten verilmiş'; continue; fi
    capture pm grant --user "$USER_ID" "$PACKAGE" "$permission"
    GRANT_RESULT=$RESULT
    refresh_permissions
    if is_granted "$permission"; then pass "$permission"
    else warn "$permission" "${GRANT_RESULT:-Android/OEM tarafından verilemedi}"; fi
done
for operation in SYSTEM_ALERT_WINDOW GET_USAGE_STATS REQUEST_INSTALL_PACKAGES MANAGE_EXTERNAL_STORAGE WRITE_SETTINGS; do
    capture cmd appops set --user "$USER_ID" "$PACKAGE" "$operation" allow
    SET_RESULT=$RESULT
    capture cmd appops get --user "$USER_ID" "$PACKAGE" "$operation"
    if ((RESULT_RC == 0)) && printf '%s\n' "$RESULT" | grep -Eq "(^|[[:space:]])${operation}: allow([;[:space:]]|$)"; then pass "AppOps $operation"
    else warn "AppOps $operation" "${SET_RESULT:-$RESULT}"; fi
done
capture dumpsys deviceidle whitelist "+$PACKAGE"
capture dumpsys deviceidle whitelist
if ((RESULT_RC == 0)) && printf '%s\n' "$RESULT" | grep -Fq ",$PACKAGE,"; then pass 'Pil optimizasyonu muafiyeti'
else warn 'Pil optimizasyonu muafiyeti' "$RESULT"; fi
# Preserve ALL other apps' accessibility and notification listener components.
append_setting() {
    local key=$1 component=$2 previous merged
    capture settings --user "$USER_ID" get secure "$key"
    if ((RESULT_RC != 0)) || [[ "$RESULT" == *Exception* || "$RESULT" == Error:* || "$RESULT" == *'Permission denial'* ]]; then
        warn "$key" 'Mevcut liste okunamadı; değiştirilmedi.'; return
    fi
    previous=$RESULT; [[ "$previous" != null ]] || previous=
    case ":$previous:" in *":$component:"*) merged=$previous ;; *) merged="${previous:+$previous:}$component" ;; esac
    capture settings --user "$USER_ID" put secure "$key" "$merged"
    capture settings --user "$USER_ID" get secure "$key"
    if ((RESULT_RC == 0)); then
        case ":$RESULT:" in *":$component:"*)
            # Also verify every previously enabled component remains present.
            local old; local -a old_components
            IFS=: read -r -a old_components <<< "$previous"
            for old in "${old_components[@]}"; do
                [[ -z "$old" ]] && continue
                case ":$RESULT:" in *":$old:"*) ;; *) warn "$key" 'Önceki bileşen korunamadı; kontrol gerekli.'; return ;; esac
            done
            pass "$key"; return ;;
        esac
    fi
    warn "$key" 'Bileşen etkinleştirilemedi.'
}
append_setting enabled_accessibility_services "$A11Y"
capture settings --user "$USER_ID" put secure accessibility_enabled 1
capture settings --user "$USER_ID" get secure accessibility_enabled
if ((RESULT_RC == 0)) && [[ "$RESULT" == 1 ]]; then pass 'Erişilebilirlik etkin'
else warn 'Erişilebilirlik etkinleştirme' "$RESULT"; fi
capture cmd notification allow_listener "$NLS" "$USER_ID"
append_setting enabled_notification_listeners "$NLS"
# Device owner is optional and cannot replace an existing owner.
capture dumpsys device_policy
POLICY=$RESULT
if ((RESULT_RC != 0)); then
    if ((DO_OWNER)); then warn 'Device owner' 'Mevcut durum okunamadı; atama yapılmadı.'; fi
else
    OWNER=$(printf '%s\n' "$POLICY" | awk '/^[[:space:]]*Device Owner:/ { found=1 } found && /ComponentInfo\{/ { sub(/.*ComponentInfo\{/, ""); sub(/\}.*/, ""); print; exit }')
    if [[ "$OWNER" == "$ADMIN" ]]; then pass 'Device owner' 'test-fk zaten cihaz sahibi'
    elif [[ -n "$OWNER" ]]; then
        if ((DO_OWNER)); then warn 'Device owner' "Mevcut sahip korunuyor: $OWNER"
        else record 'ℹ️' 'Device owner' "Mevcut sahip korunuyor: $OWNER"; fi
    elif ((DO_OWNER)); then
        if [[ "$POLICY" == *'Device Owner:'* ]] || ((USER_ID != 0)); then warn 'Device owner' 'Atama koşulları doğrulanamadı; değiştirilmedi.'
        else
            capture dpm set-device-owner --user "$USER_ID" "$ADMIN"
            OWNER_RESULT=$RESULT
            capture dumpsys device_policy
            if ((RESULT_RC == 0)) && [[ "$RESULT" == *"Device Owner:"* && "$RESULT" == *"$ADMIN"* ]]; then pass 'Device owner'
            else warn 'Device owner' "$OWNER_RESULT"; fi
        fi
    else record 'ℹ️' 'Device owner' 'Atanmadı. Gerekirse --device-owner; mevcut sahip asla kaldırılmaz.'; fi
fi
if ((LAUNCH)); then
    capture am start --user "$USER_ID" -W -n "$PACKAGE/com.mapcontrol.ui.activity.MainActivity"
    if ((RESULT_RC == 0)) && [[ "$RESULT" == *'Status: ok'* ]]; then pass 'Uygulama açıldı'
    else warn 'Uygulama açılışı' "$RESULT"; fi
fi
record 'ℹ️' 'Sonuç' "$OK doğrulandı, $WARN izin/işlem verilemedi. Rapor: $REPORT"
if ((WARN)); then
    printf '⚠️ APK kuruldu; Android bazı yetkileri vermedi. Ayrıntılar raporda.\n'
    exit 2
fi
printf '✅ Kurulum ve doğrulanan izinler tamamlandı.\n'
