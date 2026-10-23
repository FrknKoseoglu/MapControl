# Super Mod — araç testi başarısız, teşhis devamı (23 Ekim 2026)

## Kabul durumu

Kullanıcı **versionCode 3** test-fk paketini araçta denediğini ve çalışmadığını bildirdi. Gizleme seçeneğinin ve test-fk erişilebilirlik hizmetinin ikisinin de açık olduğunu ayrıca doğruladı. Araç şu anda Mint ADB listesinde görünmüyor; kullanıcı şu an bağlayamıyor. Yeni fiziksel test kaydı yok.

Önceki emülatör sonuçları araç kabulü değildir. Fiziksel neden henüz saptanmadı. Pencere eşleşmesi, hizmet bağlantısı, mod cevabı veya OEM kapatma davranışından hangisinin engel olduğu bilinmiyor. Bu güncelleme araçta sorunun kesin giderildiği iddiasını taşımaz.

## Kaynakta doğrulanan ayrı hata

Önceki izin kontrolü başka paket adlarıyla kurulu MapControl kopyalarının `...service.GlobalBackService` son ekini de kabul ediyordu. Dolayısıyla `com.mapcontrol` veya `.vnoisy` hizmeti açıksa `.testfk` için yanlış olumlu sonuç verebiliyordu.

Super Mod'a özel kontrol artık `ComponentName(context.getPackageName(), GlobalBackService.class.getName())` ile tam paket+sınıf eşleşmesini ister. Başka kopya ve hatalı kısa bileşen biçimi reddedilir; orijinal paket için geçerli kısa biçim kabul edilir. Diğer özelliklerin ortak erişilebilirlik kontrolü değiştirilmedi. Kullanıcı ikisinin de açık olduğunu söylediğinden bu hatanın araçtaki başarısızlığın nedeni olduğu varsayılmıyor.

## Yeni tanı kaydı

- Hizmet başlangıcı: gerçek paket, build numarası, runtime erişilebilirlik flags/capabilities.
- Hizmetin ayarlarda kayıtlı olması ile süreçte gerçekten bağlı olması ayrı gösterilir.
- Pencere bilgisi: pencere sayısı, türü, focused/active durumu, okunamayan kökler, odaklı kökün paket adı, ziyaret sayısı, üç hedef kaynak kimliğinin görülmesi ve eşleşme sonucu.
- Salt okunur mod sorgusunun yanıt modülü, cmd ID'si ve en fazla ilk 4 değeri; null/yanlış cevap ve istisna sınıfı.
- Son akış durumu: seçenek, zaman sınırı, kapatma kabul/red sonucu.

En fazla 32 kayıt tutulur, her mesaj 600 karakterle sınırlıdır; aynı aşamadaki aynı veri yinelenmez. Kayıtlar işlevsel tercihlerden ayrı `SuperModeDiagnostics` alanında saklanır. Ekran metinleri, tüm ayarlar veya tüm araç verileri toplanmaz. Sürüş modu/araç ayarı komutu eklenmedi. Pencereyi eşleme ve kapatma koşulları versionCode 3 ile aynıdır; odak/ID/mod kontrolleri gevşetilmedi.

## Telefonda kullanım

1. Aynı imzalı **test-fk versionCode 4** tanı APK'sını mevcut test-fk üzerine güncelle.
2. Gizleme açıkken Super Mod'u bir kez dene.
3. Uygulamada **Super Mod animasyonunu gizle** kartına uzun bas.
4. **Kaydı kopyala** düğmesiyle metni al ve inceleme için paylaş.

Mint/USB bağlantısı gerekmez. Kartın yalnız uzun basma eylemi raporu açar; normal aç/kapat eylemi korunur. Kaydı kopyalamak yeni bir araç/mod komutu göndermez.

APK: `~/dev/omoda/MapControl-test-fk-1.2.4-tani.apk` (omoda kökündeki standart APK ile aynı içerik). Paket: `com.mapcontrol.testfk`; versionName `1.2.4`, versionCode `4`; SHA-256 `0daa6ebcede29565d714c5d63fccf8f0d8ff9d574cddd2837b8a4eb924998662`. Önceki test-fk ile aynı imza kullanılır. Kurulum betiğinin özeti eşleştirildi; eski APK hash adıyla arşivlendi. OEM kurulum kısıtlarına müdahale edilmedi.

## Doğrulama ve sınır

Emülatörde pencere/mod koruma regresyonları, başka uygulama kopyasının izninin reddi, kayıtların sınırı/tekrarı, ilgisiz tercihlerin rapora alınmaması ve gerçek Activity'de kartın uzun basılması + panoya kopyalama doğrulandı. **49 Android testi (atlamasız), 14 JVM testi ve Mint kurulum betiğinin 8 testi geçti.** Bu sonuçlar fiziksel araç kabulü değildir. Son sonuç dosyaları `output/supermode-diagnostics-all-49.txt`, `output/supermode-diagnostics-copy-test-final.txt` ve `output/supermode-tani-testfk/` altındadır. Fiziksel araçta v4 henüz denenmedi.

Sonraki düzeltme, paylaşılacak gerçek kayıtla başarısız aşama belirlendikten sonra o koşula uygulanmalı. Paket/servis kapatma, fabrika reset anahtarı, kör koordinat/global BACK veya başka mod komutları kullanılmayacak.
