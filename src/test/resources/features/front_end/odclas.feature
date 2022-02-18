
@ui
Feature: New mails check

  As a user I can navigate on the website

  Scenario: as a user I should be able to see all new mails

    Given User on the webPage
    When User provide correct credentials and click login button
    And On home page click "Message" icon
    Then User shoul be able to get all senders as a list


    Scenario: as a user I should be able to check all guests
      Given User on the webPage
      When User provide correct credentials and click login button
      And On home page click "Guests" icon
      Then User should be able to get all Guests as a list
