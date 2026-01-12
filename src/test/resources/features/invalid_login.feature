Feature: Invalid Login validation for OrangeHRM

  Scenario: Login with wrong password
    Given user launches the application
    When user enters invalid username and password
    Then error message should be displayed
    
    