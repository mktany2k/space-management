package com.scwcd.enterprise.sql.util;


import com.scwcd.enterprise.sql.hbm.Plan;
import java.util.Set;


/**
 *
 * @deprecated This class will be removed by 5 Dec 2012.
 * This class doesn't do what is claimed to do.
 *
 */
@Deprecated
public class SqlUtility {

	public static String convertToDelimited(final Set<Plan> plans) {
        final StringBuilder string = new StringBuilder();
		for (Plan plan : plans) {
			string.append(plan.getFilename()).append(",");
		}
		return string.toString();
	}
}