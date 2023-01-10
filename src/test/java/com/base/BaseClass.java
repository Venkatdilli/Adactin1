package com.base;

import io.restassured.RestAssured;
import io.restassured.http.Headers;
import io.restassured.response.Response;
import io.restassured.specification.RequestSpecification;

public class BaseClass {
	RequestSpecification reqspec;
	Response response;

	public void addHeader(String key, String value) {
		reqspec = RestAssured.given().header(key, value);

	}

	public void addHeaders(Headers header) {
		reqspec = RestAssured.given().headers(header);

	}

	public void addPathParam(String key, String value) {
		reqspec = reqspec.pathParam(key, value);
	}

	public void addQueryParam(String key, String value) {
		reqspec = reqspec.queryParam(key, value);
	}

	public void addBody(String body) {
		reqspec = reqspec.body(body);
	}

	public void addBody(Object body) {
		reqspec = reqspec.body(body);
	}

	public void addBasicAuth(String username, String password) {
		reqspec = reqspec.auth().preemptive().basic(username, password);
	
	}

	public Response requestType(String type, String endPoint) {
		switch (type) {
		case "GET":
			response = reqspec.log().all().get(endPoint);
			break;
		case "POST":
			response = reqspec.log().all().post(endPoint);
			break;
		case "PUT":
			response = reqspec.log().all().put(endPoint);
			break;
		case "DELETE":
			response = reqspec.log().all().delete();
			break;
		default:
			break;
		}
		return response;
	}

	public int getStatusCode(Response response) {
		int statusCode = response.getStatusCode();
		return statusCode;
	}

	public String getAsString(Response reponse) {
		String asString = reponse.asString();
		return asString;
	}

	public String getAsPretty(Response response) {
		String asPrettyString = response.asPrettyString();
		return asPrettyString;
	}
}
