# Tab 5: Ses Ayarları (Equalizer & Sound Boost)

## Genel Bakış
* **Sekme İndeksi:** 5 (`TAB_EQUALIZER`)
* **Menü Adı (TR/EN):** Ses ayarları / Sound settings
* **Builder:** `EqualizerTabBuilder`
* **Kök View:** `ScrollView` / `LinearLayout`

## Arayüz Hiyerarşisi
1. **Loudness / Ses Güçlendirme:**
   * Loudness Enhancer açma/kapatma ve kazanç (gain dB) kaydırıcısı (`SeekBar`).
   * Canlı ses boost göstergesi.
2. **Ekolayzer Bantları:**
   * Bas, Orta ve Tiz frekans ayarları.
   * Hazır ses profilleri (Rock, Pop, Jazz, Konuşma, Düz).
