package com.rnscan;

import static android.content.Context.VIBRATOR_SERVICE;

import android.content.Context;
import android.os.Vibrator;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;

import com.facebook.react.bridge.Arguments;
import com.facebook.react.bridge.ReactContext;
import com.facebook.react.bridge.WritableMap;
import com.facebook.react.uimanager.events.RCTEventEmitter;

import cn.bingoogolapple.qrcode.core.QRCodeView;
import cn.bingoogolapple.qrcode.zxing.ZXingView;

public class ScanView extends LinearLayout implements QRCodeView.Delegate {
    private static final String TAG = ScanView.class.getSimpleName();
    private ZXingView zXingView;

    public ScanView(Context context) {
        super(context);
        initView();
    }

    private void initView() {
        LayoutInflater inflater = LayoutInflater.from(getContext());
        View view = inflater.inflate(R.layout.scan_view, null);

        // 设置布局参数
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);

        this.addView(view, params);

        zXingView = findViewById(R.id.zXingView);

        zXingView.setDelegate(this);

        zXingView.startCamera();
        zXingView.startSpotAndShowRect();
    }

    // 手机震动
    private void vibrate() {
        Vibrator vibrator = (Vibrator) getContext().getSystemService(VIBRATOR_SERVICE);
        vibrator.vibrate(200);
    }

    @Override
    public void onScanQRCodeSuccess(String result) {

        // 处理扫描结果
        ReactContext reactContext = (ReactContext) getContext();
        // 通过emit发送事件
        WritableMap event = Arguments.createMap();
        // 传递参数
        event.putString("qrcode", result);
        // 发送事件
        reactContext.getJSModule(RCTEventEmitter.class).receiveEvent(
                getId(),
                "onScanResult",
                event
        );

        //成功时震动手机
        vibrate();
        Log.i(TAG, "onScanQRCodeSuccess: " + result);

        // 重新开始扫描
        zXingView.startSpot();
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
