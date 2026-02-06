package com.hardwaretest.app;

import android.content.Intent;
import android.content.res.Configuration;
import android.os.Bundle;
import android.widget.GridView;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    private GridView gridView;
    private List<TestItem> testItems;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        initializeViews();
        setupTestItems();
        setupGridView();
    }

    private void initializeViews() {
        gridView = findViewById(R.id.gridView);
    }

    private void setupTestItems() {
        testItems = new ArrayList<>();
        testItems.add(new TestItem("Device Info", R.drawable.ic_device_info, "device_info"));
        testItems.add(new TestItem("Camera Test", R.drawable.ic_camera, "camera"));
        testItems.add(new TestItem("Audio Test", R.drawable.ic_audio, "audio"));
        testItems.add(new TestItem("Sensor Test", R.drawable.ic_sensors, "sensors"));
        testItems.add(new TestItem("Battery Test", R.drawable.ic_battery, "battery"));
        testItems.add(new TestItem("Connectivity", R.drawable.ic_wifi, "connectivity"));
        testItems.add(new TestItem("Display Test", R.drawable.ic_display, "display"));
        testItems.add(new TestItem("GPS Test", R.drawable.ic_location, "gps"));
        testItems.add(new TestItem("Vibration Test", R.drawable.ic_vibration, "vibration"));
        testItems.add(new TestItem("Flashlight Test", R.drawable.ic_flashlight, "flashlight"));
    }
    private void setupGridView() {
        TestItemAdapter adapter = new TestItemAdapter(this, testItems);
        gridView.setAdapter(adapter);

        gridView.setOnItemClickListener((parent, view, position, id) -> {
            TestItem selectedTest = testItems.get(position);
            Intent intent = new Intent(MainActivity.this, TestActivity.class);
            intent.putExtra("test_type", selectedTest.getTestType());
            intent.putExtra("test_name", selectedTest.getName());
            startActivity(intent);
        });
        gridView.setNumColumns(getResources().getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE ? 4 : 2);
    }

}
