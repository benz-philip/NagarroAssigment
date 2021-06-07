package com.api.services;

import java.io.FileReader;
import java.io.IOException;
import java.util.Map;
import java.util.Properties;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.cucumber.listener.Reporter;

import io.restassured.RestAssured;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class Utility {
	Properties properties = new Properties();

	public Response getUrlResponse(String url) {
		try {
			Reporter.addStepLog("Making Get request call for url: '" + url + "'");
			Response response = RestAssured.given().get(url);
			return response;
		} catch (Exception e) {
			Reporter.addStepLog("Exception ocurred while making Get request call for url: '" + url + "'");
			return null;
		}
	}

	public Response getUrlResponse(Map<String, String> queryParam, String path) {
		try {

			RequestSpecification request = RestAssured.given();
			request.queryParams(queryParam);
			Response response = request.get(path);
			request.given().log().uri();
			return response;
		} catch (Exception e) {
			Reporter.addStepLog("Exception ocurred while making Get request call for the url");
			return null;
		}
	}

	public int getResponseStatus(Response response) {

		Reporter.addStepLog("Fetching the response status code");

		return response.getStatusCode();
	}

	public Response postReuqestWithBody(String body, String path) {
		Response response = null;
		try {
			RequestSpecification request = RestAssured.given();
			request.body(body);
			response = request.post(path);
		} catch (Exception e) {
			throw e;
		}
		return response;
	}

	public String jsonReader(String filePath) {
		JSONParser parser = new JSONParser();
		// Use JSONObject for simple JSON and JSONArray for array of JSON.
		JSONObject data = null;
		try {
			data = (JSONObject) parser.parse(new FileReader(filePath));
		} catch (IOException | ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} // path to the JSON file.

		return data.toJSONString();

	}

}
