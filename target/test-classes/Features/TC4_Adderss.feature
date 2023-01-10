@Address1
Feature: Address Module API Automation

  Scenario Outline: Verify add user address to the application through api
    Given User add header and bearer authorization for accessing AddUseraddress endpoints
    When User add request body for add new address "<first_name>","<last_name>","<mobile>","<apartment>","<passthesavedstateid>","<passthesavedcityid>","<country>","<zipcode>","<address>" and "<address_type>"
    And User send "POST" request for addUserAddress end point
    Then User verify the status code is 200
    Then User verify the addUserAddress response message matchs "Address added Successfully"

    Examples: 
      | first_name | last_name | mobile     | apartment | country | zipcode | address            | address_type |
      | Venkat     | Dilli     | 9176266504 | greens    |     102 |  600054 | 64/63 partap nagar | home         |

  Scenario Outline: Verify update user addres to the application through api
    Given User add header and bearer authorization for accessing UpdateUseraddress endpoints
    When User add request body for update address "<Venkat>","<Dilli>","<first_name>","<last_name>","<mobile>","<apartment>","<passthesavestateid>","<passthesavecityid>","<country>","<zipcode>","<address>" and "<address_type>"
    And User send "PUT" request for updateUserAddress end point
    Then User verify the status code is 200
    Then user verify the updateUserAddress response message matchs "Address updated successfully"

    Examples: 
      | first_name | last_name | mobile     | apartment | country | zipcode | address            | address_type |
      | Venkat     | Dilli     | 9176266504 | greens    |     102 |  600054 | 64/63 partap nagar | home         |

  Scenario: Verify update user addres to the application through api
    Given User add header and bearer authorization for accessing GetUseraddress endpoints
    And User send "GET" request for login endpoint
    Then User verify the status code is 200
    Then User verify the getUseraddress response message matchs "OK"

  Scenario: Verify update user addres to the application through api
    Given User add header and bearer authorization for accessing DeleteUseraddress endpoints
    And User send "DELETE"request for login endpoint
    Then User verify the status code is 200
    Then User verify the getUseraddress response message matchs "Address deleted successfully"
