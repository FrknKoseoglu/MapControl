# MapControl test-fk — Linux Mint kurulumu

APK: `MapControl-test-fk-1.2.4.apk` · Uygulama adı: **MapControl test-fk** · Paket: `com.mapcontrol.testfk`.

## Kurulum

Aracı Mint bilgisayara bağla, araçta USB hata ayıklama bağlantısını onayla ve Mint terminalinde çalıştır:

```bash
cd ~/dev/omoda
bash scripts/araclar/kur-test-fk.sh
```

Betik PATH veya `~/Android/Sdk/platform-tools/adb` içindeki ADB'yi bulur. Bulunamazsa önce `sudo apt install adb` çalıştır. Betiği sudo ile çalıştırmak gerekmez.

Birden fazla cihaz bağlıysa `adb devices` çıktısındaki araç seri numarasını kullan:

```bash
bash scripts/araclar/kur-test-fk.sh --serial ARAC_SERI_NUMARASI
```

`--user 10` belirli Android kullanıcısını seçer; varsayılan etkin kullanıcıdır. `--no-launch` kurulum sonunda uygulamanın açılmasını engeller. Yalnız emülatör bağlıysa açıkça `--serial emulator-PORT` verilmesi gerekir.

## Yapılan işlemler

1. APK SHA-256 özetini ve hedef cihazı doğrular; test-fk paketini kurar veya günceller.
2. Konum, kamera, mikrofon ve uygulamanın istediği diğer izinleri kontrol eder; Android'in izin verdiği yetkileri vermeyi dener ve sonucu tekrar okur.
3. Üstte gösterim, kullanım erişimi, paket kurulumu, dosya erişimi ve sistem ayarı erişimini AppOps üzerinden doğrular.
4. Erişilebilirlik servisi, bildirim dinleyicisi ve pil optimizasyonu muafiyetini etkinleştirmeyi dener.
5. Sonucu `devices/omoda/logs/YYYY/MM/log-YYYY-MM-DD.md` dosyasına ekler ve uygulamayı açar.

Mevcut MapControl kaldırılmaz, verileri silinmez. Önceden etkin erişilebilirlik servisleri ve bildirim dinleyicileri korunur. Bu ayrı kimliğin kendi ayarları/izinleri vardır. Gelecekte aynı paket ve aynı imzayla üretilen APK bunun üzerine güncellenebilir; imzalama anahtarı dağıtım klasörüne eklenmemiştir.

## Verilemeyen yetkiler ve cihaz sahipliği

OEM/platform imzası isteyen veya cihazda tanımlı olmayan izinler otomatik verilemeyebilir. Betik bunları başarılı göstermeden raporlar; fiziksel araçtaki sonuç kendi Android/OEM yazılımına bağlıdır. AppOps yetkileri `pm grant` ile verilmez; ayrı doğrulanır.

Cihazda mevcut bir device owner varsa korunur. Varsayılan kurulum yeni owner atamaz. Cihaz sahibi hiç yoksa ve Android koşulları uygunsa, ayrıca `--device-owner` seçeneğiyle test-fk için atama denenebilir. Betik mevcut sahibin kaldırılması, uygulama kaldırma, veri temizleme veya fabrika sıfırlama yapmaz.

Çıkış kodları:

- `0`: Yapılan kontroller doğrulandı.
- `2`: APK kuruldu; bazı izin/işlemler verilemedi, raporu incele.
- `1`: Bağlantı, APK doğrulaması veya kurulum hatası. Kurulum hatasında izin işlemlerine geçilmez.

## Doğrulama ve sürüm eşleşmesi

Bu APK'nın SHA-256 özeti:

```text
0a9edb1460e62834525f31265d9865260bf5049efa8c96c2e52e3aa2305c85ac
```

Yeni APK üretildiğinde kurulum betiğindeki dosya adı ve SHA-256 özeti de birlikte güncellenmelidir. Dosya değişmişse betik durur.

Mint üzerinde sahte ADB ile 8 senaryo test edildi: çoklu cihaz ve yetkisiz cihaz seçimi, kurulum hatası, izin reddi, mevcut listelerin korunması, tekrar çalıştırma, farklı Android kullanıcısı, liste okuma hatası ve geçersiz APK/girdi. Gerçek Android 11 emülatöründe de kurulum/izin işlemleri denendi. Fiziksel araçta bu hazırlık sırasında kurulum yapılmadı.

Testi MapControl kaynak deposundan tekrar çalıştırmak için:

```bash
python3 scripts/tests/test_install_testfk.py --script scripts/araclar/kur-test-fk.sh --apk /home/oem/dev/omoda/MapControl-test-fk-1.2.4.apk -v
```
