package org.tl2project.RestUserControll;

import javax.json.*;


public class Utility {
	/**
	 * Null check Method
	 * 
	 * @param txt
	 * @return
	 */
	public static boolean isNotNull(String txt) {
		// System.out.println("Inside isNotNull");
		return txt != null && txt.trim().length() >= 0 ? true : false;
	}

	/**
	 * Method to construct JSON
	 * 
	 * @param tag
	 * @param status
	 * @return
	 */	
	public static String constructJSON(String tag, boolean status) {
		
		 JsonObject obj = Json.createObjectBuilder()
		
			.add("tag", tag)
			.add("status", new Boolean(status))
			.build();
			
		
		return obj.toString();
	}

	/**
	 * Method to construct JSON with Error Msg
	 * 
	 * @param tag
	 * @param status
	 * @param err_msg
	 * @return
	 */
	public static String constructJSON(String tag, boolean status,String err_msg) {
		JsonObject obj = Json.createObjectBuilder()
		
			.add("tag", tag)
			.add("status", new Boolean(status))
			.add("error_msg", err_msg)
			.build();
		
		return obj.toString(); 
	}
	
}
