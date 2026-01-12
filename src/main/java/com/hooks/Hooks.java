package com.hooks;

import com.aventstack.extentreports.*;
import com.base.DriverFactory;
import com.utils.*;
import io.cucumber.java.*;

public class Hooks {

    private static ExtentReports extent = ExtentManager.getReport();
    private static ThreadLocal<ExtentTest> test = new ThreadLocal<>();

    @Before
    public void beforeScenario(Scenario scenario) {

        // Start browser
        DriverFactory.initDriver();

        // Create ExtentTest
        ExtentTest extentTest =
                extent.createTest(scenario.getName());

        test.set(extentTest);

        // Set in StepLogger
        StepLogger.setTest(extentTest);

        StepLogger.info("Scenario started: " + scenario.getName());
    }

    @After
    public void afterScenario(Scenario scenario) {

        if (scenario.isFailed()) {

            String screenshotPath =
                ScreenshotUtil.captureScreenshot(
                        DriverFactory.getDriver(),
                        scenario.getName());

            StepLogger.fail("Scenario Failed");

            try {
                test.get().addScreenCaptureFromPath(screenshotPath);
            } catch (Exception e) {
                e.printStackTrace();
            }

        } else {
            StepLogger.pass("Scenario Passed");
        }

        DriverFactory.quitDriver();
        extent.flush();
    }
}