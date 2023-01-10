@City
Feature: City Module API Automation

  Scenario: Verify User Get CityList to the application through API
    Given User add Headers for to CityList
    And User should add request body for get citylist by using saved stateid 
    When User send "POST" request for citylist endpoint
    Then User verify the status code is 200
    Then User verify the statelist response message matches "Yercaud" and saved city_id

   