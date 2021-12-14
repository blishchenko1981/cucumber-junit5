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
      Then the spartan data with that id matches values in the database



      Scenario: Should be able to delete single spartan using DELETE/ spartans/{id}

        And I have valid random spartan id
        When I send delete request to "spartans/{id}" endpoint
       Then I should get status code 204
        When I send get request to "spartans/{id}" endpoint
        Then I should get status code 404


        Scenario: Should be able to update single spartan using PUT / spartans/{id}

          And I have valid random spartan id
          And I send the data in json format
          And I am sending below valid spartan data
            | name    | Kimberley   |
            | gender  | Female      |
            | phone   | 1876543210  |

          When I send put request to "/spartans/{id}" endpoint
          Then I should get status code 204
          When I send get request to "/spartans/{id}" endpoint
          Then the field value with this path "name" should be equal to "Kimberley"
          Then the field value with this path "gender" should be equal to "Female"
          Then the field value with this path "phone.toString()" should be equal to "1876543210"



  Scenario: Should be able to partial update single spartan using PATCH / spartans/{id}

    And I have valid random spartan id
    And I send the data in json format
    And I am sending below valid spartan data
      | name    | Vitalii   |

    When I send patch request to "/spartans/{id}" endpoint
    Then I should get status code 204
    When I send get request to "/spartans/{id}" endpoint
    Then the field value with this path "name" should be equal to "Vitalii"



