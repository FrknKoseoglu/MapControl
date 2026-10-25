# Tab 8: Yüz Tanıma (DMS Face Recognition)

## Genel Bakış
* **Sekme İndeksi:** 8 (`TAB_FACE_RECOGNITION`)
* **Menü Adı (TR/EN):** Yüz Tanıma / Face Recognition
* **Builder:** `DmsFaceRecognitionTabBuilder`
* **İlişkili Activity:** `DmsFaceProfileActivity`
* **Kök View:** `ScrollView` / `LinearLayout`

## Arayüz Hiyerarşisi
1. **DMS Yüz Tanıma Servis Kartı:**
   * Kabin içi DMS kamerası üzerinden sürücü profilini algılama açma/kapatma anahtarı.
2. **Profil Listesi & Koltuk Hafızası Eşleme:**
   * Kayıtlı sürücü profilleri kartları.
   * Her profile ait isim, fotoğraf, koltuk pozisyon numarası (1/2/3).
3. **Yeni Profil Ekle / Düzenle:**
   * Tıklama ile `DmsFaceProfileActivity` açılır.
   * Canlı kamera önizlemesi ve yüz kaydetme sihirbazı.
