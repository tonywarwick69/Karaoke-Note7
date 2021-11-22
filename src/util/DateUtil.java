package util;

import java.text.ParseException;
import java.text.SimpleDateFormat;

public class DateUtil {

	private static SimpleDateFormat sdfrmt = new SimpleDateFormat("yyyy-mm-dd");
	
	public static boolean validDate(String strDate) {   
	    sdfrmt.setLenient(false);
	    try {
	        sdfrmt.parse(strDate); 
	    }  
	    catch (ParseException e){
	        return false;
	    }
	    return true;
		
	}
}
