package com.rnscan;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.facebook.react.common.MapBuilder;
import com.facebook.react.uimanager.SimpleViewManager;
import com.facebook.react.uimanager.ThemedReactContext;

import java.util.Map;

public class ScanViewManager extends SimpleViewManager<ScanView> {
    @NonNull
    @Override
    public String getName() {
        return "ScanView";
    }

    @NonNull
    @Override
    protected ScanView createViewInstance(@NonNull ThemedReactContext themedReactContext) {
        return new ScanView(themedReactContext);
    }

    @Nullable
    @Override
    public Map getExportedCustomBubblingEventTypeConstants() {
        return MapBuilder.builder()
                .put(
                        "onScanResult",
                        MapBuilder.of(
                                "phasedRegistrationNames",
                                MapBuilder.of("bubbled", "onScanResult")))
                .build();
    }
}
