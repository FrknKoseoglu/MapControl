# Tab 14: Ayarlar (Settings)

## Genel Bakış
* **Sekme İndeksi:** 14 (`TAB_SETTINGS`)
* **Menü Adı (TR/EN):** Ayarlar / Settings
* **Builder:** `SettingsTabBuilder`
* **Kök View:** `ScrollView` / `FeatureGridLayout`

## Arayüz Hiyerarşisi ve Seçenekler

1. **Launcher Modu (Launcher Mode):**
   * MapControl'ün araç ana başlatıcısı (Home Launcher) olarak davranmasını açar/kapatır.
2. **Yüzen Geri Düğmesi (Floating Back Button):**
   * Diğer uygulamaların üzerinde her zaman görünen taşınabilir geri butonu açma/kapatma ve şeffaflık ayarları.
3. **Karanlık Mod (Dark Mode):**
   * Açık / Koyu tema seçimi veya araç far durumuna göre otomatik tema.
4. **Header Menü Konumu (Header Menu Position):**
   * **Yukarıda (Varsayılan):** Header menüsü ekranın en üstünde yer alır.
   * **Aşağıda:** Header menüsü ekranın en altında yer alır.
   * *Rerender Koruması:* Değişiklik anında Activity recreate edilmeden doğrudan ViewGroup sırası güncellenir; ekran kırpışması (flicker) yaşanmaz.
5. **Açılış Servis & UI Başlatma (Boot Start):**
   * Kontak açılışında arka plan servisinin ve arayüzün otomatik başlaması ayarları.
6. **Dil Seçimi (Language):**
   * Türkçe / İngilizce geçişi.
7. **Araç Görsel Modeli:**
   * Launcher dashboard üzerinde gösterilecek 2D / 3D araç modelinin seçimi (Chery Omoda 5 Pro, Tiggo, vb.).
8. **Gelişmiş Seçenekler (Advanced Settings - Gizlenebilir Alan):**
   * Gerekli sistem izinleri durumu (Erişilebilirlik, Bildirim dinleyici, Sistem pencereleri).
   * Uygulama sürüm bilgisi ve MapControl kaldırma butonu.
