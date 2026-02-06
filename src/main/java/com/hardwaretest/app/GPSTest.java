package com.hardwaretest.app;

import android.content.Context;
import android.location.LocationManager;

import java.util.ArrayList;
import java.util.List;

// GPSTest.java
public class GPSTest {
    private final Context context;

    public GPSTest(Context context) {
        this.context = context;
    }

    public List<TestResult> runTest() {
        List<TestResult> results = new ArrayList<>();

        LocationManager locationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);
        
        if (locationManager != null) {
            // Check GPS provider
            boolean gpsEnabled = locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
            results.add(new TestResult("GPS Provider", gpsEnabled ? "Enabled" : "Disabled", gpsEnabled));

            // Check Network provider
            boolean networkEnabled = locationManager.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
            results.add(new TestResult("Network Provider", networkEnabled ? "Enabled" : "Disabled", networkEnabled));

            // Check Passive provider
            boolean passiveEnabled = locationManager.isProviderEnabled(LocationManager.PASSIVE_PROVIDER);
            results.add(new TestResult("Passive Provider", passiveEnabled ? "Enabled" : "Disabled", passiveEnabled));

            List<String> providers = locationManager.getAllProviders();
            results.add(new TestResult("Total Providers", String.valueOf(providers.size()), true));
        } else {
            results.add(new TestResult("Location Service", "Not Available", false));
        }

        return results;
    }

    public void cleanup() {

    }
}
