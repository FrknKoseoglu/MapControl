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
