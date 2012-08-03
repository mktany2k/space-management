package com.scwcd.enterprise.sql.util;


import java.util.Set;
import com.scwcd.enterprise.sql.hbm.Plan;


public class SqlUtility {

	public static final int INCREMENT_ID = 0;

	public static final String UPDATED_BY = "SYSTEM";

	public static String convertToDelimited(final Set<Plan> plans) {
		final StringBuilder string = new StringBuilder();
		for (final Plan plan : plans) {
			string.append(plan.getFilename()).append(",");
		}
		return string.toString();
	}
}