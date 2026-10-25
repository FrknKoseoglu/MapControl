# Global Chrome: Header, SideRail & Sistem Kontrolleri

Bu belge, MapControl'ün tüm sekmelerinde paylaşılan ortak çerçeve (chrome) bileşenlerini tanımlar.

---

## 1. Sol Panel (`SideRailBuilder`)

### Boyutlandırma ve Ergonomi (%10 Daraltılmış Tasarım)
* **Minimum Genişlik (`rail_min_width`):** `270dp` (Önceki: `300dp`, %10 daraltıldı).
* **Maksimum Genişlik (`rail_max_width`):** `468dp` (Önceki: `520dp`, %10 daraltıldı).
* **Ekran Genişlik Oranları (`computeSidebarWidthPx`):**
  * `screenWidthDp >= 960`: `%27` (Önceki: %30)
  * `screenWidthDp >= 720`: `%27.9` (Önceki: %31)
  * `screenWidthDp >= 600`: `%28.8` (Önceki: %32)
  * `screenWidthDp < 600`: `%30.6` (Önceki: %34)
* **İçerik Yapısı:**
  * Üst kısım: `StatusWidgetsView` (Wi-Fi, Sürüş Hafızası, Telefon, Gösterge durum kutuları).
  * Kaydırılabilir menü: Dikey sıralı büyük dokunmatik menü butonları (Minimum 80dp satır yüksekliği).

---

## 2. Üst / Alt Başlık Çubuğu (`TopBarBuilder`)

### Bileşenler
1. **Geri Butonu (`header_back_button`):**
   * 44x44 dp minimum dokunma alanı.
   * Kamera 360 gibi iç içe sayfaların olduğu sekmelerde veya tam ekran modunda **sabit olarak görünür**.
   * Hiyerarşik gezinme: Önce alt sayfadan çıkar, ana menüdeyken Launcher'a döner.
2. **Başlık Alanı (`header_title`):**
   * Aktif sekmenin başlığını gösterir (Örn: `Aktar`, `Sürüş Hafızası`, `Kamera 360`).
   * İç içe sayfalara girildiğinde alt sayfa adını yansıtır.
   * Gizli tetikleyici: Başlığa 3 kez hızlı dokunulduğunda Sistem Günlüğü (Tab 15) açılır.
3. **Marka & Sürüm Bilgisi (`header_brand`):**
   * `MapControl by vNoisy (vX.X.X)` salt okunur marka alanı.
4. **Ayırıcı Çizgi (`topBarDivider`):**
   * Header üstteyken header'ın altında, alttayken header'ın üstünde konumlanarak içerikle sınırı çizer.

### Konumlandırma & Rerender Koruması
* `Ayarlar` üzerinden **Yukarıda** veya **Aşağıda** seçilebilir.
* `MainActivity.applyHeaderPosition(boolean isBottom)` metodu Activity'yi yeniden başlatmadan sadece görünüm sırasını değiştirir.

---

## 3. Sistem Üst Katmanları (Overlays)

* **Yüzen Geri Düğmesi (`FloatingBackButtonManager`):**
  * Diğer Android uygulamaları üzerinde yüzen pencere olarak çalışır.
* **Cluster Navigasyon Katmanları:**
  * Google Maps (`GoogleMapsClusterNavOverlay`) ve Yandex (`YandexClusterNavOverlay`) bildirim ve gösterge entegrasyonu.
