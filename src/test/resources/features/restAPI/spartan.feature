Feature: spartan App rest API up and running

  As a user ,
  I should be  asle to send api request to spartan
  and get a valid response

  Scenario: Should be able to send  GET valid response fro  /hello

    Given the base_uri and base_path set
    When I send request to "/hello" endpoint
    Then I should get status code 200
    Then The response format should be "text/plain;charset=UTF-8"
    And the response payload should be Hello from Sparta
    
    @hello
    Scenario: Should be able to call/spartans to get all data in Json format
      Given the base_uri and base_path set
      And I ask for "json" response payload
      # and I set "Accept" header to "application/json"
      When I send request to "/spartans" endpoint
      Then I should get status code 200
      Then The response format should be "application/json"

  Scenario: Should be able to call/spartans to get all data in XML format
    Given the base_uri and base_path set
    And I ask for "xml" response payload
      # and I set "Accept" header to "application/json"
    When I send request to "/spartans" endpoint
    Then I should get status code 200
    Then The response format should be "application/xml"
    
    
    