package com.hardwaretest.app;

import android.content.Context;
import android.os.Build;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

// DeviceInfoTest.java
public class DeviceInfoTest {
    private final Context context;

    public DeviceInfoTest(Context context) {
        this.context = context;
    }

    public List<TestResult> runTest() {
        List<TestResult> results = new ArrayList<>();

        results.add(new TestResult("Device Model", Build.MODEL, true));
        results.add(new TestResult("Manufacturer", Build.MANUFACTURER, true));
        results.add(new TestResult("Android Version", Build.VERSION.RELEASE, true));
        results.add(new TestResult("API Level", String.valueOf(Build.VERSION.SDK_INT), true));
        results.add(new TestResult("Device Brand", Build.BRAND, true));
        results.add(new TestResult("Hardware", Build.HARDWARE, true));

        // Display info
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        
        results.add(new TestResult("Screen Width", metrics.widthPixels + " px", true));
        results.add(new TestResult("Screen Height", metrics.heightPixels + " px", true));
        results.add(new TestResult("Screen Density", metrics.densityDpi + " dpi", true));

        return results;
    }

    public void cleanup() {

    }
}
