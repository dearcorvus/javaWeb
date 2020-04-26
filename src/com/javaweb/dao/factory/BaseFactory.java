package com.javaweb.dao.factory;

import org.json.JSONObject;


public class BaseFactory {
	
	private static JSONObject json= new JSONObject();
	
	public static String retuenShow(String msg,Integer num) {
        
        json.put("msg",msg);
        
        json.put("code",num);
        
		return json.toString();

	}
}
