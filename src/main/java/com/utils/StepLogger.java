package com.utils;

import com.aventstack.extentreports.ExtentTest;

public class StepLogger {

    private static ThreadLocal<ExtentTest> extentTest = new ThreadLocal<>();

    public static void setTest(ExtentTest test) {
        extentTest.set(test);
    }

    public static void info(String message) {
        if (extentTest.get() != null)
            extentTest.get().info(message);

        Log.logger.info(message);
    }

    public static void pass(String message) {
        if (extentTest.get() != null)
            extentTest.get().pass(message);

        Log.logger.info("PASS: " + message);
    }

    public static void fail(String message) {
        if (extentTest.get() != null)
            extentTest.get().fail(message);

        Log.logger.error("FAIL: " + message);
    }
}