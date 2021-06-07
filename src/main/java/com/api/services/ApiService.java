package com.api.services;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.json.JSONObject;

import com.api.model.Data;
import com.api.model.User;
import com.cucumber.listener.Reporter;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import io.restassured.RestAssured;
import io.restassured.response.Response;

public class ApiService {

	public ApiService(String url) {
		RestAssured.baseURI = url;
	}

	Utility apiUtil = new Utility();

	public Response getUserPage(String page) {
		Response response = null;
		try {
			Map<String, String> queryParam = new HashMap<String, String>();
			queryParam.put("page", page);
			response = apiUtil.getUrlResponse(queryParam, "/users");
			Reporter.addStepLog("Response fetched for the user service with page " + page);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return response;
	}

	public String getNameWithServiceId(Response response, int id) {
		String firstName = "";

		try {

			Gson gson = new Gson();

			User user = gson.fromJson(response.asString(), User.class);

			List<Data> datas = user.getData();

			for (Data data : datas) {
				if (data.getId() == 10) {
					firstName = data.getFirstName();
					Reporter.addStepLog("The name fetched for id " + id + " is " + firstName);
					break;
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
			Reporter.addStepLog("Exception while fetchting name for id");
		}
		return firstName;
	}

	public Response postUser(String name, String job) {
		Response response = null;
		try {

			JSONObject json = new JSONObject();
			json.put("name", name);
			json.put("job", job);

			response = apiUtil.postReuqestWithBody(json.toString(), "/users");
		} catch (Exception e) {
			e.printStackTrace();
			Reporter.addStepLog("Exception ocurred while posting the user");
		}
		return response;
	}

	public boolean verifyResponseSchemaForPostUser(Response response, String pathTofile) {
		boolean flag = false;
		try {

			SchemaValidator validator = new SchemaValidator();

			validator.validate(response, apiUtil.jsonReader(pathTofile));

		} catch (Exception e) {
			e.printStackTrace();
		}
		return flag;
	}

	public boolean verifyId(Response response) {
		boolean flag = false;
		String requestId = null;
		try {
			System.out.println(response.asString());
			JsonNode node = new ObjectMapper().readTree(response.asString());

			if (node.has("id")) {
				requestId = node.get("id").asText();
			}
			if (requestId.isEmpty() == false) {
				flag = true;
				Reporter.addStepLog("The property request id is not null and the value is " + requestId);
			} else {
				Reporter.addStepLog("The property request id is blank");
			}

		} catch (Exception e) {
			e.printStackTrace();
			Reporter.addStepLog("Exception ocurred while verifying the request id");
		}
		return flag;
	}

}
