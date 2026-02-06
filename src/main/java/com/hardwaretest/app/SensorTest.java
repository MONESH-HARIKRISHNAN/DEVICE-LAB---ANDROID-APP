package com.hardwaretest.app;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorManager;

import java.util.ArrayList;
import java.util.List;

// SensorTest.java
public class SensorTest {
    private final SensorManager sensorManager;

    public SensorTest(Context context) {
        this.sensorManager = (SensorManager) context.getSystemService(Context.SENSOR_SERVICE);
    }

    public List<TestResult> runTest() {
        List<TestResult> results = new ArrayList<>();

        // Test common sensors
        testSensor(results, Sensor.TYPE_ACCELEROMETER, "Accelerometer");
        testSensor(results, Sensor.TYPE_GYROSCOPE, "Gyroscope");
        testSensor(results, Sensor.TYPE_MAGNETIC_FIELD, "Magnetometer");
        testSensor(results, Sensor.TYPE_PROXIMITY, "Proximity Sensor");
        testSensor(results, Sensor.TYPE_LIGHT, "Light Sensor");
        testSensor(results, Sensor.TYPE_PRESSURE, "Pressure Sensor");
        testSensor(results, Sensor.TYPE_AMBIENT_TEMPERATURE, "Temperature Sensor");
        testSensor(results, Sensor.TYPE_RELATIVE_HUMIDITY, "Humidity Sensor");

        return results;
    }

    private void testSensor(List<TestResult> results, int sensorType, String sensorName) {
        Sensor sensor = sensorManager.getDefaultSensor(sensorType);
        if (sensor != null) {
            results.add(new TestResult(sensorName, "Available - " + sensor.getName(), true));
        } else {
            results.add(new TestResult(sensorName, "Not Available", false));
        }
    }

    public void cleanup() {

    }
}

