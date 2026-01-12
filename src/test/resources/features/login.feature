Feature: OrangeHRM Login

  Scenario: Valid Login
    Given user launches the application
    When user enters valid username and password
    Then dashboard should be displayed
    
   