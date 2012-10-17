package com.scwcd.enterprise.tags;


import com.scwcd.framework.constant.Month;
import java.io.IOException;
import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.SimpleTagSupport;


public class MonthYearTag extends SimpleTagSupport {

	private String monthId;
	
	private String monthName;

	private String yearId;
	
	private String yearName;
	
	private String onchange;
	
	private String onkeypress;
	
	private String monthStyle;
	
	private String yearStyle;

	@Override
	public void doTag() throws JspException, IOException {
		StringBuilder sbTag = new StringBuilder();
		sbTag.append("<select id=\"").append(monthId).append("\" name=\"").append(monthName);
		sbTag.append("\" style=\"").append(monthStyle).append("\" onchange=\"").append(onchange);
		sbTag.append("\">");
		
		for (int i = 0; i < 12; i++) {
			String string = Month.getMonth(i + 1);
			sbTag.append("<option value=\"").append(string).append("\">").append(string).append("</option>");
		}
		sbTag.append("</select>");
		sbTag.append("<input type=\"text\" maxlength=\"4\" id=\"").append(yearId);
		sbTag.append("\" name=\"").append(yearName).append("\"");
		sbTag.append("\" style=\"").append(yearStyle).append("\"");
		sbTag.append("\" onchange=\"").append(onchange).append("\"");
		sbTag.append("\" onkeypress=\"").append(onkeypress).append("\">");
		
		getJspContext().getOut().write(sbTag.toString());
	}
	
	public void setMonthId(final String monthId) {
		this.monthId = monthId;
	}
	
	public void setMonthName(final String monthName) {
		this.monthName = monthName;
	}
	
	public void setYearId(final String yearId) {
		this.yearId = yearId;
	}
	
	public void setYearName(final String yearName) {
		this.yearName = yearName;
	}
	
	public void setOnchange(final String onchange) {
		this.onchange = onchange;
	}
	
	public void setOnkeypress(final String onkeypress) {
		this.onkeypress = onkeypress;
	}
	
	public void setMonthStyle(final String monthStyle) {
		this.monthStyle = monthStyle;
	}
	
	public void setYearStyle(final String yearStyle) {
		this.yearStyle = yearStyle;
	}
}