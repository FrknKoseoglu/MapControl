import { build } from 'esbuild';
import { readFile, writeFile, mkdir } from 'node:fs/promises';
const result = await build({ entryPoints: ['src/main.mjs'], bundle: true, write: false, format: 'iife', target: ['chrome105'], minify: true, legalComments: 'eof', charset: 'utf8' });
const source = await readFile('index.template.html', 'utf8');
const script = result.outputFiles[0].text.replaceAll('</script', '<\\/script');
await mkdir('dist', { recursive: true });
let licenses = '';
for (const name of ['@yume-chan/adb','@yume-chan/adb-daemon-webusb','@yume-chan/adb-credential-web','@yume-chan/stream-extra','@yume-chan/async','@yume-chan/struct','@yume-chan/event','@yume-chan/no-data-view','fflate']) {
  licenses += `\n\n--- ${name} ---\n` + await readFile(`node_modules/${name}/LICENSE`, 'utf8');
}
await writeFile('dist/mapcontrol-installer.html', source.replace('/* INSTALLER_BUNDLE */', () => script) + '\n<!--\n' + licenses.replaceAll('-->', '-- >') + '\n-->');
await writeFile('dist/THIRD-PARTY-LICENSES.txt', licenses);
console.log('dist/mapcontrol-installer.html hazır.');
