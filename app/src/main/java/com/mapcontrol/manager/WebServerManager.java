package com.mapcontrol.manager;
import android.content.Context;
import android.net.Uri;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageManager;
import android.net.wifi.WifiInfo;
import android.net.wifi.WifiManager;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.File;
import java.io.BufferedInputStream;
import org.json.JSONArray;
import org.json.JSONObject;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.URL;
import java.net.UnknownHostException;
import java.nio.charset.StandardCharsets;
import java.util.concurrent.atomic.AtomicBoolean;
import android.os.Environment;


import com.mapcontrol.R;
import com.mapcontrol.ui.theme.UiStyles;

/**
 * Basit HTTP Server Manager
 * 7462 portunda HTTP servisi başlatır ve welcome sayfası gösterir
 */
public class WebServerManager {
    private static final int MAX_JSON_POST_BYTES = 65536;
    /** open-maps JSON içindeki url alanı: POST üst sınırına yakın; Yandex sctx gibi çok uzun paylaşım linklerine izin */
    private static final int MAX_OPEN_URL_CHARS = MAX_JSON_POST_BYTES - 200;
    private static final int MAX_KEYBOARD_TEXT_CHARS = 8000;
    private ServerSocket serverSocket;
    private Thread serverThread;
    private AtomicBoolean isRunning = new AtomicBoolean(false);
    private int serverPort;
    private volatile com.mapcontrol.http.WebSession session;
    private java.util.concurrent.ThreadPoolExecutor clientPool;
    private final java.util.Set<Socket> activeClients = java.util.concurrent.ConcurrentHashMap.newKeySet();
    private final Context context;
    private volatile WebServerListener listener;

    public interface WebServerListener {
        void onServerStarted(int port, String localIp);
        void onServerStopped();
        void onError(String error);
        void onInstallApk(String fileName);
        void onDeleteApp(String packageName);
        void onLaunchApp(String packageName);
        void onLog(String message);
        /** Araçtaki tarayıcı / uygun uygulama: {@link android.content.Intent#ACTION_VIEW} (http(s) için {@code BROWSABLE}). */
        void onOpenMapUrl(String url);
        /**
         * Odaklı metin alanına (erişilebilirlik) yazdırma.
         * @param showDeviceFeedback {@code true} ise araçta kısa başarı/hata tostu; anlık yazma için genelde {@code false}
         */
        void onTypeKeyboardText(String text, boolean showDeviceFeedback);
    }

    public WebServerManager(Context context) {
        this.context = context.getApplicationContext();
    }

    public void setListener(WebServerListener listener) {
        this.listener = listener;
    }

    /**
     * HTTP server'ı başlat (7462 portunda)
     */
    public synchronized void startServer() {
        if (isRunning.get()) return;
        try {
            serverPort = 7462;
            final ServerSocket socket = new ServerSocket(serverPort);
            final com.mapcontrol.http.WebSession startedSession = new com.mapcontrol.http.WebSession();
            final java.util.concurrent.ThreadPoolExecutor workers = new java.util.concurrent.ThreadPoolExecutor(
                    4, 4, 0L, java.util.concurrent.TimeUnit.MILLISECONDS,
                    new java.util.concurrent.ArrayBlockingQueue<>(8));
            serverSocket = socket;
            session = startedSession;
            clientPool = workers;
            isRunning.set(true);
            serverThread = new Thread(() -> {
                try {
                    String localIp = getLocalIpAddress();
                    if (listener != null) listener.onServerStarted(serverPort, localIp);
                    while (!socket.isClosed()) {
                        Socket client = socket.accept();
                        activeClients.add(client);
                        try {
                            workers.execute(() -> handleClient(client, startedSession));
                        } catch (java.util.concurrent.RejectedExecutionException busy) {
                            activeClients.remove(client);
                            closeClient(client);
                        }
                    }
                } catch (IOException error) {
                    if (!socket.isClosed() && listener != null) listener.onError("Server hatası: " + error.getMessage());
                } finally {
                    synchronized (WebServerManager.this) {
                        if (serverSocket == socket) stopServer();
                    }
                }
            }, "MapControl-HTTP");
            serverThread.start();
        } catch (IOException error) {
            if (listener != null) listener.onError("Server başlatma hatası: " + error.getMessage());
        }
    }

    public synchronized String getAccessUrl(String localIp) {
        String base = "http://" + localIp + ":" + serverPort;
        return session == null ? base : session.accessUrl(base);
    }

    public String getAccessUrl() {
        return getAccessUrl(getLocalIpAddress());
    }

    public synchronized void stopServer() {
        boolean wasRunning = isRunning.getAndSet(false);
        session = null;
        if (serverSocket != null) {
            try { serverSocket.close(); } catch (IOException ignored) { }
            serverSocket = null;
        }
        if (clientPool != null) {
            clientPool.shutdownNow();
            clientPool = null;
        }
        for (Socket client : activeClients) closeClient(client);
        activeClients.clear();
        if (wasRunning && listener != null) listener.onServerStopped();
    }

    private static void closeClient(Socket client) {
        try { client.close(); } catch (IOException ignored) { }
    }

    private void handleClient(Socket clientSocket, com.mapcontrol.http.WebSession requestSession) {
        try (Socket client = clientSocket) {
            client.setSoTimeout(10000);
            client.setTcpNoDelay(true);
            InputStream input = new BufferedInputStream(client.getInputStream(), 16384);
            OutputStream output = client.getOutputStream();
            com.mapcontrol.http.HttpRequest request;
            try {
                request = com.mapcontrol.http.HttpRequest.read(input);
            } catch (IOException malformed) {
                sendErrorResponse(output, 400, malformed.getMessage());
                return;
            }
            if (!com.mapcontrol.http.WebSession.sameOrigin(request.header("origin"), request.header("host"))) {
                sendErrorResponse(output, 403, "Bu kaynaktan erişime izin verilmiyor");
                return;
            }
            String method = request.method;
            String path = request.path;
            String query = request.query;
            boolean welcome = "/".equals(path) && ("GET".equals(method) || "HEAD".equals(method));
            if (!welcome && (!isRunning.get() || session != requestSession
                    || !requestSession.accepts(request.header("x-mapcontrol-key")))) {
                sendErrorResponse(output, 401, "Araç ekranındaki QR kodunu yeniden tarayın");
                return;
            }
            client.setSoTimeout("/upload".equals(path) ? 30000 : 10000);
            if (listener != null) listener.onLog("HTTP Request: " + method + " " + path);
            if (welcome) {
                byte[] page = generateWelcomePage().getBytes(StandardCharsets.UTF_8);
                String header = "HTTP/1.1 200 OK\r\nContent-Type: text/html; charset=UTF-8\r\n"
                        + "Cache-Control: no-store\r\nReferrer-Policy: no-referrer\r\nX-Frame-Options: DENY\r\n"
                        + "Content-Length: " + page.length + "\r\nConnection: close\r\n\r\n";
                output.write(header.getBytes(StandardCharsets.UTF_8));
                if (!"HEAD".equals(method)) output.write(page);
                output.flush();
            } else if ("POST".equals(method) && "/upload".equals(path)) {
                handleFileUpload(input, output, request);
            } else if ("POST".equals(method) && "/action/open-maps".equals(path)) {
                handleOpenMaps(input, output, request.rawHeaders);
            } else if ("POST".equals(method) && "/action/keyboard".equals(path)) {
                handleKeyboard(input, output, request.rawHeaders);
            } else if ("GET".equals(method) && "/files".equals(path)) {
                handleFileList(output);
            } else if ("DELETE".equals(method) && "/files".equals(path)) {
                handleFileDelete(output, query);
            } else if ("POST".equals(method) && "/install".equals(path)) {
                handleInstallApk(output, query);
            } else if ("GET".equals(method) && "/apps".equals(path)) {
                handleAppList(output);
            } else if ("POST".equals(method) && "/app/delete".equals(path)) {
                handleAppDelete(output, query);
            } else if ("POST".equals(method) && "/app/launch".equals(path)) {
                handleAppLaunch(output, query);
            } else {
                sendErrorResponse(output, 404, "Adres bulunamadı");
            }
        } catch (Exception error) {
            if (isRunning.get() && listener != null) listener.onLog("[ERROR] HTTP: " + error.getMessage());
        } finally {
            activeClients.remove(clientSocket);
        }
    }

    private String generateWelcomePage() {
        try {
            InputStream inputStream = context.getAssets().open("welcome.html");
            BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream, StandardCharsets.UTF_8));
            StringBuilder html = new StringBuilder();
            String line;
            while ((line = reader.readLine()) != null) {
                html.append(line).append("\n");
            }
            reader.close();
            inputStream.close();
            
            // Placeholder'ı değiştir
            String localIp = getLocalIpAddress();
            String serverUrl = "http://" + localIp + ":" + serverPort;
            return html.toString().replace("{{SERVER_URL}}", serverUrl);
        } catch (IOException e) {
            if (listener != null) listener.onLog("[ERROR] HTML dosyası okuma hatası: " + e.getMessage());
            // Fallback: basit HTML
            String localIp = getLocalIpAddress();
            String bg = String.format("#%06X", 0xFFFFFF & UiStyles.color(context, R.color.backgroundPage));
            String fg = String.format("#%06X", 0xFFFFFF & UiStyles.color(context, R.color.textPrimary));
            return "<!DOCTYPE html><html><head><meta charset=\"UTF-8\"><title>Map Control</title></head>" +
                   "<body style=\"font-family: sans-serif; background: " + bg + "; color: " + fg + "; padding: 40px; text-align: center;\">" +
                   "<h1>Hoş Geldiniz!</h1>" +
                   "<p>Map Control Web Yönetimi</p>" +
                   "<p><code>http://" + localIp + ":" + serverPort + "</code></p>" +
                   "</body></html>";
        }
    }

    /** Streams one multipart file; failed requests only remove their own temporary file. */
    private void handleFileUpload(InputStream input, OutputStream output, com.mapcontrol.http.HttpRequest request) {
        try {
            long length = request.contentLength();
            if (length > com.mapcontrol.http.MultipartUpload.MAX_UPLOAD_BYTES) {
                sendErrorResponse(output, 413, "Dosya yükleme sınırı 1 GB");
                return;
            }
            File downloadDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File file = com.mapcontrol.http.MultipartUpload.receive(input, length,
                    request.header("content-type"), downloadDir);
            if (listener != null) listener.onLog("Dosya kaydedildi: " + file.getName() + " (" + file.length() + " bytes)");
            String body = "Dosya başarıyla yüklendi: " + file.getName();
            byte[] data = body.getBytes(StandardCharsets.UTF_8);
            output.write(("HTTP/1.1 200 OK\r\nContent-Type: text/plain; charset=UTF-8\r\n"
                    + "Cache-Control: no-store\r\nX-Content-Type-Options: nosniff\r\n"
                    + "Content-Length: " + data.length + "\r\nConnection: close\r\n\r\n").getBytes(StandardCharsets.UTF_8));
            output.write(data);
            output.flush();
        } catch (IOException error) {
            sendErrorResponse(output, 400, error.getMessage());
        }
    }

    private int parseContentLengthFromHeaders(String requestHeader) {
        if (requestHeader == null) {
            return -1;
        }
        for (String line : requestHeader.split("\r\n")) {
            String l = line.trim();
            if (l.isEmpty()) {
                break;
            }
            if (l.toLowerCase().startsWith("content-length:")) {
                try {
                    return Integer.parseInt(l.substring(15).trim());
                } catch (NumberFormatException e) {
                    return -1;
                }
            }
        }
        return -1;
    }

    private byte[] readFixedBodyFromStream(InputStream input, int contentLength) throws IOException {
        if (contentLength < 0) {
            throw new IOException("Content-Length yok");
        }
        if (contentLength > MAX_JSON_POST_BYTES) {
            throw new IOException("İstek çok büyük");
        }
        byte[] buf = new byte[contentLength];
        int off = 0;
        while (off < contentLength) {
            int n = input.read(buf, off, contentLength - off);
            if (n == -1) {
                throw new IOException("Beklenmeyen son");
            }
            off += n;
        }
        return buf;
    }

    /**
     * Web textarea'dan gelen paylaşım linki: sınır ve görünmez karakterler, istenmeyen satır kırılmaları.
     */
    private static String normalizeOpenMapUrlString(String raw) {
        if (raw == null) {
            return "";
        }
        return raw.trim()
                .replace("\u200B", "")
                .replace("\uFEFF", "")
                .replaceAll("[\r\n]+", "");
    }

    /**
     * Araçta {@link android.content.Intent#ACTION_VIEW} ile açılabilecek linkler.
     * Tüm http ve https URI'ları (en az bir host ile); kısa link, yönlendirme, arama, harita sitesi — sistem uygun uygulamayı veya tarayıcıyı açar.
     * Ayrıca {@code geo:} ve {@code google.navigation:}.
     * Engelli şemalar: {@code javascript}, {@code file}, {@code data}, {@code content}.
     */
    static boolean isAllowedMapUrl(String raw) {
        if (raw == null) {
            return false;
        }
        String t = raw.trim();
        if (t.isEmpty() || t.length() > MAX_OPEN_URL_CHARS) {
            return false;
        }
        Uri u = Uri.parse(t);
        String scheme = u.getScheme();
        if (scheme == null) {
            return false;
        }
        String s = scheme.toLowerCase();
        if ("javascript".equals(s) || "file".equals(s) || "data".equals(s) || "content".equals(s)) {
            return false;
        }
        if ("geo".equals(s) || "google.navigation".equals(s)) {
            return true;
        }
        if ("http".equals(s) || "https".equals(s)) {
            String host = u.getHost();
            return host != null && !host.isEmpty();
        }
        return false;
    }

    private void handleOpenMaps(InputStream input, OutputStream output, String requestHeader) {
        try {
            int cl = parseContentLengthFromHeaders(requestHeader);
            if (cl < 0) {
                sendErrorResponse(output, 400, "Content-Length gerekli");
                return;
            }
            byte[] body = readFixedBodyFromStream(input, cl);
            JSONObject o = new JSONObject(new String(body, StandardCharsets.UTF_8));
            if (!o.has("url")) {
                sendErrorResponse(output, 400, "url alanı gerekli");
                return;
            }
            String url = normalizeOpenMapUrlString(o.getString("url"));
            if (!isAllowedMapUrl(url)) {
                if (listener != null) {
                    listener.onLog("[WARN] Engellenen link: " + url);
                }
                sendErrorResponse(output, 400, "Geçersiz veya izin verilmeyen link (http/https, geo, google.navigation; javascript/file/data/content yok)");
                return;
            }
            if (listener == null) {
                sendErrorResponse(output, 500, "Listener yok");
                return;
            }
            listener.onOpenMapUrl(url);
            if (listener != null) {
                listener.onLog("Link isteği: " + url);
            }
            String json = new JSONObject().put("ok", true).put("url", url).toString();
            sendJson200(output, json);
        } catch (Exception e) {
            if (listener != null) {
                listener.onLog("[ERROR] open-maps: " + e.getMessage());
            }
            sendErrorResponse(output, 400, "open-maps: " + e.getMessage());
        }
    }

    private void handleKeyboard(InputStream input, OutputStream output, String requestHeader) {
        try {
            int cl = parseContentLengthFromHeaders(requestHeader);
            if (cl < 0) {
                sendErrorResponse(output, 400, "Content-Length gerekli");
                return;
            }
            byte[] body = readFixedBodyFromStream(input, cl);
            JSONObject o = new JSONObject(new String(body, StandardCharsets.UTF_8));
            if (!o.has("text")) {
                sendErrorResponse(output, 400, "text alanı gerekli");
                return;
            }
            String text = o.getString("text");
            if (text.length() > MAX_KEYBOARD_TEXT_CHARS) {
                sendErrorResponse(output, 400, "Metin çok uzun (max " + MAX_KEYBOARD_TEXT_CHARS + ")");
                return;
            }
            if (listener == null) {
                sendErrorResponse(output, 500, "Listener yok");
                return;
            }
            boolean showDeviceFeedback = o.optBoolean("notify", true);
            listener.onTypeKeyboardText(text, showDeviceFeedback);
            if (listener != null) {
                if (showDeviceFeedback) {
                    listener.onLog("Klavye metni " + text.length() + " karakter");
                }
            }
            String json = new JSONObject().put("ok", true).toString();
            sendJson200(output, json);
        } catch (Exception e) {
            if (listener != null) {
                listener.onLog("[ERROR] keyboard: " + e.getMessage());
            }
            sendErrorResponse(output, 400, "keyboard: " + e.getMessage());
        }
    }

    private void sendJson200(OutputStream output, String json) throws IOException {
        String http = "HTTP/1.1 200 OK\r\n" +
                "Content-Type: application/json; charset=UTF-8\r\n" +
                "Cache-Control: no-store\r\nReferrer-Policy: no-referrer\r\nX-Content-Type-Options: nosniff\r\n" +
                "Content-Length: " + json.getBytes(StandardCharsets.UTF_8).length + "\r\n" +
                "Connection: close\r\n\r\n" +
                json;
        output.write(http.getBytes(StandardCharsets.UTF_8));
        output.flush();
    }

    /**
     * Hata yanıtı gönder
     */
    private void sendErrorResponse(OutputStream output, int statusCode, String message) {
        try {
            String response = "HTTP/1.1 " + statusCode + " " + getStatusText(statusCode) + "\r\n" +
                    "Content-Type: text/plain; charset=UTF-8\r\n" +
                    "Cache-Control: no-store\r\nReferrer-Policy: no-referrer\r\nX-Content-Type-Options: nosniff\r\n" +
                    "Content-Length: " + message.getBytes(StandardCharsets.UTF_8).length + "\r\n" +
                    "Connection: close\r\n\r\n" +
                    message;
            output.write(response.getBytes(StandardCharsets.UTF_8));
            output.flush();
        } catch (IOException e) {
            if (listener != null) listener.onLog("[ERROR] Hata yanıtı gönderme hatası: " + e.getMessage());
        }
    }

    /**
     * Dosya listesini döndür (JSON)
     */
    private void handleFileList(OutputStream output) {
        try {
            File downloadDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            if (!downloadDir.exists()) {
                downloadDir.mkdirs();
            }
            
            File[] files = downloadDir.listFiles();
            JSONArray fileArray = new JSONArray();
            
            if (files != null) {
                for (File file : files) {
                    if (file.isFile()) {
                        JSONObject fileObj = new JSONObject();
                        fileObj.put("name", file.getName());
                        fileObj.put("size", file.length());
                        fileObj.put("sizeFormatted", formatFileSize(file.length()));
                        fileObj.put("modified", file.lastModified());
                        fileArray.put(fileObj);
                    }
                }
            }
            
            JSONObject response = new JSONObject();
            response.put("files", fileArray);
            response.put("count", fileArray.length());
            
            String jsonResponse = response.toString();
            String httpResponse = "HTTP/1.1 200 OK\r\n" +
                    "Content-Type: application/json; charset=UTF-8\r\n" +
                    "Cache-Control: no-store\r\nReferrer-Policy: no-referrer\r\nX-Content-Type-Options: nosniff\r\n" +
                    "Content-Length: " + jsonResponse.getBytes(StandardCharsets.UTF_8).length + "\r\n" +
                    "Connection: close\r\n\r\n" +
                    jsonResponse;
            output.write(httpResponse.getBytes(StandardCharsets.UTF_8));
            output.flush();
            
        } catch (Exception e) {
            if (listener != null) listener.onLog("[ERROR] Dosya listesi hatası: " + e.getMessage());
            sendErrorResponse(output, 500, "Dosya listesi hatası: " + e.getMessage());
        }
    }
    
    /**
     * Dosyayı sil
     */
    private void handleFileDelete(OutputStream output, String queryString) {
        try {
            // Query string'den filename'i al
            String fileName = null;
            if (queryString != null && !queryString.isEmpty()) {
                String[] params = queryString.split("&");
                for (String param : params) {
                    String[] keyValue = param.split("=");
                    if (keyValue.length == 2 && "filename".equals(keyValue[0])) {
                        fileName = java.net.URLDecoder.decode(keyValue[1], StandardCharsets.UTF_8.name());
                        break;
                    }
                }
            }
            
            if (fileName == null || fileName.isEmpty()) {
                sendErrorResponse(output, 400, "Dosya adı belirtilmedi");
                return;
            }
            
            // Güvenlik: sadece dosya adı, path traversal saldırılarını önle
            if (fileName.contains("..") || fileName.contains("/") || fileName.contains("\\")) {
                sendErrorResponse(output, 400, "Geçersiz dosya adı");
                return;
            }
            
            File downloadDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File file = com.mapcontrol.http.UploadFiles.resolve(downloadDir, fileName);
            
            if (!file.exists()) {
                sendErrorResponse(output, 404, "Dosya bulunamadı");
                return;
            }
            
            if (!file.isFile()) {
                sendErrorResponse(output, 400, "Bu bir dosya değil");
                return;
            }
            
            boolean deleted = file.delete();
            
            if (deleted) {
                if (listener != null) listener.onLog("Dosya silindi: " + file.getAbsolutePath());
                String successResponse = "Dosya başarıyla silindi: " + fileName;
                String httpResponse = "HTTP/1.1 200 OK\r\n" +
                        "Content-Type: text/plain; charset=UTF-8\r\n" +
                        "Cache-Control: no-store\r\nReferrer-Policy: no-referrer\r\nX-Content-Type-Options: nosniff\r\n" +
                        "Content-Length: " + successResponse.getBytes(StandardCharsets.UTF_8).length + "\r\n" +
                        "Connection: close\r\n\r\n" +
                        successResponse;
                output.write(httpResponse.getBytes(StandardCharsets.UTF_8));
                output.flush();
            } else {
                sendErrorResponse(output, 500, "Dosya silinemedi");
            }
            
        } catch (Exception e) {
            if (listener != null) listener.onLog("[ERROR] Dosya silme hatası: " + e.getMessage());
            sendErrorResponse(output, 500, "Dosya silme hatası: " + e.getMessage());
        }
    }
    
    /**
     * APK kurulum işlemini handle et
     */
    private void handleInstallApk(OutputStream output, String queryString) {
        try {
            // Query string'den filename'i al
            String fileName = null;
            if (queryString != null && !queryString.isEmpty()) {
                String[] params = queryString.split("&");
                for (String param : params) {
                    String[] keyValue = param.split("=");
                    if (keyValue.length == 2 && "filename".equals(keyValue[0])) {
                        fileName = java.net.URLDecoder.decode(keyValue[1], StandardCharsets.UTF_8.name());
                        break;
                    }
                }
            }
            
            if (fileName == null || fileName.isEmpty()) {
                sendErrorResponse(output, 400, "Dosya adı belirtilmedi");
                return;
            }
            
            // Güvenlik: sadece APK dosyaları
            if (!fileName.toLowerCase().endsWith(".apk")) {
                sendErrorResponse(output, 400, "Sadece APK dosyaları kurulabilir");
                return;
            }
            
            // Güvenlik: path traversal saldırılarını önle
            if (fileName.contains("..") || fileName.contains("/") || fileName.contains("\\")) {
                sendErrorResponse(output, 400, "Geçersiz dosya adı");
                return;
            }
            
            File downloadDir = Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS);
            File file = com.mapcontrol.http.UploadFiles.resolve(downloadDir, fileName);
            
            if (!file.exists()) {
                sendErrorResponse(output, 404, "Dosya bulunamadı");
                return;
            }
            
            if (!file.isFile()) {
                sendErrorResponse(output, 400, "Bu bir dosya değil");
                return;
            }
            
            // Listener'a bildir (MainActivity'de kurulum yapılacak)
            if (listener != null) {
                listener.onInstallApk(fileName);
                if (listener != null) listener.onLog("APK kurulum isteği gönderildi: " + fileName);
                
                String successResponse = "APK kurulum başlatıldı: " + fileName;
                String httpResponse = "HTTP/1.1 200 OK\r\n" +
                        "Content-Type: text/plain; charset=UTF-8\r\n" +
                        "Cache-Control: no-store\r\nReferrer-Policy: no-referrer\r\nX-Content-Type-Options: nosniff\r\n" +
                        "Content-Length: " + successResponse.getBytes(StandardCharsets.UTF_8).length + "\r\n" +
                        "Connection: close\r\n\r\n" +
                        successResponse;
                output.write(httpResponse.getBytes(StandardCharsets.UTF_8));
                output.flush();
            } else {
                sendErrorResponse(output, 500, "Listener bulunamadı");
            }
            
        } catch (Exception e) {
            if (listener != null) listener.onLog("[ERROR] APK kurulum hatası: " + e.getMessage());
            sendErrorResponse(output, 500, "APK kurulum hatası: " + e.getMessage());
        }
    }

    /**
     * Yüklü uygulamaları listele (sadece user uygulamaları)
     */
    private void handleAppList(OutputStream output) {
        try {
            PackageManager pm = context.getPackageManager();
            java.util.List<ApplicationInfo> apps = pm.getInstalledApplications(PackageManager.GET_META_DATA);
            
            JSONArray appArray = new JSONArray();
            
            for (ApplicationInfo appInfo : apps) {
                // Sistem ve priv uygulamalarını filtrele
                if (isSystemOrPrivApp(appInfo)) {
                    continue;
                }
                
                try {
                    String appName = pm.getApplicationLabel(appInfo).toString();
                    String packageName = appInfo.packageName;
                    
                    JSONObject appObj = new JSONObject();
                    appObj.put("name", appName);
                    appObj.put("package", packageName);
                    appObj.put("locked", "com.mapcontrol".equals(packageName));
                    appArray.put(appObj);
                } catch (Exception e) {
                    // Uygulama bilgisi alınamazsa atla
                    continue;
                }
            }
            
            JSONObject response = new JSONObject();
            response.put("apps", appArray);
            
            String jsonResponse = response.toString();
            String httpResponse = "HTTP/1.1 200 OK\r\n" +
                    "Content-Type: application/json; charset=UTF-8\r\n" +
                    "Cache-Control: no-store\r\nReferrer-Policy: no-referrer\r\nX-Content-Type-Options: nosniff\r\n" +
                    "Content-Length: " + jsonResponse.getBytes(StandardCharsets.UTF_8).length + "\r\n" +
                    "Connection: close\r\n\r\n" +
                    jsonResponse;
            output.write(httpResponse.getBytes(StandardCharsets.UTF_8));
            output.flush();
            
        } catch (Exception e) {
            if (listener != null) listener.onLog("[ERROR] Uygulama listesi hatası: " + e.getMessage());
            sendErrorResponse(output, 500, "Uygulama listesi hatası: " + e.getMessage());
        }
    }

    /**
     * Uygulama silme işlemini handle et
     */
    private void handleAppDelete(OutputStream output, String queryString) {
        try {
            // Query string'den packageName'i al
            String packageName = null;
            if (queryString != null && !queryString.isEmpty()) {
                String[] params = queryString.split("&");
                for (String param : params) {
                    String[] keyValue = param.split("=");
                    if (keyValue.length == 2 && "package".equals(keyValue[0])) {
                        packageName = java.net.URLDecoder.decode(keyValue[1], StandardCharsets.UTF_8.name());
                        break;
                    }
                }
            }
            
            if (packageName == null || packageName.isEmpty()) {
                sendErrorResponse(output, 400, "Paket adı belirtilmedi");
                return;
            }
            
            // Güvenlik: com.mapcontrol silinemez
            if ("com.mapcontrol".equals(packageName)) {
                sendErrorResponse(output, 400, "Bu uygulama silinemez");
                return;
            }
            
            // Sistem uygulaması kontrolü
            PackageManager pm = context.getPackageManager();
            try {
                ApplicationInfo appInfo = pm.getApplicationInfo(packageName, 0);
                if (isSystemOrPrivApp(appInfo)) {
                    sendErrorResponse(output, 400, "Sistem uygulamaları silinemez");
                    return;
                }
            } catch (PackageManager.NameNotFoundException e) {
                sendErrorResponse(output, 404, "Uygulama bulunamadı");
                return;
            }
            
            // Listener'a bildir (MainActivity'de silme yapılacak)
            if (listener != null) {
                listener.onDeleteApp(packageName);
                if (listener != null) listener.onLog("Uygulama silme isteği gönderildi: " + packageName);
                
                String successResponse = "Uygulama silme başlatıldı: " + packageName;
                String httpResponse = "HTTP/1.1 200 OK\r\n" +
                        "Content-Type: text/plain; charset=UTF-8\r\n" +
                        "Cache-Control: no-store\r\nReferrer-Policy: no-referrer\r\nX-Content-Type-Options: nosniff\r\n" +
                        "Content-Length: " + successResponse.getBytes(StandardCharsets.UTF_8).length + "\r\n" +
                        "Connection: close\r\n\r\n" +
                        successResponse;
                output.write(httpResponse.getBytes(StandardCharsets.UTF_8));
                output.flush();
            } else {
                sendErrorResponse(output, 500, "Listener bulunamadı");
            }
            
        } catch (Exception e) {
            if (listener != null) listener.onLog("[ERROR] Uygulama silme hatası: " + e.getMessage());
            sendErrorResponse(output, 500, "Uygulama silme hatası: " + e.getMessage());
        }
    }

    /**
     * Uygulama açma işlemini handle et
     */
    private void handleAppLaunch(OutputStream output, String queryString) {
        try {
            // Query string'den packageName'i al
            String packageName = null;
            if (queryString != null && !queryString.isEmpty()) {
                String[] params = queryString.split("&");
                for (String param : params) {
                    String[] keyValue = param.split("=");
                    if (keyValue.length == 2 && "package".equals(keyValue[0])) {
                        packageName = java.net.URLDecoder.decode(keyValue[1], StandardCharsets.UTF_8.name());
                        break;
                    }
                }
            }
            
            if (packageName == null || packageName.isEmpty()) {
                sendErrorResponse(output, 400, "Paket adı belirtilmedi");
                return;
            }
            
            // Uygulama var mı kontrol et
            PackageManager pm = context.getPackageManager();
            try {
                pm.getApplicationInfo(packageName, 0);
            } catch (PackageManager.NameNotFoundException e) {
                sendErrorResponse(output, 404, "Uygulama bulunamadı");
                return;
            }
            
            // Listener'a bildir (MainActivity'de açma yapılacak)
            if (listener != null) {
                listener.onLaunchApp(packageName);
                if (listener != null) listener.onLog("Uygulama açma isteği gönderildi: " + packageName);
                
                String successResponse = "Uygulama açma başlatıldı: " + packageName;
                String httpResponse = "HTTP/1.1 200 OK\r\n" +
                        "Content-Type: text/plain; charset=UTF-8\r\n" +
                        "Cache-Control: no-store\r\nReferrer-Policy: no-referrer\r\nX-Content-Type-Options: nosniff\r\n" +
                        "Content-Length: " + successResponse.getBytes(StandardCharsets.UTF_8).length + "\r\n" +
                        "Connection: close\r\n\r\n" +
                        successResponse;
                output.write(httpResponse.getBytes(StandardCharsets.UTF_8));
                output.flush();
            } else {
                sendErrorResponse(output, 500, "Listener bulunamadı");
            }
            
        } catch (Exception e) {
            if (listener != null) listener.onLog("[ERROR] Uygulama açma hatası: " + e.getMessage());
            sendErrorResponse(output, 500, "Uygulama açma hatası: " + e.getMessage());
        }
    }

    /**
     * Bir uygulamanın sistem uygulaması veya priv-app olup olmadığını kontrol eder
     */
    private boolean isSystemOrPrivApp(ApplicationInfo appInfo) {
        try {
            if (appInfo == null) {
                return true;
            }
            
            // com.mapcontrol hariç tut
            if ("com.mapcontrol".equals(appInfo.packageName)) {
                return false;
            }
            
            // Sistem uygulaması kontrolü
            boolean isSystemApp = (appInfo.flags & ApplicationInfo.FLAG_SYSTEM) != 0;
            boolean isUpdatedSystemApp = (appInfo.flags & ApplicationInfo.FLAG_UPDATED_SYSTEM_APP) != 0;
            
            if (isSystemApp || isUpdatedSystemApp) {
                return true;
            }
            
            // Priv-app kontrolü (sourceDir ve publicSourceDir kontrolü)
            String sourceDir = appInfo.sourceDir;
            String publicSourceDir = appInfo.publicSourceDir;
            
            if (sourceDir != null && (sourceDir.contains("/system/priv-app/") || sourceDir.contains("/system/app/"))) {
                return true;
            }
            
            if (publicSourceDir != null && (publicSourceDir.contains("/system/priv-app/") || publicSourceDir.contains("/system/app/"))) {
                return true;
            }
            
            return false;
        } catch (Exception e) {
            // Hata durumunda güvenli tarafta kal (sistem uygulaması say)
            return true;
        }
    }
    
    /**
     * Dosya boyutunu formatla
     */
    private String formatFileSize(long bytes) {
        if (bytes < 1024) return bytes + " B";
        if (bytes < 1024 * 1024) return String.format("%.2f KB", bytes / 1024.0);
        if (bytes < 1024 * 1024 * 1024) return String.format("%.2f MB", bytes / (1024.0 * 1024.0));
        return String.format("%.2f GB", bytes / (1024.0 * 1024.0 * 1024.0));
    }
    
    /**
     * HTTP status text
     */
    private String getStatusText(int statusCode) {
        switch (statusCode) {
            case 400: return "Bad Request";
            case 401: return "Unauthorized";
            case 403: return "Forbidden";
            case 413: return "Payload Too Large";
            case 404: return "Not Found";
            case 405: return "Method Not Allowed";
            case 500: return "Internal Server Error";
            default: return "Error";
        }
    }

    /**
     * Local IP adresini al
     */
    private String getLocalIpAddress() {
        try {
            WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
            if (wifiManager != null) {
                WifiInfo wifiInfo = wifiManager.getConnectionInfo();
                int ipAddress = wifiInfo.getIpAddress();
                if (ipAddress != 0) {
                    return String.format("%d.%d.%d.%d",
                            (ipAddress & 0xff),
                            (ipAddress >> 8 & 0xff),
                            (ipAddress >> 16 & 0xff),
                            (ipAddress >> 24 & 0xff));
                }
            }
        } catch (Exception e) {
            if (listener != null) listener.onLog("[ERROR] IP adresi alma hatası: " + e.getMessage());
        }

        // Fallback: localhost
        try {
            return InetAddress.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            return "127.0.0.1";
        }
    }

    /**
     * Server çalışıyor mu?
     */
    public boolean isRunning() {
        return isRunning.get();
    }

    /**
     * Server port numarası
     */
    public int getPort() {
        return serverPort;
    }
}
