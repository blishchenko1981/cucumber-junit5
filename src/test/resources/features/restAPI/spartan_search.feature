Feature: Spartan Search feature

  As a user,
  I should be able to filter data
  using partial name and gender


  Background:
    Given the base_uri and base_path set
   @db
  Scenario: Should be able to search to get valid data using GET /spartans/search

    And I search for spartan with name contains "Ea" and gender "Male"
    When I send get request to "/spartans/search" endpoint
    Then I should get status code 200
    Then All Names in the result should contains "Ea" and gender Should be "Male"
    Then the spartan count in response should match the count in database "Ea" and gender should be "Male"

  @db
  Scenario Outline: Should be able to search to get valid data using GET /spartans/search DDT

    And I search for spartan with name contains "<nameColumn>" and gender "<genderColumn>"
    When I send get request to "/spartans/search" endpoint
    Then I should get status code 200
    Then All Names in the result should contains "<nameColumn>" and gender Should be "<genderColumn>"
    Then The search count for name contains "<nameColumn>" and gender "<genderColumn>" should match the count in the database
    Examples:
      | nameColumn | genderColumn |
      | Ea         | Female       |
      | ab         | Male         |
      | a          | Male         |
      | Kimberly   | Female       |
      | Mehmet     | Male         |


