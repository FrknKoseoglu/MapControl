# IVI raporu sonrası ek düzeltmeler

Bu değişiklikler `fix/ivi-reliability-security` branch'indeki PR #3'ün devamıdır. Android IVI, OEM ekran sırası, mevcut VDBus kodları ve global ekolayzer özelliği korunur.

## Pencere sahipliği ve temizliği

`DisplayHelper.hidePreparingMessage()` artık kaldırma callback'inde değişebilen global pencere alanlarına bakmaz. Kapanan View ve WindowManager yakalanır; yeni pencerenin sahipliği hemen ayrılır. `OverlayDismissal` bu pencereyi animasyon sonunda, animasyon iptalinde veya son callback gelmezse sınırlı bir gecikmenin sonunda kaldırır. İşlemler ana thread üzerinde ve tek kaldırma korumasıyla yürür.

Boot splash aynı temizleme yolunu kullanır. Böylece eski bir kapanış callback'i yeni hazırlık/boot penceresini kaldıramaz veya onun alanlarını sıfırlayamaz.

Android testleri gerçek ekran/pencere eklemeden kayıt tutan WindowManager kullanır. Otuz hazırlık penceresi aç/kapat döngüsü, eski animasyon sırasında yeni pencere, iptal/fallback temizliği, boot splash ve arka plan çağrısının ana thread'e taşınması kapsanır.

## Suspend-to-RAM uyanışı

Kullanıcının otomatik ana ekran ayarı açıksa, doğrulanmış OFF (0) -> STANDBY (1) veya ON (2) geçişi açılış isteğini yeniden kurar. Sonraki 1 -> 2 geçişi bunu tekrar etmez. Sürecin araç zaten açıkken yeniden başlaması, önceki güç durumu bilinmiyorsa, sırf bu nedenle ana ekranı öne getirmez.

Önceki gerçek Activity onayı ve sınırlı tekrar mekanizması korunur. Servis kapanmışsa veya son doğrulanmış güç modu OFF ise kuyruktaki Activity başlatma işlemi uygulanmaz. Otomatik açılış kapatıldıysa kullanıcı tercihi geçerlidir. Geçersiz güç örnekleri karşılama kaydını da değiştirmez.

Birim testleri gerçek uyanış, ayarın kapalı olması, süreç yeniden başlatma, 1 -> 2 geçişi ve geçersiz güç örneklerini kapsar. Fiziksel IVI'de STR/ön plan davranışı ayrıca kabul edilmelidir.

## Navigasyon süreleri ve yerelleştirilmiş birimler

`NavigationQuantities` bildirim kabulü, süre/distance alanı seçimi ve VDBus dakika/metre dönüşümünü aynı kurallara bağlar. Boşluksuz birimler, Türkçe/İngilizce/Almanca/Rusça saat-dakika kısaltmaları ve kelimeleri, ondalık virgül/nokta ve Unicode boşluklar desteklenir. Kelime parçası veya negatif değer süre sanılmaz; büyük değerler taşmaz.

Örnekler: `1hr20min` -> 80, `1sa10dk` -> 70, `1 Std. 20 Min.` -> 80, `1ч20мин` -> 80. `м`/`км` mesafeleri metrik görüntü/VDBus değerine çevrilir; emperyal dönüşüm korunur. Ayraç çevresinde boşluk gerekmeksizin `·`, `•` ve `|` ile alanlar ayrılır. Saat etiketi temizliği Beta gibi sokak adlarından harf silmez. Ayraçsız manevra metni korunur; TR/DE/RU dönüş ifadeleri mevcut 16 manevra koduna eşlenir. OEM 14/16/-1 kodları değişmez.

Bunlar test edilmiş bildirim biçimleridir; tüm Google Maps sürümlerinin veya tüm dillerin bütün olası biçimlerinin desteklendiği iddia edilmez. Gerçek araçtaki Google Maps bildirimleri ayrıca kontrol edilmelidir.

## Yandex pencere güncellemeleri

Navigasyon içeriği değişirken overlay penceresi yerinde kalır. İlk oluşturma ve gerekli dış pencere olaylarında üstte tutma davranışı korunur; mevcut 250 ms sınırı devam eder. MapControl'ün kendi pencere olayları yeniden ekleme tetiklemez.

Android testinde 50 farklı navigasyon özeti uygulanır ve bu sırada sıfır WindowManager ekleme/kaldırma çağrısı doğrulanır. Dış pencere olayı için üstte tutma hâlâ bir kez remove/add yapabilir; hemen yinelenen olay atlanır. Navigasyon bitince pencere kaldırılır. Gerçek OEM pencereleriyle üst üste gelme davranışı araçta kabul edilmelidir.

## Ses ve mikrofon

Genel izin incelemesi artık AudioRecord oluşturup kayıt başlatmaz. RECORD_AUDIO varsa sonuç 'donanım denenmedi' (SKIP), yoksa izin hatasıdır. Açıklama İngilizce/Türkçe olarak güncellenmiştir.

Ekolayzer kapalıyken okuma/kaydetme/servis kontrolü global ses efekti oluşturmaz. Kullanıcı arayüzü bantlarını sabit yapılandırmadan alır; yetenek listesi efekt oluşturmadan sorgulanır. OEM yetenek sorgusu kullanılamıyorsa kullanıcı açıkça etkinleştirdiğinde mevcut oluşturma yolu denenebilir.

Ekolayzer etkinse ayar sapması mevcut efekt üzerinde düzeltilir. Başka bir kontrolcü efektin kontrolünü aldıysa 20 saniyelik döngü onu yıkıp yeniden oluşturarak geri almaya çalışmaz. Ölü efekt için kaynak bırakma ve sonraki kontrolde yeniden açma yolu bulunur. Global session 0 ve mevcut öncelik korunur; farklı uygulamalardan çalan müziğe ekolayzer uygulama özelliği kaldırılmamıştır.

Android testleri izin incelemesinin mikrofon donanımı istemediğini ve kapalı ekolayzerin effect nesnesi oluşturmadığını, bant/kullanıcı ayarlarını koruduğunu doğrular. Kalıcı OEM mikrofon arızası veya ses kesintisinin tamamen giderildiği sonucu bu testlerden çıkarılmaz; gerçek ses sistemi testi bekler.

## Erişilebilirlik bağlantısı

`settingsActivity` gerçek `com.mapcontrol.ui.activity.MainActivity` sınıfını gösterir. Android testi paketlenmiş XML'i okuyup bu Activity'nin PackageManager üzerinden çözümlendiğini doğrular.

## Doğrulama sonucu

- 56 birim testi, sıfır hata/başarısızlık.
- 20 Android entegrasyon testi, API 34 emülatöründe başarılı.
- Debug APK ve Android test APK'sı derlendi.
- `lintDebug` tamamlandı; mevcut OEM/izin/dinamik View kimliği bulguları sürer. Temiz lint iddiası yoktur.
- Testler gerçek mikrofonu açmaz, global ses efekti etkinleştirmez, VDBus komutu veya araç ayarı göndermez.

Filament 1.53.4'ün detach sırasında motoru zaten temizlediği kaynak ve gerçek AAR üzerinden doğrulanmıştır. Bu nedenle rapordaki ek engine.destroy önerisi uygulanmamıştır. Güncel launcher Lite paneli kullanmaya devam eder.
