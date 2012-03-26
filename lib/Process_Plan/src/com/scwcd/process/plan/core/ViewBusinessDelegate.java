package com.scwcd.process.plan.core;


import java.util.List;
import com.scwcd.component.view.core.View;
import com.scwcd.framework.business.core.AbstractBusinessDelegate;
import com.scwcd.framework.business.core.AbstractBusinessService;
import com.scwcd.framework.command.core.ApplicationSession;


class ViewBusinessDelegate extends AbstractBusinessDelegate {

	protected ViewBusinessDelegate(final ApplicationSession session, final AbstractBusinessService<?> service) {
		super(session, service);
	}

	protected ViewBusinessDelegate(final ApplicationSession session) {
		this(session, null);
	}

	List<View> selectViews() {
		final ServiceSelectView service = (ServiceSelectView) getService();
		
		service.perform();

		return service.getOutput();
	}
}