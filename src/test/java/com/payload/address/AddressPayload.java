package com.payload.address;

import com.pojo.address.AddUserAddress_Input_Pojo;
import com.pojo.address.CityList_Input_Pojo;
import com.pojo.address.DeleteUserAddress_Input_Pojo;
import com.pojo.address.UpdateUserAddress_Input_pojo;

public class AddressPayload {
	public static CityList_Input_Pojo getCityCodePayLoad(String state_id)
	{
		CityList_Input_Pojo cityList_Input_Pojo = new CityList_Input_Pojo(state_id);
		return cityList_Input_Pojo;
	}
 
	public static AddUserAddress_Input_Pojo getAddAddressPayLoad(String firstname,String lastname,String mobile,String apartment,int stateIdNum,int cityId,int countryCode,String zipCode,String address,String addressType) 
	{
		AddUserAddress_Input_Pojo addUserAddress_Input_Pojo = new AddUserAddress_Input_Pojo(firstname,	lastname,mobile,apartment,stateIdNum,cityId,countryCode,zipCode,address,addressType);
		return addUserAddress_Input_Pojo;
	}
	public static UpdateUserAddress_Input_pojo getUpdateAddresspayload(String address_id,String firstname,String lastname,String mobile,String apartment,int stateIdNum,int cityId,int countryCode,String zipCode,String address,String addressType) 
	{
		UpdateUserAddress_Input_pojo updateUserAddress_Input_pojo = new UpdateUserAddress_Input_pojo(address_id,firstname,	lastname,mobile,apartment,stateIdNum,cityId,countryCode,zipCode,address,addressType);
		return updateUserAddress_Input_pojo;
	}
	public static DeleteUserAddress_Input_Pojo getDeleteAddresspayload(String address_id)
	{
		DeleteUserAddress_Input_Pojo deleteUserAddress_Input_Pojo =new DeleteUserAddress_Input_Pojo(address_id);
		return deleteUserAddress_Input_Pojo;
		
	}
}
