# Önceki denetimler ve QA kayıtları

22 Ekim 2026 aktarımı. Bu dosya geçmiş kanıt ve açık soruların kayıpsız arşividir. Güncel kurallar için [README](README.md), [tasarım](design.md), [ergonomi](ergonomics-and-layout.md) ve [bileşenler](components.md) esas alınır. Eski test sayıları bu görevde yeniden çalıştırılmış testler değildir. Eski raporlardaki 48 dp kontrol, üstte portrait menü ve tıklanabilir sağ durum şeridi kararları yeni HMI hedeflerini geçersiz kılmaz.

Metinler özgün Markdown olarak dört tilde çiti içinde, satır sonları LF olacak şekilde korunmuştur. İçlerindeki eski göreli yollar özgün docs/ konumuna ve satır numaraları kendi revizyonlarına aittir; güncel bağlantı olarak kullanılmamalıdır. Eski raporların birbirine bağlantıları aşağıdaki indeksten izlenir. Önizleme dosyaları docs/ui-previews/ altında korunmuştur. Mutlak yerel kanıt yolları başka bilgisayarlarda bulunmayabilir.

| Önceki belge | Aktarım |
|---|---|
| `ivi-reliability-review.md` | [Tam kayıt](#legacy-ivi-reliability-review) |
| `ivi-report-followup.md` | [Tam kayıt](#legacy-ivi-report-followup) |
| `oem-ivi-permissions-research.md` | [Tam kayıt](#legacy-oem-ivi-permissions-research) |
| `pr-3-qa-checklist.md` | [Tam kayıt](#legacy-pr-3-qa-checklist) |
| `shared-header-review.md` | [Tam kayıt](#legacy-shared-header-review) |
| `sport-animation-feature.md` | [Tam kayıt](#legacy-sport-animation-feature) |
| `ui-readability-review.md` | [Tam kayıt](#legacy-ui-readability-review) |
| `welcome-readability-review.md` | [Tam kayıt](#legacy-welcome-readability-review) |

<a id="legacy-ivi-reliability-review"></a>

## ivi-reliability-review.md

~~~~markdown
# IVI güvenilirlik incelemesi

Bu incelemenin hedefi Android tabanlı Chery/Omoda/JAC/DesaySV ana ünitesidir. Telefon veya tablet uyumluluğu kabul ölçütü değildir. OEM CarInfo/VDBus sözleşmeleri, güç modu değerleri, cluster mesajları, kamera kimlikleri, targetSdk 28 ve mevcut ayrıcalıklı kurulum modeli korunmuştur.

## Doğrulanan ve düzeltilen sorunlar

| Alan | Önceki sorun | Düzeltme |
|---|---|---|
| Debug derleme | Eksik Wi-Fi kaynakları, eksik Sentry bağımlılığı/yapılandırması ve eksik navigasyon mesafe metodu derlemeyi engelliyordu. | İngilizce/Türkçe kaynaklar, isteğe bağlı DSN, debug için bağımsız derleme ve metrik mesafe dönüşümü eklendi. Release anahtarları depoya konmuyor. |
| Web paneli erişimi | Aynı ağdaki herhangi bir istemci uygulama/dosya yönetim uçlarını kullanabiliyordu. | Her sunucu açılışında yeni 256 bit anahtar; araç ekranındaki QR ile eşleştirme; yönetim isteklerinde anahtar ve tarayıcı Origin kontrolü. Anahtar URL fragment'inde taşınır, HTTP URL/loglarına yazılmaz. |
| Dosya yükleme | Gövdenin tamamı RAM'e alınabiliyor, sınır/yarım yükleme ve dosya çakışmaları veri kaybına yol açabiliyordu. | En fazla 1 GiB istek; sınırlı başlıklar/iş parçacıkları; akış halinde geçici dosya; yalnızca tamamlanan dosyanın yayımlanması; özgün dosyaları koruyan adlandırma; kanonik dosya yolu kontrolü. |
| Web arayüzü | Dosya/uygulama adları inline JavaScript'e, sunucu hata metinleri HTML'e girebiliyordu. | Olay dinleyicileri gerçek değerleri kapatır; dinamik metinler kaçırılır; eşleştirme anahtarı tüm yönetim isteklerine eklenir. |
| Araç verisi | OEM yanıt yokluğu `{0}` olarak yorumlanıp gerçek OFF/sıfır ile karışabiliyordu. | OEM uyumlu eski API korunurken uygulamanın otomasyon okumaları nullable API kullanır. Eksik/geçersiz güç örneği işlem başlatmaz; ölçümler ve hızlı kontroller sahte sıfır üretmez. |
| Sürüş modu | OEM hizmeti hazır değilken sınırsız tekrarlar, sonraki seçimden sonra eski modu uygulayabiliyordu. | Tekrarlar 30 saniyeyle sınırlı; yeni seçim/Hiçbiri ve ekranın yok edilmesi eski isteği iptal eder. OEM mod değerleri değişmez. |
| Açılış | `startActivity` çağrısı, ekran gerçekten açılmadan başarı sayılıyordu. Boot sayacı okunamayan sistemlerde oturum kimliği kararsızdı. | Ekran yaşam döngüsünden başarı bildirimi, sınırlı tekrar denemeleri ve kararlı boot kimliği alternatifi. |
| Karşılama sesi | Görsel karşılama kapalıyken bağımsız ses de engelleniyor; oynatıcı her kapanış/hata yolunda bırakılmıyordu. | Görsel/ses kararları ayrıldı. Asenkron hazırlık, tamamlanma/hata/OFF/servis kapanışında oynatıcı temizliği. |
| Cluster keşfi | Ana iş parçacığında sınırsız dumpsys; başka ekrandaki uygulamayı cluster'da sanma ihtimali. | Arka plan okuması, 2 saniye sınırı ve yalnızca hedef ekrana ait görevin ayrıştırılması. Bilgi okunamıyorsa öndeki uygulama tahmin edilmez. |
| Yaşam döngüsü | Gizlenen/yok edilen launcher'da hava durumu, tarih ve izleyiciler çalışabiliyor; günlük tamponu sınırsız büyüyordu. | `onStop`/yeniden görünme ve `release` yolları, iptal edilen eski hava durumu istekleri, 64 KiB günlük tamponu ve toplu ekran güncellemesi. Görünür çoklu pencere ekranları `onPause` yüzünden durdurulmaz. |
| Hava durumu | Sabit Sakarya koordinatları tüm araçlarda kullanılıyordu. | Kullanıcının açık tercihiyle mevcut araç konumu iki ondalığa yuvarlanarak Open-Meteo'ya gönderilir. Konuma bağlı 15 dakika önbellek; konum/izin yoksa başka şehir gösterilmez; hata halinde tekrar deneme ve sınırlı yanıt boyutu. |
| Park konumu | Eski/düşük doğruluklu son konum gönderilebiliyor; ilk OFF örneği park olayı sayılabiliyordu. | Mevcut isteğe bağlı kayıt ayarı korunur. Yalnızca bilinen açık durumdan OFF geçişi; son 2 dakika ve en fazla 250 m hata koşulu. Konum günlükten çıkarıldı. |
| APK kurulumu | Depoda bulunmayan sessiz dex aracı her zaman deneniyor, standart kurulum yoluna geçince ek APK kuyruğu iptal ediliyordu. | OEM dex isteğe bağlı kalır; mevcut yetkilerle standart PackageInstaller alternatifi çalışır. Kuyruk yalnızca ilgili oturumun başarı sonucu üzerine ilerler; bekleyen onay kuyruğu ilerletmez, hata durdurur. Activity kapanışında dinleyiciler bırakılır. OEM yardımcı süreci süre/çıktı sınırına sahiptir. |
| Kamera testi | Her kamera için kapanmayan callback thread'i ve oturum başına ayrı iş parçacığı havuzu; Surface kaynakları ve geç gelen açılışlar temizlenmiyordu. | Ortak Activity callback yürütücüsü; kamera/oturum/Surface/thread temizliği; görünmez ekranda kameraları bırakma; geç callback korumaları. Önizleme yalnızca kamera izni ister ve izin verildikten sonra tekrar dener. OEM DMS/OMS/DVR/AVM eşlemesi korunur. |
| Profil API | Süresiz bekleyen bağlantılar, sınırsız yanıt okuma ve null hata gövdesi. | Bağlantı/okuma zaman aşımı, 1 MiB yanıt sınırı ve finally içinde bağlantı temizliği. |
| Paket boyutu | Kullanılmayan `.glb.bak` yedeği APK'ya giriyordu. | Yedek dosyalar paket dışında; asıl GLB ve OEM model desteği korunur. |

## Doğrulama

19 Ekim 2026 güncel yerel sonucu: **56 birim testi ve 20 Android entegrasyon testi başarılı**. Debug APK/test APK derlemesi başarılı; Chromium senaryosunda JavaScript hatası veya enjekte edilen kod çalışması görülmedi. Ek pencere, STR, navigasyon ve ses düzeltmeleri ile test kapsamı [takip raporunda](ivi-report-followup.md) açıklanmıştır.

- JDK 17, Gradle 8.2, Android SDK/Build Tools 34 ile debug APK ve test APK'sı derlenir. Release imzası veya DSN gerekmez.
- Birim testleri HTTP başlık/gövde ayrımı, multipart bayt bütünlüğü/yarım yükleme/çakışma/yol kontrolü, oturum anahtarı/Origin, güç örnekleri, konum doğruluğu, cluster görev ayrıştırması, günlük sınırı, başlangıç kararı ve navigasyon mesafesini kapsar.
- Android testleri gerçek soketler üzerinde tüm yönetim yollarının anahtar istemesini, Origin reddini, anahtar yenilenmesini, HEAD davranışını, fazla büyük yüklemenin gövde okunmadan reddini ve açık istemcilerin kapanmasını sınar. Ek testler konumsuz hava durumu/legacy cache ve kamera thread temizliğini kapsar.
- Chromium'da dosya adındaki apostrof/JavaScript metni, HTML içeren boyut bilgisi ve hata mesajı denendi. Kod çalışmadı; buton dosya adını eksiksiz gönderdi; istekler anahtar taşıdı; URL fragment'i geçmişten temizlendi.
- `lintDebug` raporu ayrıca incelendi. Lint'in tamamı temiz değildir: OEM kütüphanelerindeki sabitler/korumalı izinler, yakalanan izin reddi yolları, dinamik sürüş modu View kimlikleri ve ChromeOS donanım varsayımı gibi mevcut bulgular vardır. Bunlar telefon uyumluluğu gerekçesiyle değiştirilmedi.

Yerel Android testleri API 34 emülatöründe çalıştırılır; bu, fiziksel IVI donanım testi değildir. Testler VDBus komutu göndermez, araç ayarı değiştirmez ve gerçek konum isteği yapmaz.

## Fiziksel araçta kabul kontrolü

PR birleştirilmeden önce desteklenen IVI üzerinde şu senaryolar doğrulanmalıdır:

1. Soğuk açılış, servis yeniden başlatma ve OFF → STANDBY → ON geçişlerinde tek karşılama ve ana ekran açılışı.
2. OEM veri hizmeti geciktiğinde sahte OFF olayı/yanlış kapatma oluşmaması.
3. Cluster aç/kapat/uygulama taşıma; hedef ekran görev bilgisi okunamayan ünitede seçili uygulamanın normal açılması.
4. Device Owner kurulumu ve ek APK zinciri; destekleyen özel derlemede OEM dex yolu.
5. Gerçek konumla hava durumu, GPS/izin/ağ yokken boş durum ve park konumunun güncelliği.
6. DMS/OMS/DVR/AVM önizlemelerini tekrar açıp kapatınca kamera/sesli asistan kaynaklarının serbest kalması.
7. Araç ses sistemi/mikrofon kesintisi ve uzun süreli bellek tüketimi. Önceki donanım raporları bu emülatör testiyle çözülmüş sayılmaz.

Yeni `DUMP` izni veya bu izne ilişkin kurulum grant'i eklenmemiştir. Cluster görev keşfi mevcut OEM erişimiyle çalışır; erişim yoksa başka ekrandaki uygulamaya müdahale edilmez. VDBus açılış akışı korunur.

README'deki v1.1.4 3D/CPU ölçümleri tarihsel veridir. Güncel launcher orta kartı saat/hava durumu/araç ölçümlerini gösteren Lite paneldir; eski ölçümler güncel sürümün performans kanıtı olarak kullanılmamalıdır.
~~~~

<a id="legacy-ivi-report-followup"></a>

## ivi-report-followup.md

~~~~markdown
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
~~~~

<a id="legacy-oem-ivi-permissions-research"></a>

## oem-ivi-permissions-research.md

~~~~markdown
# OEM IVI izin modeli ve değişiklik etki araştırması

**Tarih:** 19 Ekim 2026 · **Durum:** Araştırma v1; fiziksel araç kabul raporu değildir.

**İncelenen kaynak:** `a73b9214716ef29080ab48bb8d10b5011541f27c`; karşılaştırma tabanı `bb4d70a46b30dfdba5e85681ce01bb9eed62069a`.

**İncelenen APK'lar:** Kullanıcının sağladığı orijinal `com.mapcontrol.apk` ve bizim `mapcontrol-custom-release-a73b921.apk` çıktımız. Orijinal APK'nın tam kaynak commit'i bilinmiyor; kaynak tabanıyla birebir eşit olduğu varsayılmadı.

Bu çalışma uygulama kodunu veya PR'ı değiştirmez. Araca bağlanılmadı, kamera/mikrofon açılmadı, OEM servisi çağrılmadı, izin/ayar verilmedi. Yerel kaynaklar, iki APK'nın statik içeriği, geliştiricinin kurulum rehberleri ve resmi Android/AOSP belgeleri incelendi. Araştırma belgesi ve yerel karşılaştırma kanıtı oluşturuldu.

## 1. Temel sonuçlar

1. **“Önizleme yalnızca kamera izni ister” cümlesi araç genelinde yeterlilik iddiası olarak kullanılamaz.** Kodun runtime izin isteği ile OEM donanım erişiminin bütün koşulları farklı şeylerdir.
2. PR, manifestten `RECORD_AUDIO` veya `WRITE_EXTERNAL_STORAGE` kaldırmamış; `CameraActivity` içindeki üçlü kontrolü CAMERA kontrolüne indirmiştir. Bunun OEM'e uygunluğu da, eski üçlü kontrolün OEM tarafından zorunlu tutulduğu da henüz kanıtlanmış değildir.
3. **Orijinal APK, OEM kurulum yardımcısını içeriyor; bizim APK içermiyor.** Standart PackageInstaller alternatifi aynı sessiz kurulum/yetki davranışını garanti etmez.
4. Yeni APK'nın Android imzası geçerli olabilir ve ilk paket kurulumu başarılı olabilir; bunlar OEM servislerinin aynı hakları tanıyacağını kanıtlamaz. Paket imzası, platform imzası, device-owner rolü, Binder erişimi ve SELinux birbirinin yerine geçmez.
5. Kamera düzeltmesi yalnızca izin düzenlemesi değildir: kapat/aç yaşam döngüsü, callback yürütücüsü, Surface sahipliği ve yeniden deneme davranışı birlikte değişmiştir. EQ/STR/cluster/kurulum değişiklikleri de ürün davranışına dokunur; tek bir büyük kabul turuna sığdırılmamalıdır.
6. Daha önce geçen 56 birim/20 emülatör testi OEM kamera/cluster/ses yetkilerini doğrulamaz. Mevcut kamera testi **gerçek CameraDevice kullanmadan thread kapanışını** kontrol eder.

## 2. Kanıt seviyeleri

| Etiket | Anlamı |
|---|---|
| **KOD/APK** | Yerel kaynak, diff veya APK içeriğinde doğrudan görüldü. |
| **AOSP** | Resmi platform sözleşmesi; hedef OEM'in birebir uyguladığı ayrıca doğrulanmalı. |
| **ÇIKARIM** | Kaynaklardan doğan risk/bağımlılık; araçta gerçekleşmiş hata değildir. |
| **BİLİNMİYOR** | Firmware, izin tanımı, OEM servis uygulaması veya fiziksel kanıt eksik. |

**Önemli sınır:** Kamuya açık belgelerle hedef DesaySV/Chery firmware'inin kamera yetki politikası bulunamadı. Bu, böyle bir belgenin hiç olmadığı anlamına gelmez. Repodaki OEM istemci sınıfları, araçta çalışan servis/HAL uygulamasının tam kaynağı değildir.

## 3. Hangi Android katmanını inceliyoruz?

Repoda `com.desaysv.ivi.vdb`, `CarInfoProxy`, açıkça seçilen OEM servis paketleri ve özel VDBus event'leri var. Bu **OEM entegrasyonunu** gösterir; ünitenin standart AAOS CarService/VHAL mimarisini eksiksiz kullandığını göstermez.

AAOS belgeleri, standart sözleşmeyi ve olası riskleri anlamak için kullanıldı. Chery/Omoda/Jaecoo/JAC isimlerinin aynı donanım/firmware sözleşmesini kullandığı kabul edilmedi. JAC ile Jaecoo da aynı marka/platform etiketi olarak ele alınmamalıdır. Araç modeli, yıl, DMC, SoC, Android build'i ve ilgili OEM paket sürümleri eşleşmeden bir modelin sonucu diğerine aktarılmaz.

`minSdk=29`, `targetSdk=28`, `compileSdk=34` bu kaynakta korunmuştur. Compile SDK gerçek cihaz Android sürümü değildir. Düşük targetSdk bazı uyumluluk davranışlarını etkileyebilir; platform imzası, SELinux veya OEM Binder erişiminin yerine geçmez.

## 4. Yetki tek bir “izin verildi” kutusu değildir

| Katman | Neyi belirler? | Bu projede görülen / eksik kanıt |
|---|---|---|
| Runtime izin | Kamera, mikrofon, konum gibi uygulama izinleri | Manifest ve `checkSelfPermission`; bunlar yalnızca ilgili katmanı gösterir. |
| AppOps / özel erişim | Overlay, kullanım erişimi, ayar ve dosya erişiminin ek koşulları | Kurulum scriptinde AppOps var; listeye komut yazılması uygulanmış olduğu anlamına gelmez. |
| İmza / system / privileged / allowlist | Korumalı platform veya OEM yetkileri | Yeni anahtar orijinal ve platform anahtarı değildir; OEM izin tanımı bilinmiyor. |
| Device/profile owner | Belirli cihaz politikası ve yönetim API'leri | Device-owner, genel root/platform imzası değildir. API bazında koşul kontrol edilmeli. |
| OEM Binder / servis politikası | Servise bağlanma, event okuma/yazma, istemci kabulü | İstemci tarafı mevcut; servis tarafı koruma/allowlist kodu mevcut değil. |
| Linux UID / SELinux / HAL | Donanım, servis ve IPC'nin alt katman erişimi | Araçtan UID/domain/denial verisi yok. |
| Kullanıcı / display / güç / kaynak sahipliği | Doğru kullanıcı/ekran, aktif politika ve donanımın başka istemcide olması | Kamera izni açık olsa bile kaynak sahibi veya araç güç politikası erişimi etkileyebilir. |

Android'in privileged izin modeli sistem imajındaki konum ve allowlist kurallarına dayanır; sıradan APK kurulumuyla bunların tamamı elde edilmez. [AOSP privileged permission allowlist](https://source.android.com/docs/core/permissions/perms-allowlist)

`DevicePolicyManager.setPermissionGrantState` runtime izin grant durumuna ilişkindir; buradan tüm signature/privileged/OEM haklarının verilebildiği sonucu çıkarılmaz. API seviyesi ve yönetim türüne bağlı sınırlamalar vardır. [DevicePolicyManager](https://developer.android.com/reference/android/app/admin/DevicePolicyManager#setPermissionGrantState(android.content.ComponentName,%20java.lang.String,%20java.lang.String,%20int))

SELinux, root süreçleri dahil süreçlere ayrı zorunlu erişim kontrolü uygular. Bu nedenle izin reddi her zaman runtime izin diyaloğuyla çözülecek bir hata değildir. [AOSP SELinux](https://source.android.com/docs/security/features/selinux)

ADB'ye erişmek de ayrı bir kapıdır. Geliştiricinin rehberi kilitli firmware'de önce `adbauth.key` akışını tarif eder; bu anahtar APK imzalama anahtarı veya uygulama donanım izni değildir. Bu araştırmada kilit açma/bypass yapılmadı. [Normal kurulum](https://vnoisy.dev/install-guide/02-00-00/), [kilitli kurulum](https://vnoisy.dev/install-guide/adb-kilid/)

### 4.1 Projedeki izinleri nasıl okumalıyız?

| İzin / grup | Bu uygulama için araştırma sorusu |
|---|---|
| CAMERA | AOSP'de dangerous izin; OEM'de hangi kamera sınıfı/ID için yeterli, ek kimlik/politika var mı? |
| RECORD_AUDIO | AOSP'de ses kaydı izni; CameraActivity'nin standart görüntü yolunda kayıt yok. Eski ortak izin isteğinin başka ekranlara etkisi var mı? |
| WRITE_EXTERNAL_STORAGE | Depolama yetkisi; Surface önizlemesini dosyaya kayıtla karıştırmamak gerekir. OEM DVR köprüsünün ek gereği ayrıca belgelenmeli. |
| MANAGE_EXTERNAL_STORAGE / READ_INTERNAL_STORAGE | İsimlerinden her dahili/OEM depolama yoluna erişim sonucu çıkarılmaz; gerçek tanım, AppOps ve hedef yol ayrı kontrol edilir. |
| SYSTEM_ALERT_WINDOW | Overlay erişimi; özel display'de Activity başlatma veya OEM panel sahipliği hakkı değildir. |
| PACKAGE_USAGE_STATS / READ_LOGS | Kullanım/günlük erişimi; `dumpsys` erişiminin veya doğru cluster görevinin kanıtı değildir. |
| QUERY_ALL_PACKAGES | AOSP'de normal paket sorgu izni; runtime kullanıcı onayı veya her pakete yönetim hakkı değildir. |
| WRITE_SETTINGS / WRITE_SECURE_SETTINGS | Farklı sözleşmeler; normal ayar özel erişimi ile korumalı secure ayar yetkisi tek “ayar izni” sayılmaz. |
| READ_SECURE_SETTINGS / READ_SETTINGS / ACCESS_COARSE_UPDATES | Kaynakta geçen eski/özel isimler için cihazdaki gerçek PermissionInfo tanımı bulunmalı; SDK'da görünmüyor diye OEM'den silme önerilmez. |
| BIND_ACCESSIBILITY_SERVICE / BIND_NOTIFICATION_LISTENER_SERVICE | Servisin `android:permission` alanı, çağıranın sahip olması gereken izindir. Uygulamanın bu izni kendisine grant etmesi ile servisin sistemce bağlanması aynı değildir. |
| REQUEST_INSTALL_PACKAGES / DELETE_PACKAGES / INTERACT_ACROSS_USERS_FULL | İstek başlatma, sessiz yönetim ve başka kullanıcıya erişim farklı yetkilerdir; kurulum scriptindeki grant satırları başarı kanıtı değildir. |
| FINE/COARSE/BACKGROUND_LOCATION | İzin durumu ile OEM'in hangi provider'da ne zaman fix ürettiği ayrılmalı. Son bilinen konumu okuma, aktif fix talebi değildir. |
| BLUETOOTH_CONNECT/SCAN ve Wi-Fi izinleri | Android sürümü, hedef SDK, OEM manager ve AppOps birlikte okunmalı; cihazda tanımsız yeni izin otomatik işlev arızası değildir. |
| `ivi.vds.permission.SERVICE` | Platform belgesinden protectionLevel uydurulamaz; OEM izin sahibi ve servis manifesti gerekir. |

Standart CAMERA/RECORD_AUDIO ve QUERY_ALL_PACKAGES sınıflandırmaları ile iki ayar izninin farklılığı için [Android Manifest.permission](https://developer.android.com/reference/android/Manifest.permission) esas alındı. Bu sınıflandırma hedef OEM PermissionInfo dökümünün yerine geçmez. Servis çağıranına ilişkin alanın anlamı [service manifest sözleşmesinde](https://developer.android.com/guide/topics/manifest/service-element#prmsn) açıklanır.

## 5. Kamera cümlesinin ayrıntılı denetimi

### 5.1 Kaynakta kesin görülen

`CameraActivity.initCamera` eski tabanda CAMERA + WRITE_EXTERNAL_STORAGE + RECORD_AUDIO kontrol ediyordu. PR sürümünde CAMERA kontrolü var; verilmemişse bu izin isteniyor. İzin sonucu olumluysa `retryCameras()` çağrılıyor.

İki APK'nın `aapt dump permissions` listeleri karşılaştırıldı: **eklenen veya kaldırılan izin satırı yok.** Kaynak manifest ve `install.sh` diff'i de boş. Dolayısıyla “uygulamanın mikrofon/depolama yetkileri tamamen kaldırıldı” ifadesi yanlış olur. Buna karşılık, kamera ekranının bu diğer izinleri kullanıcıdan isteme yan etkisi kaldırılmıştır. Kurulum scripti uygulanmamış bir kullanıcı akışında bunun başka bir özellik için izin edinme davranışını etkileyip etkilemediği ayrıca değerlendirilmelidir.

Kamera yolu `CameraManager.openCamera` → `TEMPLATE_PREVIEW` → `SurfaceTexture/Surface` → capture session → repeating request kullanıyor. İncelenen bu yolda MediaRecorder, ses kaydı veya dosyaya video yazma yok. Standart API sözleşmesinde `openCamera` CAMERA gerektirir. **Bu, OEM'in ek koşul koymadığının kanıtı değildir.** [CameraManager.openCamera](https://developer.android.com/reference/android/hardware/camera2/CameraManager#openCamera(java.lang.String,%20android.hardware.camera2.CameraDevice.StateCallback,%20android.os.Handler))

**Aynı derecede önemli:** Eski kodda üç iznin birlikte kontrol edilmesi, OEM HAL'in RECORD_AUDIO veya WRITE_EXTERNAL_STORAGE istediğinin kanıtı değildir. Bu eski önkoşulun tarihçesi, çalıştığı firmware ve mümkünse karşılaştırmalı ret kayıtları gerekiyor. Şu aşamada “kesin gereksizdi” de “kesin gerekliydi” de denmemeli.

### 5.2 Araç kameralarında neden CAMERA yeterli olmayabilir?

- Android 11+ AOSP, normal üçüncü taraf uygulamalardan gizlenen **system camera** sınıfını tanımlar. Bu sınıfta CAMERA yanında SYSTEM_CAMERA ve ilgili platform erişim koşulları bulunur. Hedef araçtaki kameraların bu sınıfta olup olmadığı bilinmiyor. Orijinal APK'nın izin listesinde de SYSTEM_CAMERA yok; bu durum ne gerekliliği ne de gereksizliği tek başına kanıtlar. [AOSP System cameras](https://source.android.com/docs/core/camera/system-cameras)
- Araç görüntüsü Camera2 yerine EVS/OEM servis yolu üzerinden sunulabilir. EVS, araç geri/surround görüntüsü için ayrı HAL/mimari tanımlar. Mevcut uygulama kodunda bu önizleme yolu doğrudan Camera2 kullanıyor; hedef OEM'in alttaki köprüsü bilinmiyor. [AOSP EVS](https://source.android.com/docs/automotive/camera/evs)
- Güncel otomotiv Camera2 belgeleri; özel kamera, sistem kullanıcısı, mahremiyet politikası ve paylaşımlı erişim gibi ek durumları ayırıyor. **Güncel belgelerdeki yeni izin/API'ler eski Android 11 ünitede varmış gibi önerilmemeli.** Bu belge bir olasılık haritasıdır; firmware'e doğrudan reçete değildir. [Automotive Camera2 migration](https://source.android.com/docs/automotive/camera/acs/camera2-migration)

Bu yüzden emülatörde CAMERA verilince preview açılması, DMS/OMS/DVR/AVM erişimi kabulü değildir. Kamera ID'sinin listelenmesi de açılabilirlik veya güvenli eşzamanlı kullanım kanıtı değildir.

### 5.3 “OEM eşlemesi korunur” ifadesinin sınırı

Kaynakta şu sabit tablo korunmuş:

| Camera ID | Kaynakta kullanılan adlar |
|---|---|
| `0` | DMS / AVM1 |
| `1` | OMS / AVM2 |
| `2` | DVR / AVM3 |
| `3` | AVM4 |

Bu, **kaynak koddaki etiket/ID tablosunun değişmediği** anlamına gelir. Tablo her firmware'de gerçek fiziksel kamerayı gösteriyor demek değildir. Logical/physical kamera ilişkisi, farklı ID'ler, gizli kameralar, birleştirilmiş AVM görüntüsü ve aynı anda açılabilen kombinasyonlar araçtan kanıt gerektirir.

Kamera callback'lerinde ve `retryCameras()` içinde birden çok ID için açılış denemesi var. Camera2 erişiminde kaynak önceliği ve eşzamanlı kamera sınırları önemlidir. Mevcut istemcinin devre dışı kalması olası bir platform davranışıdır; **hedef araçta DMS/AVM'nin kesildiği gözlenmedi.** Aynı kaynağı paylaşıp paylaşmadıkları bile bilinmiyor. Bu nedenle salt okunur envanter sırasında CameraActivity açılmamalı. [CameraManager erişim/öncelik sözleşmesi](https://developer.android.com/reference/android/hardware/camera2/CameraManager#openCamera(java.lang.String,%20android.hardware.camera2.CameraDevice.StateCallback,%20android.os.Handler))

### 5.4 İzin dışındaki kamera davranış değişiklikleri

| Değişiklik | Olası etki / doğrulama ihtiyacı |
|---|---|
| Activity `onStop` içinde kameraları bırakma | Kaynak tutmayı azaltabilir; OEM çoklu ekranında görünürlük/pencere davranışıyla istenmeyen kapanış olup olmadığı görülmeli. |
| `onStart` ve izin sonrası yeniden deneme | Tekrar erişim sağlar; eski pending callback ve yeni açılışın çakışması incelenmeli. |
| Activity handler'ı üzerinden ortak session executor | Thread havuzu sızıntısını azaltır; OEM callback gecikmesi ve sıra bağımlılığı ayrıca test edilmeli. |
| Surface sahipliği, session/device kapatma | Gerekli temizlik sağlar; geç callback, capture hatası ve OEM kaynak geri dönüşü tam döngüde doğrulanmalı. |
| Kamera başına boyut sorgulama | İlk desteklenen boyutun uygunluğu ve çoklu kamera bant genişliği bilinmiyor; uyumlu boyut listesi performans kabulü değildir. |
| `openingCameras` ile tekrar engelleme | Aynı açılışları azaltır; hata/stop/start sınırlarında doğru oturuma ait temizlendiği gösterilmeli. |

**Kaynak incelemesinden çıkan yarış adayı — araçta doğrulanmış hata değil:** `onStop` pending `openingCameras` kümesini temizliyor; `onStart` yeni açılış isteyebiliyor. Eski `onOpened` daha sonra, Activity tekrar görünürken gelirse yalnızca `destroyed/cameraScreenVisible` kontrolü onu yeni oturumdan ayırmayabilir. Deneme: gecikmiş ilk open → stop → start/yeni open → ilk open callback. Bu sıralama için oturum/generation ayrımı ve callback testi gerekiyor. Şimdi kod değiştirilmedi.

Ek aday: `onConfigureFailed` bean'i release ediyor ama Activity'nin açılış kaydı/listesiyle yeniden deneme bütünlüğü ayrıca incelenmeli. İşlem sonunda “thread kapandı” sonucundan “kamera yeniden açılır” sonucu çıkarılamaz.

**Mevcut testin gerçek kapsamı:** `CameraResourceTest` 12 kez `CameraBean("DMS", null, null)` oluşturup iki kez release eder ve CameraBean thread sayısını kontrol eder. Kamera aygıtı, capture session, OEM izin reddi, DMS/AVM paylaşımı, gerçek Surface veya stop/start callback yarışı sınanmaz.

### 5.5 Kullanılması gereken daha doğru ifade

> Kamera ekranının standart runtime izin isteği CAMERA ile sınırlandırıldı; mikrofon ve depolama izinleri manifestte korunuyor. Önizlemenin hedef OEM'deki ek yetki/servis/HAL koşulları ve diğer araç kameralarıyla birlikte çalışması henüz doğrulanmadı. Kaynakta 0–3 için mevcut DMS/OMS/DVR/AVM etiket tablosu değişmedi; fiziksel eşleşme model/firmware bazında teyit edilmeli.

Bu ifade değişikliği bir rapor düzeltme önerisidir; eski PR açıklaması bu görevde değiştirilmedi.

## 6. OEM VDBus sözleşmesi

Kaynakta en az iki bağlantı yolu var: Android servis paketine `bindService` ve `ServiceManager` üzerinden Binder edinme. `VDConnector` ayrıca uygulamanın system-app bayrağına bakıp boot kontrol broadcast action'ını farklı kuruyor. İstemci türü davranışa etki ediyor.

İstemci tanımlarındaki örnekler:

- `com.desaysv.ivi.vds.carinfo` / CarInfoService
- `com.desaysv.ivi.vds.carlan` / CarLanService
- `com.desaysv.ivi.vds.permission` / PermissionService
- `com.desaysv.ivi.vds.rvc` / RvcService
- `android.hardware.dsp@1.0-service` hedefi

Manifestte `ivi.vds.permission.SERVICE` **isteniyor**, fakat bu izni tanımlayan servis manifesti/protectionLevel burada yok. PermissionService sınıf adının bulunması; herkesin izin verebildiği veya bu uygulamanın kamera yetkisini bu servisten aldığı anlamına gelmez. Event aralığı sabitleri de yetki algoritmasını açıklamaz.

**Bilinmeyenler:** Servis exported/permission bayrakları, imza/UID/paket allowlist'i, kullanıcının UID'si, başlatma sırası, Binder ret kodları, SELinux domain/policy ve donanım sahipliği. Bunlar elde edilmeden “manifestte izin var; OEM entegrasyonu tamam” denmez.

## 7. Paket karşılaştırmasındaki somut fark

| Ölçüt | Kullanıcının orijinal APK'sı | Bizim imzalı APK |
|---|---|---|
| Paket | `com.mapcontrol` | `com.mapcontrol` |
| Sürüm | `1.2.4` / `2` | `1.2.4` / `2` |
| `aapt dump permissions` listesi | Karşılaştırma tabanı | Satır farkı yok |
| `assets/mapcontrol-silent-installer.dex` | **Var: 12.308 bayt** | **Yok** |
| İmza sertifikası SHA-256 başlangıcı | `76c78bfe6fa0…` | `880f026e8836…` |
| Kaynak commit | Bilinmiyor | `a73b921…` |

Orijinal yardımcı DEX SHA-256: `10448f05bb5621102968a81687f5473ad022563d61f69c1665080851ad5e2d90`.

Yerel yeniden kontrol kanıtı: [APK karşılaştırma JSON'u](C:/dev/mapcontrol/app/build/outputs/review/oem-permission-research-evidence.json). Bu dosyada yalnızca APK listeleri/hash'ler var; özel imzalama anahtarı yok.

**İki kurulum birbirinden ayrılmalı:**

1. İlk MapControl APK'sını ADB/web üzerinden kuran dış yükleyici.
2. MapControl kurulduktan sonra içindeki Uygulama Yönetimi'nin başka APK kurması.

Birinci yolun shell kimliğiyle çalışması, ikinci yolun uygulama UID'sine aynı hakları vermez. `app_process` kullanılması da tek başına root/system UID oluşturmaz; hangi süreç kimliğiyle çağrıldığı önemlidir. Mevcut kodun yardımcı uygulamayı başlatması ve standart PackageInstaller'a dönmesi, OEM özel kurulum davranışının birebir korunduğunu kanıtlamaz.

`ApkSessionInstaller` Android 12+ device-owner durumunda user-action isteğini ayarlıyor ve pending user action callback'ini işliyor. **İstek bayrağı kurulum başarısı değildir; final oturum sonucu ve OEM politikası esas alınmalı.** Orijinal DEX statik olarak bulundu; bu görevde çalıştırılmadı, pakete eklenmedi veya lisans/yeniden dağıtım kararı verilmedi.

Kendi anahtarımızla ilk kurulumda paket güncelleme imza çakışması bulunmaması ile OEM işlev yetkilerinin eşit olması farklı konulardır. İkinci konu henüz doğrulanmış değildir.

## 8. Diğer değişikliklerin etki haritası

Buradaki risk seviyesi “kanıtlanmış bozulma” değil, araştırma/QA önceliğidir.

| Alan | Değişen sözleşme / bağımlılık | Etkilenebilecek özellik | Gerekli kanıt |
|---|---|---|---|
| Kamera — yüksek | İzin kapısı + kapanış/yeniden açılış + executor + Surface | OEM kamera erişimi, aynı donanımın paylaşımı | Kamera yetki tanımı, ID/stream metaverisi, kontrollü callback ve OEM kaynak geri dönüşü |
| APK kurulumu — yüksek | Eksik dex'te alternatif yol; session kuyruğu | Sessiz kurulum, yardımcı APK zinciri, onay ekranı | Orijinal yardımcı sözleşmesi, çalışan UID, pending/success/failure zinciri |
| Güç/başlangıç — yüksek | Eksik OEM veri null; UI onayı; OFF sonrası rearm | Ana ekran, karşılama, park kaydı, navigasyon kapanışı | OEM değer/zaman çizgisi, STR ile cold boot ayrımı, ayar matrisi |
| Hafıza sürüş modu — yüksek | Retry süresi ve yeni seçim iptali | Geç bağlanan OEM serviste tercih uygulama | Fake CarInfo ile gönderim sırası; normal OEM kullanım kaydı |
| EQ — yüksek | Başka kontrol sahibinde eski gibi geri almaya çalışmama | Kullanıcının global EQ'sunun etkin kalması | Ürün tercihi + OEM effect sahipliği/yeniden kazanma gözlemi |
| Karşılama sesi — orta/yüksek | Görsel/ses ayrıldı; MediaPlayer temizliği | Audio focus, medya/sesli asistan birlikteliği | Gerçek audio policy, çağrı/navigasyon/medya koşulları |
| Cluster — yüksek | Hedef display parser + 2 s okuma sınırı + background IO | Uygulama açma/taşıma/kapatma, ekranı uyandırma | OEM task formatı, display erişimi, okunamadığında mevcut davranış |
| Hazırlık/boot overlay — orta/yüksek | View sahipliği/animasyon sonu/iptal | Üst katman sırası ve kullanıcı etkileşimi | Gerçek WindowManager olay sırası, eski/yeni View yarışı |
| Launcher yaşam döngüsü — orta/yüksek | Gizli ekranda updater durdurma | Çoklu display görünürlüğü, hava durumu/ölçüm tazeliği | OEM onStart/onStop sırası, farklı display'de görünürlük |
| Google/Yandex navigasyon — orta/yüksek | Parser ve pencereyi üste alma tetikleyicisi | OEM kart bilgileri, başka pencere altında kalma | Kullanılan Maps sürümü/dili, gerçek maskelenmiş bildirim fixture'ı, pencere sırası |
| Hava durumu — orta | Sabit konum yerine son bilinen araç konumu | Veri bulunamaması, önbellek, konum mahremiyeti | Cihazın provider'ları/son fix üretimi, TTL ve yaşam döngüsü |
| Park konumu — orta/yüksek | Açık→OFF geçişi + fix yaş/doğruluk eşiği | Park kaydının atlanması/tekrar etmesi | OEM kapanış penceresi, son fix, ağın ne zaman kapandığı |
| Web yönetimi — orta/yüksek | Oturum anahtarı/Origin, 1 GiB istek sınırı | Eski istemciler, QR, büyük dosya, uzak origin | Desteklenen istemci akışları; geriye uyumsuzlukların kabulü |
| Profil API — orta | Zaman aşımı/yanıt sınırı | Yavaş OEM ağında giriş/kayıt | Kontrollü bağlantı/yanıt fixture'ı; gerçek hesapta spam yapmadan |
| Derleme/telemetri/varlık — düşük/orta | Sentry bağımlılığı/DSN, backup asset hariç tutma | Paket boyutu, native ABI, DSN'li açılış | Release/debug paket farkı, açık telemetri yapılandırması, asıl GLB varlığı |
| Erişilebilirlik — düşük/orta | Settings Activity yolu | OEM erişilebilirlik ekranından uygulamaya geçiş | Paket çözümleme + OEM ayarlar arayüzünde normal açılış |

### Ses için özellikle düzeltilmesi gereken varsayım

AAOS belgeleri Android ses akışları yanında HAL/harici mixer üzerinden yönetilen araç seslerini de ayırır. Bu nedenle emülatörde müziğin çalması, araçta tüm ses yollarının etkilendiğini veya etkilenmediğini kanıtlamaz. [AOSP automotive audio](https://source.android.com/docs/automotive/audio)

Kaynakta EQ hâlâ session `0` ve en yüksek öncelikle DynamicsProcessing oluşturuyor. Genel AudioEffect belgesi session 0 insert efektleri için deprecation notu içerir; **bu not tek başına araç için tasarlanmış global EQ özelliğini kaldırma gerekçesi değildir.** OEM routing/efekt sözleşmesi ve ürün kararı gerekir. [AudioEffect](https://developer.android.com/reference/android/media/audiofx/AudioEffect)

PR'da keeper artık `hasControl()` false iken sürekli yıkıp geri almıyor. Bu olası çatışmayı azaltabilir ama “kullanıcı EQ'su her zaman yeniden uygulanır” beklentisini değiştirir. Maintainer bunu bug düzeltmesi mi, politika değişikliği mi istediğini belirtmelidir. Pasif mikrofon izin kontrolü kayıt başlatmaz; bu da sesli asistan arızasının kök nedeninin bulunduğunu göstermez.

### Güç ve display için sınır

AAOS güç belgelerinde STR, cold boot ve geçiş koordinasyonu ayrıdır. Projenin OEM `0/1/2` değerlerinin standart AAOS güç state numaraları olduğu varsayılmaz. Bunların korunması, callback sırası ve yaşam döngüsünün doğru olduğunun tek başına kanıtı değildir. [AOSP power management](https://source.android.com/docs/automotive/power/power)

`setLaunchDisplayId` display erişimi ve sistem yeteneklerine bağlıdır. Overlay izni, cluster Activity açma/taşıma yetkisine eşit değildir. Kaynakta yardımcı hâlâ OEM VDBus akışını kullanıyor; hangi display'in cluster olduğu fiziksel cihazdan doğrulanmalıdır. [ActivityOptions.setLaunchDisplayId](https://developer.android.com/reference/android/app/ActivityOptions#setLaunchDisplayId(int))

## 9. Neden mevcut testler bu soruları kapatmıyor?

| Test/kanıt | Gösterdiği | Göstermediği |
|---|---|---|
| 56 birim testi | Fixture/politika/parser kararları | OEM servis kabulü ve araç davranışı |
| 20 Android entegrasyon testi | API 34 emülatörde soket/lifecycle/fake window/kapalı EQ kontrolleri | OEM HAL, DMS/AVM, gerçek STR ve ses routing |
| Kamera thread testi | Null device ile thread cleanup | CameraActivity açılışı, izin kombinasyonları, capture session, callback yarışı |
| Kapalı EQ testleri | Kapalı durumda effect oluşturulmaması | Etkin EQ'da OEM kontrol sahipliği/route değişimi |
| Display parser testleri | Belirtilmiş dump fixture'ını doğru ayırma | Hedef firmware'in dump formatı/erişim hakkı |
| İmza doğrulaması | APK'nın kendi sertifikasıyla tutarlı olması | OEM/platform sertifikası veya servis allowlist'i |
| Aynı manifest izin listesi | İstenen izin metninin aynı olması | Gerçekte verilen hakların, UID'nin ve yardımcı dosyaların aynı olması |

İzin tanılama ekranı da tam bir yetki matrisi değildir. `GrantedPermissionStatus` ağırlıkla PM/AppOps durumlarını okur; OEM servis başarıları ve SYSTEM_CAMERA gibi özel erişimlerin bütünlüğünü göstermez. `PermissionProbe.probeCamera` ID sayısını okuyup CAMERA verildiyse SKIP döndürür; gerçek kamera açmaz. Bu doğru sınır, kullanıcıya “donanım testi geçti” diye çevrilmemelidir.

## 10. Önce edinilmesi gereken bilgiler

| Soru | Kaynak / salt okunur kanıt | Şimdiki durum |
|---|---|---|
| Hedef ünite tam olarak hangisi? | Model/yıl/DMC/build/SoC/OEM paket sürümleri | Kullanıcının hedef cihazı için eksik |
| AAOS katmanı mı, özel AOSP/OEM servis mi? | Feature/library/servis adları + paket metaverisi | Repoda OEM istemci entegrasyonu var; cihaz mimarisi kesin değil |
| `ivi.vds.permission.SERVICE` kim tarafından/hangi protectionLevel ile tanımlanmış? | İzni tanımlayan kurulu paket manifesti / hedefli package dump | Eksik |
| Orijinal paket hangi UID/rol/allowlist ile çalışıyor? | Kurulu paketin UID, flags, grant, appops ve DPM durumu | Araçta orijinal kurulu değil; geliştiricinin çalışan cihaz örneği gerekli |
| Camera2, system camera, EVS veya özel köprü? | Hedefli kamera servis metaverisi ve OEM uygulama/SDK belgesi | Eksik |
| DMS/OMS/DVR/AVM gerçek ID ve eşzamanlı kombinasyonu? | HAL/stream metaverisi ve geliştiricinin çalışan eşleme kaydı | Kaynakta yalnızca 0–3 etiketleri var |
| Mikrofon/depolama önkoşulu gerçekten OEM gereği mi? | Eski kontrolün tarihçesi; çalıştığı firmware; kontrollü permission/callback matrisi | Eksik; iki yönde de kesin hüküm yok |
| OEM yardımcı DEX'in sözleşmesi/lisansı ve uygulama UID'sinde davranışı? | Geliştirici kaynak/sözleşmesi ve izinli izole test | Binary varlığı kanıtlandı; eşdeğerlik kanıtlanmadı |
| Güç/ekran geçiş sırası? | Kullanıcının normal kullanımındaki maskelenmiş kısa zaman çizgisi | Eksik |
| Ses kontrolünü kim sahipleniyor? | Hedefli audio/effect metaverisi + OEM policy belgesi | Eksik |

ADB kapalıysa bu veriler bu bilgisayardan elde edilmiş gibi gösterilmez. Önceki salt okunur inceleme istemi erişim açıldıktan sonra kullanılabilir. İlk turda kamera/AVM ekranı açma, key/event dinleme, servis `call`, `setprop`, `pm grant`, `appops set`, install/uninstall veya güç olayı tetikleme yoktur. Salt okunur veri de araç/personal kimlikleri içerebilir; paylaşılacak rapor maskelenmelidir.

## 11. Daha küçük değişikliklerle ilerlemek için önerilen çalışma düzeni

Bu bölüm gelecekteki plan önerisidir; branch/PR ayırma işlemi yapılmadı.

1. **Önce sözleşme notu:** Çalıştığı bilinen model/firmware, gerekli kurulum ve izinler, OEM bağımlılıkları, korunması istenen davranışlar.
2. **Tek sorun / tek davranış değişikliği:** İzin isteği sadeleştirme, kamera resource cleanup, callback yarışı ve OEM yeniden açılışını tek “kamera düzeltmesi” olarak paketlememe.
3. **Ürün tercihini bug'dan ayır:** EQ başka sahibine saygı, web istemci kimlik doğrulaması, yükleme sınırı, hava durumu konumu ve açılış odağı açıkça kabul edilmiş tercihler olmalı.
4. **Önce deterministik repro:** Eski/yeni aynı fixture, hangi callback/yanıtın hatayı ürettiği, komşu özelliklerden en az bir negatif regresyon senaryosu.
5. **Kanıt katmanlarını ayır:** JVM → emülatör → desteklenen OEM test ünitesi. Bir katmanın geçmesi üst katmanı otomatik kapatmaz.
6. **Sınırlı kabul:** İzin/kurulum, güç, ses ve cluster değişikliklerini ayrı değerlendirme grupları yap; ortak bağımlılığı varsa açıkça not et.
7. **Tamamlama dili:** “Kodda şu değişti”, “şu test geçti”, “şu modelde doğrulandı”, “şu alan bilinmiyor” ayrı yazılsın.

**Önerilen ilk odak:** Kamera izin sözleşmesi + orijinal yardımcı DEX/paketleme sözleşmesi. Bu iki bilgi netleşmeden daha fazla OEM davranışı sadeleştirilmemeli. Kamera cleanup'ının niyeti doğru olabilir; mevcut kanıtla “tüm araç kameraları uyumlu” denemez.

## 12. Yerel kaynak işaretleri

Satırlar incelenen `a73b921` revizyonuna aittir:

- [Manifest: OEM servis izni](C:/dev/mapcontrol/app/src/main/AndroidManifest.xml:8), [mikrofon/kamera](C:/dev/mapcontrol/app/src/main/AndroidManifest.xml:29), [depolama](C:/dev/mapcontrol/app/src/main/AndroidManifest.xml:69).
- [CameraActivity: ID tablosu ve izin kapısı](C:/dev/mapcontrol/app/src/main/java/com/mapcontrol/ui/activity/CameraActivity.java:222), [session yürütücüsü](C:/dev/mapcontrol/app/src/main/java/com/mapcontrol/ui/activity/CameraActivity.java:214), [stop/start ve retry](C:/dev/mapcontrol/app/src/main/java/com/mapcontrol/ui/activity/CameraActivity.java:359), [geç onOpened kontrolü](C:/dev/mapcontrol/app/src/main/java/com/mapcontrol/ui/activity/CameraActivity.java:131).
- [CameraBean: capture ve cleanup](C:/dev/mapcontrol/app/src/main/java/com/mapcontrol/bean/CameraBean.java:65), [kamerasız thread testi](C:/dev/mapcontrol/app/src/androidTest/java/com/mapcontrol/bean/CameraResourceTest.java:10).
- [VDServiceDef: OEM servis hedefleri](C:/dev/mapcontrol/app/src/main/java/com/desaysv/ivi/vdb/client/bind/VDServiceDef.java:44), [VDConnector: system-app ayrımı](C:/dev/mapcontrol/app/src/main/java/com/desaysv/ivi/vdb/client/bind/VDConnector.java:376), [Binder bağlama](C:/dev/mapcontrol/app/src/main/java/com/desaysv/ivi/vdb/client/bind/VDConnector.java:452).
- [SilentDexInstaller: uygulama içi yardımcı başlatma](C:/dev/mapcontrol/app/src/main/java/com/mapcontrol/util/SilentDexInstaller.java:39), [PackageInstaller: device-owner ve user-action isteği](C:/dev/mapcontrol/app/src/main/java/com/mapcontrol/util/ApkSessionInstaller.java:85).
- [AudioEqualizerController: sahiplik kontrolü](C:/dev/mapcontrol/app/src/main/java/com/mapcontrol/audio/AudioEqualizerController.java:154), [global effect oluşturma](C:/dev/mapcontrol/app/src/main/java/com/mapcontrol/audio/AudioEqualizerController.java:245).
- [PermissionProbe: pasif mikrofon](C:/dev/mapcontrol/app/src/main/java/com/mapcontrol/util/PermissionProbe.java:374), [kamera metaverisi kontrolü](C:/dev/mapcontrol/app/src/main/java/com/mapcontrol/util/PermissionProbe.java:442).
- [MainActivity: görünürlük ve güncelleyiciler](C:/dev/mapcontrol/app/src/main/java/com/mapcontrol/ui/activity/MainActivity.java:1775), [ClusterDisplayManager](C:/dev/mapcontrol/app/src/main/java/com/mapcontrol/manager/ClusterDisplayManager.java:254), [VehicleLocationProvider](C:/dev/mapcontrol/app/src/main/java/com/mapcontrol/vehicle/VehicleLocationProvider.java:14).

## 13. Kaynak ve erişim sınırlamaları

Bağlantılar 19 Ekim 2026'da okundu. Resmi AOSP/API belgeleri platform çerçevesini açıklıyor; DesaySV hedef firmware'ine ait test sonucu değildir. Yeni Android sürümlerinin özellikleri geriye doğru varsayılmadı. Android 15 signature allowlist belgesi de incelendi; eski hedeflerde otomatik uygulanmadığı için çözüm reçetesi yapılmadı.

Birleştirilmiş `llms-full.txt` web okuma aracıyla açılamadı; ilgili normal ve kilitli kurulum sayfaları ayrı ayrı okundu. Genel README'deki eski Android 11/3D performans notları güncel APK veya kullanıcının aracı için ölçüm sayılmadı. UAPI sayfası erişilemedi ve bulguların dayanağı olarak kullanılmadı.

Bu rapor bilgi tabanının ilk sürümüdür. Fiziksel/OEM kanıt gelmeden kapatılamayan sorular açıkça bırakıldı; bu görevde hiçbir yeni uygulama düzeltmesi, commit, push veya PR güncellemesi yapılmadı.
~~~~

<a id="legacy-pr-3-qa-checklist"></a>

## pr-3-qa-checklist.md

~~~~markdown
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
~~~~

<a id="legacy-shared-header-review"></a>

## shared-header-review.md

~~~~markdown
# Ortak üst durum şeridi — 21 Ekim 2026

Tüm ana menüler ve tam ekran ana sayfa aynı başlığı kullanır. Solda aktif menünün adı, sağda sırasıyla Wi-Fi, Mod, Telefon ve Yansıtma göstergeleri bulunur. Araç ekranındaki başlık yüksekliği 89 dp; menü değişimi ve durum metni bunu değiştirmez. Dar pencerede aynı içerik yazılar küçülmeden alt satıra/iki sütuna geçer.

## Durum kaynakları

- Wi-Fi: WifiManager radyo durumu ve ConnectivityManager Wi-Fi taşıyıcısı. Ağa bağlı olmak internet erişiminden ayrı değerlendirilir; SSID/konum izni gerektirmez. Kapalı, bağlı değil, bağlı ve bilinmiyor ayrılır.
- Mod: paylaşılan VehicleMetricsRepository kaydı ve bağlantı durumu. Açılış tercihi mevcut mod gibi gösterilmez. Veri yoksa veya bağlantı koparsa çizgi görünür; bilinen modun kendi rengi kullanılır.
- Telefon: WebServerManager.isRunning(). Durum sunucunun çalışmasını gösterir; bir telefonun bağlandığı iddia edilmez.
- Yansıtma: mevcut ClusterNavigationState ve mevcut servis/manager geri bildirimleri.

Göstergelere dokunmak ilgili menüyü açar, özelliği açıp kapatmaz. Başlık adları tüm gezinme yollarında tek eşlemeden gelir. Durumlar görünür Activity süresince izlenir. Araç modu OEM olaylarıyla güncellenir; ek olarak görünürken iki saniyede bir gerçek mod arka planda okunur. Bu okuma araç servisinin geç hazır olmasını ve kaçırılan mod bildirimlerini karşılar. Bağlantı değişiminde abonelikler yenilenir; periyodik mod kontrolü tüm araç metriklerini tekrar okumaz. Yeni araç komutları veya Wi-Fi taramaları eklenmedi.

Uygulamalar sayfasının yenileme, indirilenler, sunucu/yerel seçim ve silme işlemleri sayfanın içine taşındı. Wi-Fi sayfasındaki sabit ve güncellenmeyen bağlantı yazısı kaldırıldı; bağlantı bilgisi üst şeritte gösterilir. Tam ekran ana sayfanın hızlı erişim içeriği kaydırılabilir; alt kısayollara yazılar küçültülmeden ulaşılır ve üst şerit sabit kalır.

## Doğrulama

- 65 birim testi başarılı.
- İlgili 18 Android testi başarılı: dört ortak başlık, iki canlı mod/bağlantı, bir gerçek Activity gezinme, dört kompakt kontrol, iki mod rozeti, üç mod seçeneği ve iki Sport animasyonu testi. Canlı bağlantı testlerinde kontrollü araç servisi kullanılır; gerçek donanım sonucu olarak değerlendirilmez.
- 1920×720 / 160 dpi Android 11 inceleme emülatöründe dokuz menünün başlık adı ve dört gösterge konumu doğrulandı.
- Gerçek yerel sunucu başlatıldı, farklı menüde Etkin durumu görüldü; tekrar durdurulunca Durdu durumu doğrulandı. Wi-Fi radyo kapatma/açma değişimleri başka menüde doğrulandı.
- Tam ekran ana sayfa, açık/koyu tema, durum kısayolları, ana ekrana geri dönüş ve alt hızlı erişim kısayollarından Ayarlar'a erişim kontrol edildi.
- Fiziksel araç testi yapılmadı. Canlı sürüş modu ve OEM yansıtma işlevleri donanım üzerinde ayrıca kontrol edilmelidir.

Önizlemeler: [Sürüş modu](ui-previews/shared-header/04-surus-modu.png), [Uygulamalar](ui-previews/shared-header/02-uygulamalar.png), [sunucu başka menüde etkin](ui-previews/shared-header/10-sunucu-etkin.png), [son ana ekran](ui-previews/shared-header/14-ana-ekran-son.png), [alt kısayollar](ui-previews/shared-header/15-kisayollar-asagi.png).


## Sabit üst şerit ve mod okuma düzeltmesi

Kategori geçiş örtüsü artık yalnızca tabContentArea üzerinde çalışır. Sol menü, durum göstergeleri ve ortak üst şerit bu animasyona katılmaz. Ana sayfa kısayolları ve ana sayfaya dönüş de aynı içerik geçişini kullanır. Tam ekran modunun açılıp kapanması pencere düzenini değiştirdiği için kendi geçişini korur.

Menü değişiminde başlık ve gerektiğinde ana sayfaya dönüş düğmesi güncellenir. Aynı pencere düzeni yeniden uygulanmaz, durum göstergeleri topluca bağlanmaz. Bir gösterge değiştiğinde sadece o gösterge bağlanır. Tema değişimi üst şeridi yeniden oluşturmadan mevcut görünüme uygulanır.

Araç servisi için ilk açılıştaki sınırlı bekleyiş kaldırıldı. Bağlantı bildirimi geldiğinde okuma yeniden istenir; servis henüz hazır değilse görünür üst şeridin periyodik kontrolü yeniden dener. Aynı anda gelen yenilemeler biriktirilmez. Abonelik ve okuma işlemleri aynı arka plan işçisinde yürür, önbellek erişimi eşzamanlanır. Servis okunabilir mod döndürmezse eski mod veya açılış tercihi kullanılmaz.

Yeni kontroller: geç bağlantı, olay gelmeden ECO → SPORT değişimi, OEM mod olayı, bağlantı kopması/yeniden abonelik, boş mod cevabı, dinleme sonlandırma ve ana iş parçacığında araç I/O yapılmaması. Gerçek Activity testi geçiş örtüsünün içerik alanında olduğunu, üst şerit nesnesinin ve mod göstergesinin menü değişiminde korunduğunu doğrular.

1920×720 emülatörde Wi-Fi, Sürüş modu ve Araç bilgileri ekranlarında dört göstergenin konumu ve aktif başlık kontrol edildi. Emülatörde araç servisi olmadığı için bu önizlemelerde mod çizgi görünür.

Yeni önizlemeler: [Wi-Fi](ui-previews/persistent-header/01-wifi.png), [Sürüş modu](ui-previews/persistent-header/04-surus-modu.png), [Araç bilgileri](ui-previews/persistent-header/08-arac-bilgileri.png). [Android test çıktısı](ui-previews/persistent-header/android-tests.txt).
~~~~

<a id="legacy-sport-animation-feature"></a>

## sport-animation-feature.md

~~~~markdown
# Sport animasyonunu gizle — 21 Ekim 2026

Sürüş modu sayfasına varsayılanı kapalı, kalıcı “Sport animasyonunu gizle” seçeneği eklendi. MapControl arka plan servisi çalışırken konsoldan veya başka bir yoldan gerçekleşen gerçek sürüş modu geçişlerini izler. Kayıtlı açılış tercihi veya MapControl mod düğmesinin seçili görünmesi tetikleyici olarak kullanılmaz.

## Davranış

- Seçenek kapalıyken ek araç modu okuması veya OEM yayını yapılmaz.
- Açıldığında ilk geçerli okuma yalnız başlangıç durumunu belirler. Zaten Sport'ta başlamak veya Sport'ta yeniden bağlanmak işlem başlatmaz.
- Bilinen başka bir moddan Sport'a geçildikten sonra en az 600 ms beklenir. Sonraki taze okumada hâlâ Sport ise yalnız bir pencere kapatma isteği gönderilir.
- Aynı Sport oturumunda tekrar gönderilmez; sürekli yayın veya yeniden deneme döngüsü yoktur.
- Sport'tan çıkmak, bağlantının kesilmesi, bilinmeyen veri, seçeneğin kapatılması ya da servisin kapanması bekleyen işlemi iptal eder. Hızlı kapat/aç da önceki bekleyen işlemi devralmaz.
- Mevcut servis çalışanında 500 ms aralıklı kontrol kullanılır; bekleme dahil normal koşullarda ilk geçiş örneğinden yaklaşık 1 saniye sonra istek oluşur. Yeni bir servis veya iş parçacığı oluşturulmaz.
- İstek yalnız `com.desaysv.vehiclesetting` paketine `com.desaysv.systemui.ACTION_DIALOG_CLOSE` eylemiyle gider. Araç modunu yazan bir API çağrısı, paket devre dışı bırakma veya kaldırma işlemi yoktur.
- Kayıtlarda yalnız “kapatma isteği gönderildi” yazılır; OEM penceresinin gerçekten kapandığı iddia edilmez.

## Sınırlar

Bu eylem aynı OEM paketindeki başka pencerelerde de kullanılıyor. Sayfada bu yan etki açıkça belirtilir. Fiziksel araçta pencere sahibi ve istek sonucu henüz doğrulanmadığı için özellik varsayılan olarak kapalıdır. İlk deneme araç park halindeyken, seçenek açıldıktan sonra normal moddan Sport'a geçilerek yapılmalıdır. Sport'ta kalan bir pencereyi anında kapatma düğmesi eklenmedi.

## Doğrulama

65 birim testi ve ilgili 14 Android testi başarılı. Dokuz yeni politika testi: başlangıç Sport durumu, bilinen modlardan Sport'a geçiş, bekleme eşiği, yinelenen veriler, Sport'tan erken çıkış, bağlantı kaybı, bilinmeyen değerler, kapatma ve yeniden etkinleştirme, yeni Sport oturumları. Android testleri gerçek araç yerine kontrollü mod örnekleriyle tam paket/eylem eşleşmesini, tek yayını, iptali, varsayılanı ve seçimin kalıcılığını doğrular. Önceki başlık, kompakt kontroller ve mod görünümü testleri de geçti.

1920×720 Android 11 inceleme emülatöründe yeni dairesel seçicinin görünümü ve açma/kapatma kontrol edildi. Normal uygulama kimliği `com.mapcontrol`; inceleme kimliği yalnız emülatör için kullanıldı.

Önizleme: [Kapalı](ui-previews/sport-animation/sport-animasyonu-kapali.png), [Etkin](ui-previews/sport-animation/sport-animasyonu-etkin.png).
~~~~

<a id="legacy-ui-readability-review"></a>

## ui-readability-review.md

~~~~markdown
# Kompakt arayüz düzenlemesi — 21 Ekim 2026

Güncel düzen, ilk okunabilirlik çalışmasından sonra kullanıcının geri bildirimine göre yenilendi.

## Görünüm

- Ayarlar ve gösterge seçenekleri geniş araç ekranında iki sütun. Dar alanda kart sırası korunarak tek sütuna geçer.
- Büyük Açık/Kapalı veya Evet/Hayır düğmeleri yerine radyo görünümünde yuvarlak seçimler. İkinci dokunuş seçimi kaldırır; izin reddi seçimi geri alır. Dil ve başlatma zamanı gerçek, birbirini dışlayan radyo seçenekleridir.
- Yazı boyutu kaynakları önceki sürüme göre tam %25 artırıldı: 18 → 22,5 sp; 20 → 25 sp; 22 → 27,5 sp. Büyük saat ve ölçüm yazıları da aynı oranda büyüdü.
- Ortak işlem düğmeleri 64 yerine 48 dp; gereksiz tam satır genişlikleri kaldırıldı.
- Sürücü yardımları yatay, iki sütunlu kompakt seçim kartlarına dönüştürüldü. Seçimin açılışta kapatma tercihi olduğu açıklandı. Mevcut OEM değerleri, işlem akışları ve güvenlik onayları korundu.
- Gösterge kartı konum/boyut kaydırıcıları isteğe bağlı açılır bölümlerde.

## Araç bilgileri

İlk görünümde altı büyük kart: yakıt, kalan menzil, ortalama tüketim, toplam kilometre, akü voltajı ve motor suyu sıcaklığı. Diğer 13 ölçüm ve teknik kontrol bağlantısı açılır bölümde korunur. Kartların View nesneleri veri geldikçe yeniden oluşturulmaz; yalnız değerler güncellenir. Bağlantı yokken ölçüm yerine çizgi görünür. OEM servisleri ve ham birim dönüşümleri değiştirilmedi.

## Doğrulama

- Normal com.mapcontrol debug APK derlemesi başarılı.
- 56 birim testi başarılı, sıfır başarısızlık.
- Android 11/API 30 emülatöründe 4 yeni etkileşim/yerleşim testi başarılı: seçim kaldırma, izin reddinde geri alma, dil seçiminin karşılıklı dışlanması, dar ekrana geçerken kart sırasının korunması.
- 1920×720/160 dpi ekranında Ayarlar ve Araç bilgileri açık/koyu temada, gösterge seçenekleri, sürücü yardımları ve ana sayfa görsel olarak kontrol edildi.
- Ayar başlığına dokunarak seçme/kaldırma ve Diğer ölçümler bölümünün açılıp kapanması elle doğrulandı.
- Geçici com.mapcontrol.uireview test kimliği kullanıldı; mevcut emülatör uygulaması kaldırılmadı. Nihai APK kimliği com.mapcontrol olarak doğrulandı.

Önizlemeler: [Ayarlar](ui-previews/settings.png), [Araç bilgileri](ui-previews/vehicle.png). Emülatörde canlı araç servisi bulunmadığı için sayısal değerler gösterilmez. Fiziksel araç okunabilirliği, canlı ölçümler ve OEM donanım işlevleri bu arayüz doğrulamasının kapsamı dışındadır.

## Sürüş modu renkleri

Eco yeşil, Normal mavi, Sport kırmızı, Snow buz mavisi, Mud çamur kahverengisi, Offroad zeytin/toprak yeşili, Sand kum rengi. Mod kartlarının simge, çerçeve, alt çizgi ve seçili dolgusu aynı ortak renk eşlemesini kullanır; açık/koyu tema renkleri ayrı kaynaklardan gelir.

Ana ekranın saat bölümüne mevcut modun adını ve rengini taşıyan bir etiket eklendi. Kaynak, kayıtlı açılış tercihi değil VehicleMetricsSnapshot.driveMode ve bağlantı durumudur. Veri eksikse, bilinmeyen mod gelirse veya bağlantı koparsa nötr etiket gösterilir. Tema değişimi son bilinen geçerli modu koruyup rengini yeniler.

Doğrulama: debug APK ve mevcut 56 birim testi başarılı. İki yeni Android testi, gerçek modun kayıtlı tercihten ayrılmasını; bağlantı kopması ve bilinmeyen mod durumunu; yedi ayrı renk ve mod geçişlerini; tema değişimini doğruladı. Testler ağ veya OEM donanımı başlatmaz.

Önizlemeler: [Mod renkleri](ui-previews/drive-mode-colors.png), [örnek verili ana ekran etiketi](ui-previews/drive-mode-home-sample.png). İkinci görsel canlı araç verisi içermez ve üzerinde örnek veri etiketi vardır. Fiziksel araç kabulü yapılmadı.


## İsteğe bağlı 4x4 modları ve kısa ana ekran etiketi

Ana ekran etiketi artık yalnız ECO, NORMAL, SPORT gibi mod adını gösterir; veri yoksa nötr çizgi görünür. Erişilebilirlik açıklaması sürüş modu bilgisini korur.

Sürüş Modları başlığının yanına radyo görünümünde “4x4 modlar” seçeneği eklendi. Varsayılan görünüm Eco, Normal ve Sport; seçildiğinde Snow, Mud, Offroad ve Sand aynı satıra eklenir. Görünüm tercihi hatırlanır. Seçenekleri gizlemek kayıtlı modu değiştirmez veya araca komut göndermez. Eski Hiçbiri kartının işlevi başlık satırındaki kompakt “Seçimi kaldır” işleminde korunur.

Doğrulama: 56 birim testi ve 4 ilgili Android testi başarılı. Varsayılan üç mod, 4x4 görünümünün hatırlanması, gizlenen seçimin korunması, otomatik seçimi kaldırma ve yalnız mod adını gösteren ana ekran etiketi doğrulandı. 1920×720 emülatörde her iki görünüm kontrol edildi.

Güncel önizlemeler: [Standart modlar](ui-previews/drive-mode-standard.png), [4x4 modlar](ui-previews/drive-mode-4x4.png), [örnek verili kısa ana ekran etiketi](ui-previews/home-mode-short.png).
~~~~

<a id="legacy-welcome-readability-review"></a>

## welcome-readability-review.md

~~~~markdown
# Karşılama menüsü — 21 Ekim 2026

Karşılama menüsünün eski 13–16 sp metinleri ortak 22,5 / 25 / 30 sp yazı ölçülerine taşındı. Geniş araç ekranında solda ses, sağda giriş tasarımı kartı bulunur. 1920×720 ve normal yazı ölçeğinde temel kontroller kaydırmadan görünür. Mevcut cam kart yüzeyleri, renkler ve yuvarlak seçim göstergeleri kullanılır.

Ses seçme ve Çal/Durdur kontrolleri yan yana. Dosya adı iki satıra kadar görünür; uzun ad kesilerek satırın ekranı büyütmesi önlenir. Otomatik çalma tek yuvarlak seçimle değişir. USB listesi aynı kartta korunur. Uzun dosya ekleme yönergesi büyük metinli yardım penceresinde açılır. Ses seçili değilken Çal pasiftir.

Giriş tasarımları iki sütunlu, birbirini dışlayan radyo seçenekleriyle sunulur. Seçili tasarımın açıklaması yalnızca bir kez gösterilir. İsteğe bağlı karşılama yazısı ve Önizle aynı satırda bulunur. Kapalı tasarımda Önizle pasiftir. Kayıt anahtarları ve mevcut açılış animasyonları korunur.

Paylaşılan FeatureGridLayout için isteğe bağlı eşik eklendi: mevcut ekranların varsayılan 840 dp davranışı değişmez; tasarım seçenekleri kendi 520 dp genişliğinde iki sütun kullanır. Dar pencerede ana kartlar ve gerektiğinde seçenekler alt alta geçer; ortak kaydırma ile tüm kontrollere ulaşılır. Büyük sistem yazısında üst şerit hücre yüksekliği yazı ölçeğine uyum sağlar; kategori geçişlerinde sabit kalır.

## Doğrulama

- Debug derlemesi başarılı; 65 birim testi ve mevcut sekiz ortak başlık/kompakt kontrol Android testi başarılı.
- Ayrı Android 11 emülatöründe 1920×720 görünüm, açık/koyu tema, yüzde 130 sistem yazısı ve 1024×720 dar pencere incelendi. Dar ekranda giriş tasarımı, yazı ve önizlemeye kaydırılarak ulaşıldı.
- Tasarımlar arasında geçiş, tekli seçim, kapalıyken önizleme pasifliği, otomatik çalma seçimi ve karşılama yazısının menü değişiminden sonra korunması doğrulandı.
- Yardım penceresi ve kayıtlı tasarımın tam ekran önizlemesi açıldı.
- Kayıtlı sessiz WAV dosyasıyla Çal → Durdur → Çal geri bildirimi doğrulandı. Dosya seçicinin açılması kontrol edildi; uçtan uca USB aktarımı / dosya içe aktarma bu turda yeniden doğrulanmadı.
- Araç bağlantısı gerektiren bir davranış değiştirilmedi. Kontroller fiziksel araç yerine ayrı emülatörde yapıldı.

## Önizlemeler

- [Koyu tema](ui-previews/welcome/01-koyu.png)
- [Açık tema](ui-previews/welcome/05-acik.png)
- [Büyük sistem yazısı](ui-previews/welcome/06-buyuk-yazi.png)
- [Dar ekran](ui-previews/welcome/07-dar-ekran.png)
- [Dar ekranda tasarım bölümü](ui-previews/welcome/08-dar-ekran-alt.png)
- [Android test çıktısı](ui-previews/welcome/android-tests.txt)
~~~~
