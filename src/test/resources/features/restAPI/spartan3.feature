Feature: Spartan API Single Data
  As a user, I should be able to get individual data
  using valid id and see details 
  
  Background: 
    Given the base_uri and base_path set

    @db
    Scenario: Should be able to get single spartan using?spartans/{id}
      And I have valid spartan id
      And I ask for "json" response payload
      When I send get request to "/spartans/{id}" endpoint
      Then I should get status code 200
    #  Then the spartan data with that id matches values in the database

