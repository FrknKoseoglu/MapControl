import { createServer } from 'node:http';
import { readFile } from 'node:fs/promises';
const html = await readFile(new URL('./dist/mapcontrol-installer.html', import.meta.url));
createServer((req, res) => {
  if (req.headers.host !== 'localhost:8787' && req.headers.host !== '127.0.0.1:8787') { res.writeHead(403); res.end(); return; }
  if (!['GET','HEAD'].includes(req.method) || !['/','/mapcontrol-installer.html'].includes(req.url)) { res.writeHead(404); res.end(); return; }
  res.writeHead(200, {'Content-Type':'text/html; charset=utf-8','Cache-Control':'no-store','X-Content-Type-Options':'nosniff','Permissions-Policy':'usb=(self)','Content-Security-Policy':"frame-ancestors 'none'"});
  res.end(req.method === 'HEAD' ? undefined : html);
}).listen(8787, '127.0.0.1', () => console.log('Yerel yükleyici: http://127.0.0.1:8787 (yalnızca bu cihaz)'));
