#Author: Harsh Prasad
@Web @Regression
Feature: Execute the Web test cases
  I want to execute and verify the test cases for web

  Background: 
    Given User is on "https://jqueryui.com/" url

  Scenario: Verify droppable from left panel
    And User select "Droppable" from left menu
    When User drag Drag me to my target to drop here
    Then The component should be placed inside drop here box

  Scenario: Verify selectable from left panel
    And User select "Selectable" from left menu
    When User select items
      | Item 1 | Item 3 | Item 7 |
    Then The items should be in selectd state

  Scenario: Verify controlgroup from left panel
    And User select "Controlgroup" from left menu under widgets
    When User selects "2" "SUV" in first rental car
    And User selects "1" "Truck" in second rental car
    Then User clicks on book now for the "Truck"
