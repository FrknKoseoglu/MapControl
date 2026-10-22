package com.mapcontrol.ui.activity;
import android.graphics.SurfaceTexture;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraDevice;
import android.hardware.camera2.CameraManager;
import android.hardware.camera2.params.OutputConfiguration;
import android.hardware.camera2.params.SessionConfiguration;
import android.hardware.camera2.params.StreamConfigurationMap;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.util.Size;
import android.content.Intent;
import android.view.Surface;
import android.view.TextureView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.content.ContextCompat;
import com.mapcontrol.bean.CameraBean;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashSet;
import java.util.List;
import com.mapcontrol.R;

public class CameraActivity extends AppCompatActivity implements TextureView.SurfaceTextureListener {
    private static final String TAG = CameraActivity.class.getSimpleName();
    private static final String ACTION_LOG = "com.mapcontrol.LOG_MESSAGE";
    private static final String EXTRA_LOG_MESSAGE = "log_message";
    
    // View references (findViewById ile alınacak)
    private TextureView tvSurfaceCamera1;
    private TextureView tvSurfaceCamera2;
    private TextureView tvSurfaceCamera3;
    private TextureView tvSurfaceCamera4;
    private TextView cameraText1;
    private TextView cameraText2;
    private TextView cameraText3;
    private TextView cameraText4;
    private LinearLayout llItem3;
    
    private CameraManager cameraManager;
    private Handler handler;
    private HandlerThread handlerThread;
    private int mNumCameraDevices;
    private Size outputSize;
    private final List<String> mCameraDevices = new ArrayList();
    private final List<CameraBean> mCameraBeans = new java.util.concurrent.CopyOnWriteArrayList<>();
    private volatile boolean destroyed;
    private volatile boolean cameraScreenVisible;
    private final java.util.Set<String> openingCameras = java.util.concurrent.ConcurrentHashMap.newKeySet();
    private boolean cameraPermissionRequested;
    private boolean isTextureAvailable0 = false;
    private boolean isTextureAvailable1 = false;
    private boolean isTextureAvailable2 = false;
    private boolean isTextureAvailable3 = false;
    private boolean isCameraAvailable0 = false;
    private boolean isCameraAvailable1 = false;
    private boolean isCameraAvailable2 = false;
    private boolean isCameraAvailable3 = false;
    CameraManager.AvailabilityCallback availabilityCallback = new AvailabilityCallback();

    class AvailabilityCallback extends CameraManager.AvailabilityCallback {
        AvailabilityCallback() {
        }

        @Override
        public void onCameraAvailable(@NonNull String str) {
            super.onCameraAvailable(str);
            if (destroyed) return;
            CameraActivity.this.log("[INFO] Kamera kullanılabilir: " + str);
            try {
                if (str.equals("0")) {
                    CameraActivity.this.isCameraAvailable0 = true;
                    CameraActivity.this.initCamera("DMS");
                } else if (str.equals("1")) {
                    CameraActivity.this.isCameraAvailable1 = true;
                    CameraActivity.this.initCamera("OMS");
                } else if (str.equals("2")) {
                    CameraActivity.this.isCameraAvailable2 = true;
                    CameraActivity.this.initCamera("DVR");
                } else if (str.equals("3")) {
                    CameraActivity.this.isCameraAvailable3 = true;
                    CameraActivity.this.initCamera("AVM4");
                }
            } catch (CameraAccessException e) {
                CameraActivity.this.log("[ERROR] Kamera başlatma hatası: " + str + " - " + e.getMessage());
            }
        }

        @Override
        public void onCameraUnavailable(@NonNull String str) {
            super.onCameraUnavailable(str);
        }
    }

    class CameraStateCallback extends CameraDevice.StateCallback {
        final String cameraId;

        CameraStateCallback(String str) {
            this.cameraId = str;
        }

        @Override
        public void onDisconnected(@NonNull CameraDevice cameraDevice) {
            CameraActivity.this.log("[WARN] Kamera bağlantısı kesildi: " + cameraDevice.getId());
            if (cameraDevice.getId().equals("0")) {
                CameraActivity.this.isCameraAvailable0 = false;
            } else if (cameraDevice.getId().equals("1")) {
                CameraActivity.this.isCameraAvailable1 = false;
            } else if (cameraDevice.getId().equals("2")) {
                CameraActivity.this.isCameraAvailable2 = false;
            } else if (cameraDevice.getId().equals("3")) {
                CameraActivity.this.isCameraAvailable3 = false;
            }
            CameraActivity.this.closeCamera(cameraDevice);
        }

        @Override
        public void onError(@NonNull CameraDevice cameraDevice, int i2) {
            CameraActivity.this.log("[ERROR] Kamera hatası: " + cameraDevice.getId() + " - Hata kodu: " + i2);
            CameraActivity.this.closeCamera(cameraDevice);
        }

        @Override
        public void onOpened(@NonNull CameraDevice cameraDevice) {
            synchronized (CameraActivity.this) {
            if (destroyed || !cameraScreenVisible) {
                openingCameras.remove(cameraDevice.getId());
                cameraDevice.close();
                return;
            }
            CameraActivity.this.log("[INFO] Kamera açıldı: " + cameraDevice.getId());
            try {
                CameraBean cameraBean = new CameraBean(this.cameraId, cameraDevice, new CameraBean.LogListener() {
                    @Override
                    public void onLog(String message) {
                        CameraActivity.this.log(message);
                    }
                });
                CameraActivity.this.mCameraBeans.add(cameraBean);
                CameraActivity.this.createCameraPreview(cameraBean);
            } catch (CameraAccessException | RuntimeException e) {
                CameraActivity.this.log("[ERROR] Kamera önizleme oluşturma hatası: " + e.getMessage());
                closeCamera(cameraDevice);
            }
            }
        }
    }

    private void closeCamera(CameraDevice cameraDevice) {
        if (cameraDevice != null) {
            openingCameras.remove(cameraDevice.getId());
            for (CameraBean bean : mCameraBeans) {
                if (bean.getCameraDevice() == cameraDevice) {
                    bean.release();
                    mCameraBeans.remove(bean);
                }
            }
            cameraDevice.close();
            log("[INFO] Kamera kapatıldı: " + cameraDevice.getId());
        }
    }

    private void createCameraPreview(CameraBean cameraBean) throws CameraAccessException {
        TextureView textureView;
        String cameraName = cameraBean.getCameraName();
        cameraName.hashCode();
        switch (cameraName) {
            case "DMS":
            case "AVM1":
                textureView = this.tvSurfaceCamera1;
                break;
            case "DVR":
            case "AVM3":
                textureView = this.tvSurfaceCamera3;
                break;
            case "OMS":
            case "AVM2":
                textureView = this.tvSurfaceCamera2;
                break;
            case "AVM4":
                textureView = this.tvSurfaceCamera4;
                break;
            default:
                return;
        }
        CameraDevice cameraDevice = cameraBean.getCameraDevice();
        SurfaceTexture surfaceTexture = textureView.getSurfaceTexture();
        if (destroyed || cameraDevice == null || surfaceTexture == null) {
            cameraBean.release();
            return;
        }
        StreamConfigurationMap outputs = cameraManager.getCameraCharacteristics(cameraDevice.getId())
                .get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP);
        Size[] sizes = outputs == null ? null : outputs.getOutputSizes(SurfaceTexture.class);
        if (sizes == null || sizes.length == 0) { closeCamera(cameraDevice); return; }
        surfaceTexture.setDefaultBufferSize(sizes[0].getWidth(), sizes[0].getHeight());
        Surface surface = new Surface(surfaceTexture);
        cameraBean.setPreviewSurface(surface);
        try {
            cameraBean.setCaptureRequestBuilder(cameraDevice.createCaptureRequest(CameraDevice.TEMPLATE_PREVIEW));
        } catch (CameraAccessException e2) {
            closeCamera(cameraDevice);
            return;
        }
        cameraBean.getCaptureRequestBuilder().addTarget(surface);
        try {
            cameraDevice.createCaptureSession(new SessionConfiguration(SessionConfiguration.SESSION_REGULAR,
                    Collections.singletonList(new OutputConfiguration(surface)), command -> handler.post(command),
                    cameraBean.getCaptureStateCallback()));
        } catch (CameraAccessException e3) {
            closeCamera(cameraDevice);
        }
    }

    private void initCamera(String str) throws CameraAccessException {
        if (destroyed || !cameraScreenVisible) return;
        String str2;
        str.hashCode();
        switch (str) {
            case "DMS":
            case "AVM1":
                setTitleName(this.cameraText1, str);
                str2 = "0";
                break;
            case "DVR":
            case "AVM3":
                setTitleName(this.cameraText3, str);
                str2 = "2";
                break;
            case "OMS":
            case "AVM2":
                setTitleName(this.cameraText2, str);
                str2 = "1";
                break;
            case "AVM4":
                setTitleName(this.cameraText4, str);
                str2 = "3";
                break;
            default:
                str2 = "";
                break;
        }
        log("[DEBUG] Kamera başlatılıyor: cameraId = " + str2);
        if (ContextCompat.checkSelfPermission(this, "android.permission.CAMERA") != 0) {
            runOnUiThread(() -> {
                if (!destroyed && !cameraPermissionRequested) {
                    cameraPermissionRequested = true;
                    requestPermissions(new String[]{"android.permission.CAMERA"}, 2);
                }
            });
            return;
        }
        try {
            this.outputSize = ((StreamConfigurationMap) this.cameraManager.getCameraCharacteristics(str2).get(CameraCharacteristics.SCALER_STREAM_CONFIGURATION_MAP)).getOutputSizes(SurfaceTexture.class)[0];
            log("[DEBUG] Kamera çıktı boyutu: " + this.outputSize.toString());
            if ((str2.equals("0") && this.isTextureAvailable0 && this.isCameraAvailable0) || ((str2.equals("1") && this.isTextureAvailable1 && this.isCameraAvailable1) || ((str2.equals("2") && this.isTextureAvailable2 && this.isCameraAvailable2) || (str2.equals("3") && this.isTextureAvailable3 && this.isCameraAvailable3)))) {
                if (openingCameras.add(str2)) this.cameraManager.openCamera(str2, new CameraStateCallback(str), this.handler);
            }
        } catch (Exception e2) {
            openingCameras.remove(str2);
            e2.printStackTrace();
        }
    }

    private void initCameraManager() throws CameraAccessException {
        if (this.cameraManager == null) {
            this.cameraManager = (CameraManager) getApplicationContext().getSystemService(CAMERA_SERVICE);
        }
        updateCameraDevices();
        this.cameraManager.registerAvailabilityCallback(this.availabilityCallback, this.handler);
    }

    private void initHandler() {
        log("[DEBUG] Handler başlatılıyor");
        HandlerThread handlerThread = new HandlerThread(TAG);
        this.handlerThread = handlerThread;
        handlerThread.start();
        this.handler = new Handler(this.handlerThread.getLooper());
    }

    private void setTitleName(TextView textView, String str) {
        runOnUiThread(() -> {
            textView.setText(str);
        });
    }

    private boolean updateCameraDevices() throws CameraAccessException {
        try {
            String[] cameraIdList = this.cameraManager.getCameraIdList();
            HashSet hashSet = new HashSet(Arrays.asList(cameraIdList));
            for (int i2 = 0; i2 < this.mCameraDevices.size(); i2++) {
                if (!hashSet.contains(this.mCameraDevices.get(i2))) {
                    this.mCameraDevices.set(i2, null);
                    this.mNumCameraDevices--;
                }
            }
            hashSet.removeAll(this.mCameraDevices);
            for (String str : cameraIdList) {
                if (hashSet.contains(str)) {
                    this.mCameraDevices.add(str);
                    this.mNumCameraDevices++;
                }
            }
            log("[INFO] Kamera cihazları güncellendi: " + this.mNumCameraDevices + " kamera - " + Arrays.toString(this.mCameraDevices.toArray()));
            return true;
        } catch (CameraAccessException e2) {
            log("[ERROR] Kamera cihaz listesi alınamadı: " + e2.getMessage());
            return false;
        }
    }

    @Override
    protected void onCreate(@Nullable Bundle bundle) {
        super.onCreate(bundle);
        setContentView(R.layout.activity_camera);
        setTitle(R.string.camera_test_title);
        
        // findViewById ile view'ları al
        tvSurfaceCamera1 = findViewById(R.id.tv_surface_camera1);
        tvSurfaceCamera2 = findViewById(R.id.tv_surface_camera2);
        tvSurfaceCamera3 = findViewById(R.id.tv_surface_camera3);
        tvSurfaceCamera4 = findViewById(R.id.tv_surface_camera4);
        cameraText1 = findViewById(R.id.camera_text1);
        cameraText2 = findViewById(R.id.camera_text2);
        cameraText3 = findViewById(R.id.camera_text3);
        cameraText4 = findViewById(R.id.camera_text4);
        llItem3 = findViewById(R.id.ll_item3);
        
        // Geri dön butonu
        android.widget.Button btnBack = findViewById(R.id.btn_back);
        if (btnBack != null) {
            btnBack.setOnClickListener(v -> {
                finish(); // Activity'yi kapat ve MainActivity'ye dön
            });
        }
        
        initHandler();
        try {
            initCameraManager();
        } catch (CameraAccessException e) {
            log("[ERROR] Kamera yöneticisi başlatma hatası: " + e.getMessage());
        }
        tvSurfaceCamera1.setSurfaceTextureListener(this);
        tvSurfaceCamera2.setSurfaceTextureListener(this);
        tvSurfaceCamera3.setSurfaceTextureListener(this);
        // 4. kamerayı aktif et
        tvSurfaceCamera4.setSurfaceTextureListener(this);
        // llItem3'ü gizle (5. ve 6. kamera için)
        llItem3.setVisibility(android.view.View.GONE);
    }

    @Override
    protected void onStart() {
        super.onStart();
        cameraScreenVisible = true;
        retryCameras();
    }

    @Override
    protected void onStop() {
        synchronized (this) {
            cameraScreenVisible = false;
            for (CameraBean bean : mCameraBeans) bean.release();
            mCameraBeans.clear();
            openingCameras.clear();
        }
        super.onStop();
    }

    private void retryCameras() {
        if (handler == null || destroyed) return;
        handler.post(() -> {
            for (String name : new String[]{"DMS", "OMS", "DVR", "AVM4"}) {
                try { initCamera(name); } catch (CameraAccessException unavailable) {
                    log("Kamera açılamadı: " + name);
                }
            }
        });
    }

    @Override
    protected void onDestroy() {
        synchronized (this) {
            destroyed = true;
            for (CameraBean bean : mCameraBeans) bean.release();
            mCameraBeans.clear();
        }
        super.onDestroy();
        if (cameraManager != null) {
            cameraManager.unregisterAvailabilityCallback(this.availabilityCallback);
        }
        if (this.handlerThread != null) {
            this.handlerThread.quitSafely();
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == 2 && grantResults.length > 0
                && grantResults[0] == android.content.pm.PackageManager.PERMISSION_GRANTED && !destroyed) {
            retryCameras();
        }
    }

    @Override
    public void onSurfaceTextureAvailable(@NonNull SurfaceTexture surfaceTexture, int i2, int i3) {
        try {
            if (surfaceTexture == this.tvSurfaceCamera1.getSurfaceTexture()) {
                this.isTextureAvailable0 = true;
                initCamera("DMS");
            } else if (surfaceTexture == this.tvSurfaceCamera2.getSurfaceTexture()) {
                this.isTextureAvailable1 = true;
                initCamera("OMS");
            } else if (surfaceTexture == this.tvSurfaceCamera3.getSurfaceTexture()) {
                this.isTextureAvailable2 = true;
                initCamera("DVR");
            } else if (surfaceTexture == this.tvSurfaceCamera4.getSurfaceTexture()) {
                // 4. kamerayı aktif et
                this.isTextureAvailable3 = true;
                initCamera("AVM4");
            }
        } catch (CameraAccessException e) {
            log("[ERROR] onSurfaceTextureAvailable'da kamera başlatma hatası: " + e.getMessage());
        }
    }
    
    /**
     * MapControl log sistemine mesaj gönder (MainActivity'deki log görünümüne)
     */
    private void log(String msg) {
        Intent intent = new Intent(ACTION_LOG);
        intent.setPackage(getPackageName());
        intent.putExtra(EXTRA_LOG_MESSAGE, msg);
        sendBroadcast(intent);
    }

    @Override
    public boolean onSurfaceTextureDestroyed(@NonNull SurfaceTexture surfaceTexture) {
        if (surfaceTexture == this.tvSurfaceCamera1.getSurfaceTexture()) {
            this.isTextureAvailable0 = false;
        } else if (surfaceTexture == this.tvSurfaceCamera2.getSurfaceTexture()) {
            this.isTextureAvailable1 = false;
        } else if (surfaceTexture == this.tvSurfaceCamera3.getSurfaceTexture()) {
            this.isTextureAvailable2 = false;
        } else if (surfaceTexture == this.tvSurfaceCamera4.getSurfaceTexture()) {
            // 4. kamera için
            this.isTextureAvailable3 = false;
        }
        for (CameraBean bean : mCameraBeans) {
            String name = bean.getCameraName();
            TextureView view = ("DMS".equals(name) || "AVM1".equals(name)) ? tvSurfaceCamera1
                    : ("OMS".equals(name) || "AVM2".equals(name)) ? tvSurfaceCamera2
                    : ("DVR".equals(name) || "AVM3".equals(name)) ? tvSurfaceCamera3 : tvSurfaceCamera4;
            if (view.getSurfaceTexture() == surfaceTexture) closeCamera(bean.getCameraDevice());
        }
        return true;
    }

    @Override
    public void onSurfaceTextureSizeChanged(@NonNull SurfaceTexture surfaceTexture, int i2, int i3) {
    }

    @Override
    public void onSurfaceTextureUpdated(@NonNull SurfaceTexture surfaceTexture) {
    }
}

