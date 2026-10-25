# Tab 11: Bölünmüş Ekran (Split Screen)

## Genel Bakış
* **Sekme İndeksi:** 11 (`TAB_SPLIT_SCREEN`)
* **Menü Adı (TR/EN):** Bölünmüş Ekran / Split Screen
* **Builder:** `SplitScreenTabBuilder`
* **Kök View:** `ScrollView` / `LinearLayout`

## Arayüz Hiyerarşisi
1. **Bölünmüş Ekran Konfigürasyon Kartı:**
   * Birinci ve İkinci uygulama seçimi (Uygulama seçici diyalogları).
   * Bölünme oranı seçimi (%50 / %50, %70 / %30, %30 / %70).
2. **Başlatma & Kontrol:**
   * "Bölünmüş Ekranı Başlat" butonu.
   * Dikey ekranda alt-alta, yatay ekranda yan-yana çalıştırma mantığı.
