package com.hardwaretest.app;

public class TestResult {
    private String testName;
    private String result;
    private boolean passed;
    private String details;

    public TestResult(String testName, String result, boolean passed) {
        this.testName = testName;
        this.result = result;
        this.passed = passed;
        this.details = "";
    }

    public TestResult(String testName, String result, boolean passed, String details) {
        this.testName = testName;
        this.result = result;
        this.passed = passed;
        this.details = details;
    }

    // Getters and setters
    public String getTestName() { return testName; }
    public void setTestName(String testName) { this.testName = testName; }
    
    public String getResult() { return result; }
    public void setResult(String result) { this.result = result; }
    
    public boolean isPassed() { return passed; }
    public void setPassed(boolean passed) { this.passed = passed; }
    
    public String getDetails() { return details; }
    public void setDetails(String details) { this.details = details; }
}
