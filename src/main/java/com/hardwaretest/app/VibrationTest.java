package com.hardwaretest.app;

import android.content.Context;
import android.os.Build;
import android.os.VibrationEffect;
import android.os.Vibrator;

import java.util.ArrayList;
import java.util.List;

// VibrationTest.java
public class VibrationTest {
    private final Context context;

    public VibrationTest(Context context) {
        this.context = context;
    }

    public List<TestResult> runTest() {
        List<TestResult> results = new ArrayList<>();

        Vibrator vibrator = (Vibrator) context.getSystemService(Context.VIBRATOR_SERVICE);
        
        if (vibrator != null && vibrator.hasVibrator()) {
            results.add(new TestResult("Vibrator", "Available", true));
            
            // Test vibration
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                vibrator.vibrate(VibrationEffect.createOneShot(500, VibrationEffect.DEFAULT_AMPLITUDE));
                results.add(new TestResult("Vibration Test", "Vibration executed", true));
            } else {
                vibrator.vibrate(500);
                results.add(new TestResult("Vibration Test", "Vibration executed (legacy)", true));
            }
        } else {
            results.add(new TestResult("Vibrator", "Not Available", false));
        }

        return results;
    }

    public void cleanup() {

    }
}
