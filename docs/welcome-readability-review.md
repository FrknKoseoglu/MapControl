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
