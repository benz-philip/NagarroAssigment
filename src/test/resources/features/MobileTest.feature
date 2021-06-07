#Author: Harsh Prasad
@Mobile @Regression
Feature: Execute the Mobile test cases
  I want to execute and verify the test cases for mobile

  Background: 
    Given The selendroid app is launched
	
  Scenario: Verify title and elements
    Then verify elements on the screen
	
  Scenario: Verify homepage displayed
    When user taps on "EN button" button
    And Select option [No, no]
    Then Homepage should be displayed
	
  Scenario: Verify chrome logo button
    When user taps on "chrome" button
    Then text Hello, can you.. should appear
    When user submits details  name "Harsh" and car "Mercedes"
    Then text This is my.. should appear with name "Harsh" and preferred car "Mercedes"
    When user clicks link here 
    Then default car should be "Volvo"
	
	Scenario: Verify file logo button
		When user taps on "registration" button
		Then Verify title text Welcome to register.. and elements on screen
		And name should be "Mr. Burns" and language "Ruby" by default
		When user fill the fields with new value, check I accept and registers
		Then user deatils should match on next screen
		When user taps on register
		Then user should land on home screen
	
	Scenario: Verify progress bar
    When user taps on "show progress bar" button
    Then user should wait for loader to disappear
    And Verify element on registration screen
    
 	Scenario: Verify toast text
    When user taps on "display toast" button
    Then user should see toast text
   
	Scenario: Verify pop up window
    When user taps on "display popup" button
    Then user should be able to close pop up window
  
	Scenario: Verify unhandled exception
    When user taps on "Press to throw unhandled exception" button
    Then he should see home screen
   
	Scenario: Verify unhandled exception typing field
    When user types to throw unhandled exception
    Then he should see home screen

		 
		