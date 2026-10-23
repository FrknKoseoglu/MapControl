> **Kapsam güncellemesi (22 Ekim 2026):** İşlevsel düzeltmeler main'e geri döndürüldü; yalnız UI ve Super Mod istisnası korunur. Aşağıdaki hedef/refactor maddeleri, özellikle veri davranışını değiştirenler, ayrıca kullanıcı talebi olmadan uygulanmaz. Tarihsel envanter güncel işlev garantisi değildir.

# Bileşen standartları ve mevcut durum

[İndeks](README.md) · [Tokenlar](design.md) · [Erişim ve layout](ergonomics-and-layout.md) · [Tam API/kaynak envanteri](source-inventory.md)

## Uygulama modeli

UI, Android `Button`, `TextView`, `ImageButton`, `ScrollView`, `ListView`, `SeekBar`, `LinearLayout`, `FrameLayout` ve `GridLayout` ile oluşturulur. React prop veya CSS class sözleşmesi yoktur: kurucu argümanları, public setter'lar, callback arayüzleri ve `R.color`/`R.dimen` bileşen sözleşmesidir. XML `activity_camera` aktif bir ekran tarifidir; `activity_main.xml` içindeki eski log/form görünümü güncel MainActivity'nin programatik shell'inin tek kaynağı sayılmaz.

Aşağıda **MEVCUT** kod davranışı, devamında **HEDEF** standart ve uygulanmamış refactor listesi bulunur. UI düzenlemesi OEM callback/komut davranışlarını değiştirmez.

## Ortak bileşenler ve API'ler

### Renk, yüzey ve buton — UiStyles

Kaynak: [UiStyles.java](../../app/src/main/java/com/mapcontrol/ui/theme/UiStyles.java).

| API | Sözleşme / stil |
|---|---|
| `color(Context, @ColorRes int)` / `drawable(Context, @DrawableRes int)` | uiModeContext ile güncel tema; drawable mutate edilir |
| `setTextSize(TextView, int)` / `dimenPx(Context, int)` | Kaynak `sp` doğru px ile uygulanır; ölçü dönüşümünü iki kez yapma |
| `setRootBackground`, `setRailPanelBackground`, `setGlassCardBackground` | İlgili ortak drawable'ı uygular |
| `applySolidRoundedBackground(View, int colorArgb)` | OEM button radius 14 dp |
| `applySolidRoundedBackgroundDp(View, int colorArgb, float radiusDp)` | Bağlama özel radius; parametre dp |
| `styleOemButton(Button, int colorArgb)` | 25 sp, no all-caps, luminance ile siyah/beyaz yazı, yatay 16/dikey 4 dp padding, mevcut minHeight 48 dp |
| `setButtonStartIconTinted`, `setButtonIconOnlyTinted` | İkon rengi ve erişilebilir açıklama; boyut/yerleşim uygunluğunu tek başına garanti etmez |
| `applyOptionalBackdropBlur(View, float radiusPx)` / `clearRenderEffect` | API≥31, radius px; alt API no-op. Metin de etkilenebilir |

**HEDEF:** Düğme en/boy 56 dp, kritik eylem 64 dp; etkileşim kutuları arasında 12/16 dp. Disabled yalnız alpha değildir: enabled/clickable/focus ve açıklama birlikte doğru olmalıdır. Loading etiketi durumu anlatır, aynı komut yinelenmez; başarı OEM geri bildirimi yoksa uydurulmaz. Tehlikeli eylemin kısa onayı ve geri dönüşü sürücü tarafındadır.

```java
// Önkoşul: Context context, Runnable onClose.
Button close = new Button(context);
close.setText(R.string.common_close);
UiStyles.styleOemButton(close, UiStyles.color(context, R.color.buttonNeutral));
int side = Math.round(64 * context.getResources().getDisplayMetrics().density);
close.setMinWidth(side);
close.setMinimumWidth(side);
close.setMinHeight(side);
close.setMinimumHeight(side);
close.setOnClickListener(v -> onClose.run());
// Ölçümü kısıtlamayan LayoutParams ile ekranın 1. bölge eylem bandına ekle.
```

Bu kod mevcut yardımcıyı kullanır ve **yeni hedef boyutunu** açıkça ekler; mevcut kaynak güncellenmiş gibi sunulmaz. Yerleşim ve komşu boşluk ayrıca sağlanmalıdır.

### Seçim kartları — binary / ternary

`UiStyles.addBinarySegmentedControl(Context, ViewGroup, @Nullable String title, String leftLabel, String rightLabel, String leftHelp, String rightHelp, boolean initialLeftSelected, Consumer<Boolean> onCommit)`:

- Normal boolean seçenek iki ayrı hap yerine **tek kart + yuvarlak CheckBox görünümü**dür. Kart min 64 dp, radius 16 dp, padding 16×14 dp; ikinci dokunuş tersine çevirir. Başlık 27.5 sp, yardım 22.5 sp.
- Türkçe/İngilizce etiketleri dil seçimine eşleşiyorsa ayrı, birbirini dışlayan radyo kartları kullanılır: min 52 dp, arada 10 dp. Bu özel dal HMI hedefinin altındadır.
- Seçili kart `assistCardActive` + 2 dp `assistStrokeActive`; boşta `assistCardIdle` + 1 dp `outline`. Yalnız mevcut seçeneğin yardım metni görünür.
- `BinarySegmentHandle.setLeftSelected(boolean)` değişimde **onCommit çağırır**; izin reddinde rollback için callback etkisi düşünülmelidir. `syncVisualWithoutCommit(boolean)` yalnız görünümü günceller; `setInteractionEnabled`, `isLeftSelected`, `reapplyTheme` sunulur.

```java
// Önkoşul: Context context, FeatureGridLayout grid,
// boolean initialValue, Consumer<Boolean> onCommit.
UiStyles.BinarySegmentHandle handle = UiStyles.addBinarySegmentedControl(
    context, grid, null,
    context.getString(R.string.common_yes),
    context.getString(R.string.common_no),
    "Etkin", "Kapalı", initialValue, onCommit);
// Etkin/Kapalı örnek metinleridir; üretimde R.string kaynağından alınır.
handle.syncVisualWithoutCommit(initialValue);
```

`addTernarySegmentedControl(Context, LinearLayout, @Nullable String title, String[] labels, String[] helps, int[] modeIds, int initialModeId, Consumer<Integer> onCommit)` tam üç paralel dizi ister, aksi halde IllegalArgumentException. Kartlar min 52 dp. Görsel sıra ile OEM mode ID aynı şey değildir; `modeIds` eşlemesi korunur. Handle `syncVisualFromModeId(int)` callback olmadan senkronlar, bilinmeyen ID'de ilk seçeneği gösterir; bu davranış gerçek araç durumunu tahmin etmek için kullanılmaz. `getSelectedModeId()` seçimi verir. **HEDEF:** Üç seçenek de 56 dp ve ≥12 dp aralık, kritik seçimler 1/2.

### FeatureGridLayout — adaptive kart grubu

Kaynak: [FeatureGridLayout.java](../../app/src/main/java/com/mapcontrol/ui/theme/FeatureGridLayout.java). `new FeatureGridLayout(context)` eşik 840 dp; `new FeatureGridLayout(context, 520)` özel eşiktir. `addCard(View)` layout parametrelerini gruba uygun kurar. Kendi kullanılabilir eni ölçülür; sütun 1/2, `GONE` çocuk atlanır ve görünür sıra korunur. Dış margin 6 dp + 6 dp komşular arası 12 dp üretir. `onMeasure` görünür kart sırası veya sütun düzeni değişince eski satır/sütun span önbelleğini temizler, yeni eksen boyutlarını kurup konumları yeniden atar; yalnız en değişiminde kart enini günceller. Tekrar eden 2→1→2 geçişi, filtre/GONE ve yeni kart ekleme test edilir. Bu erişim bölgesi motoru değildir.

```java
FeatureGridLayout cards = new FeatureGridLayout(context);
Button action = new Button(context);
action.setText(R.string.common_close);
UiStyles.styleOemButton(action, UiStyles.color(context, R.color.buttonNeutral));
cards.addCard(action);
// API örneği: HMI min en/boy ve 1/2 konumu ayrıca uygulanır.
```

### Navigasyon ve üst şerit

Kaynaklar: [SideRailBuilder](../../app/src/main/java/com/mapcontrol/ui/builder/SideRailBuilder.java), [TopBarBuilder](../../app/src/main/java/com/mapcontrol/ui/builder/TopBarBuilder.java), [MainActivity](../../app/src/main/java/com/mapcontrol/ui/activity/MainActivity.java).

- `SideRailBuilder(Context, SharedPreferences, SideRailCallback)`; `build()` landscape, `buildPortrait()` mevcut üstte yatay menü; `setSelectionForTabIndex`, `setLauncherModeActive`, `reapplyTheme`. Callback: `onTabSelected(int,String)`, `onAppManagementRequested`, `log`. Profil rail öğesi eklenmesi yorum satırında, kamera öğesi GONE; mevcut görünür menü sayısına dahil edilmez.
- `TopBarBuilder(Context, TopBarCallback).build()`; `setTitle`, `setWifiState`, `setDriveMode`, `setSelectedDriveMode`, `setServerRunning`, `setProjectionActive`, `setSoundBoost`, launcher back setter/listener, `release` ve `reapplyTheme`. Callback: `onStatusSelected(int)`, `onLogTabToggle`, `log`.
- `WifiState` CONNECTED/DISCONNECTED/OFF/UNKNOWN; telefon etiketi web sunucusunun etkinliğidir, bağlı telefon sayısı değildir. Ses boost chip'i yalnız etkin pozitif gain'de görünür. Başlığa üç dokunma log görünürlüğünü değiştirir; normal sürüşte keşfedilmesi gereken temel eylem olamaz.
- `StatusWidgetsView` header dışında, normal görünümde sol menünün tepesinde sabittir. Tam ekran launcher'da header GONE olur; widget'lar sol Hızlı Erişim kartında başlığın altında iki sütuna, `launcher_clock_title` saat kartının sağ üstüne yerleşir. Marka/sürüm normal header'ın sağ üstünde salt okunur `header_brand` olarak kalır. Launcher geri düğmesi yalnız 36×36 dp; solda üsttedir. Bu iki bulgu hedef standarda uymaz.
- **23 Ekim seçim düzeltmesi:** Widget, MapControl'da kayıtlı `driveModeSetting` seçimini `setSelectedDriveMode(int)` üzerinden gösterir. Bu UI tercihi için OEM bağlantısı/cevabı beklenmez; araç bağlantısı açıkmış gibi işaretlenmez. `setDriveMode(boolean,int)` doğrulanmış araç verisi için ayrı kalır. Açıkça kaldırılmış veya bilinmeyen seçim `—` olur. Erişilebilir açıklama “Seçili mod: …” der. Görüntüleme kayıtlı tercihi değiştirmez ve araç komutu göndermez.

**GÜNCEL:** Durum widget kısayolları normalde sol rail üstündedir; tam ekranda sol Hızlı Erişim kartı içindedir. Sağ üstteki normal header markası salt okunurdur. **Diğer HEDEF:** Home/Geri kalıcı 1. bölgededir. Sol üstte dialog geri alternatifi olabilir; ana home/back erişiminin yerini almaz. Mevcut header nesnesini ve içerik geçişi ayrımını koru.

### Bağımsız durum widget'ları — StatusWidgetsView

`StatusWidgetsView(Context, String prefix, IntConsumer onSelected)` bir `ViewGroup` ve `HeaderStatusSink` uygulamasıdır. `header` prefix normal rail, `launcher_status` prefix tam ekran Hızlı Erişim kartı içindir. Wi-Fi/mod/sunucu/yansıtma/ses boost durumları tek ortak stil ve ölçüm kodunu kullanır; callback yalnız ilgili sayfayı açar. Normal örnek `TopBarBuilder.getStatusWidgets()` uyumluluk erişimiyle SideRailBuilder'a bağlanır, header'ın çocuk View'ı değildir. `SideRailBuilder.setStatusWidgets`/yeniden build mevcut View'ı eski parent'ından ayırıp menü scroll'unun üstüne ekler. `reapplyTheme` iki konumun renklerini de yeniler. `setSingleRow(true)` yalnız rail örneğinde kullanılır: tüm görünür widget'lar aynı satırda, 6 dp aralıkla dizilir. Üst etiket sığmazsa simge/durum ve erişilebilir açıklama korunur; durum değeri kesilmeden kendi hücresinde sarılabilir. `setColumnCount(2)` launcher örneğinde iki sütunu sabitler; 12 dp aralık korunur. Dar hücrelerde veya büyük yazı ölçeğinde kompakt düzen durum değerini sarar. Aktif ses boost kutusu varsa üçüncü sıraya eklenir. Saat kartı yalnız pasif başlığını sağ üstte taşır. İkon gridi Wi-Fi/Mod/Telefon/Yansıtma hedeflerini tekrarlamaz; swipe ayarlarındaki bu hedefler korunur. Kalan beş ikonun etiketleri 32 dp minimum yükseklik ve üst hizayla satırlar arasındaki boşluğu azaltır.

**Mod seçimi ve canlı veri:** `HeaderStatusMonitor`, `MapControlPrefs.driveModeSetting` anahtarı varsa mevcut seçimi bütün sink'lere `setSelectedDriveMode` ile iletir. MainActivity'nin mevcut `onModeSelected` ⇒ `refresh()` callback'i seçimi aynı UI olayında gösterir; SharedPreferences dinleyicisi callback dışındaki UI tercih değişikliklerini de yakalar. Yeni bağlanan widget ve yeniden açılan ekran aynı tercihi gösterir. `-1` / geçersiz tercih nötr `—` olur. Cevapsız veya eski araç okumaları seçimi ezmez. Tercih anahtarı varken mod için periyodik OEM okuması yapılmaz. `stop()` tercih dinleyicisini ve bekleyen UI işlerini kaldırır.

Kayıtlı seçim anahtarı henüz yoksa `WidgetDriveModeSource` önceki salt okunur `MODULE_NEW_ENERGY / ID_DRIVE_MODE` okumasını sürdürür. Bu yolda eksik/boş/yanlış cevap `—`, gerçek `0` ECO'dur; tek/çok baytlı diziler mevcut çözümlemeyle yorumlanır. Genel metrik callback'i mod widget'ını tekrar yazmaz. Ortak repository, araç komutları, mod eşlemesi ve Super Mod değişmez. `setSelectedDriveMode` araçtan doğrulanmış bağlantı durumu üretmez; iki farklı bilgi türü birbirine karıştırılmaz.

`DriveModeSelectionWidgetsTest` gerçek MainActivity ve mod kartlarını kullanır: önceki APK'da ECO seçimi sonrası `—` hatasını üretir; düzeltilmiş APK'da yedi modu iki widget'ta, mevcut komutların sayısını/değerlerini, seçimi kaldırmayı, 2 saniyeden sonra seçimin korunmasını ve yeniden açılışı doğrular. `HeaderStatusMonitorTest` eski/cevapsız araç verisinin seçimi silememesini ve tercih dinleyicisini kapsar. Diğer ham cevap testleri anahtar yokken canlı okuma yolunu sınar. Emülatör testleri fiziksel OEM kabulünün yerine geçmez.

## Ekran/builder envanteri — MEVCUT

Kurucularda yazan callback türleri ilgili sınıfın iç arayüzüdür. Tam public imzalar ve kaynak satırları [envanterde](source-inventory.md) listelenir.

| Builder / ekran | Kurucu girdileri ve dönüş | Düzen, durum ve sorumluluk |
|---|---|---|
| `WifiTabBuilder` | `Context, WifiCallback`; `build(): LinearLayout` | Aç/kapat/tarama, scroll ağ listesi, SSID/şifre dialog; log callback. Ortak header bağlantı durumunu verir |
| `AppsTabBuilder` | `Context, AppsCallback`; `build(): LinearLayout` | Sunucu/yerel seçimi, yenileme/indirmeler, uygulama kart/liste ve kur/kaldır/onay. Callback ayrıcalıklı paket kontrolü ve log; kurulum/queue davranışı main sürümüdür |
| `FileUploadTabBuilder` | `Context, WebServerManager`; `build(): ScrollView` | Sunucuyu aç/kapat, durum, QR ve telefon yönergeleri; `getToggleButton/getStatusText/getQrImageView` |
| `ProfileTabBuilder` | `Context, SharedPreferences, ProfileApiService, ProfileCallback`; `build(): ScrollView` | Giriş/token/hesap formları; rayda gizli olsa da kod korunur; karmaşık park akışı |
| `ProjectionTabBuilder` | `Context, SharedPreferences, ProjectionCallback`; `build(): ScrollView` | Cluster aç/kapat, hedef uygulama, ana display'e taşıma, güç tercihi, key listener ve kart offset/size açılır ayarları |
| `DriveModeTabBuilder` | `Context, SharedPreferences, DriveModeCallback`; `build(): ScrollView` | Mod kartları ve `onModeSelected(int)`. Kalıcı 4x4 görünümü, seçimi kaldır ve Sport gizleme; canlı değer/başlangıç tercihi ayrılmalı |
| `AssistTabBuilder` | `Context, SharedPreferences, AssistCallback`; `build(): ScrollView` | İki sütun kompakt yardım tercih kartları; `onSettingChanged` ve `onSafetyWarningRequired(...,Runnable)` güvenlik onayı korunur; DriveMode içine eklenir |
| `SettingsTabBuilder` | `Context, SharedPreferences, SettingsCallback`; `build(): ScrollView` | FeatureGrid; locale, nightMode, launcher ve açılış/güç/konum tercihleri. Callback `getCarToken`, `onLocaleChanged`, `onNightModeChanged`, `onLauncherModeChanged`, `log` |
| `WelcomeSoundTabBuilder` | `AppCompatActivity, SharedPreferences, ActivityResultLauncher<Intent>`; `getScrollView()` | Ses seçme/USB listesi, Çal-Durdur, auto-play, tasarım ve önizleme; oynatıcı lifecycle |
| `WelcomeSoundScreenBuilder` | Statik `buildTabScrollView(...)`; `Screen` | Ana FeatureGrid iki kart, seçenek grubu 520 dp; 56 dp `actionRow`, dosya adı ≤2 satır; dosya yokken çalma, off tasarımda preview pasif |
| `EqualizerTabBuilder` | `Context`; `build(): ScrollView`, `rebuild()` | Ses kazancı/preset/bant SeekBar; 112 dp slider, 72 dp thumb, 24 dp track kaynakları. Efekt oluşturma/uygulama davranışı main sürümüdür |
| `VehicleInfoTabBuilder` | `Context, VehicleMetricsRepository, VehicleInfoCallback`; kurucu build eder, `getScrollView()` | Main sinyal/formatter eşleşmeleriyle ALL/GENERAL/TRIP/FUEL/ENGINE ve aktif filtre; 19 metrik binding, Yenile ve mühendislik menüsü. Model/yıl çıkarımı yoktur. Snapshot güncellemelerinde View korunur; `startListening/stopListening/release` |
| `ClimateAutomationTabBuilder` | `Context, SharedPreferences, Callback`; `build(): LinearLayout` | Özel automation paleti, sürücü/yolcu koltuk ve iklim kuralları, gecikme/sıcaklık/seviye kaydırıcıları, çakışma notu; engine Listener |
| `DmsFaceRecognitionTabBuilder` | `Activity, SharedPreferences, Logger`; `build(): LinearLayout` | Tanıma aç/kapat, kayıtlı profiller, kamera izni ve profil Activity bağlantısı; deneysel menü onayı |
| `LogTabBuilder` | `Context, LogCallback`; `build(): LinearLayout` | Monospace log, temizleme, TTS metin/ses; onClearLogs/onReadAloud/onWelcomeTts. Tampon ve güncelleme davranışı main sürümüdür; park tanılama |
| `LauncherTabBuilder` | `Context, VehicleMetricsRepository, LauncherCallback`; `build(): View` | Dashboard facade; callback `onShortcutSelected`, `onExitLauncherRequested`, `onAppLaunchRequested`, `onClusterProjectionRequested` |
| `LauncherDashboardBuilder` | `Context, VehicleMetricsRepository, LauncherCallback`; `build(): View` | Quick/Lite/media-drive kartları, medya izin/boş durum ve kontroller. `start/stop/reapplyTheme` ile sekme görünürlüğü ve abonelikler; medya/hava yaşam döngüsü main ile korunur |
| `CameraActivity` | Activity + `activity_camera.xml` | Landscape kilitli, 2 sütun ağırlıklı TextureView grupları; üst solda Geri min48 dp/16 sp; izin ve kaynak temizliği |
| `DmsFaceProfileActivity`, `DmsClusterPreview` | Activity/önizleme | Yüz kayıt/profil yönetimi ve cluster önizleme; karmaşık park akışları, kamera kaynağı ve hedef display sınırı |
| `ClusterVDBusTestActivity` | Landscape Activity | OEM bench/tanılama; normal sürüş navigasyonu değildir |

Önemli tarihsel fark: önceki okunabilirlik raporunda araç bilgileri “altı büyük kart + diğer 13 açılır” olarak anlatılır. Güncel çalışma ağacındaki `VehicleInfoTabBuilder` kategori/aktif filtre ve 19 metrik kullanır; eski görünüm güncel ekranın tarifi değildir. Wi-Fi görünümü korunmuştur; yeni kimlik/model-yıl hesaplaması kapsam temizliğinde kaldırılmıştır.

## Widget, liste, slider ve görsel yüzeyler

| Bileşen | Mevcut sözleşme | HMI standardı |
|---|---|---|
| `LauncherLitePanelView` | Sistem sans/light saat, tarih, hava, mod badge, hız/vites/yakıt; snapshot ile güncelleme, tema yenileme | Bilgi pasif olabilir; bilinmeyenler çizgi. Üzerindeki eylemler ayrı sürücü alanına |
| `LauncherClockSwipeLayout` | `Listener.onDirection(int)`, `setListener`; 48 dp tanıma/96 dp commit, 72 dp hint; yönler LauncherSwipeStore | Gizli swipe Home/Geri/primary'nin tek yolu olamaz; 4'te sürücü gesture yok |
| `VehicleModelHostView` | Model/OEM görsel host, yükleme/geçiş ve lifecycle | Pasif görüntü 4'te olabilir; sürücü dokunması eklenirse 1/2'ye kontrol gerekir |
| `OemFrameAnimationView` | OEM frame animasyonu | Kaynak temizliği; sürüşte dekorasyon politikası gerekir |
| `ListView` / app-ağ satırı | Özel row adapter, ikon + ad + eylem; farklı local min/padding | Tüm satır hedefi ≥56 dp; kritik işlem butonu ≥64 dp. Uzun basma tek silme/geri yolu değil |
| `SeekBar` | Equalizer, projection offset/size, climate; `onProgressChanged` / `onStopTrackingTouch` yolları | Sürükleme yüksek göz yükü: park ayarı; sürüşte kısa adım +/− gibi sürücü tarafı alternatif. Gerçek hit rect kontrolü |
| `WelcomeDesign` | `id/titleRes/helpRes/createView(Context)`; createView null olabilir | `off/signature/winter/hike`, preference welcomeDesignId/welcomeCustomText korunur; null/off önizleme pasif |
| `Signature`, `WinterTouch`, `MountainHike` scene | Outfit/özel Canvas/animasyonlar | Dekorasyon metin/touch standartlarının yerine geçmez; hızlı iptal 1/2 |
| Google/Yandex cluster overlay | Manevra/ETA kartları, nav snapshot ve offset tercihleri | Ortak renkler korunur; cluster koordinatları kendi display'iyle, konsol bölgelerinden bağımsız doğrulanır |
| Telefon web paneli | HTML/CSS: welcome.html + WebServerManager | Ayrı yüzey, oturum/Origin ve metin kaçırma korunur. Konsoldaki QR kontrolü 1/2; web CSS paleti native token yerine konmaz |

## Modal, drawer ve bildirim standardı — HEDEF

**Sağ üst `[X]` veya başka biçimde Kapat/Geri/Ayarlar kesinlikle eklenmez.** Kural yalnız ikonun biçimine değil eylemin konumuna ilişkindir. Yerel kartın üst sağı ekranın solunda kalsa bile yeni kapatma standardı kart başlığına sağ X eklememektir; temel kapatma 1. bölgedeki eylem bandına, gerekirse 2. bölgedeki sol üst geri alanına alınır. Ekranın 4. bölgesindeki tüm sürücü tıklamaları yasaktır.

| Tür | Yerleşim ve davranış |
|---|---|
| Modal/AlertDialog | Başlık + kısa içerik + sürücü tarafı eylem bandı. Kapat/iptal ekran 1, gerekirse 2; ≥64 dp, aralık16. Sistem dialog buton sırası ergonomi garantisi değildir |
| Sol drawer/hub menü | Sürücü kenarında, alt sabit Home/Geri; içerik scroll. Projede tek ortak Drawer bileşeni yok; floating hub/overlay benzeri davranışlar ayrı yöneticilerde |
| Uyarı/bildirim kartı | Kritik bilgi 2'de, gerekiyorsa erişilebilir işlem 1'de. 4'teki bildirim salt okunur; oraya kapatma/undo bindirilmez |
| Toast/hazırlanıyor | Kritik kontrolleri kapatmaz; durumla eylem karıştırılmaz. Otomatik kapanış ve iptal/fallback cleanup |
| Park onayı/tehlikeli işlem | Kısa sonuç odaklı etiket, iptal sürücü tarafında. Mevcut DialogHelper hukuki/safety onayları refactor sırasında korunur |

Mevcut dialog kaynakları `util/DialogHelper`, `PermissionProbeDialog`, `OnboardingHelper`, `WifiTabBuilder`, `AppsTabBuilder`, `WelcomeSoundScreenBuilder`, `vehicle/material/VehicleMaterialPickerDialog` ve ilgili ekran içi AlertDialog çağrılarıdır. Aynı görünüm tümünde merkezi değildir; her çağrıdaki gerçek konum/minimum ölçü ayrıca denetlenmelidir. `setPositiveButton` var diye alt sol uygunluğu varsayılmaz. Dış scrim/Android Back tek kapatma yolu olamaz.

## Refactor Checklist

Aşağıdaki maddelerden HMI-01 ve HMI-09 tamamlandı; diğerleri açıktır. P0/P1/P2 burada uygulama sırasıdır; testlerin çalıştırıldığı veya yasal standart ihlali tespiti anlamına gelmez. Kaynak linklerinin satır numaraları yerine metot adı da verilmiştir; tam satır izi envanterdedir.

- [x] **HMI-01 / P0 — Sağ üst durum kısayollarını header'dan ayır.** Bağımsız StatusWidgetsView normal görünümde sol rail'in kaymayan üst bölümündedir. Tam ekran launcher header kullanmaz; widget'lar sol Hızlı Erişim kartında iki sütundadır; saat kartının pasif etiketi sağ üsttedir. Diğer ekranların sağ üst eylemleri HMI-06/07/08 kapsamındadır.
- [ ] **HMI-02 / P0 — Portrait bottom dock.** `SideRailBuilder.buildPortrait` ve `MainActivity.mountShell` rail'i içerikten önce üste koyuyor. Sabit alt dock ve Home→Geri→Ses→önemli eylem sırasını uygula; primary'ler sol yarıda kalsın. Kabul: 9:16 ve %130 yazıda scroll olmadan temel erişim; yön dönüşünde seçim/lifecycle kaybolmaz.
- [ ] **HMI-03 / P0 — Ölçü tabanını yükselt.** `dimens.button_height`/`UiStyles.styleOemButton` 48 dp; TopBar launcher back 36×36; dil/ternary 52 dp. Standart56/kritik64 dp en ve boy, aralık12/16 dp uygula. Kabul: LayoutParams sonrası gerçek bounds ve çakışmayan hit rect; yalnız resource değişikliği yeterli değildir.
- [ ] **HMI-04 / P0 — Kalıcı altın bölge.** Landscape rail scroll menüsünde bağımsız sabit Home/Geri/voice bandı yok; launcher quick card ayar/çıkış üst başlıkta ve scroll kısayolları temel erişim yolu. Temel driver kontrollerini 1'de sabitle. Kabul: her tab/launcher/scroll durumunda aynı konum; sesli asistan mevcut tekil ortak bileşen olarak varsayılmaz, ürün entegrasyonu gerekir.
- [ ] **HMI-05 / P0 — Floating geri varsayılanı.** `FloatingBackButtonManager.load...` geri hub'ını `screenWidth-cell-16dp` ile sağ altta başlatıyor. 1'e taşı; restore/drag clamp'te driver alanını uygula. `FloatingQuickActionsManager` ve `FloatingProjectionControlsManager` mevcut alt sol varsayılanlarına rağmen kaydedilmiş konum ve sürükleme sonrası 4'e kaçışı engellemez. Kabul: her display/yön ve kayıtlı eski koordinatta erişim politikası korunur.
- [ ] **HMI-06 / P1 — Overlay kapatmalarını ortaklaştır.** `TargetAppPickerOverlay` ve `DisplayRunningAppsOverlay` `titleRow` içinde esnek başlıktan sonra `btnClose` ekleyip sağ üstte metin kapatma gösterir; yeterli min hedef de yok. Ortak sürücü tarafı alt eylem bandına taşı; iptal/temizle ayrımı açık olsun. Kabul: modalın screen-space 1/2 kapatması; scrim'e basmadan kapatılabilir.
- [ ] **HMI-07 / P1 — Launcher sağ kartını ayır.** `LauncherDashboardBuilder.buildMusicDriveTabTrack` sağ kartın üstünde etkileşimli Music/Drive tablar; `mediaEmptyContainer` bildirim erişim CTA'sı; medya kumandaları sağda. Tab/izin ve primary kontrolleri sola/1–2'ye, ikincil medya eylemlerini 3'e taşı. Kabul: 4 yalnız görüntüleme; quick card içindeki ayar/exit standarda alınır. Bir kartın kendi üst sağı ile ekranın 4'ünü karıştırma.
- [ ] **HMI-08 / P1 — Grid bölge farkındalığı.** Settings, Assist, Welcome, Climate ve filtreleri olan VehicleInfo'da eşit iki sütun mantığı sağ üstte etkileşim üretebilir. Salt okunur kartlarla etkileşimleri ayır, sürücü ayar/CTA panelini sola koy. Kabul: mevcut sıra ve state korunur, GONE/tek sütun/%130 font; ekranda gerçek konum ölçümü.
- [x] **HMI-09 / P1 — Mod bilgisinin kaynağı (23 Ekim).** MapControl'da seçim varsa kayıtlı UI modu iki yerde anında gösterilir; bağlantı veya komut başarısı iddiası değildir. Seçimi kaldırma nötrdür. Kayıtlı seçim yoksa yalnız bilinen/bağlı araç modu gösterilir. Gerçek kart tıklamaları ve yeniden açılış test edilir; OEM komut/hesaplama katmanı değişmez.
- [ ] **HMI-10 / P1 — Küçük metin ve dialog eylemleri.** Header12.5/16.5 sp, theme dialog16 sp, bazı overlay13 sp ve camera16 sp ortak okunabilirlik ölçeğinin altında. 22.5/25 sp sürüş etiketleri ve büyüyen hücreyle düzelt; gerekirse öğe azalt. Kabul: iki tema/%130 yazıda anlam kaybı yok; en uzun TR/EN etiket ve bileşik zemin kontrastı ölçülür.
- [ ] **HMI-11 / P1 — Park/sürüş görev ayrımı.** APK/profil/yüz kayıt/tanılama, yoğun climate/EQ/offset slider'ları ve uzun metinler için ortak izinli park akışı tasarla. Mevcut genel hız kilidi varmış gibi davranma. Kabul: bilinmeyen hızda da karmaşık akış otomatik açılmaz; geri/iptal erişimi korunur; OEM onay/komut zinciri değişmez.
- [ ] **HMI-12 / P2 — Tema dışı renk ve birim borcu.** Drawable hardcoded gradient/stroke, welcome scene literal renkleri ve SideRail `setPadding(36,26,28,22)` gibi çıplak **px** değerleri var. Bağlama özel renkleri gerekçesiyle kaynaklaştır, ölçüleri dp'ye dönüştür. Kabul: tek/çift ölçekleme yok; iki yoğunluk ve DayNight; dekoratif renkleri yanlış semantik status tokenına bağlama.
- [ ] **HMI-13 / P2 — Pencere eni ve dar landscape.** MainActivity tam display metrics ve min300dp rail kullanır; StatusGrid en az2 kolon ve sınırlı cellWidth üretir. Inset/çoklu pencere eniyle yeniden ölç; dar durumda okunabilir uygun şablon seç. Kabul: dar landscape'de içerik/etiket sıfıra sıkışmaz, temel kontrol4'e taşmaz.
- [ ] **HMI-14 / P2 — Hareket ve gesture.** Sürekli welcome/splash animasyonları, clock swipe ve hub overshoot için sürüş/reduced-motion politikası ekle. Kabul: kritik bilgi/erişim kapanmaz; iptal/end/fallback callback pencereyi bir kez kaldırır; animasyon kapalıyken işlev kaybolmaz.

## Refactor doğrulama ve regresyon kapsamı

Mevcut testler kaynak envanterinde listelenir. İlgili başlangıç noktaları `DriveModeSelectionWidgetsTest`, `WidgetDriveModeSourceTest`, `HeaderStatusMonitorTest`, `CompactControlsTest`, `SharedHeaderTest`, `SharedHeaderNavigationTest`, `DriveModeBadgeTest`, `DriveModeOptionsTest`, `SportAnimationPolicyTest` ve `SportAnimationControllerTest`tir. Yeni zone/minimum hit hedeflerini doğrulayan tam bir otomatik suite bu görevle eklenmedi.

Bir refactor kabul kaydında orientation, çözünürlük, density, fontScale, tema, gerçek hit rect, 1/2/3/4 sınıfı ve ekran görüntüsü bulunur. Dialog/overlay restore ve pencere koordinatları da dahil edilir. Var olan testin beklediği 48 dp gibi eski değer yeni standarda geçince yeni davranışa göre değerlendirilir; test yeşil olsun diye hedef standardı küçültülmez.

Veri regresyonu: bilinmeyen mod/konum ve geç servis bağlantısı; tercih ile gerçek mod ayrımı; iptal edilen eski işin komut göndermemesi; header nesnesinin tab geçişinde korunması; tekrar aç/kapatta kamera/oynatıcı/WindowManager temizliği. Donanım gerektiren QA-01…26 senaryoları ve sonuç şablonu [arşivde](audit-history.md) korunur. Tarihsel test başarıları yeni tasarımın veya OEM araç kabulünün yerine geçmez.
