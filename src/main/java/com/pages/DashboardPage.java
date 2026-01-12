package com.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class DashboardPage {

    WebDriver driver;

    public DashboardPage(WebDriver driver) {
        this.driver = driver;
    }

    private By dashboardHeader =
        By.xpath("//h6[text()='Dashboard']");

    public boolean isDashboardVisible() {
        return driver.findElement(dashboardHeader).isDisplayed();
    }
}