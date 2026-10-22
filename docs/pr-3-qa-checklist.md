# PR #3 — Repro ve QA planı

PR: https://github.com/berkpekatik/MapControl/pull/3

İncelenen revizyon: `a73b9214716ef29080ab48bb8d10b5011541f27c`.
Karşılaştırma tabanı: `bb4d70a46b30dfdba5e85681ce01bb9eed62069a`.
Hazırlanma: 19 Ekim 2026.

Bu belge, “hangi ekranda hangi adımı yapacağım, ne görmeliyim?” sorusuna cevap verir. QA planıdır; listedeki manuel senaryoların çalıştırıldığı anlamına gelmez.

## 1. Kanıt durumu ve kapsam

- Önceki yerel doğrulama: **56 birim + 20 Android entegrasyon testi** geçti. Ayrıca güncel kaynaklardan release derlendi ve **56 release birim testi** geçti.
- Android entegrasyon ortamı API 34 emülatörüdür. Fiziksel IVI, OEM güç/cluster/ses kabulü değildir.
- Aşağıdaki “önceki sorun” açıklamaları kaynak incelemesindeki kusuru anlatır. Eski sürümde birebir manuel A/B kaydı henüz yoksa böyle bir kayıt varmış gibi sunulmaz.
- Özellikle sürüş modu retry, gecikmiş Activity onayı, gerçek kamera oturumları, etkin EQ kontrol sahipliği ve profil API hata enjeksiyonu için otomatik kapsam eksikleri ayrıca belirtilmiştir.
- Ayrı yerel HTML yükleyici, yeni imzalama anahtarı ve APK imzalama işlemleri PR #3 kod kapsamına dahil değildir.
- Filament Engine temizliği için değişiklik yapılmadı; bu nedenle “Filament sızıntısı düzeltildi” kabul maddesi yoktur.
- Lint temiz değildir. Mevcut OEM/korumalı izin/sabit/dinamik View kimliği bulguları ayrı değerlendirilir.

## 2. Ortam ve A/B hazırlığı

Her çalıştırmada şu bilgileri kaydet:

| Alan | Kaydedilecek bilgi |
|---|---|
| Derleme | Commit, APK SHA-256, debug/release, imza sertifikası özeti |
| Araç | Model/yıl, DMC/Android/API sürümü, OEM platformu |
| Ekran | Ana ekran/cluster Display ID, çözünürlük, yoğunluk |
| Erişim | ADB mevcut mu, gerekli izinler, mevcut device-owner durumu |
| Uygulamalar | Google Maps/Yandex sürümü ve dili |
| Başlangıç | İlgili ayarlar, ağ/konum durumu, otomatik açılış ve ses tercihleri |

`versionName=1.2.4` ve `versionCode=2` eski ve yeni dosyada aynı olabilir; sürüm yazısını tek başına derleme kanıtı sayma. Yukarıdaki commit/hash bilgilerini kullan.

**A/B kuralı:** Aynı senaryo, aynı ayarlar ve aynı fixture ile tabanda ve PR revizyonunda denenir. Taban mevcut derleme sorunları nedeniyle derlenemiyorsa bunu `BASELINE_BUILD_BLOCKED` olarak kaydet. Geliştiricinin eski imzalı APK'sının hangi commit olduğu bilinmiyorsa onu bu tabanın kesin karşılığı sayma.

Farklı imzalı APK'lar aynı paketin üzerine kurulamayabilir. A/B için üretim verisini silme; ayrı test cihazı veya korunmuş emülatör snapshot'ları kullan. Zorunlu taban derleme yamaları uygulanırsa bunları ayrıca kaydet; ilgili davranışı değiştiren yama A/B kanıtını geçersiz kılar.

**Koşul etiketleri:**

- **MASA:** JVM/emülatör/tarayıcı; fiziksel araç gerekmez.
- **ARAÇ:** Desteklenen IVI üzerinde, park halinde normal kullanıcı akışıyla kabul testi.
- **DÜZENEK:** Mock/fake servis, kontrollü ağ yanıtı veya test callback'i gerekir. Mevcut test sınıfı belirtilmediyse bu düzenek henüz sağlanmış sayılmaz.

ADB kilitliyse ilgili araç maddeleri `ENGELLİ — ADB erişimi yok` kalır. Bu QA kapsamında kilit aşılmaz veya yeni ayrıcalık verilmez. OEM kontrol komutu enjeksiyonu yapılmaz; güç ve sürüş modu negatif senaryoları masaüstü test düzeneğinde denenir. Kamera/ses/kurulum gibi aktif testler salt okunur envanter incelemesinden ayrıdır; yalnızca uygun test ortamında uygulanır.

## 3. Öncelikli kısa tur

Önce QA-01, 02, 03, 08, 09, 11, 12, 15, 18, 20, 23, 24 ve 26 çalıştırılır. Bunlardan biri başarısızsa ilgili ayrıntılı senaryoya geçilir; tek bir kısa turun tamamlanması tüm PR'ın kabulü sayılmaz.

## 4. Tekrarlanabilir senaryolar

### QA-01 · Debug derleme ve paket bütünlüğü — P1 · MASA

**Önceki sorun:** Eksik kaynak/bağımlılık/metotlar debug derlemeyi engelliyordu; release yapılandırmasına bağımlılık vardı. Yedek GLB APK'ya giriyordu.

1. JDK 17 ve Android SDK 34 olan temiz test checkout'u aç.
2. Release keystore/parolaları ve DSN sağlamadan debug APK, birim testleri ve Android test APK'sını derle.
3. APK arşivindeki model varlıklarını incele.

**Geçer:** Debug derlenir; 56 birim test geçer; asıl GLB vardır, `.glb.bak` yoktur. “DSN yok” yüzünden uygulama açılışı çökmez. SDK/OEM uyumluluğu için targetSdk değiştirilmez.

**Kanıt:** Derleme çıktısı, test XML/HTML raporu, APK varlık listesi. Temiz checkout derlemesi önceki yerel incremental derlemeden ayrı kaydedilir.

### QA-02 · Anahtarsız web yönetimi — P1 · MASA + ARAÇ

**Nereye bakılır:** MapControl web sunucusu ve aynı ağdaki bağımsız tarayıcı/HTTP istemcisi. Varsayılan port `7462`.

**Önceki sorun:** Aynı ağdan yetkisiz yönetim isteği yapılabiliyordu.

1. Web sunucusunu aç; ikinci istemcide QR eşleştirmesi yapma.
2. Ana sayfayı ve `HEAD /` isteğini dene.
3. Anahtarsız `GET /files`, `GET /apps` gönder.
4. Diğer yönetim yollarının anahtarsız reddini mevcut entegrasyon testiyle doğrula; gerçek araçta silme/kurma çağrılarına geçerli anahtar verme.
5. QR ile eşleştir; normal dosya/uygulama listesini aç.

**Geçer:** Ana sayfa açılabilir ama anahtarı içermez; HEAD gövdesizdir. Yönetim yolları anahtarsız `401`; eşleşmiş istemcide normal listeleme çalışır.

**Otomatik karşılık:** `WebServerIntegrationTest.welcomeAndHeadDoNotExposeKey`, `everyManagementRouteRequiresSession`.
**Kanıt:** HTTP durumları ve maskelenmiş istekler; anahtarı rapora koyma.

### QA-03 · Web oturumu yenileme ve Origin — P1 · MASA + ARAÇ

**Önceki sorun:** Sunucu oturum anahtarı ve tarayıcı kaynak sınırı yoktu.

1. Geçerli anahtarla `GET /apps` isteğini aynı `Host`/Origin üzerinden gönder: `200` bekle.
2. Aynı anahtara `Origin: https://example.com` ekle: `403` bekle.
3. Sunucuyu kapat/aç; eski anahtarla tekrar dene: `401` bekle.
4. Yeni QR ile eşleştir; listeyi yeniden aç.

**Geçer:** Yeni QR çalışır, eski anahtar çalışmaz. Anahtar HTTP query'sine veya HTML'e yazılmaz. QR açıldıktan sonra sayfa adresindeki fragment temizlenir. Origin göndermeyen komut satırı istemcisi yine anahtar ister; sırf Origin yok diye reddedilmesi beklenmez.

**Otomatik karşılık:** `WebSessionTest`, `WebServerIntegrationTest.originAndSessionRotationAreEnforced`.
**Kanıt:** Anahtarları maskelenmiş Network kaydı ve durum kodları.

### QA-04 · Dosya aktarımı, aynı ad ve yarım yükleme — P1 · MASA + ARAÇ

**Önceki sorun:** Gövde RAM'e alınıyordu; çakışma/yarım aktarım ve ikili veri bütünlüğü sorunları vardı.

1. Test verisi olarak küçük bir ikili dosya oluştur; son baytları CR/LF ve multipart boundary'sine benzeyen içerik de bulunsun. Kaynak SHA-256'yı kaydet.
2. Web panelinden yükle; araçtaki tamamlanmış dosyanın SHA-256'sını karşılaştır.
3. Aynı adla farklı içerikli ikinci dosyayı yükle.
4. Ayrı bir test yüklemesini aktarım ortasında istemci tarafından kes; ardından dosya listesini yenile.

**Geçer:** Baytlar/hash korunur. İlk dosya üzerine yazılmaz; ikinci dosya ayrı adla korunur. Yarım aktarım tamamlanmış dosya olarak listelenmez; geçici dosya temizlenir. Sunucu sonraki küçük yüklemeye cevap verir.

**Otomatik karşılık:** `MultipartUploadTest` ikili veri/CRLF/çakışma/yol/temizlik testleri.
**Kanıt:** İki dosyanın ad/hash listesi, yarım aktarım öncesi/sonrası dosya durumu.

### QA-05 · HTTP sınırları ve sunucu kapanışı — P1 · MASA

**Önceki sorun:** İstek başlıkları, istemciler ve yükleme boyutu sınırsız kalabiliyordu.

1. `WebServerIntegrationTest.malformedAndOversizedRequestsAreRejectedBeforeBody` çalıştır: iki farklı Content-Length ve 1 GiB + 1 bayt beyanı gönderilir; büyük gövde gönderilmez.
2. `HttpRequestTest` ile bitmeyen başlık, chunked/çakışan framing ve geçersiz Host/uzunluk koşullarını çalıştır.
3. `stopClosesClientsWithIncompleteHeaders` testini çalıştır; açık, yarım başlıklı istemci varken sunucuyu durdur.

**Geçer:** Hatalı istek `400`, fazla büyük yetkili yükleme `413`; gövde okunmadan ret. Kapanışta istemci kapanır. Başlık/işçi sınırı aşılınca uygulama çökmez.

**Sınır:** 1 GiB sınırı multipart toplam istek gövdesidir, tam 1 GiB dosya boyutu değildir. İşçi kapasitesinde doyum ve uzun süreli bellek davranışı bu mevcut testlerle kanıtlanmış değildir; yalnızca test düzeneğinde ayrıca ölçülür. Araçta yük/DoS testi yapılmaz.

### QA-06 · Web metinleri kod çalıştırmamalı — P1 · MASA

**Önceki sorun:** Dosya/uygulama adları inline JavaScript'e, hata metinleri HTML'e girebiliyordu.

1. İzole tarayıcı fixture'ında uygulama/dosya adını `qa');window.__qaInjected=1;//.apk` yap.
2. Ayrı fixture'da boyut/hata metnini `<img src=x onerror="window.__qaInjected=1">` yap.
3. Listeyi göster; dosya butonuna yalnızca sahte HTTP backend ile bas.
4. Gönderilen dosya adını ve Network başlığını kontrol et.

**Geçer:** Metin düz metin görünür, `window.__qaInjected` oluşmaz, JavaScript hatası yoktur. Dosya adı bozulmadan gönderilir; yönetim isteği `X-MapControl-Key` taşır.

**Kanıt:** Tarayıcı konsolu/istek kaydı. Bu Chromium denemesi önceki yerel çalışmada yapıldı; fixture burada otomatik test paketi olarak sunulmamıştır. Geliştirici aynı sahte backend'i hazırlamadan gerçek araçta kötü amaçlı adla APK kurmaz.

### QA-07 · OEM yanıt yokluğu gerçek OFF değildir — P1 · DÜZENEK

**Önceki sorun:** Eksik yanıt `{0}` olarak yorumlanıp OFF/sıfır sanılabiliyordu.

1. `PowerModeSampleTest` ile eksik/geçersiz ve gerçek `0/1/2` örneklerini çalıştır.
2. `StartupGateTest.invalidSamplesDoNotClearOrLaunchAnything` çalıştır.
3. Tam servis düzeneğinde, önceden ON bilinen duruma eksik/geçersiz örnek ver; park/ses/açılış yan etkilerini gözle.

**Geçer:** Eksik/geçersiz örnek OFF olayı üretmez, karşılama kaydını temizlemez, açılış veya park işlemi başlatmaz. Gerçek OEM `0/1/2` eşlemeleri korunur.

**Kapsam boşluğu:** Mevcut testler örnek politikası ve gate kararını kapsar; üçüncü adımın tam servis/OEM yan etki düzeneği henüz yoktur. Telefon emülatöründe OEM servisinin bulunmaması tek başına bu testin tamamı değildir.

### QA-08 · Açılışın gerçek Activity onayı — P1 · ARAÇ + DÜZENEK

**Nereye bakılır:** Otomatik ana ekran açılışı ve açılış/karşılama ayarları.
**Önceki sorun:** `startActivity` çağrısı ekran açılmış gibi kabul ediliyordu.

1. Otomatik açılışı etkinleştir; normal araç açılışında ana ekranın gerçekten göründüğünü kontrol et.
2. Kontrollü geliştirici düzeneğinde ilk Activity girişini geciktir/reddet; sonra izin ver.
3. Activity görünür olduktan sonra bekle; tekrar tekrar öne getirilmediğini kontrol et.
4. Boot sayacının okunamadığı koşulu yalnızca sahte okuyucuyla dene.

**Geçer:** Başarı gerçek Activity yaşam döngüsü onayından sonra kaydedilir. Gecikmede sınırlı retry vardır; onaydan sonra retry durur. Aynı boot oturumu yeniden kimliklendirilip karşılama tekrarlanmaz.

**Kanıt:** Activity giriş/onay ve retry zaman çizgisi. `StartupGateTest` karar kapsamıdır; Activity engelleme ve boot okuyucu hatası için hazır uçtan uca test yoktur.

### QA-09 · STR uyanışı ve kullanıcı tercihi — P1 · ARAÇ + MASA

**Önceki sorun:** Süreç STR boyunca yaşadığında OFF sonrası ekran açılış isteği yeniden kurulmayabiliyordu.

1. Otomatik açılış açıkken normal OFF → STANDBY/ON döngüsünü araçta gözle.
2. Ana ekran açıldıktan sonra STANDBY → ON ilerleyişinde ikinci açılış olmadığını kontrol et.
3. Otomatik açılışı kapatıp aynı normal kullanım döngüsünü tekrarla.
4. Zaten ON durumunda servis yeniden oluşturma koşulunu test düzeneğinde dene; başka uygulama ön planda olsun.

**Geçer:** Açık tercih ile doğrulanmış OFF sonrası açılış yeniden kurulur; 1 → 2 geçişi ikinci kez kurmaz. Kapalı tercih uygulanır. Önceki durum bilinmeyen servis yeniden başlangıcı sırf bu nedenle ön planı çalmaz.

**Otomatik karşılık:** `StartupGateTest.strWakeRearmsUiAfterConfirmedOffWhenEnabled`, `strWakeRespectsDisabledAutoLaunch`, `serviceRestartWhileOnDoesNotStealTheForeground`, `standbyToOnDoesNotRearmAfterAcknowledgement`.
**Kanıt:** Ayar değerleri, normal güç geçişleri ve ekrandaki açılış sayısı. ADB ile güç/VDBus değeri enjekte edilmez.

### QA-10 · Bekleyen eski sürüş modu uygulanmamalı — P1 · DÜZENEK

**Nereye bakılır:** Hafıza/sürüş modu seçimi. Gerçek araca test komutu gönderme.
**Önceki sorun:** OEM hizmeti gecikince eski seçim sınırsız retry ile sonra uygulanabiliyordu.

1. Fake CarInfo hizmetini “bağlı değil” tut; A modunu seç.
2. 500 ms retry aralığı içinde B modunu seç; sonra fake hizmeti bağla.
3. A → Hiçbiri ve A → ekran release durumlarını ayrı ayrı dene.
4. Hizmeti en az 31 saniye bağlantısız tut; sonradan bağla.

**Geçer:** Son geçerli B dışında A gönderilmez; Hiçbiri/release bekleyen A'yı iptal eder. Yaklaşık 30 saniye/60 retry sonrası deneme bırakılır; sonradan eski mod gönderilmez. OEM sayısal mod eşlemesi değişmez.

**Kanıt:** Fake hizmete gönderilen değerler ve zamanları. **Mevcut PR'da bunun bağımsız otomatik testi yok; fake CarInfo düzeneği hazırlanması gerekiyor.** Sadece UI'da B'nin seçili görünmesi geçiş kanıtı değildir.

### QA-11 · Hazırlanıyor/boot penceresi kapanışı — P1 · MASA + ARAÇ

**Nereye bakılır:** Yansıtma/cluster açılışındaki hazırlık penceresi ve boot splash.
**Önceki sorun:** Fade callback'i null/değişmiş global View'a bakınca pencere kalabiliyor veya yeni pencere etkilenebiliyordu.

1. `OverlayDismissalTest` çalıştır: 30 aç/kapat döngüsü, eski fade sırasında yenisi, iptal/fallback ve arka plan çağrısı kapsanır.
2. Araçta normal cluster aç/kapat akışını 10 kez tekrarla; eski pencere/tıklama engeli kalıp kalmadığını kontrol et.
3. Normal boot splash kapanışından sonra ana ekranın etkileşimli olduğunu kontrol et.

**Geçer:** Her eski pencere bir kez kaldırılır; eski callback yeni pencereyi kaldırmaz/alanını temizlemez. Fade iptalinde de temizlenir; ana thread hatası yoktur.

**Kanıt:** Fake WindowManager add/remove sayıları ve gerçek ekrandaki sonuç. Otomatik test gerçek OEM pencere katmanını doğrulamaz. Cluster hazırlığındaki mevcut yaklaşık 7 saniyelik timeout, kasıtlı bekleme ise sızıntı sayılmaz.

### QA-12 · Görsel karşılama ve ses bağımsızlığı — P2 · ARAÇ + DÜZENEK

**Önceki sorun:** Görsel kapalıyken bağımsız ses de engellenebiliyor, MediaPlayer bazı yollarda bırakılmıyordu.

1. Görsel karşılama kapalı/ses açık seç; normal açılışta sesi kontrol et.
2. Görsel açık/ses kapalı kombinasyonunu dene.
3. Ses tamamlanması, hatalı test ses dosyası ve normal OFF sırasında kaynak bırakmayı kontrollü düzeneğe veya uygun test ünitesine al.

**Geçer:** İki tercih birbirini yanlışlıkla kapatmaz. Oynatıcı tamamlanma/hata/OFF/servis kapanışında bırakılır; eski callback sonraki oturumu bozmaz.

**Kanıt:** Tercihler, ses çalma sayısı ve oynatıcı create/release kayıtları. Genel gate testleri tüm MediaPlayer yaşam döngüsü yollarının testi değildir; tam kaynak sayımı ek düzenek ister.

### QA-13 · Cluster yanlış ekrandaki uygulamayı seçmemeli — P1 · MASA + ARAÇ

**Önceki sorun:** Global foreground kaydı başka ekrana aitken cluster uygulaması sanılabiliyordu; dumpsys ana thread'i bekletebiliyordu.

1. `DisplayTaskParserTest` ile hedef ekran görevleri, OEM display alanları ve global foreground kaydını çalıştır.
2. Araçta ana ekranda A, cluster'da B uygulaması açıkken normal yansıtma akışını dene.
3. Okuma yapılamaması/2 saniyeyi aşması koşulunu fake komut okuyucusuyla dene; gerçek OEM izinlerini değiştirme.

**Geçer:** Yalnızca hedef display görevi seçilir; ana ekrandaki A cluster'daymış gibi kapatılmaz/taşınmaz. Görev bilgisi bilinmiyorsa tahmin yapılmaz; seçili uygulamanın normal VDBus açılış yolu korunur. UI okuma bitene kadar bloklanmaz.

**Kanıt:** Maskelenmiş display/task örneği ve karar kaydı. Parser birim testi timeout/gerçek uygulama taşıma zincirinin tam testi değildir.

### QA-14 · Launcher görünürlüğü ve sınırlı günlük — P2 · MASA + ARAÇ

**Önceki sorun:** Gizlenen/yok edilen ekranda izleyici/güncelleme çalışabiliyor; günlük sınırsız büyüyebiliyordu.

1. Launcher'ı göster → başka uygulamayla tamamen gizle → geri getir; 10 kez tekrarla.
2. Desteklenen görünür çoklu pencere halinde MapControl odağını değiştir ama görünür tut.
3. Geliştirici düzeneğinde görünüm yok edildikten sonra gecikmiş hava durumu callback'ini tamamla.
4. `BoundedLogBufferTest.keepsRecentEventsWithinTheMemoryBudget` çalıştır.

**Geçer:** Tamamen gizli/yok edilmiş görünümün eski işleri UI güncellemez; geri dönünce güncellemeler yeniden çalışır ve çoğalmaz. Görünür çoklu pencere yalnızca onPause yüzünden durmaz. Günlük tamponu 64 KiB sınırını korur, yeni kayıtlar kalır.

**Kanıt:** Görünürlük/izleyici sayıları ve tampon ölçümü. RAM'in sistem önbelleği nedeniyle anında başlangıç değerine dönmesi şart değildir; “sızıntı yok” için tek anlık bellek ölçümü yeterli değildir.

### QA-15 · Konum yokken sahte şehir gösterilmemeli — P1 · MASA + ARAÇ

**Nereye bakılır:** Launcher hava durumu kartı.
**Önceki sorun:** Sabit Sakarya verisi her araçta kullanılıyordu.

1. İzole test profilinde eski sabit konum önbelleği bulunsun ama kullanılabilir konum olmasın.
2. Hava durumu kartını aç.
3. `WeatherLifecycleTest.legacyFixedCityCacheIsNotDisplayedWithoutVehicleFix` ve `destroyPreventsQueuedCallbacksAndRestart` çalıştır.

**Geçer:** Konumsuzken eski sabit şehir verisi yeni/güncel araç hava durumu diye gösterilmez. Yok edilmiş istemcinin callback'i UI'a dönmez veya yeniden başlamaz.

**Kanıt:** Kart görünümü ve callback sayıları. Üretim araç verileri test için silinmez; fixture izole ortamda hazırlanır.

### QA-16 · Yaklaşık hava durumu konumu, önbellek ve ağ hatası — P2 · DÜZENEK + ARAÇ

**Önceki sorun:** Sabit koordinat kullanımı ve konumla ilişkilendirilmemiş önbellek aracın bulunduğu yeri temsil etmeyebiliyordu; yaşam döngüsü/ağ hatası yolları ayrıca doğrulanmalı.

1. Kontrollü konum sağlayıcıda `41.12345, 29.98765` örneği ver; oluşan isteği uygulama test düzeneğinde yakala.
2. Aynı yaklaşık konumda 15 dakika dolmadan yeniden göster.
3. Yaklaşık konumu değiştir; önceki konum önbelleğinin yeni konuma sunulmadığını kontrol et.
4. Ağ hatası ver, sonra başarılı yanıtı geri getir; ekranı istek sırasında gizleme/destroy koşulunu da dene.

**Geçer:** Open-Meteo sorgusu `latitude=41.12&longitude=29.99`; tam hassasiyet gönderilmez. Aynı konum için geçerli 15 dakikalık önbellek kullanılır. Hata sonrasında yaklaşık 60 saniyelik retry yolu ve görünürlük/generation koruması uygulanır; eski yanıt yeni ekrana yazılmaz.

**Kanıt:** Maskelenmiş, yalnızca test koordinatlı istek dizisi ve callback zamanları. Mevcut WeatherLifecycle testleri tam koordinat/ağ/TTL matrisini kapsamaz; bunun için ağ/konum düzeneği gerekir. TLS güvenliğini kapatarak araç trafiği yakalama önerilmez.

### QA-17 · Park konumu yalnızca geçerli geçiş ve taze fix ile — P1 · MASA + DÜZENEK

**Önceki sorun:** İlk OFF örneği veya eski/düşük doğruluklu konum kayıt tetikleyebiliyordu.

1. `LocationSamplePolicyTest` çalıştır: 30 saniye/20 m geçerli; 10 dakika eski, 2000 m hata, NaN/sınır dışı ve eksik doğruluk geçersiz.
2. Servis düzeneğinde ilk örnek OFF ile bilinen ON → OFF geçişini ayrı ayrı ver.
3. İsteğe bağlı park kaydı kapalıyken aynı geçişi dene; sunucu çağrılarını sahte backend'de say.

**Geçer:** Yalnızca kayıt tercihi açık, bilinen açık durumdan OFF geçişi ve en fazla 2 dakika yaş/250 m hata koşulunda kayıt olur. İlk OFF ve geçersiz örnek kayıt üretmez. Koordinatlar loga yazılmaz.

**Kapsam boşluğu:** Mevcut birim testi konum politikasını doğrular; tam servis geçişi/backend çağrı sayımı için hazır otomatik test yoktur. Gerçek sunucuya QA amaçlı park kaydı gönderilmez.

### QA-18 · APK kurulum kuyruğu gerçek sonucu beklemeli — P1 · ARAÇ/EMÜLATÖR + DÜZENEK

**Nereye bakılır:** Uygulama Yönetimi → Yerel/Sunucu kurulum akışı. Yalnızca test APK'ları kullan.
**Önceki sorun:** Opsiyonel OEM dex bulunmadığında standart kurulum yoluna geçiş ek APK kuyruğunu bozabiliyordu.

1. OEM dex içermeyen standart derlemede ana test APK + ek test APK içeren bir kurulum işini başlat.
2. Android onay ekranını beklet; ikinci paketin henüz kurulmadığını kontrol et.
3. İlkini onayla; gerçek başarılı oturum sonucu sonrasında ikinci işin başladığını kontrol et.
4. Ayrı koşulda ilk kurulumdan vazgeç veya hatalı test APK kullan; sonraki iş başlamamalı.
5. Fake callback ile farklı session ID sonucu ve Activity kapandıktan sonraki sonucu ver.

**Geçer:** Eksik opsiyonel dex çökme yaratmaz. Bekleyen onay ilerleme sayılmaz; ilgili oturum başarısı ilerletir; hata durdurur. Başka oturumun sonucu kuyruğu ilerletmez; kapanmış Activity dinleyicisi bırakılır.

**Kanıt:** Test paketlerinin önce/sonra kurulu listesi, session ID ve callback sırası. **Bu kuyruk için bağımsız otomatik test mevcut değil.** OEM dex içeren özel derleme ayrıca denenmelidir. Device-owner atanması bu PR düzeltmesinin başarı şartı olarak zorlanmaz; mevcut yetkiyle çalışan yollar ayrı raporlanır.

### QA-19 · Kamera aç/kapat kaynak temizliği — P1 · ARAÇ + MASA

**Nereye bakılır:** Kamera testi ekranı; mevcut DMS/OMS/DVR/AVM seçenekleri.
**Önceki sorun:** Callback thread, oturum, Surface ve geç açılış callback'leri temizlenmiyordu.

1. Uygun test ünitesinde her desteklenen önizlemeyi aç/kapat; 10 döngü yap.
2. Önizleme açılırken ekranı terk et; sonra geri gel.
3. Kamera izni verilmemiş izole test profilinde giriş yap; yalnızca kamera izni verip tekrar dene.
4. `CameraResourceTest.releasingCameraBeansStopsTheirHandlerThreads` çalıştır.

**Geçer:** Görünmez ekranda kamera/Surface bırakılır; geç callback kapalı ekranı tekrar açmaz. Thread/oturum sayısı döngü başına artmaz. Önizleme mikrofon iznini şart koşmaz. OEM kamera ID eşlemeleri korunur.

**Kanıt:** İlgili thread/oturum/Surface durumu ve kaynak meşgul hataları. Otomatik test yalnızca CameraBean thread temizliğini kapsar; gerçek kamera oturumlarını kapsamaz. Test sonrası ünitenin normal sesli asistan/kamera kullanımını kontrol et; kalıcı HAL arızasının çözüldüğü varsayılmaz.

### QA-20 · Google Maps süre/mesafe/etiket ayrıştırma — P1 · MASA + ARAÇ

**Önceki sorun:** Bazı bitişik/yerelleştirilmiş süreler sıfır veya pasif navigasyon olabiliyor; ETA temizliği sokak adını bozabiliyordu.

1. `NavigationQuantitiesTest` ve `GoogleMapsNavNotificationParserTest` çalıştır.
2. Aşağıdaki fixture'ların sonuçlarını kontrol et.
3. Araçta mevcut Google Maps sürümünün gerçek bildirimini kart/cluster çıktısıyla karşılaştır; sürüm ve dili kaydet. Test için sürüş yapma veya OEM navigasyon mesajı enjekte etme.

| Girdi | Beklenen |
|---|---|
| `1hr20min` | 80 dakika |
| `1sa10dk` | 70 dakika |
| `1 Std. 20 Min.` / `1ч20мин` | 80 dakika |
| `1,5 hr` | 90 dakika |
| `5 км•1ч20мин·12:30` | 5 km / 80 dakika / 12:30; alanlar ayrılır |
| `500 м` | 500 metre |
| `500 ft` | Yuvarlanmış 152 metre |
| `80 m - Beta Street` | Yol adı `Beta Street` kalır |
| `-5 min`, `2 minimal`, `12:30` tek başına | Süre uydurulmaz |

**Geçer:** Fixture sonuçları aynıdır; sayı taşması negatif değere dönüşmez. Gerçek bildirim farklıysa maskelenmiş yeni fixture eklenmeden “bütün Maps dilleri destekleniyor” denmez. OEM 14/16/-1 manevra kodları değişmez.

### QA-21 · Yandex içerik güncellemesi pencereyi yeniden yaratmamalı — P1 · MASA + ARAÇ

**Önceki sorun:** Normal içerik değişiminde remove/add pencere hareketi oluşuyordu.

1. `YandexOverlayUpdateTest.liveContentUpdatesKeepOneWindowAndWindowEventsCanStillRaiseIt` çalıştır.
2. 50 değişen snapshot'ta add/remove sayısını kontrol et.
3. Fake dış pencere olayı ve hemen tekrarı ver; sonra navigasyonu pasifleştir.
4. Araçta normal Yandex kart güncellemesini ve başka uygulama penceresi açılınca görünürlüğü gözle.

**Geçer:** 50 içerik güncellemesinde sıfır remove/add. Gerekli dış olayda bir kez üste alma mümkün; hemen tekrar 250 ms sınırına takılır. MapControl'ün kendi olayları yeniden ekleme döngüsü üretmez. Navigasyon bitince pencere kaldırılır.

**Kanıt:** Fake WindowManager çağrı sayıları ve gerçek görünürlük. Amaç her koşulda hiç remove/add olmaması değildir; dış pencere sonrası üste alma korunmuştur.

### QA-22 · Genel izin kontrolü mikrofonu açmamalı — P1 · MASA + ARAÇ

**Önceki sorun:** İzin denetimi gerçek AudioRecord/kayıt başlatabiliyordu.

1. `PassiveDiagnosticsTest` çalıştır: RECORD_AUDIO verilmiş ve verilmemiş iki durum.
2. Araçta yalnızca genel izin tanılama ekranını aç; özel bir mikrofon testi başlatma.
3. Verilmiş izin sonucunun açıklamasını ve erişilebilen kayıt istemcisi durumunu kontrol et.

**Geçer:** İzin varsa `SKIP / donanım denenmedi`, yoksa izin hatası gösterilir. Bu işlem AudioRecord/kayıt oturumu açmaz. “Mikrofon donanımı sağlam” sonucu verilmez.

**Kanıt:** Tanılama sonucu, fake context'in donanım servisi istemediği test sonucu; gerçek ünitede kullanılabiliyorsa kayıt istemcisi öncesi/sonrası.

### QA-23 · Kapalı EQ hiçbir global efekt açmamalı — P1 · MASA + ARAÇ

**Önceki sorun:** EQ kapalıyken durum okuma/kaydetme/keeper kontrolü efekt açabiliyordu.

1. EQ kapalıyken ayar ekranını aç; bant değerlerini değiştir/kaydet ve kapalı bırak.
2. En az 45 saniye bekle; ekranı yeniden aç.
3. `EqualizerDisabledTest` içindeki üç testi çalıştır.

**Geçer:** Bantlar/kaydedilmiş değerler korunur. Kapalı okuma/kaydetme/keeper sırasında yeni efekt nesnesi oluşturulmaz; ses çıkışı gereksiz etkilenmez.

**Kanıt:** Testte efekt nesnesinin yokluğu ve ayarlar. Araçta efekt sahipliği ölçülemiyorsa yalnızca “ses kesilmedi” gözlemini kaynak kanıtı yerine koyma.

### QA-24 · Etkin EQ ayarı ve kontrol sahipliği — P1 · ARAÇ + DÜZENEK

**Önceki sorun:** Keeper, ayar/kontrol durumunu düzeltmek için global efekti gereksiz yere yıkıp tekrar oluşturabiliyordu.

1. Uygun test ünitesinde düşük sesle EQ'yu etkinleştir; ayar değiştir ve en az 45 saniye gözle.
2. Fake effect ile dış kontrol sahibinin kontrol aldığı koşulu ver; keeper kontrollerini ilerlet.
3. Ayrı koşulda efektin geçersiz/ölü olmasını simüle et.

**Geçer:** Kontrol kendisindeyken ayar sapması aynı efekt üzerinde düzeltilir. Başka kontrol sahibi varken her 20 saniyede yıkıp yeniden oluşturma döngüsü oluşmaz. Ölü efekt bırakılır; sonraki kontrolde normal yeniden açılma denenebilir. Global oturum özelliği korunur.

**Kanıt:** Efekt kimliği/create/release ve kontrol zamanları. **Mevcut otomatik kapsam yalnızca kapalı EQ'yu doğrular; etkin sahiplik/ölü efekt için hazır otomatik test yoktur.** OEM ses/mikrofon sorununun tamamının giderildiği iddia edilmez.

### QA-25 · Profil API başarısızlığında sınırlı bekleme — P2 · DÜZENEK

**Önceki sorun:** Süresiz ağ bekleme, sınırsız yanıt okuma ve null hata gövdesi vardı.

1. Üretim profil API'sine istek göndermeyen geliştirici test derlemesi/fake bağlantı hazırla.
2. Bağlantı kurmayan sunucu, bağlanıp gövde göndermeyen sunucu, hata kodu/null error stream ve 1 MiB'den büyük yanıtı ayrı dene.
3. Her durumda callback ve bağlantı kapanışını gözle.

**Geçer:** 10 s bağlantı/15 s okuma zaman aşımı yapılandırması işler; 1 MiB yanıt sınırı aşılınca işlem hata verir. Null hata gövdesi NPE üretmez; bağlantı bırakılır. Soket timeout'u tüm işlemin kesin duvar saati süresi olarak yorumlanmaz.

**Kapsam boşluğu:** Endpoint şu anda kaynakta sabittir; uygulamada kullanıcıya açık test sunucusu seçimi yoktur. Bu hata matrisi için hazır otomatik test/test endpoint'i bulunmuyor. Gerçek hesaba tekrar tekrar doğrulama kodu göndererek QA yapılmaz.

### QA-26 · Erişilebilirlik ayar girişinin açılması — P2 · MASA + ARAÇ

**Önceki sorun:** Accessibility XML'indeki settingsActivity mevcut olmayan Activity'yi gösteriyordu.

1. Android erişilebilirlik ayarlarında MapControl servisinin ayrıntısını aç.
2. Sistem bu bağlantıyı gösteriyorsa uygulama ayar girişine bas.
3. `AccessibilityEntryPointTest.configuredSettingsActivityResolvesToTheRealActivity` çalıştır.

**Geçer:** `com.mapcontrol.ui.activity.MainActivity` çözülür/açılır; ActivityNotFound yoktur. OEM arayüzünde ayar bağlantısı gösterilmiyorsa fiziksel adım `DESTEKLENMİYOR`; paket çözümleme testi ayrıca geçebilir.

## 5. Otomatik testleri tekrar çalıştırma

Kaynak checkout'unda JDK 17 / SDK 34 ile:

```text
java -classpath gradle/wrapper/gradle-wrapper.jar org.gradle.wrapper.GradleWrapperMain --no-daemon :app:testDebugUnitTest :app:assembleDebug :app:assembleDebugAndroidTest
```

Tek bir JVM alanını çalıştırma örneği:

```text
java -classpath gradle/wrapper/gradle-wrapper.jar org.gradle.wrapper.GradleWrapperMain --no-daemon :app:testDebugUnitTest --tests com.mapcontrol.nav.NavigationQuantitiesTest
```

Android testleri ayrı, boş bir API 34 test emülatöründe çalıştırılır; fiziksel araç hedef seçilmez. `<TEST_EMULATOR_SERIAL>` gerçek emülatör seri numarasıyla değiştirilir. Test uygulaması 7462 portunu kullanabildiğinden aynı portta çalışan MapControl web sunucusu kapalı olmalıdır.

```text
adb -s <TEST_EMULATOR_SERIAL> install -r app/build/outputs/apk/debug/app-debug.apk
adb -s <TEST_EMULATOR_SERIAL> install -r app/build/outputs/apk/androidTest/debug/app-debug-androidTest.apk
adb -s <TEST_EMULATOR_SERIAL> shell am instrument -w com.mapcontrol.test/androidx.test.runner.AndroidJUnitRunner
```

Bunlar yeniden çalıştırma talimatıdır; bu QA belgesi hazırlanırken testler tekrar çalıştırılmadı. CI mevcut iş akışında birim testleri çalıştırır ve Android test APK'sını derler; emülatör instrumentation testlerini otomatik çalıştırmaz.

Raporlar: Gradle `testDebugUnitTest` raporu ve instrumentation sonundaki `OK (20 tests)` çıktısı birlikte saklanır. Fixture/test assertion'ı eski tabana uygulanamıyorsa “eski sürümde bu test fail oldu” yazılmaz; derleme/API uyarlaması ayrıca belgelenir.

## 6. Sonuç kaydı ve kabul

Her QA-ID için şu şablonu doldur:

```text
QA-ID:
Test eden / tarih:
Ortam / araç / uygulama sürümleri:
Taban commit veya APK hash:
PR commit veya APK hash:
Başlangıç ayarları / fixture:
Uygulanan adımlar:
Eski sonuç: GÖZLENDİ / KOD İNCELEMESİ / ENGELLİ
Yeni sonuç: GEÇTİ / KALDI / ENGELLİ / ÇALIŞTIRILMADI / DESTEKLENMİYOR
Gerçek gözlem:
Maskelenmiş kanıt dosyası / satırı:
Temizlik / eski ayarlara dönüş:
Takip issue'su:
```

- İlk doldurulmamış manuel durum **ÇALIŞTIRILMADI** kabul edilir. Birim test geçişiyle araç sütunu işaretlenmez.
- P1 kabul maddelerindeki başarısızlıklar giderilmeden kabul verilmez. ENGELLİ/eksik düzenekler maintainer tarafından açıkça değerlendirilir; sessizce geçer sayılmaz.
- OEM kamera/cluster/EQ/STR özelliği cihazda yoksa bunun yerine telefon davranışı test edilerek madde kapatılmaz.
- Araç kabulünde en az normal açılış/uyanış, cluster, kamera kaynak bırakma ve ses/mikrofon regresyonu için kanıt gerekir; her araç modeli için otomatik genelleme yapılmaz.
- QA sırasında değiştirilen uygulama tercihleri ve test dosyaları kayıtlı başlangıç durumuna geri alınır. Kullanıcının mevcut dosyaları/uygulamaları silinmez.

İlgili kapsam açıklamaları: `ivi-reliability-review.md` ve `ivi-report-followup.md`.
