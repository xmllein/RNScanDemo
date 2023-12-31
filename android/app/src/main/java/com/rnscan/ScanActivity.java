package com.rnscan;

import android.os.Bundle;
import android.os.Vibrator;
import android.util.Log;

import androidx.appcompat.app.AppCompatActivity;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;

import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;

public class ScanActivity extends AppCompatActivity implements QRCodeView.Delegate {
    private static  final String TAG = ScanActivity.class.getSimpleName();
    private ZXingView zXingView;

    // 用于回调 js promise
    public static Promise promise;

    // 用于回调 js callback
    public static Callback callback;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_scan);

        zXingView = findViewById(R.id.zXingView);

        zXingView.setDelegate(this);
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(TAG, "onStart: " + zXingView);
        zXingView.startCamera();
        zXingView.startSpotAndShowRect();

    }

    @Override
    protected void onStop() {
        zXingView.stopCamera();
        super.onStop();
    }

    @Override
    protected void onDestroy() {
        zXingView.onDestroy();
        super.onDestroy();
    }

    // 手机震动
    private void vibrate(){
        Vibrator vibrator = (Vibrator)getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }

    @Override
    public void onScanQRCodeSuccess(String result) {
        //成功时震动手机
        vibrate();
        Log.i(TAG, "onScanQRCodeSuccess: " + result);
        // promise 回调 (推荐)
         promise.resolve(result);
         promise = null;

        // callback 回调
        // callback.invoke(result);



        // 退出当前页面
        this.finish();

    }

    @Override
    public void onCameraAmbientBrightnessChanged(boolean isDark) {
        // TODO: 处理环境光变化

    }

    @Override
    public void onScanQRCodeOpenCameraError() {
        // TODO: 处理打开相机出错
        // 1, 相机错误
        // 2, 权限错误

    }

}
