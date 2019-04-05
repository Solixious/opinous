package com.opinous.utils;

import java.util.Date;

import org.ocpsoft.prettytime.PrettyTime;

public class PrettyTimeUtils {

	private static final PrettyTime prettyTime = new PrettyTime();
	
	public static String convertToTimeAgo(final Date date) {
		return prettyTime.format(date);
	}
}
