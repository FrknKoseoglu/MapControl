# Sport animasyonunu gizle — 21 Ekim 2026

Sürüş modu sayfasına varsayılanı kapalı, kalıcı “Sport animasyonunu gizle” seçeneği eklendi. MapControl arka plan servisi çalışırken konsoldan veya başka bir yoldan gerçekleşen gerçek sürüş modu geçişlerini izler. Kayıtlı açılış tercihi veya MapControl mod düğmesinin seçili görünmesi tetikleyici olarak kullanılmaz.

## Davranış

- Seçenek kapalıyken ek araç modu okuması veya OEM yayını yapılmaz.
- Açıldığında ilk geçerli okuma yalnız başlangıç durumunu belirler. Zaten Sport'ta başlamak veya Sport'ta yeniden bağlanmak işlem başlatmaz.
- Bilinen başka bir moddan Sport'a geçildikten sonra en az 600 ms beklenir. Sonraki taze okumada hâlâ Sport ise yalnız bir pencere kapatma isteği gönderilir.
- Aynı Sport oturumunda tekrar gönderilmez; sürekli yayın veya yeniden deneme döngüsü yoktur.
- Sport'tan çıkmak, bağlantının kesilmesi, bilinmeyen veri, seçeneğin kapatılması ya da servisin kapanması bekleyen işlemi iptal eder. Hızlı kapat/aç da önceki bekleyen işlemi devralmaz.
- Mevcut servis çalışanında 500 ms aralıklı kontrol kullanılır; bekleme dahil normal koşullarda ilk geçiş örneğinden yaklaşık 1 saniye sonra istek oluşur. Yeni bir servis veya iş parçacığı oluşturulmaz.
- İstek yalnız `com.desaysv.vehiclesetting` paketine `com.desaysv.systemui.ACTION_DIALOG_CLOSE` eylemiyle gider. Araç modunu yazan bir API çağrısı, paket devre dışı bırakma veya kaldırma işlemi yoktur.
- Kayıtlarda yalnız “kapatma isteği gönderildi” yazılır; OEM penceresinin gerçekten kapandığı iddia edilmez.

## Sınırlar

Bu eylem aynı OEM paketindeki başka pencerelerde de kullanılıyor. Sayfada bu yan etki açıkça belirtilir. Fiziksel araçta pencere sahibi ve istek sonucu henüz doğrulanmadığı için özellik varsayılan olarak kapalıdır. İlk deneme araç park halindeyken, seçenek açıldıktan sonra normal moddan Sport'a geçilerek yapılmalıdır. Sport'ta kalan bir pencereyi anında kapatma düğmesi eklenmedi.

## Doğrulama

65 birim testi ve ilgili 14 Android testi başarılı. Dokuz yeni politika testi: başlangıç Sport durumu, bilinen modlardan Sport'a geçiş, bekleme eşiği, yinelenen veriler, Sport'tan erken çıkış, bağlantı kaybı, bilinmeyen değerler, kapatma ve yeniden etkinleştirme, yeni Sport oturumları. Android testleri gerçek araç yerine kontrollü mod örnekleriyle tam paket/eylem eşleşmesini, tek yayını, iptali, varsayılanı ve seçimin kalıcılığını doğrular. Önceki başlık, kompakt kontroller ve mod görünümü testleri de geçti.

1920×720 Android 11 inceleme emülatöründe yeni dairesel seçicinin görünümü ve açma/kapatma kontrol edildi. Normal uygulama kimliği `com.mapcontrol`; inceleme kimliği yalnız emülatör için kullanıldı.

Önizleme: [Kapalı](ui-previews/sport-animation/sport-animasyonu-kapali.png), [Etkin](ui-previews/sport-animation/sport-animasyonu-etkin.png).
