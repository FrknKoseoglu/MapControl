package com.mapcontrol.api;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Handler;
import android.os.Looper;
import org.json.JSONObject;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.nio.charset.StandardCharsets;
import com.mapcontrol.ui.activity.MainActivity;

/**
 * Profile API Service - API işlemleri için ayrı servis sınıfı
 * MainActivity'den bağımsız çalışır
 */
public class ProfileApiService {
    // Android emülatörden localhost:5000'e erişmek için 10.0.2.2 kullanılır
    private static final String API_BASE_URL = "https://api.vnoisy.dev";
    private static final String PREFS_NAME = "MapControlPrefs";
    private static final String KEY_CAR_TOKEN = "carToken";
    private static final String KEY_USER_EMAIL = "userEmail";
    
    private Context context;
    private Handler mainHandler;
    
    public interface ApiCallback {
        void onSuccess(String message, JSONObject data);
        void onError(String error);
    }
    
    public ProfileApiService(Context context) {
        this.context = context.getApplicationContext();
        this.mainHandler = new Handler(Looper.getMainLooper());
    }
    
    /**
     * Kaydedilmiş token'ı getir
     */
    public String getCarToken() {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getString(KEY_CAR_TOKEN, null);
    }
    
    /**
     * Token kaydet
     */
    private void saveCarToken(String token) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(KEY_CAR_TOKEN, token);
        editor.apply();
    }
    
    /**
     * Kullanıcı email'ini kaydet
     */
    public void saveUserEmail(String email) {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.putString(KEY_USER_EMAIL, email);
        editor.apply();
    }
    
    /**
     * Kaydedilmiş email'i getir
     */
    public String getUserEmail() {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        return prefs.getString(KEY_USER_EMAIL, null);
    }
    
    /**
     * Token'ı sil (çıkış yap)
     */
    public void clearToken() {
        SharedPreferences prefs = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE);
        SharedPreferences.Editor editor = prefs.edit();
        editor.remove(KEY_CAR_TOKEN);
        editor.remove(KEY_USER_EMAIL);
        editor.apply();
    }
    
    /**
     * Giriş yapılmış mı kontrol et
     */
    public boolean isLoggedIn() {
        return getCarToken() != null && !getCarToken().isEmpty();
    }
    
    /**
     * Doğrulama kodu gönder
     */
    public void sendVerificationCode(String email, ApiCallback callback) {
        new Thread(() -> {
            HttpURLConnection conn = null;
            try {
                URL url = new URL(API_BASE_URL + "/api/auth/send-code");
                conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(10000);
                conn.setReadTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setDoOutput(true);
                
                JSONObject requestBody = new JSONObject();
                requestBody.put("email", email);
                
                OutputStream os = conn.getOutputStream();
                os.write(requestBody.toString().getBytes(StandardCharsets.UTF_8));
                os.flush();
                os.close();
                
                int responseCode = conn.getResponseCode();
                BufferedReader reader;
                if (responseCode >= 200 && responseCode < 300) {
                    reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
                } else {
                    reader = new BufferedReader(new InputStreamReader(errorStream(conn), StandardCharsets.UTF_8));
                }
                
                String response = readResponse(reader);
                reader.close();
                conn.disconnect();
                
                JSONObject jsonResponse = new JSONObject(response.toString());
                
                if (responseCode >= 200 && responseCode < 300) {
                    String message = jsonResponse.optString("message", "Verification code sent");
                    mainHandler.post(() -> callback.onSuccess(message, jsonResponse));
                } else {
                    String error = jsonResponse.optString("message", "Failed to send verification code");
                    mainHandler.post(() -> callback.onError(error));
                }
            } catch (Exception e) {
                mainHandler.post(() -> callback.onError("Hata: " + e.getMessage()));
            } finally {
                if (conn != null) conn.disconnect();
            }
        }).start();
    }
    
    /**
     * Kodu doğrula ve token al
     */
    public void verifyCode(String email, String code, ApiCallback callback) {
        new Thread(() -> {
            HttpURLConnection conn = null;
            try {
                URL url = new URL(API_BASE_URL + "/api/auth/verify-code");
                conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(10000);
                conn.setReadTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setDoOutput(true);
                
                JSONObject requestBody = new JSONObject();
                requestBody.put("email", email);
                requestBody.put("code", code);
                
                OutputStream os = conn.getOutputStream();
                os.write(requestBody.toString().getBytes(StandardCharsets.UTF_8));
                os.flush();
                os.close();
                
                int responseCode = conn.getResponseCode();
                BufferedReader reader;
                if (responseCode >= 200 && responseCode < 300) {
                    reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
                } else {
                    reader = new BufferedReader(new InputStreamReader(errorStream(conn), StandardCharsets.UTF_8));
                }
                
                String response = readResponse(reader);
                reader.close();
                conn.disconnect();
                
                JSONObject jsonResponse = new JSONObject(response.toString());
                
                if (responseCode >= 200 && responseCode < 300) {
                    String token = jsonResponse.optString("token", "");
                    if (!token.isEmpty()) {
                        saveCarToken(token);
                        saveUserEmail(email);
                    }
                    String message = jsonResponse.optString("message", "Login successful");
                    mainHandler.post(() -> callback.onSuccess(message, jsonResponse));
                } else {
                    String error = jsonResponse.optString("message", "Verification failed");
                    mainHandler.post(() -> callback.onError(error));
                }
            } catch (Exception e) {
                mainHandler.post(() -> callback.onError("Hata: " + e.getMessage()));
            } finally {
                if (conn != null) conn.disconnect();
            }
        }).start();
    }
    
    /**
     * Kullanıcı verilerini kaydet
     */
    public void saveUserData(JSONObject data, ApiCallback callback) {
        String token = getCarToken();
        if (token == null || token.isEmpty()) {
            mainHandler.post(() -> callback.onError("Giriş yapılmamış. Lütfen önce giriş yapın."));
            return;
        }
        
        new Thread(() -> {
            HttpURLConnection conn = null;
            try {
                URL url = new URL(API_BASE_URL + "/api/userdata/save");
                conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(10000);
                conn.setReadTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("Authorization", "Bearer " + token);
                conn.setDoOutput(true);
                
                JSONObject requestBody = new JSONObject();
                requestBody.put("data", data);
                
                OutputStream os = conn.getOutputStream();
                os.write(requestBody.toString().getBytes(StandardCharsets.UTF_8));
                os.flush();
                os.close();
                
                int responseCode = conn.getResponseCode();
                BufferedReader reader;
                if (responseCode >= 200 && responseCode < 300) {
                    reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
                } else {
                    reader = new BufferedReader(new InputStreamReader(errorStream(conn), StandardCharsets.UTF_8));
                }
                
                String response = readResponse(reader);
                reader.close();
                conn.disconnect();
                
                JSONObject jsonResponse = new JSONObject(response.toString());
                
                if (responseCode >= 200 && responseCode < 300) {
                    String message = jsonResponse.optString("message", "Data saved successfully");
                    mainHandler.post(() -> callback.onSuccess(message, jsonResponse));
                } else {
                    String error = jsonResponse.optString("message", "Failed to save data");
                    mainHandler.post(() -> callback.onError(error));
                }
            } catch (Exception e) {
                mainHandler.post(() -> callback.onError("Hata: " + e.getMessage()));
            } finally {
                if (conn != null) conn.disconnect();
            }
        }).start();
    }
    
    /**
     * Kullanıcı verilerini getir
     */
    public void getUserData(ApiCallback callback) {
        String token = getCarToken();
        if (token == null || token.isEmpty()) {
            mainHandler.post(() -> callback.onError("Giriş yapılmamış. Lütfen önce giriş yapın."));
            return;
        }
        
        new Thread(() -> {
            HttpURLConnection conn = null;
            try {
                URL url = new URL(API_BASE_URL + "/api/userdata/get");
                conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(10000);
                conn.setReadTimeout(15000);
                conn.setRequestMethod("GET");
                conn.setRequestProperty("Authorization", "Bearer " + token);
                
                int responseCode = conn.getResponseCode();
                BufferedReader reader;
                if (responseCode >= 200 && responseCode < 300) {
                    reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
                } else {
                    reader = new BufferedReader(new InputStreamReader(errorStream(conn), StandardCharsets.UTF_8));
                }
                
                String response = readResponse(reader);
                reader.close();
                conn.disconnect();
                
                if (responseCode >= 200 && responseCode < 300) {
                    JSONObject jsonResponse = new JSONObject(response.toString());
                    mainHandler.post(() -> callback.onSuccess("Data retrieved successfully", jsonResponse));
                } else {
                    JSONObject jsonResponse = new JSONObject(response.toString());
                    String error = jsonResponse.optString("message", "Failed to get data");
                    mainHandler.post(() -> callback.onError(error));
                }
            } catch (Exception e) {
                mainHandler.post(() -> callback.onError("Hata: " + e.getMessage()));
            } finally {
                if (conn != null) conn.disconnect();
            }
        }).start();
    }
    
    /**
     * Mevcut konumu kaydet/güncelle
     */
    public void saveCoordinates(double latitude, double longitude, String label, String description, ApiCallback callback) {
        String token = getCarToken();
        if (token == null || token.isEmpty()) {
            mainHandler.post(() -> callback.onError("Giriş yapılmamış. Lütfen önce giriş yapın."));
            return;
        }
        
        new Thread(() -> {
            HttpURLConnection conn = null;
            try {
                URL url = new URL(API_BASE_URL + "/api/coordinates/save");
                conn = (HttpURLConnection) url.openConnection();
                conn.setConnectTimeout(10000);
                conn.setReadTimeout(15000);
                conn.setRequestMethod("POST");
                conn.setRequestProperty("Content-Type", "application/json");
                conn.setRequestProperty("Authorization", "Bearer " + token);
                conn.setDoOutput(true);
                
                JSONObject requestBody = new JSONObject();
                requestBody.put("latitude", latitude);
                requestBody.put("longitude", longitude);
                if (label != null && !label.isEmpty()) {
                    requestBody.put("label", label);
                }
                if (description != null && !description.isEmpty()) {
                    requestBody.put("description", description);
                }
                
                OutputStream os = conn.getOutputStream();
                os.write(requestBody.toString().getBytes(StandardCharsets.UTF_8));
                os.flush();
                os.close();
                
                int responseCode = conn.getResponseCode();
                BufferedReader reader;
                if (responseCode >= 200 && responseCode < 300) {
                    reader = new BufferedReader(new InputStreamReader(conn.getInputStream(), StandardCharsets.UTF_8));
                } else {
                    reader = new BufferedReader(new InputStreamReader(errorStream(conn), StandardCharsets.UTF_8));
                }
                
                String response = readResponse(reader);
                reader.close();
                conn.disconnect();
                
                JSONObject jsonResponse = new JSONObject(response.toString());
                
                if (responseCode >= 200 && responseCode < 300) {
                    String message = jsonResponse.optString("message", "Current location saved successfully");
                    mainHandler.post(() -> callback.onSuccess(message, jsonResponse));
                } else {
                    String error = jsonResponse.optString("message", "Failed to save location");
                    mainHandler.post(() -> callback.onError(error));
                }
            } catch (Exception e) {
                mainHandler.post(() -> callback.onError("Hata: " + e.getMessage()));
            } finally {
                if (conn != null) conn.disconnect();
            }
        }).start();
    }
    private static java.io.InputStream errorStream(HttpURLConnection connection) {
        java.io.InputStream error = connection.getErrorStream();
        return error != null ? error : new java.io.ByteArrayInputStream(new byte[0]);
    }    private static String readResponse(BufferedReader reader) throws java.io.IOException {
        try (BufferedReader input = reader) {
            StringBuilder body = new StringBuilder();
            char[] buffer = new char[4096];
            for (int count; (count = input.read(buffer)) != -1;) {
                if (body.length() + count > 1024 * 1024) throw new java.io.IOException("API yanıtı çok büyük");
                body.append(buffer, 0, count);
            }
            return body.toString();
        }
    }
}
