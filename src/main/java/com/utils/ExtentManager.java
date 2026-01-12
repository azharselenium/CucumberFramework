package com.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

import com.aventstack.extentreports.*;
import com.aventstack.extentreports.reporter.ExtentSparkReporter;

public class ExtentManager {

    public static ExtentReports extent;

    public static ExtentReports getReport() {
        if (extent == null) {
        	 String timeStamp = new SimpleDateFormat("yyyy-MM-dd_HH-mm-ss")
                     .format(new Date());
            String reportPath = "reports/ExtentReports/ExtentReport_" + timeStamp  + ".html";
            ExtentSparkReporter reporter = new ExtentSparkReporter(reportPath);
            reporter.config().setReportName("Orange HRM Test Report");

            extent = new ExtentReports();
            extent.attachReporter(reporter);
        }
        return extent;
    }
}
