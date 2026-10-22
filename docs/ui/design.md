# Tasarım dili ve görsel tokenlar

[İndeks](README.md) · [Ergonomi](ergonomics-and-layout.md) · [Kod ifadeleri](source-inventory.md)

**MEVCUT değerler** çalışma ağacından çıkarılmıştır; **HEDEF** etiketli kurallar yeni LHD HMI standardıdır. Native arayüzün token adresleri `R.color.ad`, XML'de `@color/ad`; boyutlar `R.dimen.ad` / `@dimen/ad` biçimindedir. Uydurma CSS değişkeni veya projede bulunmayan Secondary tokenı oluşturulmamıştır.

## Tasarım dili ve semantik roller

Koyu/açık OEM yüzeyler, yumuşak mavi vurgu, cam dolgulu yuvarlak kartlar, simge ve kısa etiket, renk + metinle durum anlatımı kullanılır. Cam yüzey gerçek backdrop blur değildir; temel görünüm alfa dolgulu drawable'dır. Gerçek sürüş modları kendi rengiyle ayrılır; kayıtlı başlangıç seçimi ayrı bir tercihtir.

| Rol | Gerçek tokenlar | Açık / koyu ana değer |
|---|---|---|
| Background | `backgroundColor`, `backgroundPage`, `root_gradient_*` | `backgroundColor`: #E8ECF3 / #10141D |
| Surface | `surfaceColor`, `surfaceBar`, `surfaceCard{,Inner,Elevated}`, `surfaceRail`, `glassFill` | `surfaceColor`: #FFFFFF / #161B22 |
| Primary | `primaryColor`, `oemAccent`, `buttonPrimary` | `primaryColor`: #3568A0 / #4586C5 |
| Secondary eylem | `buttonNeutral`, `buttonSecondaryMuted`, `surfaceCardInner` + `textPrimary` | Ayrı bir `secondaryColor` yok; nötr roller kullanılır |
| Accent / seçim | `accentColor`, `accentHighlight`, `selectionBar`, `assistStrokeActive` | `accentColor`: #4586C5 / #73B2F5 |
| Ana metin | `textPrimary`, `textSecondary`, `textHint` | `textPrimary`: #171A20 / #FFFFFF |
| Başarı / uyarı / hata / bilgi | `statusSuccess`, `statusWarning`, `statusError`, `statusInfo` | Tam eşleştirmeler aşağıda |
| Destructive | `buttonDanger`, `buttonDestructiveBg`, `textDestructive` | İşlem türünü etiket/ikonla da anlat |
| Navigasyon | `cluster_nav_card_*`, `cluster_nav_maneuver_*`, `cluster_nav_eta_*` | Gösterge kartlarının semantik renkleri |
| Bağlama özel | `automation*`, `log*`, `wifi_toast_*`, `boot_splash_*`, `map_grid_*` | Başka işlevlere rastgele taşınmaz |

## Renk biçimleri ve paletin tamamı — MEVCUT

Kaynaklar: [values/colors.xml](../../app/src/main/res/values/colors.xml), [values-night/colors.xml](../../app/src/main/res/values-night/colors.xml). **156 aynı adlı token**, her temada ayrı değer. Aşağıdaki tablo tüm tanımları içerir; aynı değeri paylaşmaları semantik olarak aynı token oldukları anlamına gelmez.

Android sekiz haneli renk sırası **#AARRGGBB**'dir, CSS #RRGGBBAA değildir. Örneğin `#2818212C` → A=40/255≈0.157, RGB(24,33,44); `#CCF0F3F8` → A=0.8, RGB(240,243,248). Opak #3568A0 → RGB(53,104,160); #73B2F5 → RGB(115,178,245). Native kaynaklarda HSL tanımı yoktur. HTML/CSS yüzeyindeki `rgba()` ve hex'ler [kaynak envanterinde](source-inventory.md) ayrı tutulur; CSS hex8 ile Android hex8 birbirine çevrilmeden kopyalanmaz.


| `R.color` / `@color` adı | Açık (values) | Koyu (values-night) | Semantik kullanım |
|---|---|---|---|
| `oemAccent` | `#3568A0` | `#73B2F5` | OEM ortak vurgu |
| `oemAccentPressed` | `#2B5582` | `#5A9FE8` | OEM basılı vurgu |
| `glassFill` | `#CCF0F3F8` | `#2818212C` | Cam kart alfa dolgusu |
| `glassStroke` | `#503568A0` | `#4073B2F5` | Cam kart çerçevesi |
| `segmentTrackFill` | `#D0D6E0` | `#2A3440` | Segment zemini |
| `surfaceRail` | `#DCE3ED` | `#121821` | Sürücü menüsü zemini |
| `outlineSubtle` | `#14000000` | `#14FFFFFF` | Hafif çerçeve/ayırıcı |
| `tabContentScrim` | `#00000000` | `#00000000` | Sekme içeriği örtüsü |
| `root_gradient_start` | `#EDF1F6` | `#10141D` | Kök gradient: start |
| `root_gradient_center` | `#E6EBF2` | `#121922` | Kök gradient: center |
| `root_gradient_end` | `#D8E1ED` | `#0B0F14` | Kök gradient: end |
| `nav_pill_selected_start` | `#D2E3F5` | `#3D4A5C` | Seçili menü gradient: start |
| `nav_pill_selected_end` | `#B9D0EE` | `#252E3D` | Seçili menü gradient: end |
| `primaryColor` | `#3568A0` | `#4586C5` | Material birincil |
| `primaryDarkColor` | `#2B5582` | `#3568A0` | Material koyu birincil |
| `primaryLightColor` | `#4586C5` | `#73B2F5` | Açık birincil tonu |
| `accentColor` | `#4586C5` | `#73B2F5` | Material vurgu |
| `accent` | `#3568A0` | `#4586C5` | Vurgu alias |
| `accentPressed` | `#2B5582` | `#3568A0` | Basılı vurgu alias |
| `accentHighlight` | `#4586C5` | `#73B2F5` | Parlak vurgu |
| `filament_scene_clear` | `#F0F3F8` | `#18212C` | 3D sahne temizleme rengi |
| `backgroundColor` | `#E8ECF3` | `#10141D` | Pencere/kök taban |
| `backgroundPage` | `#E2E8F0` | `#10141B` | Sayfa tabanı |
| `surfaceColor` | `#FFFFFF` | `#161B22` | Genel yüzey/diyalog |
| `surfaceBar` | `#F4F6FA` | `#141A24` | Üst şerit |
| `surfaceCard` | `#FFFFFF` | `#151A22` | Kart |
| `surfaceCardInner` | `#F0F3F9` | `#1A212C` | Kart içi yüzey |
| `surfaceCardElevated` | `#DDE4EF` | `#1C2630` | Yükseltilmiş yüzey |
| `cluster_nav_card_fill` | `#F4F6FA` | `#1E2834` | Cluster navigasyon: card / fill |
| `cluster_nav_card_stroke` | `#9AABC4` | `#4A5C72` | Cluster navigasyon: card / stroke |
| `cluster_nav_maneuver_start` | `#3D72B8` | `#3D72B8` | Cluster navigasyon: maneuver / start |
| `cluster_nav_maneuver_end` | `#2B5582` | `#2B5582` | Cluster navigasyon: maneuver / end |
| `cluster_nav_maneuver_text` | `#FFFFFF` | `#FFFFFF` | Cluster navigasyon: maneuver / text |
| `cluster_nav_maneuver_subtext` | `#D9E8F8` | `#C8DCF2` | Cluster navigasyon: maneuver / subtext |
| `cluster_nav_eta_fill` | `#F8FAFD` | `#1A222E` | Cluster navigasyon: eta / fill |
| `cluster_nav_eta_stroke` | `#C5D0E0` | `#3A4A5E` | Cluster navigasyon: eta / stroke |
| `cardColor` | `#FFFFFF` | `#1E2530` | Kart alias |
| `textPrimary` | `#171A20` | `#FFFFFF` | Birincil metin |
| `textSecondary` | `#5C6470` | `#9CA3AF` | İkincil metin |
| `textSecondaryCool` | `#55606C` | `#9DABB9` | Soğuk ikincil metin |
| `textAccent` | `#3568A0` | `#73B2F5` | Vurgu metni |
| `textMuted` | `#526172` | `#ADBAC7` | Bastırılmış metin |
| `textMessage` | `#2D3340` | `#E6E6E6` | Mesaj |
| `textHint` | `#526172` | `#BFCAD6` | Açıklama/ipucu |
| `textPrimary87` | `#DE000000` | `#E6FFFFFF` | Alfa birincil metin: 87 |
| `textPrimary85` | `#D9000000` | `#D9FFFFFF` | Alfa birincil metin: 85 |
| `textPrimary70` | `#B3000000` | `#B3FFFFFF` | Alfa birincil metin: 70 |
| `textPrimary80` | `#CC000000` | `#CCFFFFFF` | Alfa birincil metin: 80 |
| `textDialogButtonSecondary` | `#99000000` | `#AAFFFFFF` | İkincil dialog eylemi |
| `textDestructive` | `#B83232` | `#E07070` | Yıkıcı işlem metni |
| `textLoading` | `#8A6A20` | `#C4A050` | Yükleniyor metni |
| `buttonPrimary` | `#3568A0` | `#4586C5` | Birincil buton |
| `buttonSuccess` | `#4A7A58` | `#5A8F6A` | Başarı eylemi |
| `buttonSuccessBright` | `#3D8A4E` | `#4D9B5C` | Parlak başarı eylemi |
| `buttonDanger` | `#A34040` | `#B54A4A` | Tehlike/yıkıcı eylem |
| `buttonNeutral` | `#C5CCD8` | `#3D4552` | Nötr buton |
| `buttonActive` | `#3568A0` | `#4586C5` | Etkin eylem |
| `buttonInactive` | `#B8C0CE` | `#3D4552` | Etkin olmayan eylem |
| `buttonDestructiveBg` | `#F3E8E8` | `#3D2528` | Yıkıcı eylem zemini |
| `chipOn` | `#3D7A52` | `#4A8C62` | Açık chip |
| `selectionRow` | `#E2E8F0` | `#2A3447` | Seçili satır |
| `selectionBar` | `#4586C5` | `#73B2F5` | Seçim şeridi |
| `driveModeSelected` | `#D5DCE8` | `#343E4F` | Seçili mod kartı |
| `driveModeUnselected` | `#EDF1F6` | `#1A1F26` | Seçilmeyen mod kartı |
| `drive_mode_eco` | `#2F9E5F` | `#4ADE80` | Gerçek mod vurgusu: eco |
| `drive_mode_normal` | `#4586C5` | `#73B2F5` | Gerçek mod vurgusu: normal |
| `drive_mode_sport` | `#D4453A` | `#F07167` | Gerçek mod vurgusu: sport |
| `assistCardActive` | `#D8E4F5` | `#252E42` | Seçili yardım kartı |
| `assistStrokeActive` | `#5A7FC0` | `#6B8FD4` | Seçili yardım çerçevesi |
| `assistCardIdle` | `#E8EDF5` | `#1C2630` | Boşta yardım kartı |
| `assistStrokeIdle` | `#C5CEDC` | `#2E3848` | Boşta yardım çerçevesi |
| `automationPanel` | `#F4F6FA` | `#17191E` | Klima otomasyonu: Panel |
| `automationCardSurface` | `#FFFFFF` | `#24272D` | Klima otomasyonu: CardSurface |
| `automationIconSurface` | `#FFFFFF` | `#090B0F` | Klima otomasyonu: IconSurface |
| `automationCardStroke` | `#D5DAE2` | `#464B55` | Klima otomasyonu: CardStroke |
| `automationIconGlyph` | `#171A20` | `#F4F5F7` | Klima otomasyonu: IconGlyph |
| `automationText` | `#171A20` | `#F4F5F7` | Klima otomasyonu: Text |
| `automationTextMuted` | `#626B78` | `#B7BBC3` | Klima otomasyonu: TextMuted |
| `outline` | `#C5CEDC` | `#2E3848` | Standart çerçeve |
| `outlineMuted` | `#B0B8C4` | `#404040` | Bastırılmış çerçeve |
| `dividerColor` | `#D0D8E4` | `#2A3140` | Ayırıcı |
| `dividerWhite12` | `#1F000000` | `#1FFFFFFF` | Temaya uyarlı alfa ayırıcı |
| `logBackground` | `#F4F6FA` | `#0A0D12` | Günlük/terminal: Background |
| `logText` | `#2D6A4F` | `#8BC7A8` | Günlük/terminal: Text |
| `logPanelBg` | `#ECEFF5` | `#141920` | Günlük/terminal: PanelBg |
| `logButtonBg` | `#D0D6E0` | `#3D4552` | Günlük/terminal: ButtonBg |
| `wifiItemBackground` | `#FFFFFF` | `#1E2530` | Wi-Fi liste satırı: Background |
| `wifiItemPressed` | `#E2E8F0` | `#2A3440` | Wi-Fi liste satırı: Pressed |
| `statusSuccess` | `#4A7A58` | `#6B9E7A` | Başarı |
| `statusWarning` | `#8A6A20` | `#C4A050` | Uyarı |
| `statusError` | `#A34040` | `#C75C5C` | Hata |
| `statusInfo` | `#3568A0` | `#6B8EBF` | Bilgi |
| `statusErrorBright` | `#D32F2F` | `#F44336` | Belirgin hata |
| `statusNeutralGray` | `#6B7280` | `#808080` | Nötr durum |
| `transparent` | `#00000000` | `#00000000` | Tam şeffaf |
| `logFieldBg` | `#F0F2F7` | `#0B1116` | Günlük/terminal: FieldBg |
| `logTerminalChrome` | `#E8ECF3` | `#0B1116` | Günlük/terminal: TerminalChrome |
| `outlineStrong` | `#B8C4D4` | `#2D3748` | Güçlü çerçeve |
| `textTertiary` | `#374151` | `#E5E7EB` | Üçüncül metin |
| `buttonSecondaryMuted` | `#D0D6E0` | `#2A3440` | Sakin ikincil eylem |
| `buttonWelcome` | `#3568A0` | `#6B8EBF` | Karşılama eylemi |
| `buttonGold` | `#8A6A20` | `#C4A050` | Altın/uyarı eylemi |
| `textOnGold` | `#F8FAFC` | `#111827` | Altın zemin üstü yazı |
| `terminalDotRed` | `#A34040` | `#C75C5C` | Terminal durum noktası: Red |
| `terminalDotYellow` | `#8A6A20` | `#C4A050` | Terminal durum noktası: Yellow |
| `terminalDotGreen` | `#4A7A58` | `#6B9E7A` | Terminal durum noktası: Green |
| `wifiScreenBg` | `#00000000` | `#00000000` | Wi-Fi sayfa zemini |
| `wifi_toast_global_eyebrow_ok` | `#2D6A4F` | `#9FD4B0` | Wi-Fi bildirimi: global / eyebrow / ok |
| `wifi_toast_global_eyebrow_fail` | `#8B4518` | `#E8B89A` | Wi-Fi bildirimi: global / eyebrow / fail |
| `wifi_toast_global_text_primary` | `#171A20` | `#F0F4FA` | Wi-Fi bildirimi: global / text / primary |
| `wifi_toast_global_icon_tint_ok` | `#3D8A5E` | `#B8E8C8` | Wi-Fi bildirimi: global / icon / tint / ok |
| `wifi_toast_global_icon_tint_fail` | `#B85C40` | `#F0B8A0` | Wi-Fi bildirimi: global / icon / tint / fail |
| `wifi_toast_preparing_eyebrow` | `#3568A0` | `#9FC8E8` | Wi-Fi bildirimi: preparing / eyebrow |
| `wifi_toast_preparing_icon` | `#4586C5` | `#9EC8E8` | Wi-Fi bildirimi: preparing / icon |
| `wifi_toast_success_grad_start` | `#FFF2FAF6` | `#E8223038` | Wi-Fi bildirimi: success / grad / start |
| `wifi_toast_success_grad_center` | `#FFE8F4ED` | `#E8182430` | Wi-Fi bildirimi: success / grad / center |
| `wifi_toast_success_grad_end` | `#FFDCEEE4` | `#E810141C` | Wi-Fi bildirimi: success / grad / end |
| `wifi_toast_success_stroke` | `#6688B898` | `#5088C49A` | Wi-Fi bildirimi: success / stroke |
| `wifi_toast_error_grad_start` | `#FFFDF3F3` | `#E8322420` | Wi-Fi bildirimi: error / grad / start |
| `wifi_toast_error_grad_center` | `#FFF5E8E8` | `#E8281C1C` | Wi-Fi bildirimi: error / grad / center |
| `wifi_toast_error_grad_end` | `#FFEBDCDC` | `#E8141014` | Wi-Fi bildirimi: error / grad / end |
| `wifi_toast_error_stroke` | `#70D08080` | `#50E0A07A` | Wi-Fi bildirimi: error / stroke |
| `wifi_toast_preparing_grad_start` | `#FFF0F5FA` | `#E0222834` | Wi-Fi bildirimi: preparing / grad / start |
| `wifi_toast_preparing_grad_center` | `#FFE6EEF8` | `#E0182230` | Wi-Fi bildirimi: preparing / grad / center |
| `wifi_toast_preparing_grad_end` | `#FFDCE6F2` | `#E0101820` | Wi-Fi bildirimi: preparing / grad / end |
| `wifi_toast_preparing_stroke` | `#7080A8D0` | `#5098B4D0` | Wi-Fi bildirimi: preparing / stroke |
| `wifi_toast_halo_ok_grad_start` | `#88C8E8D8` | `#404A9A72` | Wi-Fi bildirimi: halo / ok / grad / start |
| `wifi_toast_halo_ok_grad_center` | `#55A0C8B0` | `#302E6B4A` | Wi-Fi bildirimi: halo / ok / grad / center |
| `wifi_toast_halo_ok_grad_end` | `#30687870` | `#20182A20` | Wi-Fi bildirimi: halo / ok / grad / end |
| `wifi_toast_halo_ok_stroke` | `#6690C8B8` | `#45A0C4A5` | Wi-Fi bildirimi: halo / ok / stroke |
| `wifi_toast_halo_error_grad_start` | `#90F0C8C8` | `#50D07050` | Wi-Fi bildirimi: halo / error / grad / start |
| `wifi_toast_halo_error_grad_center` | `#60E0A0A0` | `#30C24A2A` | Wi-Fi bildirimi: halo / error / grad / center |
| `wifi_toast_halo_error_grad_end` | `#40C08080` | `#20181014` | Wi-Fi bildirimi: halo / error / grad / end |
| `wifi_toast_halo_error_stroke` | `#70F0B0A8` | `#50E8A07A` | Wi-Fi bildirimi: halo / error / stroke |
| `wifi_toast_halo_preparing_grad_start` | `#88B8D8F0` | `#404A7AA0` | Wi-Fi bildirimi: halo / preparing / grad / start |
| `wifi_toast_halo_preparing_grad_center` | `#5598B8D8` | `#302E5B8A` | Wi-Fi bildirimi: halo / preparing / grad / center |
| `wifi_toast_halo_preparing_grad_end` | `#307898C0` | `#20162A2E` | Wi-Fi bildirimi: halo / preparing / grad / end |
| `wifi_toast_halo_preparing_stroke` | `#66A0C0E8` | `#4568A0C0` | Wi-Fi bildirimi: halo / preparing / stroke |
| `preparing_overlay_root` | `#E6E8ECF3` | `#E60A0D12` | Hazırlık overlay zemini |
| `boot_splash_gradient_edge` | `#FFF8FAFC` | `#FF0A0F18` | Açılış görseli: gradient / edge |
| `boot_splash_gradient_mid` | `#FFE8ECF3` | `#FF101826` | Açılış görseli: gradient / mid |
| `boot_splash_grad_a` | `#FFF7FAFD` | `#FF0A0F18` | Açılış görseli: grad / a |
| `boot_splash_grad_b` | `#FFE4EEF8` | `#FF121C2C` | Açılış görseli: grad / b |
| `boot_splash_grad_c` | `#FFD5E6F5` | `#FF1A2A3E` | Açılış görseli: grad / c |
| `boot_splash_grad_d` | `#FFEAF0F7` | `#FF0E1520` | Açılış görseli: grad / d |
| `boot_splash_grad_accent` | `#FFC5D9EF` | `#FF243B55` | Açılış görseli: grad / accent |
| `map_grid_line_accent` | `#304586C5` | `#1A73B2F5` | Dekoratif harita grid: line / accent |
| `map_grid_glow_accent` | `#504586C5` | `#4D73B2F5` | Dekoratif harita grid: glow / accent |
| `map_grid_vignette_edge` | `#CCDCE4F0` | `#CC0A0F18` | Dekoratif harita grid: vignette / edge |
| `boot_splash_pin_dot` | `#FF2D3748` | `#FF0A0F18` | Açılış görseli: pin / dot |
| `boot_splash_pin_shadow` | `#55000000` | `#55000000` | Açılış görseli: pin / shadow |
| `modal_overlay_scrim` | `#99000000` | `#CC000000` | Modal ekran örtüsü |
| `drive_mode_snow` | `#67C4E5` | `#9ADFF2` | Gerçek mod vurgusu: snow |
| `drive_mode_mud` | `#8C6545` | `#C39673` | Gerçek mod vurgusu: mud |
| `drive_mode_offroad` | `#6D7D40` | `#AFBE7B` | Gerçek mod vurgusu: offroad |
| `drive_mode_sand` | `#C69D54` | `#E0C080` | Gerçek mod vurgusu: sand |


### Mod renklerinin davranış sözleşmesi

`DriveModeStyle`: ECO=0, NORMAL=1, SPORT=2, SNOW=3, MUD=4, OFFROAD=5, SAND=7. ID=6 uydurulmaz; bilinmeyen mod nötrdür. Mod ismi ve `contentDescription` renk anlamını destekler. Varsayılan görünüm Eco/Normal/Sport; isteğe bağlı 4x4 görünümü Snow/Mud/Offroad/Sand ekler. Gizlemek kayıtlı seçimi değiştirmez. Renk eşlemesi tek `DriveModeStyle.accentColor` üzerinden yapılır.

### Kaynak dışı ve hesaplanan renkler

Palet XML'leri tüm görsel renklerin tek kaynağı değildir. Java welcome scene çizimleri, `Color.rgb/argb`, `Color.WHITE/BLACK/TRANSPARENT`, drawable literal'ları ve telefon web panelinin CSS paleti [source-inventory.md](source-inventory.md)'de dosya/satır bağlamıyla çıkarılmıştır. Bunlar otomatik DayNight garantisi taşımaz.

Örnekler: `bg_launcher_dashboard` gradient'i #EEF3FA → #E2EBF6 → #D4E0F0; exit çerçevesi #44A34040; araç glow #554586C5 → #004586C5. Bunlar mevcut bağlama özel sabitlerdir, yeni genel token değildir. Bağımsız status widget arka planı `ColorUtils.blendARGB(surfaceCardInner, accent, .07f)`; floating ripple `textPrimary` üzerine alpha `0x55`, yüzey alpha `.96f`; welcome action ripple accent alpha **55 ondalık** kullanır. Karıştırılmış/alfa rengi opak paletteki rengin kontrastıyla eşitleme.

## Tipografi — MEVCUT ve HEDEF

Genel kontroller sistem/default sans-serif; normal/bold (`Typeface.NORMAL` / `BOLD`) kullanır. Lite saati `sans-serif-light`, tarih/hava etiketleri `sans-serif`dir. `res/font/outfit.ttf` yalnız kullanan welcome tasarımlarında `ResourcesCompat.getFont` ile yüklenir; Outfit bütün uygulamaya theme fontFamily olarak atanmış değildir. Signature/WinterTouch gibi tasarımlarda ağırlık 200/300/400, fallback `sans-serif-light`; lisans `assets/fonts/OFL-Outfit.txt` altında korunur. Log alanları monospace olabilir.

| Kaynak | Boyut | Kullanım |
|---|---|---|
| `text_size_tiny` | 18 sp | En küçük ortak metin |
| `text_size_small` | 22.5 sp | Yardım, ikincil etiket |
| `text_size_normal` | 25 sp | Ortak düğme/temel metin |
| `text_size_medium` | 27.5 sp | Bölüm/üst başlık |
| `text_size_large` | 30 sp | Büyük etiket |
| `text_size_title` | 35 sp | Başlık |
| `text_size_header` | 37.5 sp | Büyük başlık |
| Lite clock / dashboard speed | 80 sp / 70 sp | Büyük sayısal bilgi |
| Launcher ölçüm/birim/etiket | 22.5–35 sp | Tam adlar aşağıdaki boyut envanterinde |
| **İstisna:** StatusWidgetsView StatusChip | 12.5 sp label / 16.5 sp value | Ortak ölçeğin altında; HMI okunabilirlik bulgusu |
| **İstisna:** CustomAlertDialogButton | 16 sp bold | Ortak kaynakta; küçük hedeflerle birlikte incelenmeli |
| Signature welcome | 92 sp saat / 16 sp tarih / 32 sp ad | Dekoratif açılış ölçeği, ana kontrol standardı değildir |

Tek bir global `lineHeight` veya tipografik satır yüksekliği tokenı yoktur; çoğu TextView platform font metrics/default font padding kullanır. `DriveModeTabBuilder` başlığı `setLineSpacing(2, 1.0f)` (ek **2 px**), bazı welcome metinleri letterSpacing ve özel font kullanır; tam ifadeler envanterdedir. 1.2/1.4 gibi oranları mevcut token diye sunma. `RelativeSizeSpan(.82f)` rail sürüm etiketini küçültür.

**HEDEF okunabilirlik:** Sürüşte okunacak temel eylem 25 sp, yardımcı bilgi 22.5 sp ve kritik başlık ≥27.5 sp ortak ölçeği kullanır; 18 sp veya daha küçük metin kritik sürüş eyleminin tek etiketi olmaz. Yer yetmezse yazıyı küçültmek yerine öğe sayısı/yerleşim değişir. %130 fontScale'de etiket, birim ve iptal eylemi kesilmez. İki satır açıklama kontrollü olabilir; kritik talimat ellipsize ile anlam kaybetmez.

**HEDEF kontrast:** Proje içi kabul eşiği normal metin ≥4.5:1, büyük metin/önemli ikon ≥3:1; bu belge tüm paletin bu eşikleri geçtiğini veya otomotiv sertifikasını iddia etmez. Alfa yüzey, gradient ve gerçek arka planla bileşik renk ölçülür. `UiStyles.textOnColor` luminance >0.179 ise siyah, aksi halde beyaz seçer; sonradan `setTextColor` ile override edilen butonlar ayrıca denetlenir. Seçili/uyarı rengine ek metin, şekil veya ikon gerekir.

## Spacing, radius ve efektler

Ortak spacing 4/8/16/24/32 dp; OEM içerik 16 dp, kart dış margin 12 dp, iç padding 18 dp. `FeatureGridLayout.addCard`: çocuk başına 6 dp margin → komşu iki kart arasında 12 dp. Herhangi bir 6 dp ikon aralığı, 12–16 dp **etkileşim hedefi aralığı** kuralının yerine geçmez.

Radius: genel button 14 dp, genel card 12 dp; OEM cam kart 26 dp, nav pill 22 dp, button 14 dp, segment track 16 dp ve thumb 12 dp. Bağlama özel Java kartları 16/18 dp, launcher ikon slotu 20 dp, cluster ETA/manevra 18 dp, fuel progress 4 dp, floating bar 10 dp kullanır. Bu çeşitlilik mevcut durumdur; hepsi yeni ortak radius tokenı değildir.

Elevation Android gölge mekanizmasıdır; CSS box-shadow kullanılmaz. `button_elevation=4dp`, `card_elevation=6dp` kaynakları tanımlıdır, her View'ın bunları gerçekten uyguladığı varsayılmaz. Overlay kartları 12 dp, floating back 2 dp gibi doğrudan değerler içerir. `styleOemButton` radius/yazı/padding/minHeight ayarlar, elevation uygulamaz. XML cam kart: 1 dp stroke; cluster kart: 1.5 dp stroke. Optional RenderEffect blur API≥31'de çalışır, alt API'de no-op; çocuk metinleri de bulanıklaştırabilir ve gerçek backdrop blur değildir. Android 11 IVI'de zorunlu efekt yapılamaz.

### Tüm boyut kaynakları

Kaynak: [values/dimens.xml](../../app/src/main/res/values/dimens.xml). Aşağıda tanımlı değerler eksiksizdir; tanımlı olmak her kullanımın aktif veya HMI uyumlu olduğu anlamına gelmez. Özellikle `button_height=48dp` mevcut değerdir; yeni minimum **56/64 dp** [ergonomi belgesinde](ergonomics-and-layout.md) tanımlıdır.

| `R.dimen` / `@dimen` adı | Birebir değer |
|---|---|
| `text_size_tiny` | `18sp` |
| `text_size_small` | `22.5sp` |
| `text_size_normal` | `25sp` |
| `text_size_medium` | `27.5sp` |
| `text_size_large` | `30sp` |
| `text_size_title` | `35sp` |
| `text_size_header` | `37.5sp` |
| `spacing_tiny` | `4dp` |
| `spacing_small` | `8dp` |
| `spacing_medium` | `16dp` |
| `spacing_large` | `24dp` |
| `spacing_xlarge` | `32dp` |
| `button_height` | `48dp` |
| `button_corner_radius` | `14dp` |
| `button_elevation` | `4dp` |
| `card_corner_radius` | `12dp` |
| `card_elevation` | `6dp` |
| `oem_card_radius` | `26dp` |
| `oem_nav_pill_radius` | `22dp` |
| `oem_button_radius` | `14dp` |
| `oem_segment_track_radius` | `16dp` |
| `oem_segment_thumb_radius` | `12dp` |
| `oem_content_padding` | `16dp` |
| `oem_card_margin` | `12dp` |
| `oem_card_inner_padding` | `18dp` |
| `rail_min_width` | `300dp` |
| `rail_max_width` | `520dp` |
| `rail_row_min_height` | `80dp` |
| `icon_size_small` | `16dp` |
| `icon_size_medium` | `24dp` |
| `icon_size_large` | `32dp` |
| `launcher_icon_size` | `58dp` |
| `launcher_icon_slot_size` | `76dp` |
| `launcher_tile_spacing` | `6dp` |
| `launcher_tile_label_min_height` | `60dp` |
| `launcher_section_gap` | `14dp` |
| `launcher_dashboard_speed_text` | `70sp` |
| `launcher_dashboard_drive_unit` | `22.5sp` |
| `launcher_dashboard_drive_gear` | `25sp` |
| `launcher_dashboard_drive_status` | `22.5sp` |
| `launcher_dashboard_drive_metric_label` | `22.5sp` |
| `launcher_dashboard_drive_metric_value` | `27.5sp` |
| `launcher_dashboard_fuel_section_title` | `25sp` |
| `launcher_dashboard_fuel_label` | `22.5sp` |
| `launcher_dashboard_stat_value` | `30sp` |
| `launcher_dashboard_card_min_height` | `300dp` |
| `launcher_dashboard_card_gap` | `10dp` |
| `launcher_dashboard_media_art_size` | `64dp` |
| `launcher_dashboard_media_title` | `25sp` |
| `launcher_dashboard_media_artist` | `22.5sp` |
| `launcher_dashboard_media_time` | `22.5sp` |
| `launcher_dashboard_media_control_size` | `72dp` |
| `launcher_dashboard_media_control_icon_size` | `44dp` |
| `launcher_dashboard_control_size` | `56dp` |
| `launcher_dashboard_quick_slot_size` | `72dp` |
| `launcher_dashboard_quick_icon_size` | `44dp` |
| `launcher_mapcontrol_slot_size` | `64.8dp` |
| `launcher_mapcontrol_icon_size` | `39.6dp` |
| `launcher_mapcontrol_label_text` | `16.875sp` |
| `launcher_mapcontrol_label_min_height` | `32dp` |
| `launcher_dashboard_model_loading_icon` | `40dp` |
| `launcher_dashboard_model_loading_bar_width` | `160dp` |
| `launcher_dashboard_lite_clock` | `70sp` |
| `launcher_lite_clock` | `80sp` |
| `launcher_lite_date` | `25sp` |
| `launcher_lite_weather_glyph` | `35sp` |
| `launcher_lite_weather_temp` | `27.5sp` |
| `launcher_lite_weather_label` | `22.5sp` |
| `launcher_lite_stat_value` | `35sp` |
| `launcher_lite_stat_label` | `22.5sp` |
| `launcher_lite_divider_height` | `1dp` |
| `launcher_dashboard_vehicle_height` | `300dp` |
| `launcher_quick_control_size` | `64dp` |
| `vehicle_picker_tile_min` | `128dp` |
| `vehicle_picker_tile_max` | `172dp` |
| `equalizer_slider_height` | `112dp` |
| `equalizer_thumb_size` | `72dp` |
| `equalizer_track_height` | `24dp` |


## Animasyon ve transition — MEVCUT

| Yüzey | Süre / davranış | Kaynak |
|---|---|---|
| Sekme/launcher içerik değişimi | 110 ms cover + 200 ms reveal; gradient veil | MainActivity `crossfadeContent` çağrısı / `crossfadeWithVeil` |
| Kabuk/tam ekran geçişi | 140 + 260 ms | MainActivity `crossfadeWithVeil` |
| Mod seçimi | 550 ms, AccelerateDecelerate | DriveModeTabBuilder |
| Floating hub giriş/çıkış | 320 ms Overshoot(1.08) / 240 ms Accelerate; diğer geçiş 200 ms Decelerate | FloatingBackButtonManager |
| Hazırlık overlay | root 220 ms, 60 ms gecikme + 320 ms giriş; spinner 900 ms linear sonsuz; pulse 1100 ms; noktalar 520 ms, i×160 ms stagger | DisplayHelper |
| Boot dekorasyonu | root 360 ms; akış 10000 ms; scroll 8000 ms; 900/2200/3200/1400/1800 ms dekoratif döngüler | DisplayHelper |
| Welcome kapatma | 620 ms Accelerate | WelcomeScreenPresenter `DISMISS_MS` |
| Signature welcome | giriş 720/640 ms, halo 900 ms; parça döngüleri 2400/2800 ms | SignatureWelcomeDesign |
| WinterTouch giriş | 700 ms Decelerate, parametreli delay | WinterTouchWelcomeDesign |
| Model host geçişi | 300 ms fade; diğer fazda 200 ms delay + 300 ms | VehicleModelHostView |

Global duration/interpolator tokenı veya genel reduced-motion ayarı mevcut değildir. Diğer scene/HTML animasyonları ve değişkene bağlı süreler [kaynak envanterinde](source-inventory.md) birebir korunur. `postDelayed` ağ/poll timeout'ları animasyon tokenı değildir. Animasyon iptal/end/fallback yolları pencere sahipliğini bozmamalıdır.

**HEDEF:** Sürüşte dekoratif sürekli hareket azaltılır; kritik bilgi ve Home/Geri görünür kalır. Geçişler üst şeridi veya driver dock'u kaydırmaz. Tema değişimi açık renk bir pencereyi karanlıkta flaş gibi açığa çıkarmamalı; mevcut gradient veil bu amaçla korunur. Reduced-motion/park politikası bir refactor hedefidir, şu an uygulanmış ayar değildir.

## Tema sözleşmesi

`Theme.MapControl.Base` → `Theme.MaterialComponents.DayNight.NoActionBar`. `colorPrimary`, `colorPrimaryDark`, `colorAccent` yukarıdaki kaynaklara; pencere/status/navigation background `backgroundColor`'a bağlıdır. Açık temada `windowLightStatusBar=true`, koyuda false. `Theme.MapControl.Splash` launch gradient kullanır; sonrasında normal tema uygulanır. Legacy `Theme.AppCompat.Light.NoActionBar` adı MapControl parent'ını izler.

MainActivity uiMode değişimini kendisi yönetir; `UiStyles.setUiModeOverride`/`uiModeContext` güncel config/app context üzerinden kaynak çözer. `reapplyTheme` mevcut görünüm/drawable ve metni yeniden boyar. Arayüz yeniden oluşturulmadan eski `ColorInt`/drawable cache'ini tutmak yanlış tema üretebilir. `nightModeSetting` varsayılanı FOLLOW_SYSTEM olsa da mevcut Settings UI yalnız açık/koyu binary seçim sunar; kullanıcı dokunduğunda MODE_NIGHT_YES/NO kaydedilip AppCompatDelegate ve callback üzerinden uygulanır. Üç seçenekli sistem/açık/koyu kontrol henüz yoktur. Mevcut kayıt davranışı korunur; gerçek callback imzaları envanterdedir.

```java
Button button = new Button(context);
button.setText(R.string.common_close);
UiStyles.styleOemButton(button, UiStyles.color(context, R.color.buttonNeutral));
// HEDEF HMI boyutu; styleOemButton'ın mevcut 48dp tabanını yükseltir.
int target = Math.round(64 * context.getResources().getDisplayMetrics().density);
button.setMinWidth(target);
button.setMinimumWidth(target);
button.setMinHeight(target);
button.setMinimumHeight(target);
```

Bu örnek stil ve minimum ölçüyü gösterir; LayoutParams ölçümü ve ekran 1/2 konumlandırması ayrıca uygulanmalıdır. Theme değişiminde rengi tekrar çöz. `getDimension(sp)` çıktısı px olduğundan `UiStyles.setTextSize(view, R.dimen.text_size_normal)` kullanılır; `setTextSize(getDimension(...))` ikinci ölçekleme yapar.

### Kullanıcı tarafından belirlenen kompakt MapControl kısayol ölçeği

Yalnız tam ekran MapControl grubunda slot 72→64.8 dp, temel ikon 44→39.6 dp (%10 küçültme) ve etiket 22.5→16.875 sp (%25 küçültme) kullanılır. Önceden var olan 0.85 ikon iç ölçeği korunur. Medya ve kullanıcı uygulaması ikonlarının ortak quick/media kaynakları bu değişiklikten etkilenmez; kısayolun tıklanabilir tile alanı korunur.
