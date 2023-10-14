package com.rnscan;

import android.app.Activity;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.ReactApplicationContext;
import com.facebook.react.bridge.ReactContextBaseJavaModule;
import com.facebook.react.bridge.ReactMethod;

public class ScanModule extends ReactContextBaseJavaModule {

    public ScanModule(@Nullable ReactApplicationContext reactContext) {
        super(reactContext);
    }

    @NonNull
    @Override
    public String getName() {
        return "Scan";
    }

    // js 层调用方法, 用于原生桥接方法
    @ReactMethod
    public void startScan(){
        // 跳转到扫描页面
        Activity activity = getCurrentActivity();
        if (activity == null || activity.isDestroyed()){
            return;
        }
        Intent intent = new Intent(activity, ScanActivity.class);
        activity.startActivity(intent);
    }
}
