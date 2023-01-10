@State
Feature: State Module APL automation

  Scenario: Verify User Get statelist through api
    Given User add Header for to statelist
    When User send "GET" request for statelist endpoint
    Then User verify the status code is 200
    Then User verify the statelist response message matches "Tamil Nadu" and saved state_id

   