# MapControl otomotiv UI/HMI tasarım sistemi

**Denetim tarihi:** 22 Ekim 2026. **Hedef:** soldan direksiyonlu (LHD) aracın orta konsolu; yatay 16:9, ultra-wide ve dikey 9:16. Bu set Android Java/XML uygulamasına göre yazılmıştır: CSS, Tailwind, Compose veya React prop API'si kullanılmaz. Yerleşimler çoğunlukla Java `View`/`ViewGroup` ağacıdır; ölçüler `dp`, yazılar `sp`, renkler `R.color` kaynaklarıdır.

## Güncel widget yerleşimi — 22 Ekim 2026

Normal görünümde canlı durum widget'ları header'ın dışında, sol kenar barının en üstünde sabittir; menü listesi widget'ların altında kayar. İçerik header'ı eski sayfa başlığı/geri düzenindedir; marka/sürüm sağ üstte salt okunur kalır. Tam ekran launcher'da header tamamen gizlenir: widget'lar sol Hızlı Erişim kartında başlığın altında iki sütunda yer alır. “Saat ve durum” etiketi saat kartının sağ üstünde kalır.

Panel widget'ları tek satıra sığdırılır; dar hücrede simge ve durum değeri korunur, sığmayan üst etiket erişilebilir açıklamada kalır. Tam ekran kısayolları daha küçük ikon ve yazı ölçeği kullanır; yüzde talepleri tasarım yönlendirmesidir. Etiketler üst hizalı, ikonla 4 dp boşlukludur; etiket minimum yüksekliği 32 dp ile satırlar sıklaştırılır. Wi-Fi, Mod, Telefon ve Yansıtma canlı kutularla erişildiği için ikon gridinde tekrarlanmaz; “MAPCONTROL” ara başlığı kaldırılmıştır.

`ui/widget/StatusWidgetsView` her iki konumda kullanılan bağımsız bileşendir. SideRail yeniden kurulunca normal widget View'ı yeniden bağlanır; canlı durum ve tema güncellemeleri korunur. Launcher kartı kendi widget örneğini aynı mevcut monitörden günceller. Önceki “launcher dahil ortak üst header” yerleşimi bu düzenlemeyle değiştirilmiştir.

İlk denetim kapsamı ve source-inventory/audit-manifest hash'leri UI değişikliklerinden önceki kaynak görüntüsüdür. Güncel davranış bu not ve components.md ile izlenir; diğer ergonomi refactor maddeleri tamamlanmış sayılmaz.

## Doküman haritası

| Belge | Ne zaman okunmalı? |
|---|---|
| [design.md](design.md) | Açık/koyu paletin tamamı, semantik roller, tipografi, boyutlar, efektler ve tema API'leri |
| [ergonomics-and-layout.md](ergonomics-and-layout.md) | 1–2–3–4 erişim haritası, yatay/dikey şablonlar, dokunma ve göz ayırma ilkeleri |
| [components.md](components.md) | Gerçek builder/widget API'leri, örnekler, ekran envanteri ve öncelikli refactor checklist |
| [source-inventory.md](source-inventory.md) | Kaynak dosyaları ve satırlarıyla sabit renk/stil ifadeleri, XML drawable'ları, API imzaları ve tarama kapsamı |
| [audit-history.md](audit-history.md) | Önceki sekiz raporun tam metni, OEM araştırması, 26 QA senaryosu, tarihsel test sonuçları |
| [audit-manifest.json](audit-manifest.json) | Aktarılan belgelerin hash'leri ve kaynak dosyalarının değişmediğini kontrol eden denetim kaydı |

## Yeni geliştirici/agent için ilk kurallar

1. **Erişim sırası 1 → 2 → 3 → 4.** Sol alt birincil eylemler, Home/Geri, acil erişim ve sesli asistan içindir. Sol üst sürüş bilgisi ve ana sekmeler içindir. Sağ alt ikincil/yolcu eylemleri içindir. Sağ üst sürücü etkileşimine kapalıdır.
2. Sağ üste kapat `[X]`, geri, ayarlar, menü veya tıklanabilir durum kısayolu ekleme. Modalın kendi sol altı bile ekranın 4. bölgesine düşüyorsa uygun değildir; ekran koordinatını kontrol et.
3. Yatayda sürücü şeridi solda, dikeyde ana dock altta olmalıdır. Home/Geri ve önemli eylemler kaydırma içinde kaybolmaz. Dikey dock sırası fiziksel soldan sağa önem sırasıdır.
4. Yeni sürücü kontrolleri en az **56×56 dp**; Home/Geri/birincil/acil/sesli asistan hedefi **64×64 dp**. Komşu hedefler arasında en az **12 dp**, kritiklerde **16 dp** boşluk. Mevcut 48 dp kaynak değeri yeni HMI standardını karşılamaz.
5. Görsel token için [design.md](design.md)'deki gerçek `R.color`/`R.dimen` kullan. `UiStyles.color`, `UiStyles.drawable`, `UiStyles.setTextSize` ile tema ve yoğunluğu doğru çöz. Zaten piksele çevrilmiş `getDimension()` sonucunu yeniden `sp`/`dp` gibi ölçekleme.
6. Canlı veri yoksa `—`/bilinmiyor göster. Kayıtlı başlangıç tercihini gerçek araç durumu, Wi-Fi bağlantısını internet veya çalışan web sunucusunu bağlı telefon sayma.
7. Görünürlük, abonelik, pencere, ses ve kamera temizliğini koru. UI düzenlemesi OEM komutlarını, izin modelini, güç kodlarını veya kurulum sözleşmesini sessizce değiştirmez.
8. Türkçe/İngilizce metinler `values`, `values-tr`, `values-en` kaynaklarındadır. Yeni metni kaynaklaştır; %130 yazı ölçeği, uzun etiket ve iki tema ile kontrol et.

## Mevcut durum ile hedefin ayrımı

**MEVCUT:** Koddan doğrulanmış davranış ve değerler. **HEDEF:** Bu görevle getirilen HMI ürün kuralları. **AÇIK BULGU:** Mevcut kodun hedefe uymadığı veya eski raporla çeliştiği alan. İlk denetim yalnız dokümantasyon ve eski belge temizliğidir; sonraki ortak başlık değişikliği yukarıdaki güncellemede ayrıca belirtilmiştir. Diğer uygulama refactor'ları yapılmış sayılmaz.

Denetlenen temel revizyon `c7de3ff348f66f5efbb75965aa45e31d58728d08`; çalışma ağacında önceden değişmiş araç kimliği/ölçümler, Wi-Fi ve araç bilgileri dosyaları da okunmuştur. Belge kaynak gerçeği çalışma ağacıdır. Güncel `TopBarBuilder` mod tercihi fallback'i gibi tarihsel raporlarla çelişen durumlarda [bulgular](components.md#refactor-checklist) esas alınır.

### Kod haritası

| Katman | Yer ve sorumluluk |
|---|---|
| Kabuk | `app/src/main/java/com/mapcontrol/ui/activity/MainActivity.java`: servis/builder bağlama, tabContentArea, orientation/uiMode ve geçişler |
| Görsel yardımcı | `ui/theme/UiStyles.java`: renk/drawable, button, binary/ternary kontrol; `FeatureGridLayout.java`: ölçülen genişliğe göre 1/2 sütun |
| Ekranlar | `ui/builder/`: SideRail, TopBar ve özellik sayfaları; `ui/widget/`: Lite panel, saat swipe, OEM frame/model host |
| Kaynaklar | `app/src/main/res/values{,-night}/colors.xml`, `values/dimens.xml`, `themes.xml`, `drawable/bg_*.xml` |
| Araç verisi | `vehicle/VehicleMetricsRepository`, snapshot/formatter, `DriveModeStyle`; UI ve OEM yazma komutları ayrı sorumluluklar |
| Diğer yüzeyler | `manager/Floating*`, `*Overlay`, `nav/*ClusterNavOverlay`, `ui/welcome/`, `util/DisplayHelper`, kamera/DMS/bench Activity'leri |
| Telefon paneli | `assets/welcome.html`, `manager/WebServerManager.java`: ayrı tarayıcı yüzeyi; konsol dock yerleşimiyle karıştırılmaz |

## Korunması gereken mimari kararlar

- Uygulama OEM Android IVI içindir; `minSdk 29`, `targetSdk 28`, `compileSdk 34`, Java 8 kaynak uyumu; geliştirmede JDK 17/Gradle 8.2. Standart emülatör OEM servislerini temsil etmez. Paket `com.mapcontrol`, sürüm `1.2.4`/`2` tek başına build kanıtı değildir.
- Ana ekran Lite saat/hava/ölçüm panelidir; GLB/OEM model desteği kod ve varlıklarda bulunur. README'deki eski 3D CPU/PSS değerleri güncel performans ölçümü değildir. Filament detach kendi engine temizliğini yapıyorsa ikinci `destroy` eklenmez.
- Güç örneği yokluğu OFF değildir. OEM `0/1/2` korunur; gerçek Activity onayı sonrası açılış tamamlanır; OFF→uyanış rearm, 1→2'de tekrar yok, belirsiz süreç başlangıcında odağı çalma yok. Ses/görsel karşılama bağımsızdır.
- Canlı mod ve başlangıç tercihi ayrıdır. Sürüş modu retry 30 saniyeyle sınırlıdır, yeni seçim/Hiçbiri/release eski işi iptal eder. Sport gizleme varsayılan kapalıdır: bilinen mod→Sport, ≥600 ms ve taze doğrulama; mevcut 500 ms servis kontrolü; tek paketli kapatma isteği. Başlangıç Sport/yeniden bağlantı tetiklemez. İsteğin gönderilmesi OEM penceresinin kapandığı anlamına gelmez.
- Üst şerit ve rail sekme geçişi boyunca sabit kalır; içerik örtüsü yalnız `tabContentArea` üzerindedir. Tema değişimi mevcut görünümleri günceller. Görünür Activity abonelikleri, geç servis bağlantısı ve arka plan okuması korunur.
- Sunucu portu 7462; her açılışta 256 bit anahtar, QR fragment'i ve Origin kontrolü. Anahtar log/query'ye yazılmaz. Multipart toplam istek sınırı 1 GiB; geçici dosya, yol kontrolü, çakışmada dosya koruma ve dinamik metin kaçırma korunur.
- Hava durumu kullanıcı tercihiyle araç konumunu iki ondalığa yuvarlayıp Open-Meteo'ya gönderir; 15 dk konumlu cache, yaklaşık 60 sn retry; konum yokken sabit şehir yok. Park kaydı isteğe bağlı, bilinen açık→OFF, ≤2 dk/≤250 m örnekle yapılır; koordinat loglanmaz.
- Pencere kapanışı sahip olduğu eski View/WindowManager'ı yakalar; iptal/fallback dahil bir kez kaldırır. Başka ekrandaki görev cluster sanılmaz; görev keşfi arka planda 2 sn sınırıyla yapılır. Yandex normal içerik güncellemesi pencereyi yeniden eklemez; gerekli dış olay için 250 ms sınırı korunur.
- EQ kapalıysa global efekt oluşturulmaz; etkinse session 0 özelliği korunur; dış kontrol sahibine karşı 20 sn yık/oluştur döngüsü yoktur. Genel mikrofon tanılaması kayıt açmaz; SKIP donanım başarısı değildir.
- Kamera izin diyaloğunun CAMERA istemesi OEM donanımına tek başına erişim garantisi değildir. Kaynak 0–3 DMS/OMS/DVR/AVM etiketleri firmware doğrulaması ister. Runtime izin/AppOps/imza/device owner/Binder/SELinux/display farklı katmanlardır. Yeni APK'nın geçerli imzası OEM hak eşitliği değildir; eksik opsiyonel installer DEX için standart kurulum birebir sessiz kurulum garantisi vermez.

Ayrıntılı izin araştırması, kamera callback yarışı adayları, eski APK farkları ve QA-01…QA-26'nın tamamı [tarihsel kayıtta](audit-history.md) korunmuştur.

## Kanıt ve kabul

Bu görevde kaynak/doküman/yerel önizleme incelemesi ve belge bütünlüğü kontrolü yapılır; yeni emülatör veya fiziksel araç testi iddia edilmez. Önceki 56/20 ve daha sonra 65 birim/ilgili Android test sonuçları kendi tarih ve revizyonlarıyla arşivdedir. Lint'in temiz olduğu iddia edilmez. Yeni 1–2–3–4 standardının fiziksel kabulü henüz açıktır.

UI refactor'ında hem tema hem orientation, %100/%130 yazı, bilinmeyen/bağlantısız veri, hızlı sekme/tema değişimi ve overlay tekrar aç/kapat senaryoları ölçülür. OEM kabulünde model/firmware/display/yoğunluk ve APK hash kaydedilir. Kaynak test yolu [source-inventory.md](source-inventory.md)'de, çalıştırma ve sonuç şablonları [audit-history.md](audit-history.md)'dedir.

## Doküman temizliği ve bilgi izi

Sekiz eski `docs/*.md` tam metni önce arşivlenip içerik/hash doğrulamasından sonra kaldırılır; silinen yollar [manifestte](audit-manifest.json) listelenir. `docs/ui-previews/` görselleri/JSON/TXT kanıtları ve kök README korunur; kök README'nin eski rapor bağlantısı yeni indekse yönlendirilir. `.gitignore` yalnız `docs/ui/` için Git görünürlüğü sağlar; önizlemeler ve diğer yerel docs içerikleri ignore kapsamında kalır. Arşiv yeni tasarım sisteminden ayrı bir tarihsel kayıttır; eski hedefleri güncel gereklilik diye uygulamayın.
