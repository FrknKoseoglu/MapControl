# Tab 15: Sistem Günlüğü (Developer Log)

## Genel Bakış
* **Sekme İndeksi:** 15 (`TAB_LOG`)
* **Erişim Yolu:** Header başlığına peş peşe 3 kez dokunulduğunda açılan gizli geliştirici sekmesi.
* **Builder:** `LogTabBuilder`
* **Kök View:** `ScrollView` / `TextView`

## Arayüz Hiyerarşisi
1. **Günlük Akışı (Log Console):**
   * Servis olayları, CAN mesajları, UI geçişleri ve hata kayıtlarının canlı terminal görünümü.
2. **Eylemler:**
   * Günlüğü temizle (Clear).
   * Günlüğü panoya kopyala / dışa aktar.
