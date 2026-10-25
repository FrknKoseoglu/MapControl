# Ana proje 1.3.4 eşitlemesi — 25 Ekim 2026

## Alınan sürüm

Kaynak `berkpekatik/mapcontrol`, `experimental` dalındaki `1.3.4` etiketi, commit `dce10827100001d932772f0ad6eabac30c00a9b7`. Ana projenin `main` dalı hâlâ `187093b` üzerindedir ve önceki çalışmamızda zaten bulunur. Bu yüzden kullanıcının bildirdiği yeni yayımlanmış sürümün etiketi birleştirildi. Birleştirme öncesi bizim branch `dceb2e8` idi; geri dönüş referansı `codex/pre-upstream-1.3.4-20261025`.

## Güncellemeler

- 360 kamera: önizleme, USB/dahili kayıt, klip izleme ve yerel HAB kütüphanesi.
- Bölünmüş ekran: uygulama çifti, oran ve araç hareketi ayarları.
- Yandex gösterge kartları ve Google Maps navigasyon bildirimlerinin güncel upstream davranışı.
- Kabin DMS alarmı anahtarı ve upstream yüz profili/koltuk hafızası arayüz güncellemeleri.
- Sürüm bilgisi `1.3.4` / `versionCode 6`; upstream ARM64/CMake ve bağımlılık ayarları.

## Arayüz birleştirmesi

Yedi dosyadaki metin çatışmaları çözüldü. Yeni kamera ve bölünmüş ekran sekmeleri mevcut ortak başlık, sabit durum widget'ları, sidebar, portrait şeridi ve tam ekran Hızlı Erişim kısayollarıyla bağlandı. Tam ekranda eski Wi-Fi/Mod/Telefon/Yansıtma kartları iki sütunda kalır; ikonlarda tekrarları oluşturulmaz. Yeni iki kısayol aynı upstream erken erişim uyarısı ve menü akışını kullanır.

Kamera geniş görünümü sidebar'ı gizlerken sol geri kontrolü korunur. Yeni sekmeler temayı değiştirince bizim lazy yeniden kurma akışında güncellenir. Upstream üç seçenekli kontrolün etkileşim kilidi, mevcut kart/radyo tasarımına bağlandı; kilitliyken tıklama yeni bir tercih göndermez. Kamera menüsü seçimi tema/orientation yenilemelerinden sonra doğru kalır.

Upstream'in değiştirdiği/eklediği 49 dosyanın 40'ı upstream ile birebir aynıdır. Kalan 9 dosya build/test ayarları, mevcut arayüz entegrasyonları, Super Mod servis bağlantıları ve çevirilerin birleşimidir. Ek olarak tam ekran kısayol listesi uyarlandı. Kamera/kayıt, split-screen, yeni hareket denetleyicisi, araç DMS anahtarı, MapControlService ve InstallerConfirmClicker kaynakları upstream ile aynıdır. AssistTabBuilder içindeki araç komutu gönderme metodu da upstream ile aynıdır; sürüş modu seçme komutları değiştirilmedi.

## Korunanlar ve doğrulama

- Mevcut renk/boyut tokenları, widget mod göstergesi ve tam ekran başlıksız düzen korundu.
- DMS tanıma yöneticisi ve klima otomasyonunun işlevleri üzerinde bağımsız düzeltme yapılmadı; upstream kaynakları kullanılır.
- Super Mod controller/window/diagnostics sınıfları önceki branch ile aynıdır. Bu eşitleme videodaki hız/turbo ekranı sorununu çözmez; araç kabulü iddia edilmez.
- Native ARM64 debug APK ve Android test APK derlemesi başarılı.
- **19 JVM testi ve 51 Android testi atlamasız geçti.** Yeni menüler, yatay/dikey yerleşim, gece/gündüz yeniden kurma, geniş görünüm geri kontrolü, tam ekran kısayolları ve seçenek kilidi kapsandı. Önceki widget ve Super Mod regresyonları da geçti.
- OEM pencere test fixture'ı yalnız emülatörde kullanıldı ve sonra kaldırıldı. Fiziksel araç/kamera/ECU testi yapılmadı.

Kanıtlar: `output/upstream-sync-1.3.4/build.log`, `android-tests.txt`, `integration-tests.txt`, `verification.json`.

Kullanıcının `.gitignore`, `.codex-remote-attachments/` ve `docs/ui-previews/` çalışmaları bu commit'e alınmaz. Önceden paylaşılan MapControl-fk APK ve imzalama anahtarı değiştirilmez; bu kayıt kaynak branch eşitlemesini anlatır.
