# Super Mod animasyon gizleme düzeltmesi — 23 Ekim 2026

## Değişen davranış

Önceki uygulama `MapControlService` içinde 500 ms arayla mod okuyor, sonradan gözlediği Sport geçişinden sonra tek genel OEM kapatma broadcast'i gönderiyordu. Seçenek zaten Sport modundayken açıldığında işlem yapmıyor; pencerenin oluşumu veya kapanışı doğrulanmıyordu. Aynı paketin başka diyalogları da bu eylemi dinliyordu.

Yeni uygulama mevcut `GlobalBackService` erişilebilirlik bağlantısında çalışır. `hideSportAnimation` anahtarı korunur, varsayılan kapalıdır. Özellik açılırken, servis bağlanırken ve ilgili pencere/içerik olayında kısa süreli tarama yapılır. MapControl ana arka plan servisinin mod geçişi yakalamasına ihtiyaç duymaz.

Yalnız odaklı OEM penceresinde, kendi geri düğmesinin aynı parent/window alanında şu üç kaynak kimliği birlikte bulunursa aday kabul edilir:

- `com.desaysv.vehiclesetting:id/btn_back`
- `com.desaysv.vehiclesetting:id/view_video`
- `com.desaysv.vehiclesetting:id/layout_items`

Geri düğmesi görünür, etkin ve tıklanabilir olmalı. Bu kimlikler arşiv `dialog_drive_mode.xml` ve VideoContainer DEX referanslarıyla eşleştirildi. Genel BACK, koordinat tıklaması veya paket kapsamlı kapatma broadcast'i kullanılmaz.

Aday bulunduğunda mod arka plan işçisinde salt okunur `0x50002 / ID_DRIVE_MODE=4` cevabından doğrulanır. `driveModeSetting` tercihi gerçek mod olarak kullanılmaz; CarInfoProxy'nin ayrı bağlantı bayrağı geçerli cevabı engellemez. Yanlış kanal/ID, eksik veya geçersiz değer kapatma yetkisi vermez.

Gerçek mod **Sport (2)** ise pencere yeniden bulunur, window ID'nin değişmediği kontrol edilir ve yalnız kendi geri düğmesinde `ACTION_CLICK` uygulanır. Böylece arşivdeki `DriveModeDialog$1.btnBackClick -> dismiss()` yolu kullanılır. İncelenen kapanış zincirinde Normal mod yazımı yoktur.

Pencere geç oluşursa 200 ms aralıklarla en fazla 2 saniyelik tarama yapılır; aynı pencereye en fazla 3 tıklama girişimi uygulanır. Kapatma kabulünden sonra yeni tarama görünür hedefin kalıp kalmadığını kontrol eder; log, `ACTION_CLICK` kabulünü bildirir ve gerçek araçta kapanış garantisi iddia etmez. Seçeneğin kapanması, hızlı kapat/aç veya servisin sonlandırılması bekleyen eski okumaları geçersiz kılar. Asenkron okumadan sonra eski düğüm saklanarak tıklanmaz.

## Kullanım ve yetki

**MapControl erişilebilirlik hizmeti açık olmalı.** Seçenek etkinleştirildiğinde bu izin yoksa açıklama gösterilir ve erişilebilirlik ayarları açılır. Seçenek açık kalır; hizmet bağlanınca mevcut pencere de taranır.

Bu bir **pencere görünürken hızlı kapatma/gizleme** düzeltmesidir. OEM süreçteki `show(Context)` metodunu önceden engellemez; ilk video karesinin hiç görünmemesi garanti edilmez. Bunun için OEM kaynak/imza veya doğrulanmış süreç içi enstrümantasyon gerekir. [Android pencere erişimi](https://developer.android.com/reference/android/accessibilityservice/AccessibilityService#getWindows()) ve [düğüm işlemi](https://developer.android.com/reference/android/view/accessibility/AccessibilityNodeInfo#performAction(int)) API'leri kullanılır.

## Kapsam

- Araç moduna, ESE/motor sesine, CarConfig'e veya reset anahtarına yazılmaz.
- OEM paketi veya CommonService kapatılmaz. `CommonService` sonrası araç mesajları değiştirilmez.
- DMS/yüz tanıma, klima otomasyonu, sürüş modu seçim komutları ve genel hesaplamalar değiştirilmedi.
- Önceki kapsam manifestindeki 27 korumalı dosyanın 26'sı aynı kaldı. GlobalBackService istisnası yalnız Super Mod import/alan ve event/start/destroy bağlantılarıdır; bu ekler çıkarıldığında önceki dosyayla birebir eşitliği doğrulandı. Yandex, global geri, metin girişi ve kurulum onayı akışları aynen korundu.
- Eski `SportAnimationPolicy` ve geçişe bağlı testleri kaldırıldı; yerlerine görünür pencere, iptal, gerçek mod ve yanlış hedef testleri kondu. Tarihsel denetim hash'leri geçmiş kaydıdır; güncel dosya eşitliği iddiası değildir.

## Doğrulama

- Debug APK ve Android test APK derlendi.
- **14 JVM testi ve Android test paketinin tamamı olan 44 test geçti.**
- Yanlış/eksik mod, Normal/ECO/diğer modlar, yanlış paket/pencere/parent, eksik kaynak kimliği, gizli veya devre dışı geri düğmesi, geç pencere, açık Sport'ta etkinleştirme, pencere değişimi, hızlı kapat/aç, servis sonlandırma ve sınırlı tekrar doğrulandı.
- Ayrı emülatör fixture uygulaması gerçek Android pencere ve AccessibilityNodeInfo ağacını sağladı. Sport penceresinin kendi düğmesiyle kapandığı, fixture mod değerinin aynı kaldığı ve Normal penceresine dokunulmadığı doğrulandı. Fixture, OEM APK'sı veya ECU değildir; mod okuması fixture'ın salt okunur provider'ından sağlandı. Gerçek VDBus cevabının doğrulaması ayrı transport testleriyle sınandı.
- Fixture normal uygulama Dialog'u kullanır; OEM'in type 2008 sistem penceresi fiziksel araçta henüz test edilmedi. Üretim eşleştiricisi sistem pencere türünü dışlamaz; odak/paket/kaynak kontrolleri aynı uygulanır.
- Fiziksel araç bu çalışma sırasında bağlı değildi; cihaz kabulü beklenir.

## APK ve kurulum paketi

Güncel test paketi: **MapControl test-fk**, `com.mapcontrol.testfk`, versionName `1.2.4`, versionCode **3**. Önceki test-fk ile aynı imzayı taşır; emülatörde mevcut test-fk üzerine güncelleme ve açılış doğrulandı.

APK SHA-256: `3025421fe17c8e16baba1c729f64996fc193ac3370e2a1be67fc43ee76e49c68`.

APK ve ona eşleşen kurulum betiği Mint'teki `~/dev/omoda` klasörüne kopyalandı. Önceki APK `devices/omoda/apk-arsivi/` altında hash adıyla korundu. Betiğin SHA-256 kontrolü yeni APK'ya güncellendi; mevcut Android/OEM kurulum kısıtlarını aşmaya yönelik değişiklik yapılmadı.

## Tekrar çalıştırma

```powershell
.\gradlew.bat :app:testDebugUnitTest :app:assembleDebug :app:assembleDebugAndroidTest
python scripts/tests/build_sport_window_fixture.py
adb -s emulator-5584 install -r output/sport-window-fixture/fixture.apk
adb -s emulator-5584 install -r app/build/outputs/apk/debug/app-debug.apk
adb -s emulator-5584 install -r app/build/outputs/apk/androidTest/debug/app-debug-androidTest.apk
adb -s emulator-5584 shell am force-stop com.mapcontrol.testfk
adb -s emulator-5584 shell am force-stop com.mapcontrol.vnoisy
adb -s emulator-5584 shell am instrument -w -r com.mapcontrol.test/androidx.test.runner.AndroidJUnitRunner
```

Fixture yalnız izole emülatöre kurulmalıdır; gerçek OEM paketiyle aynı paket adını taşır ve araca kurulmaz. Fixture üretim APK'sına dahil değildir. `SportAnimationRealWindowTest` fixture provider'ı ve emülatör ortamı yoksa atlanır. Diğer testler fixture gerektirmez.

Yerel kanıtlar: `output/supermode-fix-testfk/{verification.json,apk-identity.json,update-launch.txt}`, `output/supermode-regression-all.txt`, `output/supermode-real-window-tests.txt`. Mint inceleme devamı: `devices/omoda/incelemeler/2026-10-23-super-mode-engelleme/devam/`.
