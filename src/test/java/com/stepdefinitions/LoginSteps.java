package com.stepdefinitions;

import com.base.DriverFactory;
import io.cucumber.java.en.*;
import com.pages.LoginPage;
import com.pages.DashboardPage;
import com.utils.ConfigReader;
import com.utils.StepLogger;

import java.time.Duration;
import org.testng.Assert;

public class LoginSteps extends DriverFactory {

    LoginPage loginPage;
    DashboardPage dashboardPage;

    @Given("user launches the application")
    public void user_launches_application() {
        StepLogger.info("Launching application and initializing pages");

        System.out.println("Application already launched by Hooks");

        loginPage = new LoginPage(DriverFactory.getDriver());
        dashboardPage = new DashboardPage(DriverFactory.getDriver());

        StepLogger.info("LoginPage and DashboardPage initialized");
    }

    @When("user enters valid username and password")
    public void user_enters_credentials() {

        StepLogger.info("Entering valid username and password");

        loginPage.login(
                ConfigReader.getProperty("username"),
                ConfigReader.getProperty("password"));

        StepLogger.info("Login button clicked");

        getDriver().manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(10));

        StepLogger.info("Waiting for dashboard to load");
    }

    @Then("dashboard should be displayed")
    public void verify_dashboard() {

        StepLogger.info("Verifying dashboard visibility");

        getDriver().manage().timeouts()
                .implicitlyWait(Duration.ofSeconds(10));

        Assert.assertTrue(
                dashboardPage.isDashboardVisible(),
                "Dashboard is not visible");

        StepLogger.pass("Dashboard displayed successfully");

        DriverFactory.quitDriver();

        StepLogger.info("Browser closed");
    }
    @When("user enters invalid username and password")
    public void user_enters_invalid_credentials() {

        StepLogger.info("Entering invalid username and password");

        loginPage.login("Admin", "wrongPassword123");

        StepLogger.info("Clicked login with invalid credentials");
    }

    @Then("error message should be displayed")
    public void verify_error_message() {

        StepLogger.info("Verifying error message for invalid login");

        Assert.assertTrue(
            loginPage.isErrorMessageDisplayed(),
            "Error message is not displayed for invalid login"
        );

        StepLogger.pass("Error message displayed successfully");
    }
}