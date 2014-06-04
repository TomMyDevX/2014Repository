package com.tmd.date;

import java.util.Calendar;
import java.util.Date;

public class ClickDate {
	public static int getDaysDifference(Date fromDate, Date toDate) {
		if ( fromDate == null || toDate == null ) return 0;

		return (int) ((toDate.getTime() - fromDate.getTime()) / (1000 * 60 * 60 * 24));
	}

	public static int getDaysDifference(Calendar calendar1, Calendar calendar2) {
		if ( calendar1 == null || calendar2 == null ) return 0;

		return (int) ((calendar2.getTimeInMillis() - calendar1.getTimeInMillis()) / (1000 * 60 * 60 * 24));
	}
}
