package com.hardwaretest.app;

import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.BatteryManager;

import java.util.ArrayList;
import java.util.List;

public class BatteryTest {
    private final Context context;

    public BatteryTest(Context context) {
        this.context = context;
    }

    public List<TestResult> runTest() {
        List<TestResult> results = new ArrayList<>();

        IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);
        Intent batteryStatus = context.registerReceiver(null, ifilter);

        if (batteryStatus != null) {
            int level = batteryStatus.getIntExtra(BatteryManager.EXTRA_LEVEL, -1);
            int scale = batteryStatus.getIntExtra(BatteryManager.EXTRA_SCALE, -1);
            int batteryPct = (int)((level / (float)scale) * 100);

            int status = batteryStatus.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
            boolean isCharging = status == BatteryManager.BATTERY_STATUS_CHARGING ||
                                status == BatteryManager.BATTERY_STATUS_FULL;

            int temperature = batteryStatus.getIntExtra(BatteryManager.EXTRA_TEMPERATURE, -1);
            int voltage = batteryStatus.getIntExtra(BatteryManager.EXTRA_VOLTAGE, -1);

            results.add(new TestResult("Battery Level", batteryPct + "%", true));
            results.add(new TestResult("Charging Status", isCharging ? "Charging" : "Not Charging", true));
            results.add(new TestResult("Temperature", (temperature / 10.0) + "°C", true));
            results.add(new TestResult("Voltage", voltage + " mV", true));
        }

        return results;
    }

    public void cleanup() {

    }
}
