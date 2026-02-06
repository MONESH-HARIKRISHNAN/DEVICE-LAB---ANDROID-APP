package com.hardwaretest.app;

import android.content.res.Configuration;
import android.os.Bundle;
import android.os.Handler;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class TestActivity extends AppCompatActivity {
    private TextView testNameView;
    private ProgressBar progressBar;
    private RecyclerView resultsList;
    private Button runTestButton;
    private Button backButton;

    private String testType;
    private HardwareTestManager testManager;
    private TestResultAdapter resultAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_test);

        initializeViews();
        getIntentData();
        setupRecyclerView();
        setupClickListeners();

        testManager = new HardwareTestManager(this);
    }

    private void initializeViews() {
        testNameView = findViewById(R.id.testNameView);
        progressBar = findViewById(R.id.progressBar);
        resultsList = findViewById(R.id.resultsList);
        runTestButton = findViewById(R.id.runTestButton);
        backButton = findViewById(R.id.backButton);
    }

    private void getIntentData() {
        testType = getIntent().getStringExtra("test_type");
        String testName = getIntent().getStringExtra("test_name");
        testNameView.setText(testName);
    }

    private void setupRecyclerView() {
        resultAdapter = new TestResultAdapter();
        resultsList.setLayoutManager(new LinearLayoutManager(this));
        resultsList.setAdapter(resultAdapter);
    }

    private void setupClickListeners() {
        runTestButton.setOnClickListener(v -> runTest());
        backButton.setOnClickListener(v -> finish());
    }


    private void runTest() {
        progressBar.setVisibility(ProgressBar.VISIBLE);
        runTestButton.setEnabled(false);
        runTestButton.setText(R.string.running_test);

        // Simulate test execution with delay
        new Handler().postDelayed(() -> {
            List<TestResult> results = testManager.runTest(testType);

            progressBar.setVisibility(ProgressBar.GONE);
            runTestButton.setEnabled(true);
            runTestButton.setText(R.string.run_test_again);

            resultAdapter.updateResults(results);
        }, 2000);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        finish(); // Ensure the activity is finished when back is pressed

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        // Handle configuration changes if necessary
        resultsList.setLayoutManager(new LinearLayoutManager(this));
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Clean up resources if needed
        if (testManager != null) {
            testManager.cleanup();
        }
    }
    @Override
    protected void onResume() {
        super.onResume();
        // Refresh the UI or data if needed
        if (testManager != null) {
            testManager.refreshData();
        }
        resultsList.setLayoutManager(new LinearLayoutManager(this));
        resultsList.setAdapter(resultAdapter);
        resultAdapter.notifyDataSetChanged();
    }
    @Override
    protected void onPause() {
        super.onPause();
        // Pause any ongoing operations if necessary
        if (testManager != null) {
            testManager.cleanup();
        }
        resultsList.setLayoutManager(new LinearLayoutManager(this));
        resultsList.setAdapter(resultAdapter);
        resultAdapter.notifyDataSetChanged();
    }
}