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
