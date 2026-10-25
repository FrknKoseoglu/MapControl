# Tab 12: Gösterge Ekranı (Projection / Cluster Display)

## Genel Bakış
* **Sekme İndeksi:** 12 (`TAB_PROJECTION`)
* **Menü Adı (TR/EN):** Gösterge ekranı / Instrument display
* **Builder:** `ProjectionTabBuilder`
* **Kök View:** `ScrollView` / `LinearLayout`

## Arayüz Hiyerarşisi
1. **Cluster Yansıtma Kontrolü:**
   * Gösterge ekranına (QNX / Cluster) navigasyon/uygulama yansıtma açma/kapatma.
   * Hedef yansıtma uygulaması seçimi (Google Maps, Yandex Navigasyon vb.).
2. **Jest & Sistem Kısayolları (`CarSystemGestureController`):**
   * Üç parmak kaydırma hareketiyle yansıtma tetikleme.
   * Direksiyon tuşu veya harici tetikleyici eşlemeleri.
