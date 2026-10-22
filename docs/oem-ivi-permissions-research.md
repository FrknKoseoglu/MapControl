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
