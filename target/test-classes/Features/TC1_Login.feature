@Login
Feature: Login Module APL automation

  Scenario: Get User Logtoken from login endpoint
    Given User add Header
    When User add basic authentication for login
    And User send "POST" request for login endpoint
    Then User verify the status code is 200
    Then User verify the login response body firstName present as "VENKAT" and get the logtoken saved
