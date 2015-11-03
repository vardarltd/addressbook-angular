Feature: UI test of the Address Book Application, Angular Way
  CRUD operations should be user friendly.
  Scenario: Create a Contact
    Given I am already using the address book application
    When I entered all contact infos and press the save button
    Then I should see the contact listed on the Contact List
  Scenario: Edit a Contact Info
    Given I am already using 100 the address book application
    And I somewhat want to change a contact's info
    When I select a contact and update its values
    And Press save button
    Then I should see updated contact info listed.
  Scenario: Delete a contact
    Given I am already using the address book application
    And I want to delete a contact
    When I press the "Delete" button of the contact
    Then I should see the contact is deleted from the Contact list

