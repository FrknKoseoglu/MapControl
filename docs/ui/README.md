# MapControl otomotiv UI/HMI tasarım sistemi

**Denetim tarihi:** 22 Ekim 2026. **Hedef:** soldan direksiyonlu (LHD) aracın orta konsolu; yatay 16:9, ultra-wide ve dikey 9:16. Bu set Android Java/XML uygulamasına göre yazılmıştır: CSS, Tailwind, Compose veya React prop API'si kullanılmaz. Yerleşimler çoğunlukla Java `View`/`ViewGroup` ağacıdır; ölçüler `dp`, yazılar `sp`, renkler `R.color` kaynaklarıdır.

## UI/UX kapsamı ve main ile eşitlik — 22 Ekim 2026

İşlevsel sağlamlaştırmalar, yeni model/VIN/yıl çıkarımı ve hesaplama yardımcıları bu daldan çıkarıldı; kaynak taban `origin/main` (`187093b`). Araç veri/komutları, OEM okuma, ses efektleri, açılış/karşılama, navigasyon, hava durumu, kamera, API ve kurulum akışları main davranışına döndürüldü. **İzin verilen tek işlevsel ek Super Mod animasyon kontrolüdür**; politikası ve opt-in akışı korunur. Ortak OEM proxy değiştirilmeden, yalnız Super Mod kendi cevapsız-okuma kontrolünü yapar.

Araç bilgisi kart/filtre düzeni korunur; 19 sinyal/formatter eşleşmesi main ile aynıdır, mühendislik menüsü eylemi vardır. Başlıktaki yeni model/yıl çıkarımı kaldırılmıştır. Durum widget'ları mevcut veriyi ve kayıtlı UI durumunu okur; ortak repository için yeni reconnect/poll algoritması eklemez. `source-inventory.md`, `audit-history.md` ve eski manifest doğrulamaları **temizlik öncesi tarihsel kayıttır**; geri alınan özellikler için güncel uygulama garantisi oluşturmaz. Önceki hâl `codex/ui-scope-backup-20261022` dalında korunur.

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

Denetlenen temel revizyon `c7de3ff348f66f5efbb75965aa45e31d58728d08`; çalışma ağacında önceden değişmiş araç kimliği/ölçümler, Wi-Fi ve araç bilgileri dosyaları da okunmuştur. Belge kaynak gerçeği çalışma ağacıdır. 23 Ekim düzeltmesiyle mod göstergesi yalnız canlı ve bağlı modu gösterir; önceki tercih fallback'i güncel davranış değildir. [Güncel bileşen kuralları](components.md#refactor-checklist) esas alınır.

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

- Android Java/XML IVI uygulaması: minSdk 29, targetSdk 28, compileSdk 34; JDK 17/Gradle 8.2. Emülatör OEM donanımının karşılığı değildir.
- Launcher Lite saat/hava/ölçüm panelidir; GLB/OEM model desteği kaynaklarda kalır. Eski performans ölçümleri tarihsel veridir.
- UI refactor'ı araç komutlarını, birim dönüşümlerini, veri filtrelerini, kayıt tercihlerini ve servis yaşam döngülerini değiştirmez. Yeni callback mevcut işlemi çağırır; mevcut parametre eşlemelerini korur.
- Sport gizleme varsayılan kapalıdır: bilinen mod→Sport, ≥600 ms ve taze doğrulama; 500 ms servis kontrolü; tek paketli kapatma isteği. Başlangıç Sport/yeniden bağlantı tetiklemez. Bu özellik açıkça izin verilen istisnadır.
- Header/rail konumları ve içerik geçişleri bu UI dalının sorumluluğudur. HeaderStatusMonitor mevcut snapshot ve tercihleri görüntüler; core repository main sürümüdür.
- Hava durumu ve otomatik park kaydının konum/yenileme kuralları main'e aittir; eski araç-konumu temelli hava durumu yenilemesi artık bu dalın özelliği değildir.
- Ekolayzer DSP, kamera kaynakları, mikrofon tanısı, APK kurulum kuyruğu, cluster görev tespiti ve boot oturumu main ile korunur. Önceki raporlardaki düzeltme garantileri güncel kabul kriteri sayılamaz.
- DMS/yüz tanıma ve klima otomasyonu dosyaları main ile aynı kalır. UI tasarım standardı bu özelliklerde otomatik değişiklik yapma izni değildir.

Ayrıntılı izin araştırması, kamera callback yarışı adayları, eski APK farkları ve QA-01…QA-26'nın tamamı [tarihsel kayıtta](audit-history.md) korunmuştur.

## Kanıt ve kabul

Main kaynak eşitliği ve 19 metrik eşleşmesi kapsam temizliğinde doğrulandı. Temizlik sırasında kaydedilen dört UI test başarısızlığı, 23 Ekim tarihli kullanıcı talebiyle yalnız UI/test katmanında düzeltildi: kart tıklama hedefleri, tekrarlı grid daralması ve bağlantısız/bilinmeyen modun nötr gösterimi. Debug APK/test APK derlendi; **23 JVM testi ve 19 ilgili Android UI/Super Mod testi başarılıdır**. Fiziksel araç testi yapılmadı; lint temizliği iddia edilmez. Önceki başarısızlık kaydı `ui_only_scope_cleanup`, güncel sonuç `ui_four_failure_fixes` manifest alanındadır.

UI refactor'ında hem tema hem orientation, %100/%130 yazı, bilinmeyen/bağlantısız veri, hızlı sekme/tema değişimi ve overlay tekrar aç/kapat senaryoları ölçülür. OEM kabulünde model/firmware/display/yoğunluk ve APK hash kaydedilir. Kaynak test yolu [source-inventory.md](source-inventory.md)'de, çalıştırma ve sonuç şablonları [audit-history.md](audit-history.md)'dedir.

## Doküman temizliği ve bilgi izi

Sekiz eski `docs/*.md` tam metni önce arşivlenip içerik/hash doğrulamasından sonra kaldırılır; silinen yollar [manifestte](audit-manifest.json) listelenir. `docs/ui-previews/` görselleri/JSON/TXT kanıtları ve kök README korunur; kök README'nin eski rapor bağlantısı yeni indekse yönlendirilir. `.gitignore` yalnız `docs/ui/` için Git görünürlüğü sağlar; önizlemeler ve diğer yerel docs içerikleri ignore kapsamında kalır. Arşiv yeni tasarım sisteminden ayrı bir tarihsel kayıttır; eski hedefleri güncel gereklilik diye uygulamayın.
