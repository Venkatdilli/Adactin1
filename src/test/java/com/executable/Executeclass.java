package com.executable;

import java.util.ArrayList;
import java.util.List;

import com.pojo.address.*;
import com.pojo.address.*;
import com.pojo.address.*;
import com.pojo.address.*;
import com.pojo.address.*;
import com.pojologin.*;
import com.pojo.address.*;
import com.pojo.address.*;
import com.pojo.address.*;
import org.testng.Assert;
import org.testng.annotations.Test;

import com.base.BaseClass;
import com.payload.address.*;

import io.restassured.http.Header;
import io.restassured.http.Headers;
import io.restassured.response.Response;

public class Executeclass extends BaseClass {
	String logtoken;
	int stateIdNum;
	String state_id;
	int cityId;
	String address_id;

	@Test(priority = 1)
	public void login() {

		addHeader("accept", "application/json");

		addBasicAuth("venkatdilli24@gmail.com", "Whencut@1998");

		Response response = requestType("POST", "https://omrbranch.com/api/postmanBasicAuthLogin");

		int actstatusCode = getStatusCode(response);
		System.out.println(actstatusCode);
		Assert.assertEquals(actstatusCode, 200, "Verify status code");

		Login_Output_Pojo login_Output_Pojo = response.as(Login_Output_Pojo.class);
		String first_name = login_Output_Pojo.getData().getFirst_name();
		org.testng.Assert.assertEquals(first_name, "VENKAT", "Verify the firstname");
		System.out.println(first_name);
		logtoken = login_Output_Pojo.getData().getLogtoken();
	}

	@Test(priority = 4)
	public void addUserAdderss() {
		List<Header> listHeaders = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		Header h3 = new Header("Content-Type", "application/json");

		listHeaders.add(h1);
		listHeaders.add(h2);
		listHeaders.add(h3);

		Headers header = new Headers(listHeaders);
		addHeaders(header);

		// 2.payload
//		AddUserAddress_Input_Pojo address = new AddUserAddress_Input_Pojo("Venkat", "Dilli", "9176266504", "greens", stateIdNum,
//				cityId, 102, "600054", "64/63 partap nagar", "home");
		addBody(AddressPayload.getAddAddressPayLoad("Venkat", "Dilli", "9176266504", "greens", stateIdNum, cityId, 102,
				"600054", "64/63 partap nagar", "home"));

		// method type
		Response response = requestType("POST", "https://www.omrbranch.com/api/addUserAddress");

		int actstatusCode = getStatusCode(response);
		System.out.println(actstatusCode);
		Assert.assertEquals(actstatusCode, 200, "Verify StatusCode");
		String asPretty = getAsPretty(response);
		System.out.println(asPretty);

		AddUserAdderss_Output_Pojo as = response.as(AddUserAdderss_Output_Pojo.class);
		String actmessage = as.getMessage();
		Assert.assertEquals(actmessage, "Address added successfully", "Verify Address added successfully");
		int addressIdNum = as.getAddress_id();
		address_id = String.valueOf(addressIdNum);
		System.out.println(address_id);
	}

	@Test(priority = 2)
	public void getStateList() {
		addHeader("accept", "application/json");
		Response requestType = requestType("GET", "https://www.omrbranch.com/api/stateList");
		int actstatusCode = getStatusCode(requestType);
		System.out.println(actstatusCode);
		Assert.assertEquals(actstatusCode, 200, "Verify StatusCode");

		StateList_Output_pojo stateList_Output_pojo = requestType.as(StateList_Output_pojo.class);
		ArrayList<StateList> listStatelist = stateList_Output_pojo.getData();
		for (StateList eachstateList : listStatelist) {
			String actStateName = eachstateList.getName();
			if (actStateName.equals("Tamil Nadu")) {
				stateIdNum = eachstateList.getId();
				state_id = String.valueOf(stateIdNum);

				System.out.println(stateIdNum);
				Assert.assertEquals(actStateName, "Tamil Nadu", "verify StateID");
				break;

			}

		}

	}

	@Test(priority = 3)
	public void getCityList() {
		// 1.Header
		List<Header> listheader = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Content-Type", "application/json");
		listheader.add(h1);
		listheader.add(h2);

		Headers headers = new Headers(listheader);
		addHeaders(headers);

		// 2.payload
		// CityList_Input_Pojo cityList_Input_Pojo =new CityList_Input_Pojo(state_id);
		addBody(AddressPayload.getCityCodePayLoad(state_id));
		// 3.Method Type
		Response response = requestType("POST", "https://www.omrbranch.com/api/cityList");
		int actstatusCode = getStatusCode(response);
		System.out.println(actstatusCode);
		Assert.assertEquals(actstatusCode, 200, "Verify StatusCode");
		CityList_Output_Pojo cityList_Output_Pojo = response.as(CityList_Output_Pojo.class);

		// Find the city id(pass the city name as yercaud and get the city id)

		ArrayList<CityList> citylist = cityList_Output_Pojo.getData();
		for (CityList eachcityList : citylist) {
			String actCityName = eachcityList.getName();
			if (actCityName.equals("Yercaud")) {
				cityId = eachcityList.getId();
				System.out.println(cityId);
				Assert.assertEquals(actCityName, "Yercaud", "Verify the City name as yercaud");
				break;
			}

		}

	}

	@Test(priority = 5)
	public void updateAddress() {
		List<Header> listHeaders = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		Header h3 = new Header("Content-Type", "application/json");

		listHeaders.add(h1);
		listHeaders.add(h2);
		listHeaders.add(h3);

		Headers header = new Headers(listHeaders);
		addHeaders(header);
		// 2.payload
//		UpdateUserAddress_Input_pojo updateUserAddress_Input_pojo =new UpdateUserAddress_Input_pojo(address_id,"Venkat", "Dilli", "9176266504", "greens", stateIdNum,
//				101, 102, "600054", "64/63 partap nagar", "home");
		addBody(AddressPayload.getUpdateAddresspayload(address_id, "Venkat", "Dilli", "9176266504", "greens",
				stateIdNum, 101, 102, "600054", "64/63 partap nagar", "home"));
		// 3.method
		Response response = requestType("PUT", "https://www.omrbranch.com/api/updateUserAddress");
		int actstatusCode = getStatusCode(response);
		System.out.println(actstatusCode);
		Assert.assertEquals(actstatusCode, 200, "Verify StatusCode");

		UpdateUserAddress_Output_pojo updateUserAddress_Output_pojo = response.as(UpdateUserAddress_Output_pojo.class);
		String actmessage = updateUserAddress_Output_pojo.getMessage();
		System.out.println(actmessage);
		Assert.assertEquals(actmessage, "Address updated successfully", "Address updated successfully");
	}

	@Test(priority = 6)
	public void getUserAddress() {
		List<Header> listHeaders = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);

		listHeaders.add(h1);
		listHeaders.add(h2);

		Headers header = new Headers(listHeaders);
		addHeaders(header);
		// 3.method type
		Response response = requestType("GET", "https://www.omrbranch.com/api/getUserAddress");
		int actstatusCode = getStatusCode(response);
		System.out.println(actstatusCode);
		Assert.assertEquals(actstatusCode, 200, "Verify StatusCode");
		GetUserAddress_Output_Pojo getUserAddress_Output_Pojo = response.as(GetUserAddress_Output_Pojo.class);
		String actmessage = getUserAddress_Output_Pojo.getMessage();
		Assert.assertEquals(actmessage, "OK", "Verify the message");

	}

	// @Test(priority = 7)
//	public void deleteUserAddress() {
//		List<Header> listHeaders = new ArrayList<>();
//		Header h1 = new Header("accept", "application/json");
//		Header h2 = new Header("Authorization", "Bearer "+ logtoken);
//		Header h3 = new Header("Content-Type", "application/json");
//
//		listHeaders.add(h1);
//		listHeaders.add(h2);
//		listHeaders.add(h3);
//
//		Headers header = new Headers(listHeaders);
//		addHeaders(header);
//		DeleteUserAddress_Input_Pojo deleteUserAddress_Input_Pojo=new DeleteUserAddress_Input_Pojo(address_id);
//		addBody(deleteUserAddress_Input_Pojo);
//		Response response = requestType("DELETE", "https://www.omrbranch.com/api/deleteAddress");
//int actstatusCode = getStatusCode(response);
//System.out.println(actstatusCode);
////Assert.assertEquals(actstatusCode,200,"Verify StatusCode");
//DeleteUserAddress_Output_Pojo deleteUserAddress_Output_Pojo = response.as(DeleteUserAddress_Output_Pojo.class);
//String actMessage = deleteUserAddress_Output_Pojo.getMessage();
////Assert.assertEquals(actMessage,"Address deleted successfully","Verify Address Deleted Successfully");
//
//	}
	@Test(priority = 7)
	public void deleteUserAddress() {
		List<Header> listHeaders = new ArrayList<>();
		Header h1 = new Header("accept", "application/json");
		Header h2 = new Header("Authorization", "Bearer " + logtoken);
		Header h3 = new Header("Content-Type", "application/json");

		listHeaders.add(h1);
		listHeaders.add(h2);
		listHeaders.add(h3);

		Headers header = new Headers(listHeaders);
		addHeaders(header);
		// 2.payload
		addBody(AddressPayload.getDeleteAddresspayload(address_id));
		Response response = requestType("DELETE", "https://www.omrbranch.com/api/deleteAddress");
		int actstatusCode = getStatusCode(response);
		System.out.println(actstatusCode);
		//Assert.assertEquals(actstatusCode, 200, "Verify StatusCode");
		DeleteUserAddress_Output_Pojo deleteUserAddress_Output_Pojo = response.as(DeleteUserAddress_Output_Pojo.class);
		String actMessage = deleteUserAddress_Output_Pojo.getMessage();
		//Assert.assertEquals(actMessage, "Address deleted successfully", "Verify Address Deleted Successfully");
        String asPretty = getAsPretty(response);
        System.out.println(asPretty);
	}
}
