import { inflateSync } from 'fflate';

export async function apkPackage(file) {
  const tailStart = Math.max(0, file.size - 65557);
  const tail = new Uint8Array(await file.slice(tailStart).arrayBuffer());
  const view = new DataView(tail.buffer);
  let end = -1;
  for (let i = tail.length - 22; i >= 0; i--) {
    if (view.getUint32(i, true) === 0x06054b50 && i + 22 + view.getUint16(i + 20, true) === tail.length) { end = i; break; }
  }
  if (end < 0) throw new Error('APK geçerli bir ZIP dosyası değil.');
  const size = view.getUint32(end + 12, true), offset = view.getUint32(end + 16, true);
  if (size > 8 * 1024 ** 2 || offset + size > tailStart + end) throw new Error('APK dizini geçersiz veya çok büyük.');
  const directory = new Uint8Array(await file.slice(offset, offset + size).arrayBuffer());
  const dv = new DataView(directory.buffer);
  for (let i = 0; i + 46 <= directory.length;) {
    if (dv.getUint32(i, true) !== 0x02014b50) break;
    const nameLen = dv.getUint16(i + 28, true), extraLen = dv.getUint16(i + 30, true), commentLen = dv.getUint16(i + 32, true);
    const name = new TextDecoder().decode(directory.subarray(i + 46, i + 46 + nameLen));
    if (name === 'AndroidManifest.xml') {
      const method = dv.getUint16(i + 10, true), compressed = dv.getUint32(i + 20, true), raw = dv.getUint32(i + 24, true), local = dv.getUint32(i + 42, true);
      if (!raw || raw > 8 * 1024 ** 2 || compressed > 4 * 1024 ** 2 || local + 30 > offset) throw new Error('Manifest boyutu geçersiz.');
      const header = new DataView(await file.slice(local, local + 30).arrayBuffer());
      if (header.getUint32(0, true) !== 0x04034b50) throw new Error('APK dosya başlığı geçersiz.');
      const start = local + 30 + header.getUint16(26, true) + header.getUint16(28, true);
      if (start + compressed > offset) throw new Error('Manifest sınır dışında.');
      const bytes = new Uint8Array(await file.slice(start, start + compressed).arrayBuffer());
      const xml = method === 0 ? bytes : method === 8 ? inflateSync(bytes, { out: new Uint8Array(raw) }) : null;
      if (!xml || xml.length !== raw) throw new Error('Manifest sıkıştırması desteklenmiyor.');
      return binaryManifestPackage(xml);
    }
    i += 46 + nameLen + extraLen + commentLen;
  }
  throw new Error('AndroidManifest.xml bulunamadı.');
}

export function binaryManifestPackage(bytes) {
  const v = new DataView(bytes.buffer, bytes.byteOffset, bytes.byteLength);
  if (bytes.length < 8 || v.getUint16(0, true) !== 3 || v.getUint32(4, true) !== bytes.length) throw new Error('Android manifest biçimi geçersiz.');
  let strings = [];
  for (let pos = v.getUint16(2, true); pos + 8 <= bytes.length;) {
    const type = v.getUint16(pos, true), header = v.getUint16(pos + 2, true), size = v.getUint32(pos + 4, true);
    if (header < 8 || size < header || pos + size > bytes.length) throw new Error('Manifest bölümü geçersiz.');
    if (type === 1) {
      if (header < 28) throw new Error('Manifest metin tablosu geçersiz.');
      const count = v.getUint32(pos + 8, true), utf8 = !!(v.getUint32(pos + 16, true) & 256), data = pos + v.getUint32(pos + 20, true);
      if (count > 100000 || header + count * 4 > size || data < pos + header + count * 4 || data > pos + size) throw new Error('Manifest metin tablosu geçersiz.');
      strings = [];
      for (let i = 0; i < count; i++) {
        let p = data + v.getUint32(pos + header + i * 4, true);
        function length8() { let n = bytes[p++]; if (n & 128) n = ((n & 127) << 8) | bytes[p++]; return n; }
        let length;
        if (utf8) { length8(); length = length8(); }
        else { length = v.getUint16(p, true); p += 2; if (length & 32768) { length = ((length & 32767) * 65536) + v.getUint16(p, true); p += 2; } length *= 2; }
        if (!Number.isFinite(length) || p < data || p + length > pos + size) throw new Error('Manifest metni sınır dışında.');
        strings.push(new TextDecoder(utf8 ? 'utf-8' : 'utf-16le', { fatal: true }).decode(bytes.subarray(p, p + length)));
      }
    } else if (type === 0x102) {
      const ext = pos + header;
      if (ext + 20 > pos + size) throw new Error('Manifest etiketi geçersiz.');
      if (strings[v.getUint32(ext + 4, true)] === 'manifest') {
        const start = ext + v.getUint16(ext + 8, true), stride = v.getUint16(ext + 10, true), count = v.getUint16(ext + 12, true);
        if (stride < 20 || start < ext + 20 || start + stride * count > pos + size) throw new Error('Manifest alanları geçersiz.');
        for (let i = 0; i < count; i++) {
          const a = start + stride * i;
          if (strings[v.getUint32(a + 4, true)] === 'package') {
            const raw = v.getUint32(a + 8, true);
            const value = raw !== 0xffffffff ? strings[raw] : bytes[a + 15] === 3 ? strings[v.getUint32(a + 16, true)] : null;
            if (typeof value === 'string') return value;
          }
        }
      }
    }
    pos += size;
  }
  throw new Error('Manifest paket adı okunamadı.');
}
