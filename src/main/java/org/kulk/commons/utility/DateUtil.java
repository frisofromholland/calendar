package org.kulk.commons.utility;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * Created by gebruiker on 3-12-2015.
 */
public class DateUtil {

    public static Date createDateFromString(String dateString) {
	Date date;
	try {
	    date = new SimpleDateFormat("dd/MM/yyyy").parse(dateString);
	} catch (ParseException e) {
	    date = new Date();
	}
	return date;
    }
}
