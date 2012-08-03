package com.scwcd.framework.command.core;


import java.util.Set;
import javax.servlet.http.HttpSession;
import com.scwcd.framework.command.util.CommandUtility;


/**
 * HttpSession wrapper class.
 * @author allanc
 */
public class ApplicationSession {

	private HttpSession session;

	public static final String RCCODES = "message";

	public static final String SESSION_APPLICATION = "session.application";

	public static final String SESSION_PROJECT = "session.project";

	public static final String SESSION_PLANS = "session.plans";

	public static final String SESSION_USER = "session.user";

	public static final String SESSION_CACHE = "session.cache";

	ApplicationSession(final HttpSession session) {
		this.session = session;
	}

	public void setProject(final Object project) {
		session.setAttribute(SESSION_PROJECT, project);
	}

	public Object getProject() {
		return session.getAttribute(SESSION_PROJECT);
	}

	public Set<Integer> getPlanIds() {
		@SuppressWarnings(value="unchecked")
		final Set<Integer> planIds = (Set<Integer>) session.getAttribute(SESSION_PLANS);
		if (planIds == null) {
			synchronized (this) {
				if (planIds == null) {
					final Set<Integer> _planIds = CommandUtility.newPlanIds();
					session.setAttribute(SESSION_PLANS, _planIds);
					return _planIds;
				}
			}
		}
		return planIds;
	}

	public void setUser(final Object user) {
		session.setAttribute(SESSION_USER, user);
	}

	public Object getUser() {
		return session.getAttribute(SESSION_USER);
	}

	public void setCache(final Object lot) {
		session.setAttribute(SESSION_CACHE, lot);
	}

	public Object getCache() {
		return session.getAttribute(SESSION_CACHE);
	}

	public Object removeCache() {
		final Object cache = session.getAttribute(SESSION_CACHE);
		session.removeAttribute(SESSION_CACHE);
		return cache;
	}

	public void destroy() {
		session.invalidate();
	}

	public String toString() {
		final StringBuilder stringBuilder = new StringBuilder();
		stringBuilder.append(session.getCreationTime()).append(";");
		stringBuilder.append(session.getLastAccessedTime()).append(";");
		stringBuilder.append(session.getLastAccessedTime() - session.getCreationTime()).append(";");
		stringBuilder.append(session.getMaxInactiveInterval() * 1000).append(";");
		stringBuilder.append((session.getMaxInactiveInterval() * 1000) - (session.getLastAccessedTime() - session.getCreationTime())).append("\n");
		stringBuilder.append(session.getId()).append(";");
		stringBuilder.append(session.isNew()).append("\n");
		stringBuilder.append(getUser()).append("\n");
		stringBuilder.append(getProject()).append("\n");
		stringBuilder.append(getCache()).append("\n");
		final Set<Integer> planIds = getPlanIds();
		stringBuilder.append("planId").append(planIds).append("\n");
		return stringBuilder.toString();
	}
}