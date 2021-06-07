package com.api.services;

import static io.restassured.matcher.RestAssuredMatchers.matchesXsd;
import static io.restassured.module.jsv.JsonSchemaValidator.matchesJsonSchema;

import org.hamcrest.MatcherAssert;

import com.cucumber.listener.Reporter;

import io.restassured.response.Response;

public class SchemaValidator {

	public void validate(Response response, String schema) {
		try {

			String json = response.getBody().asString();
			if (response.getContentType().contains("application/json")) {
				MatcherAssert.assertThat(json, matchesJsonSchema(schema));
				Reporter.addStepLog("Successfully validated the response schema");
			} else if (response.getContentType().contains("application/xml")) {
				MatcherAssert.assertThat(json, matchesXsd(schema));
				Reporter.addStepLog("Successfully validated the response schema");
			} else {
				Reporter.addStepLog("Invalid content type in the response");
			}
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.addStepLog("Exception while validating the response schema");
		}

	}
}
