Feature: spartan App rest API up and running

  As a user ,
  I should be  asle to send api request to spartan
  and get a valid response

  Background:
    Given the base_uri and base_path set

  Scenario Outline:Should be able to call/spartans to get all data in desire format

    And I ask for "<FormatAskedFor>" response payload
      # and I set "Accept" header to "application/json"
    When I send request to "/spartans" endpoint
    Then I should get status code 200
    Then The response format should be "<expectedContentTypeHeader>"

    Examples:
      | FormatAskedFor | expectedContentTypeHeader |
      | json           | application/json          |
      | xml            | application/xml           |


  Scenario: Should be able to add valid data to Spartan app
      #set the content type, provide the json body (or map body ) send request and verify 201
    Given I send the data in json format
    And I am sending below valid spartan data
      | name   | Barbos     |
      | gender | Male       |
      | phone  | 1231231230 |

    When I send POST request to endpoint "/spartans"
    Then I should get status code 201
    Then The success field value should be "A Spartan is Born!"

    Then the field value with this path "success" should be equal to "A Spartan is Born!"
    Then the field value with this path "data.name" should be equal to "Barbos"
    Then the field value with this path "data.gender" should be equal to "Male"
    Then the field value with this path "data.phone.toString()" should be equal to "1231231230"








