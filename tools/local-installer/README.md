# MapControl yerel web yükleyici

Kullanıcının seçtiği `com.mapcontrol` APK'sını USB ADB üzerinden kurar. Android telefon + OTG veya bilgisayar + USB kullanılabilir. Rehberdeki sunucu APK'sını indirmez. Bağımlılıklar HTML içine gömülür; CDN, telemetri ve ağ isteği yoktur.

## Bilgisayarda açma

Bu klasörde Node.js ile:

```text
npm ci --ignore-scripts
npm test
npm run build
npm start
```

Chrome'da `http://127.0.0.1:8787` adresini açın. Sunucu yalnızca bu bilgisayarın loopback adresini dinler. `dist/mapcontrol-installer.html` tek dosyalık çıktıdır; yanında dağıtılan `THIRD-PARTY-LICENSES.txt` lisanslarını koruyun. Kaynak değiştiğinde yeniden derleyin.

## Android telefonda açma

HTML dosyasını ve APK'yı telefona aktarın. WebUSB destekli Android Chrome kullanın. Telefonun **kendi** localhost sunucusundan veya HTTPS üzerinden HTML'yi açın. Dosya yöneticisinin `content://` adresi WebUSB'yi sağlamayabilir; sayfa destek durumunu gösterir. Bilgisayarın `http://192.168...` adresini telefonda açmak güvenli bağlam sağlamaz. HTTPS'ye yayınlama bu görevde yapılmadı. iPhone desteklenmez.

1. Aracın ADB bağlantısını resmi rehbere göre açın.
2. Normal / OEM kurulum yolunu seçin.
3. Kendi APK'nızı seçin.
4. USB ile bağlanın.
5. Cihaz bilgisini okuyun ve doğru araç olduğunu kontrol edin.
6. Kurulum tercih kutusunu işaretleyin.
7. Seçtiğiniz APK'yı kurun.
8. Sonucu ve izin uyarılarını okuyun.

Bu projede üretilen APK: `../../app/build/outputs/apk/debug/app-debug.apk`.

## İki kurulum yolu

- **Normal ADB:** `DEVICE EYE PATTERN` bulunan araçlarda normal paket yöneticisi kullanılır. Paket düşürme zorlanmaz; mevcut uygulama kaldırılmaz. İmza uyuşmazlığı kullanıcıya gösterilir.
- **OEM / adbauth:** ADB kilidi önce resmi anahtar/USB bellek akışıyla açılmalıdır. Orijinal `mapcontrol-silent-installer.dex` ayrıca seçilir. Bu yardımcı depoda bulunmadığından pakete dahil edilmez. Aracın `/system/bin/app_process64` ortamında `com.mapcontrol.adb.MapControlSilentInstaller` sınıfı `APK --grant-runtime` parametreleriyle çağrılır; mevcut `SilentDexInstaller.java` sözleşmesi esas alınmıştır. Yardımcının gerçek OEM donanım uyumu henüz doğrulanmadı. Rehberdeki `mc-run.sh` yerine bu çağrı doğrudan yapılır.

`adbauth.key` DEX yardımcısı değildir. Yükleyici anahtar üretmez, ödeme yapmaz, ADB kilidi açmaz.

Resmi yükleyicinin yardımcı dosya adresi: https://vnoisy.dev/apk/mapcontrol-silent-installer.dex (sayfada indirme bağlantısı var; otomatik indirilmez). Resmi `https://vnoisy.dev/apk/mc-run.sh` dosyası salt okunur incelendi; aynı CLASSPATH/app_process64/sınıf çağrısını kullanıyor.

## İzinler ve sonuçlar

Kullanıcının açık onayıyla `install.sh` izin akışı uygulanır: AppOps, Doze, konum/kamera/mikrofon, güvenli ayarlar, erişilebilirlik, bildirim dinleyicisi ve diğer listelenmiş izinler. Mevcut erişilebilirlik ve bildirim listeleri korunarak ekleme yapılır. **Cihaz sahibi atanmaz.** OEM/normal/signature izinleri Android tarafından reddedilebilir; bu hatalar APK başarısı ile karıştırılmaz. Bağlantı kaybında sonuç belirsiz olarak sunulur, başarı iddia edilmez. Erişim onayları araç ekranında gerekebilir.

APK'nın ZIP/ikili manifest paket adı yerelde kontrol edilir; bu kontrol APK imzasını veya kaynağını doğrulamaz. Aktarım parçalıdır, dosya adları komuta eklenmez. Rastgele geçici yollar işlem sonunda temizlenir; kablo koparsa artık dosya kalabilir. Komut yanıtları 64 KiB ile sınırlıdır. ADB anahtarı tarayıcıda saklanır. İşlem kaydı sayfadan dışarı gönderilmez.

## Doğrulama sınırı

Otomatik testler komut planını ve sahte ADB yanıtlarıyla başarı/hata/temizlik akışlarını, gerçek debug APK'sının manifestini doğrular. Fiziksel araçta USB bağlantısı, OEM DEX ve tüm izinlerin uygulanması henüz test edilmedi. Testler araca komut göndermez.

Kaynak rehberler:

- https://vnoisy.dev/install-guide/araclar/
- https://vnoisy.dev/install-guide/02-00-00/
- https://vnoisy.dev/install-guide/adb-kilid/

Teknik WebUSB API kaynağı: https://tangoadb.dev/tango/daemon/
