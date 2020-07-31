package com.pitang.desafio.util;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public  class Util {

	
	
	public static Date stringToDate(String date)  {
		
		if(date ==null || date.equals("")) {
			return null;
		}
		DateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
		Date formatedDate= null;
		try {
			formatedDate = (Date)formatter.parse(date);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return formatedDate;
	}
}
