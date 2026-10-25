# Tab 0: Wi-Fi Yönetimi

## Genel Bakış
* **Sekme İndeksi:** 0 (`TAB_WIFI`)
* **Menü Adı (TR/EN):** Wi-Fi
* **Builder:** `WifiTabBuilder`
* **Kök View:** `ScrollView` / `LinearLayout`

## Arayüz Hiyerarşisi
1. **Durum Kartı (Glass Card):**
   * Canlı Wi-Fi açma/kapatma anahtarı (Switch).
   * Bağlı ağ SSID, IP adresi ve sinyal gücü göstergesi.
2. **Kayıtlı Ağlar Listesi:**
   * Bilinen ve kapsama alanındaki Wi-Fi ağları.
   * Şifre giriş ve bağlantı diyalogları.
3. **Hotspot / Erişim Noktası:**
   * Araç hotspot kontrolü ve yapılandırması.

## Durum & Olaylar
* `WifiState` (CONNECTED, DISCONNECTED, OFF, UNKNOWN) Header durum rozetine yansıtılır.
* Ağ tarama döngüsü sekme görünür olduğunda etkinleşir.
