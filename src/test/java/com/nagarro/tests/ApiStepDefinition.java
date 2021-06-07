package com.nagarro.tests;

import org.testng.Assert;

import com.api.services.ApiService;

import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import io.restassured.response.Response;

public class ApiStepDefinition {

	ApiService service;
	Response response = null;
	Response postResponse = null;

	@Given("^The base uri is \"([^\"]*)\"$")
	public void the_base_uri_is(String url) throws Throwable {

		service = new ApiService(url);
	}

	@When("^user inquires for page \"([^\"]*)\"$")
	public void user_inquires_for_page(String page) throws Throwable {
		response = service.getUserPage(page);
	}

	@Then("^status for the reponse should be \"([^\"]*)\"$")
	public void status_for_the_reponse_should_be(int status) throws Throwable {
		Assert.assertEquals(response.getStatusCode(), status);
	}

	@Then("^First name for id \"([^\"]*)\" should be \"([^\"]*)\"$")
	public void first_name_for_id_should_be(int id, String name) throws Throwable {
		Assert.assertEquals(service.getNameWithServiceId(response, id), name);
	}

	@When("^User posts with name \"([^\"]*)\" and job \"([^\"]*)\"$")
	public void user_posts_with_name_and_job(String name, String job) throws Throwable {
		postResponse = service.postUser(name, job);
	}

	@Then("^status code should be \"([^\"]*)\"$")
	public void status_code_should_be(int status) throws Throwable {
		Assert.assertEquals(postResponse.getStatusCode(), status);
	}

	@Then("^the id should be generated$")
	public void the_id_should_be_generated() throws Throwable {
		Assert.assertTrue(service.verifyId(postResponse));
	}

	@Then("^verify schema with file \"([^\"]*)\"$")
	public void verify_schema_with_file(String pathTofile) throws Throwable {

		service.verifyResponseSchemaForPostUser(postResponse, pathTofile);
	}

}
