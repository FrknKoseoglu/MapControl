# Tab 7: Otomasyon (Climate Automation)

## Genel Bakış
* **Sekme İndeksi:** 7 (`TAB_CLIMATE_AUTOMATION`)
* **Menü Adı (TR/EN):** Otomasyon / Automation
* **Builder:** `ClimateAutomationTabBuilder`
* **Kök View:** `ScrollView` / `LinearLayout`

## Arayüz Hiyerarşisi
1. **Sürücü Koltuk Otomasyonu:**
   * Isıtma ve Havalandırma otomatik sıcaklık eşikleri.
   * Çakışma önleme mantığı (Isıtma ve havalandırma aynı anda etkin olamaz).
2. **Yolcu Koltuk Otomasyonu:**
   * Yolcu koltuğu için bağımsız sıcaklık eşikleri ve kademe seçimleri.
3. **Cam & Direksiyon Otomasyonu:**
   * Arka cam rezistansı, ön cam buğu çözme ve direksiyon ısıtma otomasyonu.
