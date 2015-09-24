Feature Address Book Application UI, Angular Way
  Scenario: Create a Contact
    Given I am already using the address book application
    When I entered all contact infos and press the save button
    Then I see the contact listed on the Contact List
  Scenario: Edit a Contact Info
    Given I am already using the address book application
    And I somewhat want to change a contact's info
    When
