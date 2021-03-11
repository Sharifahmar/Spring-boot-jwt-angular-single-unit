/**
 * 
 */
package com.spring.utils;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author Asus
 *
 */
public class CommonUtils {

	public static Date stringTodate(String date) throws Exception {
		try {
			SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			return formatter.parse(date);
		} catch (Exception e) {
			throw new Exception(e.getMessage());
		}

	}

}
