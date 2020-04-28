package com.javaweb.dao.factory;

import org.json.JSONObject;


public class BaseFactory {
	
	private static JSONObject json= new JSONObject();
	/**
	 * ���󷵻�
	 * @param msg
	 * @return
	 */
	public static String errorShow(String msg) {
        
        json.put("msg",msg);
        
        json.put("code",0);
        
		return json.toString();

	}
	/**
	 * �ɹ�����
	 * @param msg
	 * @param url
	 * @return
	 */
	public static String successShow(String msg,String url) {
        
        json.put("msg",msg);
        
        json.put("code",1);
        
        json.put("url",url);
        
		return json.toString();

	}
}
