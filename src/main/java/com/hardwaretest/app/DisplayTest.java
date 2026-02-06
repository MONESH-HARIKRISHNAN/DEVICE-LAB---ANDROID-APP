package com.hardwaretest.app;

import android.content.Context;
import android.util.DisplayMetrics;
import android.view.WindowManager;

import java.util.ArrayList;
import java.util.List;

// DisplayTest.java
public class DisplayTest {
    private final Context context;

    public DisplayTest(Context context) {
        this.context = context;
    }


    public List<TestResult> runTest() {
        List<TestResult> results = new ArrayList<>();

        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        DisplayMetrics metrics = new DisplayMetrics();
        wm.getDefaultDisplay().getMetrics(metrics);
        
        results.add(new TestResult("Resolution", metrics.widthPixels + "x" + metrics.heightPixels, true));
        results.add(new TestResult("Density", metrics.densityDpi + " dpi", true));
        results.add(new TestResult("Density Scale", String.format("%.2f", metrics.density), true));

        float xdpi = metrics.xdpi;
        float ydpi = metrics.ydpi;
        results.add(new TestResult("X DPI", String.format("%.2f", xdpi), true));
        results.add(new TestResult("Y DPI", String.format("%.2f", ydpi), true));

        // Calculate screen size in inches
        double widthInches = metrics.widthPixels / xdpi;
        double heightInches = metrics.heightPixels / ydpi;
        double diagonalInches = Math.sqrt(Math.pow(widthInches, 2) + Math.pow(heightInches, 2));
        
        results.add(new TestResult("Screen Size", String.format("%.2f inches", diagonalInches), true));

        return results;
    }

    public void cleanup() {

    }
}