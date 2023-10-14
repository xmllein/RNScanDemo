package com.rnscan;

import android.app.Activity;
import android.content.Intent;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.bridge.Callback;
import com.facebook.react.bridge.Promise;
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

    // js 层调用方法, 用于原生桥接方法,promise 用于回调
    @ReactMethod
    public void startScan(Promise promise) {
        // 跳转到扫描页面
        Activity activity = getCurrentActivity();
        if (activity == null || activity.isDestroyed()) {
            return;
        }
        // 传递 promise 对象
        ScanActivity.promise = promise;

        Intent intent = new Intent(activity, ScanActivity.class);
        activity.startActivity(intent);
    }


    // js 层调用方法, 用于原生桥接方法,callback 用于回调
    @ReactMethod
    public void startScan2(Callback callback) {
        // 跳转到扫描页面
        Activity activity = getCurrentActivity();
        if (activity == null || activity.isDestroyed()) {
            return;
        }
        // 传递 callback 回调
        ScanActivity.callback = callback;

        Intent intent = new Intent(activity, ScanActivity.class);
        activity.startActivity(intent);
    }
}
