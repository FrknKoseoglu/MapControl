# Tab 9: Kamera 360 (Surround View & DVR)

## Genel Bakış
* **Sekme İndeksi:** 9 (`TAB_CAMERA_360`)
* **Menü Adı (TR/EN):** Kamera 360 / Camera 360
* **Builder:** `Camera360TabBuilder`
* **Kök View:** `FrameLayout` (İç içe görünüm geçişleri için container)

---

## İç İçe Sayfa (Nested Subpage) Mimarisi

Kamera 360 sekmesi, kendi içinde 5 farklı çalışma moduna ve alt sayfaya sahiptir:

```
[Camera360 Ana Menü] (Mode.MENU)
   ├── [Canlı Önizleme] (Mode.PREVIEW) -> 4 kamera canlı mozaik akışı
   ├── [USB Kaydı] (Mode.RECORD) -> Manuel kayıt kontrolleri, çözünürlük, USB depolama
   ├── [Otomatik Kayıt] (Mode.AUTO) -> Kontak ile otomatik kayıt ayarları
   └── [Kayıtlar / Klip Oynatıcı] (Mode.VIEWER) -> Video listesi, oynatma, USB kopyalama
```

### 1. Ana Menü (`Mode.MENU`)
* 4 ana eylem kartı: Canlı Önizleme, USB Kaydı, Otomatik Kayıt, Kayıtları Gör.
* USB depolama durumu ve depolama alanı bilgisi.

### 2. Canlı Önizleme (`Mode.PREVIEW`)
* 4 açının (Ön, Arka, Sol, Sağ) tek bir `SurfaceView` üzerinde canlı gösterimi.
* Çift tıklama ile tam ekran/geniş ekran geçişi.

### 3. USB Manuel Kayıt (`Mode.RECORD`)
* "Kaydı Başlat / Durdur" butonları.
* Çözünürlük seçimi (1080p / 720p / 480p).
* 4 açıyı ayrı dosyalara kaydetme seçeneği.

### 4. Otomatik Kayıt (`Mode.AUTO` - `Camera360AutoView`)
* Kontak açıldığında (PowerMode 2) otomatik kayıt başlatma politikası.
* Döngüsel kayıt (eski klipleri otomatik silme) ve acil durum koruması.

### 5. Klip Oynatıcı (`Mode.VIEWER` - `Camera360ClipViewer`)
* Kayıtlı kliplerin tarih/saat sırasına göre listesi.
* Çoklu seçim, toplu silme, USB belleğe dışa aktarma (Copy to USB).
* Video oynatıcı görünümü (`VideoView` / `TextureView`).

---

## Header Sabit Geri Butonu Entegrasyonu

Kamera 360 gibi derinlikli/iç içe sayfaların bulunduğu sekmeler için Header bileşeni özel bir davranış sergiler:

1. **Sabit Geri Butonu (`header_back_button`):**
   * Kamera 360 sekmesi aktif olduğunda, sol panel açık olsa dahi **Header başlığının solunda sabit bir geri butonu** yer alır.
   * Dokunmatik ergonomi için minimum `44x44 dp` boyutunda ve araç içi `ic_vehicle_back` ikonuna sahiptir.
2. **Geriye Doğru Hiyerarşik Çıkış:**
   * Kullanıcı alt sayfalardayken (`PREVIEW`, `RECORD`, `AUTO`, `VIEWER`), geri butonuna bastığında alt sayfadan çıkılır ve Kamera 360 ana menüsüne dönülür (`enterMenu()`).
   * Kullanıcı Kamera 360 ana menüsündeyken geri butonuna basarsa, Launcher ana sayfasına dönüş yapılır.
   * Android donanım/sistem geri tuşu (`MainActivity.onBackPressed()`) de birebir aynı hiyerarşiyi çalıştırır (`camera360TabBuilder.handleBackPressed()`).
3. **Dinamik Başlık Güncellemesi:**
   * Alt sayfalara girildiğinde Header başlığı otomatik olarak ilgili alt sayfa adıyla (örn. `Canlı Önizleme`, `USB Kaydı`, `Kayıtlar`) güncellenir; ana menüye dönüldüğünde tekrar `Kamera 360` olur.
