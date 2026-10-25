# Tab 10: Launcher Dashboard

## Genel Bakış
* **Sekme İndeksi:** 10 (`TAB_LAUNCHER`)
* **Menü Adı:** Ana Ekran / Launcher Dashboard
* **Builder:** `LauncherTabBuilder` + `LauncherDashboardBuilder`
* **Kök View:** `ScrollView` / `FrameLayout`

## Arayüz Hiyerarşisi
1. **Sol Panel: Hızlı Erişim Kartı (Saat & Canlı Rozetler):**
   * Dijital saat ve tarih.
   * Canlı Wi-Fi, Sürüş Hafızası, Telefon sunucusu ve Gösterge bağlantı durum kutuları (`StatusWidgetsView`).
2. **Orta Panel: 3D / 2D Araç Modeli (`VehicleModelHostView`):**
   * OEM material araç modeli veya 2D frame animasyonu.
   * Canlı kapı açık/kapalı durumları ve far durumları.
3. **Alt / Sağ Panel: Hızlı Kısayol Grid'i:**
   * Wi-Fi, Aktar, Sürüş Hafızası, Kamera 360, Bölünmüş Ekran, Ayarlar gibi sık kullanılan sekmelere doğrudan tek dokunuşla geçiş ikonları.

## Tam Ekran / Sürükleyici Mod
* Launcher modu etkinleştirildiğinde sol menü (`SideRail`) ve üst başlık (`TopBar`) gizlenir; içerik ekranın tamamına yayılır (`ImmersiveFullscreenHelper`).
