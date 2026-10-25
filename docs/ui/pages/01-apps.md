# Tab 1: Uygulamalar (Apps)

## Genel Bakış
* **Sekme İndeksi:** 1 (`TAB_APPS`)
* **Menü Adı (TR/EN):** Uygulamalar / Apps
* **Builder:** `AppsTabBuilder`
* **Kök View:** `ScrollView` / `GridLayout`

## Arayüz Hiyerarşisi
1. **Uygulama Izgarası (Grid):**
   * Araç ünitesinde yüklü Android uygulamalarının simgeleri ve başlıkları.
   * Geniş dokunmatik hedef alanı (minimum 64dp ikon boyutu).
2. **Hızlı Başlatma Etkileşimi:**
   * Tıklama: Uygulamayı ön plana getirir.
   * Uzun basma: Uygulama bilgisi veya kısayol atama seçenekleri.
