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
