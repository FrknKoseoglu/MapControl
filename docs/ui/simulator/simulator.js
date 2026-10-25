// ==========================================================================
// MapControl IVI Simulator - Engine & State Management
// ==========================================================================

const state = {
  theme: 'dark',
  ratio: '1920x720',
  heatmap: false,
  shellMode: 'unified', // 'unified' or 'legacy'
  activePage: 'launcher',
  driveMode: 'eco', // 'eco', 'normal', 'sport'
  headerPos: 'top', // 'top' or 'bottom'
  stats: {
    speed: 0,
    gear: 'P',
    temp: '22.5°C',
    fuel: '78%',
    time: '22:45'
  }
};

// SVG Icons
const icons = {
  home: `<svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><path d="M3 9l9-7 9 7v11a2 2 0 0 1-2 2H5a2 2 0 0 1-2-2z"></path><polyline points="9 22 9 12 15 12 15 22"></polyline></svg>`,
  back: `<svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><line x1="19" y1="12" x2="5" y2="12"></line><polyline points="12 19 5 12 12 5"></polyline></svg>`,
  drive: `<svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><circle cx="12" cy="12" r="10"></circle><circle cx="12" cy="12" r="4"></circle><line x1="4.93" y1="4.93" x2="9.17" y2="9.17"></line><line x1="14.83" y1="14.83" x2="19.07" y2="19.07"></line><line x1="14.83" y1="9.17" x2="19.07" y2="4.93"></line><line x1="14.83" y1="9.17" x2="18.36" y2="5.64"></line><line x1="4.93" y1="19.07" x2="9.17" y2="14.83"></line></svg>`,
  camera: `<svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><path d="M23 19a2 2 0 0 1-2 2H3a2 2 0 0 1-2-2V8a2 2 0 0 1 2-2h4l2-3h6l2 3h4a2 2 0 0 1 2 2z"></path><circle cx="12" cy="13" r="4"></circle></svg>`,
  settings: `<svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><circle cx="12" cy="12" r="3"></circle><path d="M19.4 15a1.65 1.65 0 0 0 .33 1.82l.06.06a2 2 0 0 1 0 2.83 2 2 0 0 1-2.83 0l-.06-.06a1.65 1.65 0 0 0-1.82-.33 1.65 1.65 0 0 0-1 1.51V21a2 2 0 0 1-2 2 2 2 0 0 1-2-2v-.09A1.65 1.65 0 0 0 9 19.4a1.65 1.65 0 0 0-1.82.33l-.06.06a2 2 0 0 1-2.83 0 2 2 0 0 1 0-2.83l.06-.06a1.65 1.65 0 0 0 .33-1.82 1.65 1.65 0 0 0-1.51-1H3a2 2 0 0 1-2-2 2 2 0 0 1 2-2h.09A1.65 1.65 0 0 0 4.6 9a1.65 1.65 0 0 0-.33-1.82l-.06-.06a2 2 0 0 1 0-2.83 2 2 0 0 1 2.83 0l.06.06a1.65 1.65 0 0 0 1.82.33H9a1.65 1.65 0 0 0 1-1.51V3a2 2 0 0 1 2-2 2 2 0 0 1 2 2v.09a1.65 1.65 0 0 0 1 1.51 1.65 1.65 0 0 0 1.82-.33l.06-.06a2 2 0 0 1 2.83 0 2 2 0 0 1 0 2.83l-.06.06a1.65 1.65 0 0 0-.33 1.82V9a1.65 1.65 0 0 0 1.51 1H21a2 2 0 0 1 2 2 2 2 0 0 1-2 2h-.09a1.65 1.65 0 0 0-1.51 1z"></path></svg>`,
  apps: `<svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><rect x="3" y="3" width="7" height="7"></rect><rect x="14" y="3" width="7" height="7"></rect><rect x="14" y="14" width="7" height="7"></rect><rect x="3" y="14" width="7" height="7"></rect></svg>`,
  transfer: `<svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><polyline points="16 3 21 3 21 8"></polyline><line x1="4" y1="20" x2="21" y2="3"></line><polyline points="21 16 21 21 16 21"></polyline><line x1="15" y1="15" x2="21" y2="21"></line><line x1="4" y1="4" x2="9" y2="9"></line></svg>`,
  vehicle: `<svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><rect x="1" y="3" width="22" height="13" rx="2" ry="2"></rect><line x1="8" y1="21" x2="16" y2="21"></line><line x1="12" y1="17" x2="12" y2="21"></line></svg>`,
  music: `<svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><path d="M9 18V5l12-2v13"></path><circle cx="6" cy="18" r="3"></circle><circle cx="18" cy="16" r="3"></circle></svg>`,
  sound: `<svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2"><polygon points="11 5 6 9 2 9 2 15 6 15 11 19 11 5"></polygon><path d="M19.07 4.93a10 10 0 0 1 0 14.14M15.54 8.46a5 5 0 0 1 0 7.07"></path></svg>`
};

document.addEventListener('DOMContentLoaded', () => {
  initControls();
  renderScreen();
});

function initControls() {
  // Ratio switcher
  document.querySelectorAll('[data-set-ratio]').forEach(btn => {
    btn.addEventListener('click', () => {
      document.querySelectorAll('[data-set-ratio]').forEach(b => b.classList.remove('active'));
      btn.classList.add('active');
      state.ratio = btn.getAttribute('data-set-ratio');
      const bezel = document.getElementById('iviBezel');
      bezel.setAttribute('data-ratio', state.ratio);
      renderScreen();
    });
  });

  // Heatmap toggle
  const heatmapBtn = document.getElementById('btnToggleHeatmap');
  heatmapBtn.addEventListener('click', () => {
    state.heatmap = !state.heatmap;
    heatmapBtn.classList.toggle('heatmap-active', state.heatmap);
    const overlay = document.getElementById('heatmapOverlay');
    overlay.classList.toggle('show', state.heatmap);
  });

  // Theme toggle
  const themeBtn = document.getElementById('btnToggleTheme');
  themeBtn.addEventListener('click', () => {
    state.theme = state.theme === 'dark' ? 'light' : 'dark';
    document.documentElement.setAttribute('data-theme', state.theme);
    themeBtn.textContent = state.theme === 'dark' ? '🌙 Koyu' : '☀️ Açık';
  });

  // Shell Mode toggle (Unified vs Legacy)
  const shellBtn = document.getElementById('btnToggleShell');
  shellBtn.addEventListener('click', () => {
    state.shellMode = state.shellMode === 'unified' ? 'legacy' : 'unified';
    shellBtn.textContent = state.shellMode === 'unified' ? '✨ Birleşik (Önerilen)' : '⚠️ Bölünmüş (Mevcut)';
    shellBtn.classList.toggle('active', state.shellMode === 'unified');
    renderScreen();
  });
}

function navigateTo(pageId) {
  state.activePage = pageId;
  renderScreen();
}

function setDriveMode(mode) {
  state.driveMode = mode;
  renderScreen();
}

function renderScreen() {
  const container = document.getElementById('screenContent');
  if (!container) return;

  if (state.shellMode === 'unified') {
    renderUnifiedShell(container);
  } else {
    renderLegacyShell(container);
  }
}

// ==========================================================================
// RENDER UNIFIED SHELL (Command Deck + Ambient Stage Architecture)
// ==========================================================================
function renderUnifiedShell(container) {
  const isLauncher = state.activePage === 'launcher';

  container.innerHTML = `
    <div class="unified-shell">
      <!-- LEFT COMMAND DECK (Driver Reach Zone: ~38%) -->
      <div class="command-deck">
        <div class="deck-header">
          <div class="deck-brand-chip">
            <svg width="18" height="18" viewBox="0 0 24 24" fill="none" stroke="currentColor" stroke-width="2.5"><polygon points="5 3 19 12 5 21 5 3"></polygon></svg>
            <span>OMODA IVI</span>
          </div>
          <div class="deck-status-chips">
            <span class="mini-chip">📶 Wi-Fi</span>
            <span class="mini-chip" style="color:var(--${state.driveMode}-color); border-color:var(--${state.driveMode}-color)">
              ${state.driveMode.toUpperCase()}
            </span>
          </div>
        </div>

        <!-- Scrollable Command Items -->
        <div class="deck-scroll-body">
          <div class="nav-item ${state.activePage === 'launcher' ? 'active' : ''}" onclick="navigateTo('launcher')">
            <div class="nav-item-icon">${icons.home}</div>
            <span>Ana Ekran Dashboard</span>
          </div>
          <div class="nav-item ${state.activePage === 'drive' ? 'active' : ''}" onclick="navigateTo('drive')">
            <div class="nav-item-icon">${icons.drive}</div>
            <span>Sürüş Hafızası</span>
          </div>
          <div class="nav-item ${state.activePage === 'camera' ? 'active' : ''}" onclick="navigateTo('camera')">
            <div class="nav-item-icon">${icons.camera}</div>
            <span>Kamera 360</span>
          </div>
          <div class="nav-item ${state.activePage === 'apps' ? 'active' : ''}" onclick="navigateTo('apps')">
            <div class="nav-item-icon">${icons.apps}</div>
            <span>Uygulamalar</span>
          </div>
          <div class="nav-item ${state.activePage === 'transfer' ? 'active' : ''}" onclick="navigateTo('transfer')">
            <div class="nav-item-icon">${icons.transfer}</div>
            <span>Aktar (Telefon)</span>
          </div>
          <div class="nav-item ${state.activePage === 'vehicle' ? 'active' : ''}" onclick="navigateTo('vehicle')">
            <div class="nav-item-icon">${icons.vehicle}</div>
            <span>Araç Bilgileri</span>
          </div>
          <div class="nav-item ${state.activePage === 'equalizer' ? 'active' : ''}" onclick="navigateTo('equalizer')">
            <div class="nav-item-icon">${icons.sound}</div>
            <span>Ses & Ekolayzer</span>
          </div>
          <div class="nav-item ${state.activePage === 'settings' ? 'active' : ''}" onclick="navigateTo('settings')">
            <div class="nav-item-icon">${icons.settings}</div>
            <span>Sistem Ayarları</span>
          </div>
        </div>

        <!-- ZONE 1: FIXED GOLDEN DOCK (Always visible at bottom left!) -->
        <div class="golden-dock">
          <button class="dock-btn primary-dock" onclick="navigateTo('launcher')">
            ${icons.home}
            <span>HOME</span>
          </button>
          <button class="dock-btn back-dock" onclick="navigateTo('launcher')">
            ${icons.back}
            <span>GERİ</span>
          </button>
          <button class="dock-btn" onclick="navigateTo('drive')">
            ${icons.drive}
            <span>HAFIZA</span>
          </button>
          <button class="dock-btn" onclick="navigateTo('settings')">
            ${icons.settings}
            <span>AYARLAR</span>
          </button>
        </div>
      </div>

      <!-- RIGHT AMBIENT STAGE (Glanceable Visual Stage: ~62%) -->
      <div class="ambient-stage">
        <div class="stage-top-bar">
          <div class="stage-title-wrap">
            <span class="stage-title">${getPageTitle(state.activePage)}</span>
          </div>
          <div class="stage-glance-info">
            <span>🌡️ ${state.stats.temp}</span>
            <span>⛽ ${state.stats.fuel}</span>
            <span style="font-weight:700; color:var(--text-primary);">🕒 ${state.stats.time}</span>
          </div>
        </div>

        <div class="stage-canvas-content">
          ${renderPageContent(state.activePage)}
        </div>
      </div>
    </div>
  `;
}

// ==========================================================================
// RENDER LEGACY SHELL (Current Codebase Comparison)
// ==========================================================================
function renderLegacyShell(container) {
  if (state.activePage === 'launcher') {
    container.innerHTML = `
      <div style="display:flex; width:100%; height:100%; background:var(--bg-window); padding:16px; gap:16px;">
        <div style="flex:0.25; background:var(--surface-card); border-radius:18px; padding:16px; border:1px solid var(--outline);">
          <h4 style="color:var(--oem-accent); margin-bottom:12px;">Hızlı Erişim</h4>
          <p style="font-size:11px; color:var(--text-secondary); margin-bottom:16px;">Durum widget'ları</p>
          <div style="display:flex; flex-direction:column; gap:8px;">
            <button class="dock-btn" onclick="navigateTo('apps')">Uygulamalar</button>
            <button class="dock-btn" onclick="navigateTo('settings')">Ayarlar</button>
          </div>
        </div>
        <div style="flex:0.5; background:var(--surface-card); border-radius:18px; padding:20px; text-align:center; display:flex; flex-direction:column; justify-content:center; align-items:center; border:1px solid var(--outline);">
          <h1 style="font-size:48px; font-weight:300;">22:45</h1>
          <p style="color:var(--text-secondary);">25 Ekim Pazar · Açık 22°C</p>
          <div style="margin-top:20px;">
            <span style="font-size:24px; font-weight:700; color:var(--eco-color);">0 km/h · P</span>
          </div>
        </div>
        <!-- ERGONOMIC VIOLATION IN LEGACY: Media Controls in Far Right (Zone 4) -->
        <div style="flex:0.25; background:rgba(239, 68, 68, 0.1); border:2px dashed #EF4444; border-radius:18px; padding:16px;">
          <span style="background:#B91C1C; color:#FFF; font-size:10px; padding:3px 6px; border-radius:4px; font-weight:700;">BÖLGE 4 İHLALİ</span>
          <h4 style="margin-top:8px;">Medya Oynatıcı</h4>
          <p style="font-size:11px; color:#FCA5A5; margin-top:4px;">Sürücünün kol mesafesi dışında!</p>
          <div style="display:flex; gap:8px; margin-top:16px; justify-content:center;">
            <button class="dock-btn">⏮</button>
            <button class="dock-btn primary-dock">▶</button>
            <button class="dock-btn">⏭</button>
          </div>
        </div>
      </div>
    `;
  } else {
    container.innerHTML = `
      <div style="display:flex; width:100%; height:100%;">
        <!-- Thick Legacy SideRail -->
        <div style="width:30%; background:var(--surface-rail); border-right:1px solid var(--outline); padding:16px;">
          <h4 style="color:var(--oem-accent); margin-bottom:16px;">Klasik Yan Menü (%30)</h4>
          <div style="display:flex; flex-direction:column; gap:6px;">
            <div class="nav-item" onclick="navigateTo('launcher')">🏠 Ana Ekran</div>
            <div class="nav-item" onclick="navigateTo('drive')">⚙️ Sürüş Hafızası</div>
            <div class="nav-item" onclick="navigateTo('settings')">🛠️ Ayarlar</div>
          </div>
          <div style="margin-top:30px; font-size:11px; color:var(--text-muted);">
            ⚠️ Sabit Alt Dock Yoktur. Menü kaydıkça butonlar kaybolur.
          </div>
        </div>
        <!-- Right Content with TopBar -->
        <div style="flex:1; display:flex; flex-direction:column; background:var(--bg-page);">
          <div style="padding:12px 20px; border-bottom:1px solid var(--outline); background:var(--surface-bar); display:flex; justify-content:space-between;">
            <h3>${getPageTitle(state.activePage)}</h3>
            <span>Marka Logo</span>
          </div>
          <div style="padding:20px; flex:1; overflow-y:auto;">
            ${renderPageContent(state.activePage)}
          </div>
        </div>
      </div>
    `;
  }
}

function getPageTitle(pageId) {
  const titles = {
    launcher: 'Sürüş Kokpiti & Dashboard',
    drive: 'Sürüş Hafızası & Mod Seçimi',
    camera: '360° Çevre Görüş Kamerası',
    apps: 'Uygulama Yöneticisi',
    transfer: 'Aktar (Kablosuz Dosya & QR)',
    vehicle: 'Canlı Telemetri & Araç Durumu',
    equalizer: 'Ses Ayarları & Ekolayzer',
    settings: 'Sistem Tercihleri & HMI Ayarları'
  };
  return titles[pageId] || 'Sayfa';
}

function renderPageContent(pageId) {
  switch (pageId) {
    case 'launcher':
      return `
        <!-- Vehicle 3D / Wireframe Showcase Card -->
        <div class="vehicle-showcase-box">
          <svg class="car-svg-wireframe" viewBox="0 0 400 160" fill="none" stroke="currentColor">
            <path d="M40 110 L90 110 L120 70 L260 70 L300 110 L370 110 L380 130 L350 140 L50 140 Z" stroke="var(--oem-accent)" stroke-width="2.5" fill="rgba(69, 134, 197, 0.08)"/>
            <circle cx="100" cy="135" r="22" stroke="var(--text-primary)" stroke-width="3" fill="#10141D"/>
            <circle cx="310" cy="135" r="22" stroke="var(--text-primary)" stroke-width="3" fill="#10141D"/>
            <path d="M125 75 L180 75 L180 105 L105 105 Z" fill="rgba(115, 178, 245, 0.25)"/>
            <path d="M190 75 L255 75 L285 105 L190 105 Z" fill="rgba(115, 178, 245, 0.25)"/>
          </svg>
          <div class="car-hud-stats">
            <div class="car-hud-chip">FL: 2.3 bar (34 psi)</div>
            <div class="car-hud-chip">FR: 2.3 bar (34 psi)</div>
            <div class="car-hud-chip" style="color:var(--eco-color)">🔋 12V Akü: 14.2V</div>
            <div class="car-hud-chip">RL: 2.2 bar</div>
            <div class="car-hud-chip">RR: 2.2 bar</div>
          </div>
        </div>

        <!-- Quick Glance Cards Grid -->
        <div style="display:grid; grid-template-columns:1fr 1fr; gap:12px; margin-top:12px;">
          <div class="ivi-card">
            <div class="ivi-card-title">
              <span>🎵 Medya Oynatıcı</span>
              <span style="font-size:11px; color:var(--text-secondary);">Bluetooth Audio</span>
            </div>
            <div style="display:flex; align-items:center; gap:14px;">
              <div style="width:48px; height:48px; border-radius:12px; background:#1E2638; display:flex; align-items:center; justify-content:center; color:var(--oem-accent);">
                ${icons.music}
              </div>
              <div style="flex:1;">
                <h4 style="font-size:13px;">Omoda Chill Ride</h4>
                <p style="font-size:11px; color:var(--text-secondary);">Stereo Ambient · 3:24</p>
              </div>
              <div style="display:flex; gap:6px;">
                <button class="dock-btn" style="min-height:36px; padding:0 10px;">▶</button>
                <button class="dock-btn" style="min-height:36px; padding:0 10px;">⏭</button>
              </div>
            </div>
          </div>

          <div class="ivi-card">
            <div class="ivi-card-title">
              <span>🗺️ Son Navigasyon</span>
              <span style="font-size:11px; color:var(--oem-accent);">Aktif</span>
            </div>
            <p style="font-size:12px; font-weight:600;">İstanbul Çevre Yolu - 4.5 km</p>
            <p style="font-size:11px; color:var(--text-secondary); margin-top:2px;">Varış: 23:15 (28 dk)</p>
          </div>
        </div>
      `;

    case 'drive':
      return `
        <div class="ivi-card">
          <div class="ivi-card-title">
            <span>Sürüş Modu Tercihi</span>
            <span style="font-size:11px; color:var(--text-secondary);">Kayıtlı başlangıç modu</span>
          </div>
          <div class="drive-mode-grid">
            <div class="mode-tile ${state.driveMode === 'eco' ? 'active-eco' : ''}" onclick="setDriveMode('eco')">
              <div class="mode-name">ECO</div>
              <div class="mode-desc">Ekonomik & Sessiz</div>
            </div>
            <div class="mode-tile ${state.driveMode === 'normal' ? 'active-normal' : ''}" onclick="setDriveMode('normal')">
              <div class="mode-name">NORMAL</div>
              <div class="mode-desc">Dengeli Konfor</div>
            </div>
            <div class="mode-tile ${state.driveMode === 'sport' ? 'active-sport' : ''}" onclick="setDriveMode('sport')">
              <div class="mode-name">SPORT</div>
              <div class="mode-desc">Yüksek Performans</div>
            </div>
          </div>
        </div>

        <div class="ivi-card">
          <div class="ivi-card-title">
            <span>Sürüş Hafızası Ayarları</span>
          </div>
          <div class="toggle-row">
            <div class="toggle-info">
              <h4>Son Seçilen Modu Hatırla</h4>
              <p>Araç yeniden çalıştığında son kullanılan sürüş modunu otomatik kurar.</p>
            </div>
            <div class="switch-pill on" onclick="this.classList.toggle('on')"><div class="switch-knob"></div></div>
          </div>
          <div class="toggle-row">
            <div class="toggle-info">
              <h4>Kalkışta Karşılama Sesi</h4>
              <p>Kontağın açılışında seçilen ses temasını hoparlörden çalar.</p>
            </div>
            <div class="switch-pill on" onclick="this.classList.toggle('on')"><div class="switch-knob"></div></div>
          </div>
        </div>
      `;

    case 'settings':
      return `
        <div class="ivi-card">
          <div class="ivi-card-title">
            <span>Arayüz & Yerleşim Ergonomisi</span>
          </div>
          <div class="toggle-row">
            <div class="toggle-info">
              <h4>Header Menü Konumu</h4>
              <p>Sayfa başlığı ve geri düğmesi yukarıda veya aşağıda yerleşsin.</p>
            </div>
            <div class="sim-btn-group">
              <button class="sim-btn ${state.headerPos === 'top' ? 'active' : ''}" onclick="state.headerPos='top'; renderScreen();">Yukarıda</button>
              <button class="sim-btn ${state.headerPos === 'bottom' ? 'active' : ''}" onclick="state.headerPos='bottom'; renderScreen();">Aşağıda</button>
            </div>
          </div>
          <div class="toggle-row">
            <div class="toggle-info">
              <h4>Sol Paneli %10 Daralt</h4>
              <p>İçerik alanına daha fazla yatay genişlik kazandırır.</p>
            </div>
            <div class="switch-pill on" onclick="this.classList.toggle('on')"><div class="switch-knob"></div></div>
          </div>
          <div class="toggle-row">
            <div class="toggle-info">
              <h4>Koyu Tema (Night Mode)</h4>
              <p>Gece sürüşünde göz yorgunluğunu azaltan OEM paleti.</p>
            </div>
            <div class="switch-pill ${state.theme === 'dark' ? 'on' : ''}" onclick="document.getElementById('btnToggleTheme').click()"><div class="switch-knob"></div></div>
          </div>
        </div>
      `;

    case 'camera':
      return `
        <div class="ivi-card" style="background:#070A0F; border-color:#2A364F; text-align:center; padding:30px;">
          <div style="font-size:32px; color:var(--oem-accent); margin-bottom:12px;">📷 360° AVM SİMÜLASYONU</div>
          <p style="color:var(--text-secondary); max-width:400px; margin:0 auto 20px;">
            Ön, arka ve ayna kameraları birleştirilmiş kuş bakışı çevre görünümü.
          </p>
          <div style="display:flex; justify-content:center; gap:10px;">
            <button class="dock-btn" style="max-width:140px;">Ön Görünüm</button>
            <button class="dock-btn primary-dock" style="max-width:140px;">3D Kuş Bakışı</button>
            <button class="dock-btn" style="max-width:140px;">Geniş Arka</button>
          </div>
        </div>
      `;

    default:
      return `
        <div class="ivi-card">
          <div class="ivi-card-title">${getPageTitle(pageId)}</div>
          <p style="color:var(--text-secondary);">Bu sayfanın Komuta Masası + Ambiyans Sahnesi görünümü hazır.</p>
        </div>
      `;
  }
}
