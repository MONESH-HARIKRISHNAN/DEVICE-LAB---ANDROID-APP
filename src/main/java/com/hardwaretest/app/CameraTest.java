package com.hardwaretest.app;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraCharacteristics;
import android.hardware.camera2.CameraManager;

import androidx.annotation.NonNull;

import java.util.ArrayList;
import java.util.List;

// CameraTest.java
public class CameraTest {
    private final CameraManager cameraManager;

    public CameraTest(Context context) {
        this.cameraManager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
    }

    public List<TestResult> runTest() {
        List<TestResult> results = new ArrayList<>();

        try {
            String[] cameraIds = cameraManager.getCameraIdList();
            results.add(new TestResult("Camera Count", String.valueOf(cameraIds.length), true));

            for (String cameraId : cameraIds) {
                String cameraType = getString(cameraId);

                results.add(new TestResult("Camera " + cameraId, cameraType + " Available", true));
            }
        } catch (Exception e) {
            results.add(new TestResult("Camera Test", "Error: " + e.getMessage(), false));
        }

        return results;
    }

    @NonNull
    private String getString(String cameraId) throws CameraAccessException {
        CameraCharacteristics characteristics = cameraManager.getCameraCharacteristics(cameraId);
        Integer facing = characteristics.get(CameraCharacteristics.LENS_FACING);

        String cameraType = "Unknown";
        if (facing != null) {
            switch (facing) {
                case CameraCharacteristics.LENS_FACING_FRONT:
                    cameraType = "Front Camera";
                    break;
                case CameraCharacteristics.LENS_FACING_BACK:
                    cameraType = "Rear Camera";
                    break;
                case CameraCharacteristics.LENS_FACING_EXTERNAL:
                    cameraType = "External Camera";
                    break;
            }
        }
        return cameraType;
    }

    public void cleanup() {


    }
}
