# Tab 2: Aktar (Transfer)

> **Güncelleme:** Önceki "Telefondan yönet" / "Phone access" alanı kullanıcı isteği doğrultusunda **Aktar / Transfer** olarak yeniden adlandırılmıştır.

## Genel Bakış
* **Sekme İndeksi:** 2 (`TAB_WEB` / `TAB_FILE_UPLOAD`)
* **Menü Adı (TR/EN):** Aktar / Transfer
* **Builder:** `FileUploadTabBuilder`
* **Kök View:** `ScrollView` / `LinearLayout`

## Arayüz Hiyerarşisi
1. **QR Kod ve Hızlı Erişim Kartı:**
   * Araç dahili web sunucusuna bağlanmak için büyütülmüş (2.5x) QR kod görseli.
   * Doğrudan erişim IP adresi ve port bilgisi (`http://<ip>:8080`).
2. **Web Sunucu Kontrolü:**
   * Sunucu Aç / Kapat geçiş butonu (`btnWebServerToggle`).
   * Canlı sunucu durumu ve etkin bağlantı sayısı.
3. **Dosya Aktarım & Yönetim:**
   * Telefondan gönderilen APK, medya veya yapılandırma dosyalarının listesi.
   * APK doğrudan kurulum butonu ve dosya temizleme işlemleri.

## Başlık & Gezinme Davranışı
* Header başlığı: `Aktar` (`R.string.side_rail_web`).
* Sol panel menüsü ve Launcher kısayol etiketi senkronize olarak `Aktar` adını kullanır.
