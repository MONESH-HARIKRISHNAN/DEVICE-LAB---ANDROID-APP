package com.hardwaretest.app;

import android.content.Context;
import java.util.ArrayList;
import java.util.List;

public class HardwareTestManager {
    private final Context context;
    private DeviceInfoTest deviceInfoTest;
    private CameraTest cameraTest;
    private AudioTest audioTest;
    private SensorTest sensorTest;
    private BatteryTest batteryTest;
    private ConnectivityTest connectivityTest;
    private DisplayTest displayTest;
    private GPSTest gpsTest;
    private VibrationTest vibrationTest;
    private FlashlightTest flashlightTest;

    public HardwareTestManager(Context context) {
        this.context = context;
        initializeTests();
    }

    private void initializeTests() {
        deviceInfoTest = new DeviceInfoTest(context);
        cameraTest = new CameraTest(context);
        audioTest = new AudioTest(context);
        sensorTest = new SensorTest(context);
        batteryTest = new BatteryTest(context);
        connectivityTest = new ConnectivityTest(context);
        displayTest = new DisplayTest(context);
        gpsTest = new GPSTest(context);
        vibrationTest = new VibrationTest(context);
        flashlightTest = new FlashlightTest(context);
    }

    public List<TestResult> runTest(String testType) {
        List<TestResult> results = new ArrayList<>();

        switch (testType.toLowerCase()) {
            case "device_info":
                results = deviceInfoTest.runTest();
                break;
            case "camera":
                results = cameraTest.runTest();
                break;
            case "audio":
                results = audioTest.runTest();
                break;
            case "sensors":
                results = sensorTest.runTest();
                break;
            case "battery":
                results = batteryTest.runTest();
                break;
            case "connectivity":
                results = connectivityTest.runTest();
                break;
            case "display":
                results = displayTest.runTest();
                break;
            case "gps":
                results = gpsTest.runTest();
                break;
            case "vibration":
                results = vibrationTest.runTest();
                break;
            case "flashlight":
                results = flashlightTest.runTest();
                break;
            default:
                results.add(new TestResult("Error", "Unknown test type", false));
                break;
        }

        return results;
    }

    public void cleanup() {
        if (deviceInfoTest != null) {
            deviceInfoTest.cleanup();
        }
        if (cameraTest != null) {
            cameraTest.cleanup();
        }
        if (audioTest != null) {
            audioTest.cleanup();
        }
        if (sensorTest != null) {
            sensorTest.cleanup();
        }
        if (batteryTest != null) {
            batteryTest.cleanup();
        }
        if (connectivityTest != null) {
            connectivityTest.cleanup();
        }
        if (displayTest != null) {
            displayTest.cleanup();
        }
        if (gpsTest != null) {
            gpsTest.cleanup();
        }
        if (vibrationTest != null) {
            vibrationTest.cleanup();
        }
        if (flashlightTest != null) {
            flashlightTest.cleanup();
        }
    }

    public void refreshData() {
        if (deviceInfoTest != null) {
            deviceInfoTest.runTest();
        }
        if (cameraTest != null) {
            cameraTest.runTest();
        }
        if (audioTest != null) {
            audioTest.runTest();
        }
        if (sensorTest != null) {
            sensorTest.runTest();
        }
        if (batteryTest != null) {
            batteryTest.runTest();
        }
        if (connectivityTest != null) {
            connectivityTest.runTest();
        }
        if (displayTest != null) {
            displayTest.runTest();
        }
        if (gpsTest != null) {
            gpsTest.runTest();
        }
        if (vibrationTest != null) {
            vibrationTest.runTest();
        }
        if (flashlightTest != null) {
            flashlightTest.runTest();
        }
    }
}