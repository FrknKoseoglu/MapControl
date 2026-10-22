# Ergonomi, erişim ısı haritası ve layout mimarisi

[İndeks](README.md) · [Görsel tokenlar](design.md) · [Bileşenler ve refactor](components.md)

## 1–2–3–4 kuralı

Bu, projenin LHD orta konsol için ürün yerleşim kuralıdır. Sürücü ekranın sol çaprazında, sağ dirsek orta konsolda desteklidir. Simetri yerine erişim maliyeti ve minimum göz ayırma süresi gözetilir. Gerçek koltuk/ekran açısı ve fiziksel erişim ayrıca araçta doğrulanır; diyagram bir homologasyon veya antropometrik ölçüm sonucu değildir.

```text
                      EKRAN / KULLANILABILIR ALAN
                +--------------------+--------------------+
                | 2 - SOL UST        | 4 - SAG UST        |
                | Birincil gorus     | SALT OKUNUR        |
                | Nav / uyari / tabs | ETKILESIM YASAK    |
                +--------------------+--------------------+
  Surucu (LHD)  | 1 - SOL ALT        | 3 - SAG ALT        |
       o   ---> | ALTIN BOLGE        | Ikincil / yolcu    |
      /|\       | Home / Geri / CTA  | Liste devami       |
      / \       +--------------------+--------------------+
                  ERISIM ONCELIGI: 1 -> 2 -> 3 -> 4
```

| Bölge | Yerleştirilecekler | Kısıt |
|---|---|---|
| **1: sol alt, altın bölge** | Birincil CTA, Home, Geri, acil erişim, sesli asistan ve sürücü kontrolleri | Daima görünür ve tek dokunuşla erişilir; kaydırma/uzun basma tek erişim yolu olamaz |
| **2: sol üst** | Aktif navigasyon manevrası, kritik sürüş/uyarı kartları, primary tab geçişleri | Yola yakın bakış alanı; bilgi baskın, tablar kolay erişilir. Temel Home/Geri için 1. bölge hedefi korunur |
| **3: sağ alt** | İkincil kontroller, listenin devamı, yolcu kontrolleri | Kritik sürücü eyleminin tek kopyası olamaz. Yolcu etkileşimi sürücünün ana kontrollerini kapatmaz |
| **4: sağ üst** | Saat, sıcaklık, Wi-Fi/BT/GPS durumu, pasif kamera/harita görüntüsü | Kapat, geri, ayarlar, menü, izin CTA'sı, tıklanabilir status chip, slider veya gizli gesture yok |

**Bölge sınıflaması fiziksel ekrana aittir.** Modal/drawer/kart içinde yeniden numaralandırılmaz. Sağ üstteki bir kamera görüntüsünün dokunarak tam ekran açılması da sürücü etkileşimidir ve yasaktır. Dikeyde 4. bölgenin küçük üst şeridi durum içindir; kalan görsel alan pasif olabilir, üzerine sürücü kontrolü bindirilemez. Kritik uyarı yalnız 4'te gösterilmez.

Uygulama için önerilen başlangıç bölme: sistem inset'leri çıktıktan sonraki kullanılabilir pencerenin orta dikey/yatay çizgileri. Bu %50 sınır bir **HEDEF mühendislik başlangıcıdır**; kodda mevcut bir zone engine değildir. Gerçek araçta erişim alanı ölçülüp daraltılır. Bir kontrol hedefi iki bölgeyi kesiyorsa daha zor bölgenin kuralına tabidir; 4'e taşan etkileşim kabul edilmez. Çoklu ekranda her display kendi koordinat ve kullanım rolüyle ölçülür; cluster, orta konsolun uzantısı sayılmaz.

## Minimum göz ayırma ilkeleri

- Tek bakışta eylem/durum anlaşılır: sabit konum, tanınır ikon + kısa etiket + birim. Yalnız renkle hata/aktiflik anlatılmaz.
- Home/Geri/iptal kalıcıdır. Menü veya içerik değişiminde bu hedefler yer değiştirmez; bildirim bunları örtmez. Kaydırma sonrası da görünürler.
- Uzun kurulum, profil, APK, tanılama, yüz kaydı ve metin girişi park kullanımına ayrılır. Hız verisi bilinmiyorsa karmaşık akışa otomatik sürüş izni verilmez. Mevcut kodda ortak hız/park kilidi **uygulanmış değildir**; bu refactor hedefidir.
- Scroll yalnız gövde içindedir; çift iç scroll ve gizli swipe temel eylemlerin yerine geçmez. Liste devamı 3'e uzayabilir; seçim/onay sürücü tarafında yapılır.
- Kritik bilgi kısa, sabit ve tekrar okunabilir olur. Animasyon önemli metni taşımaz, titretmez veya kapatmaz. Dekoratif splash/karşılama sürüş görevini geciktirmez.
- Bu sette sayısal göz ayırma süresi sınırı veya standart uygunluk sertifikası ileri sürülmez; ölçüm sürüş simülatörü/uygun donanım kabulünde planlanır.

## Ölçü ve dokunma sözleşmesi — HEDEF

| Öğe | Minimum hedef | Hedefler arası boşluk |
|---|---|---|
| Standart etkileşimli satır/ikon/seçenek | 56×56 **dp** | 12 dp |
| Home/Geri, primary/acil, sesli asistan | 64×64 **dp** | 16 dp |
| Kalıcı dock hücresi | En az 64×64 dp | 16 dp; sığmıyorsa ikincil öğeleri taşı |
| Modal kapat/iptal | En az 64×64 dp, ekran 1 veya 2 | 16 dp |
| Slider thumb erişim yüzeyi | En az 64 dp iki eksende; alternatif adım tuşları | 16 dp |

İstekteki 56×56 px / 64×64 px, Android'de yoğunluktan bağımsız **dp** olarak uygulanır: `px = round(dp × density)`. mdpi'de 56 dp=56 px, xhdpi'de 56 dp=112 px; metin `sp` ve fontScale ile hesaplanır. İkon çizimi 24/32/44 dp olabilir, dokunma kutusu bu boyutla karıştırılmaz. Yalnız `minHeight` yatay hedefi garanti etmez; ölçülmüş en/boy ve komşu hit rect'ler kontrol edilir. TouchDelegate kullanılırsa hedefler çakışmamalı ve 4'e taşmamalıdır.

## Mevcut adaptive yerleşimler — KOD

| Kaynak | Bugünkü davranış | HMI sonucu |
|---|---|---|
| `MainActivity.mountShell` | Landscape yatay LinearLayout: rail sonra mainContent. Portrait dikey LinearLayout: **rail önce**, içerik sonra | Portrait navigasyon üstte; bottom dock eksik |
| `computeSidebarWidthPx` | Ekran ≥960 dp: %30; ≥720: %31; ≥600: %32; altı %34. Sonuç 300–520 dp clamp | Mevcut sabit min, dar yatay pencerede içeriği daraltabilir; kullanılabilir pencereyle ölçüm gerekir |
| `SideRailBuilder` | Landscape dikey ScrollView, satır min 80 dp; portrait yatay kaydırılan ikon/etiketler | Sol rail yön olarak uygun; altın bölge için kalıcı Home/Geri bandı eksik |
| `FeatureGridLayout` | Kendi padding sonrası eni ≥840 dp ise 2, değilse 1 sütun; çocuk başına 6 dp dış margin (arada 12 dp) | Sırayı ve GONE atlamayı korur; etkileşimli sağ üst kartları bölge bakımından ayırmaz |
| Karşılama tasarım seçenekleri | `FeatureGridLayout(context, 520)` | Yalnız seçenek grubunun eniyle karar verir; tüm ekran breakpoint'i değildir |
| `TopBarBuilder.HeaderRow` | Normal içerik header'ı: solda sayfa başlığı/geri, sağda pasif marka. Launcher'da GONE | Widget'lar bu header'ın çocuğu değildir; normalde rail üstünde, tam ekranda saat kartı içinde |
| `StatusWidgetsView` | 12 dp aralık, min160×fontScale dp hücre; 1+ sütun; 68×max(1,fontScale) dp yükseklik; padding hesaba katılır | Rail üstünde sabit; tam ekranda ClockCardHeader sol üstündedir, kart etiketi sağ üsttedir |
| `LauncherDashboardBuilder` | Landscape quick/Lite/media-drive ağırlıkları .22/.56/.22; portrait dikey .28/.44/.28 | Portrait quick alanı tepede; sağ kartın tab/izin/medya eylemleri 4'e düşebilir |

MainActivity `configChanges=orientation|screenSize|keyboardHidden|uiMode` yönetir. Yön değişince kabuk ve launcher yeniden düzenlenir; çoğu normal sekme kaydırmayla korunur. Kamera ve VDBus bench Activity manifestte landscape kilitlidir. Dikey tam ürün desteği bu mevcut davranıştan çıkarılamaz.

## 16:9 ve ultra-wide — HEDEF şablonu

```text
+------------------+------------------------+-------------------------+
| 2: SOL SURUCU    | 2: manevra / uyari     | 4: saat / durum         |
| primary tabs    | bilgi agirlikli         | pasif harita / kamera   |
|                  +------------------------+-------------------------+
| 1: sabit         | 1: ana is / CTA         | 3: ikincil / yolcu      |
| Home Geri Ses    | surucu kontrolleri      | liste devami / medya    |
+------------------+------------------------+-------------------------+
```

ASCII şema göreli rolü gösterir; gerçek 1/2 ve 3/4 sınırları ekran ortalarıyla/ölçülmüş erişimle doğrulanır. Sol şerit içinde primary tablar üstte, Home/Geri/sesli asistan altta sabit bir ViewGroup'tadır. Menü listesi bu ikisinin arasında kayar. Alt bar tercih edilirse primary küme sol alta yığılır; tüm en boyunca eşit dağıtılıp sağa sürüklenmez.

Split-view'da interactive panel solda, izleme paneli sağdadır. Kontrol paneli toplam genişliğin sol yarısını aşarsa sürücü kontrolünün bir kısmı 3/4'e düşebilir; ağırlık oranı tek başına yeterli değildir. Ultra-wide'da ek genişlik pasif harita/görselleştirmeye verilir. Sağ üst panelde tab, overflow, tam ekran/kapat veya izin alma düğmesi olmaz. Medya izin CTA'sı sürücü panelinde, sürüş dışı akışta açılır.

Android uygulama önerisi: yatay shell; solda dikey `LinearLayout` (tabs ScrollView + sabit bottom actions), sağda bilgi/ikincil panel. Ana eylemler scroll gövdesinden ayrılır. Insets sonrası mevcut pencere ölçüsü kullanılır; `DisplayMetrics.widthPixels` tek başına çoklu pencere kullanılabilir alanı garantilemez. Mevcut 300–520 dp clamp yeni ortak breakpoint diye kopyalanmaz.

## 9:16 portrait — HEDEF şablonu

```text
+------------------------+-------------------------+
| 2: nav / kritik bilgi  | 4: salt okunur status   |
|                        |                         |
|           PASIF HARITA / GORSEL ALANI            |
+------------------------+-------------------------+
| 1: surucu ana isleri   | 3: ikincil/yolcu        |
| kisa eylem / CTA       | liste devami            |
+--------------------------------------------------+
| BOTTOM DOCK: Home | Geri | Ses | Primary | Diger   |
|              1 -> soldan saga oncelik -> 3        |
+--------------------------------------------------+
```

Dock en altta, sistem alt inset'inin üstünde, scroll'dan bağımsızdır. Sabit yükseklik yerine **en az 64 dp hücre + gerekli iç padding + inset** kullanılır. Home/Geri/sesli asistan ve primary hedefler sol yarıda kalmalıdır; beş eşit hücre örneği gerçek cihazda primary'yi sağa itiyorsa öğe azaltılır/ikincil menüye taşınır. Sağ uçta yalnız ikincil veya yolcu eylemleri bulunur. Ana erişim için yatay kaydırma zorunlu değildir.

Üst yarı harita/görsel bilgi, sol üstte kritik manevra içindir. Sağ üst yalnız durum/pasif görüntüdür. Araç ekranı dikey olduğunda sol üst her birincil eylem için uygun varsayılmaz; Home/Geri ve sürücü CTA'ları yine altın bölgededir. Ekran klavyesi/sistem diyaloğu dock'u örterse karmaşık işi park akışına al, ana sürücü erişimini kapatma.

## Modal, drawer ve geçici katmanlar

Kapat/Geri varsayılan olarak ekranın sol altındaki kalıcı eylem bandında; yer yetmezse sol üstte belirgin alternatif olur. Sağ üst `[X]` yasaktır. Dialog pozitif/negatif butonlarının platformdaki varsayılan sağ dizilimine güvenilmez. İptal sol tarafta erişilebilir; onay ayrı etiketli, 16 dp aralıklıdır. Scrim dokunuşu veya Android sistem geri hareketi tek kapatma yöntemi değildir.

Drawer sürücü tarafı sol kenardan açılır; kapatma hedefi ekran 1/2'de kalır. Bildirim 2'de kısa sürüş bilgisi veya 4'te pasif durum olabilir; eylem gerekiyorsa 1/2'de ayrı kontrol sunulur. Toast/splash kritik kontrolü örtmez. Yüzen hub sürükleme ve kaydedilmiş koordinat geri yükleme sonrası 1'e sabitlenir; 4'e sürükleyip bırakma engellenir. Bu sınırlamalar mevcut floating manager'larda henüz ortak bir ergonomi politikası değildir.

## Kabul matrisi

| Senaryo | Kontrol |
|---|---|
| 1920×1080 16:9 / 1920×720 ultra-wide | Yoğunluğu ayrıca kaydet; 1/2 erişimi, pasif 4 ve split-view hit rect'leri |
| 1080×1920 9:16 | Alt dock, soldan sağa önem, üst pasif alan, inset ve orientation dönüşü |
| 1024×720 dar / 840 ve 520 dp grup eşikleri | Font küçültmeden tek sütun, son eyleme scroll, minimum hedefler |
| Her iki tema, %100/%130 fontScale | Kesilmeyen kritik etiket, yeterli hücre yüksekliği, okunur durum ve gerçek arka plan kontrastı |
| Hızlı tab/tema/yön değişimi | Home/Geri sabit, üst şerit durumları korunur, eski callback yeni pencereyi kapatmaz |
| Dialog/drawer/overlay açık | 4'te tıklanabilir/odaklanabilir hedef yok; kapat/iptal 1/2'de |
| Bilinmeyen hız/veri veya OEM bağlantısız | Sahte canlı durum yok; karmaşık sürüş akışı serbest bırakılmaz |

Ölçümleri `getLocationOnScreen`/gerçek hit rect ve inset sonrası zone koordinatlarıyla kaydet. Sadece görsel benzerlik veya `Gravity.START` uygunluk kanıtı değildir; locale RTL olsa da LHD fiziksel sol/sürücü tarafı korunur. Bölge numaraları arayüz diline göre aynalanmaz.
