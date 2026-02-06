package com.hardwaretest.app;

import android.content.Context;
import android.hardware.camera2.CameraAccessException;
import android.hardware.camera2.CameraManager;

import java.util.ArrayList;
import java.util.List;

// FlashlightTest.java
public class FlashlightTest {
    private final CameraManager cameraManager;

    public FlashlightTest(Context context) {
        this.cameraManager = (CameraManager) context.getSystemService(Context.CAMERA_SERVICE);
    }

    public List<TestResult> runTest() {
        List<TestResult> results = new ArrayList<>();

        try {
            String[] cameraIds = cameraManager.getCameraIdList();
            boolean hasFlash = false;
            
            for (String cameraId : cameraIds) {
                Boolean flashAvailable = cameraManager.getCameraCharacteristics(cameraId)
                    .get(android.hardware.camera2.CameraCharacteristics.FLASH_INFO_AVAILABLE);
                
                if (flashAvailable != null && flashAvailable) {
                    hasFlash = true;
                    results.add(new TestResult("Camera " + cameraId + " Flash", "Available", true));
                    
                    // Test turning flash on and off
                    cameraManager.setTorchMode(cameraId, true);
                    Thread.sleep(1000); // Keep flash on for 1 second
                    cameraManager.setTorchMode(cameraId, false);
                    results.add(new TestResult("Flash Test", "Flash toggled successfully", true));
                    break;
                } else {
                    results.add(new TestResult("Camera " + cameraId + " Flash", "Not Available", false));
                }
            }
            
            if (!hasFlash) {
                results.add(new TestResult("Flashlight", "No flash available on device", false));
            }
            
        } catch (CameraAccessException e) {
            results.add(new TestResult("Flashlight Test", "Camera access error: " + e.getMessage(), false));
        } catch (Exception e) {
            results.add(new TestResult("Flashlight Test", "Error: " + e.getMessage(), false));
        }

        return results;
    }

    public void cleanup() {


    }
}
