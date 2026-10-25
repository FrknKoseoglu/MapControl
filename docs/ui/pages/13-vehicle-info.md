# Tab 13: Araç Bilgileri (Vehicle Information)

## Genel Bakış
* **Sekme İndeksi:** 13 (`TAB_VEHICLE_INFO`)
* **Menü Adı (TR/EN):** Araç bilgileri / Vehicle information
* **Builder:** `VehicleInfoTabBuilder`
* **Kök View:** `ScrollView` / `LinearLayout`

## Arayüz Hiyerarşisi
1. **Canlı Telemetri Kartları (19 Sinyal / Formatter):**
   * Hız, Motor Devri (RPM), Batarya / Akü voltajı.
   * Lastik basınçları (TPMS) ve lastik sıcaklıkları.
   * Yakıt seviyesi, menzil, ortalama tüketim.
   * Dış ortam sıcaklığı, odometer (kilometre sayacı).
2. **Mühendislik & Tanı Menüsü:**
   * CAN Bus / VDBus ham veri izleme ve loglama seçenekleri.
