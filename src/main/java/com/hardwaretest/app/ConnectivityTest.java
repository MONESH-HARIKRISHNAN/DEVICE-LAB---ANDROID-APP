package com.hardwaretest.app;

import android.bluetooth.BluetoothAdapter;
import android.content.Context;
import android.net.wifi.WifiManager;
import android.telephony.TelephonyManager;

import java.util.ArrayList;
import java.util.List;

// ConnectivityTest.java
public class ConnectivityTest {
    private final Context context;

    public ConnectivityTest(Context context) {
        this.context = context;
    }

    public List<TestResult> runTest() {
        List<TestResult> results = new ArrayList<>();

        // WiFi Test
        WifiManager wifiManager = (WifiManager) context.getApplicationContext().getSystemService(Context.WIFI_SERVICE);
        if (wifiManager != null) {
            boolean wifiEnabled = wifiManager.isWifiEnabled();
            results.add(new TestResult("WiFi", wifiEnabled ? "Enabled" : "Disabled", true));
        }

        // Bluetooth Test
        BluetoothAdapter bluetoothAdapter = BluetoothAdapter.getDefaultAdapter();
        if (bluetoothAdapter != null) {
            boolean bluetoothEnabled = bluetoothAdapter.isEnabled();
            results.add(new TestResult("Bluetooth", bluetoothEnabled ? "Enabled" : "Disabled", true));
        } else {
            results.add(new TestResult("Bluetooth", "Not Supported", false));
        }

        // Cellular Test
        TelephonyManager telephonyManager = (TelephonyManager) context.getSystemService(Context.TELEPHONY_SERVICE);
        if (telephonyManager != null) {
            String networkOperator = telephonyManager.getNetworkOperatorName();
            results.add(new TestResult("Network Operator", networkOperator.isEmpty() ? "No Network" : networkOperator, true));
        }

        return results;
    }

    public void cleanup() {

    }
}
