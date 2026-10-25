# Tab 6: Sürüş Hafızası (Drive Memory)

> **Güncelleme:** Önceki "Sürüş modu" / "Drive mode" sekmesi kullanıcı isteğiyle **Sürüş Hafızası / Drive Memory** olarak yeniden adlandırılmıştır. Super Mod animasyonu gizleme mekanizması bu daldan kaldırılmış olup `feature/super-mode-animation` dalında bağımsızlaştırılmıştır.

## Genel Bakış
* **Sekme İndeksi:** 6 (`TAB_DRIVE_MODE`)
* **Menü Adı (TR/EN):** Sürüş Hafızası / Drive Memory
* **Builder:** `DriveModeTabBuilder` (Sürüş modu seçimleri) + `AssistTabBuilder` (Açılışta kapatılacak sürüş destekleri)
* **Kök View:** `ScrollView` / `LinearLayout`

## Arayüz Hiyerarşisi
1. **Sürüş Modu Kartları (Drive Modes):**
   * ECO, NORMAL, SPORT mod kartları.
   * Genişletilebilir 4x4 arazi modları: SNOW, MUD, OFFROAD, SAND.
   * Seçilen mod `driveModeSetting` anahtarıyla kalıcı hafızaya alınır.
   * Seçili mod anlık olarak Header ve Launcher widget'larına yansıtılır.
2. **Sürüş Asistanları (Açılışta Kapatma Tercihleri - `AssistTabBuilder`):**
   * ISS (Start/Stop)
   * Hız Limitleyici (Speed Limiter)
   * LDW (Şerit Takip Uyarısı)
   * LDP (Şeritte Kalma Yardımı)
   * FCW (Ön Çarpışma Uyarısı)
   * AEB (Otomatik Acil Frenleme)
   * CPD (Çocuk Varlık Algılama)
   * DMS (Sürücü Dikkat Monitörü)

## Başlık & Menü Senkronizasyonu
* Header başlığı: `Sürüş Hafızası` (`R.string.side_rail_drive_mode`).
* Sol panel ve launcher kısayolları bu başlıkla hizalanmıştır.
