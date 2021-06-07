#Author: Harsh Prasad
@Regression
Feature: Execute the API test cases
  I want to execute and verify the test cases for API

	Background:
		Given The base uri is "https://reqres.in/api"

	
  Scenario: Verify get users API
 		When user inquires for page "2"
  	Then status for the reponse should be "200"
  	And First name for id "10" should be "Byron"
	
	
  Scenario: Verify post user API
  	When User posts with name "Bryant" and job "BA"
  	Then status code should be "201"
  	And the id should be generated
  	And verify schema with file "/Users/designer61/HarshPC/Workspace/NagarroAssigment/src/test/resources/user.json"
