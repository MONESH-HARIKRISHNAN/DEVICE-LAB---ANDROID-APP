package com.hardwaretest.app;

public class TestItem {
    private final String name;
    private final int iconResource;
    private final String testType;

    public TestItem(String name, int iconResource, String testType) {
        this.name = name;
        this.iconResource = iconResource;
        this.testType = testType;
    }

    public String getName() {
        return name;
    }


    public int getIconResource() {
        return iconResource;
    }



    public String getTestType() {
        return testType;
    }


}


