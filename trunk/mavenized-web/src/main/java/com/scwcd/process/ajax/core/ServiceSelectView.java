package com.scwcd.process.ajax.core;


import com.scwcd.component.view.core.View;
import com.scwcd.component.view.factory.ViewFactory;
import com.scwcd.framework.business.core.AbstractBusinessService;


class ServiceSelectView extends AbstractBusinessService<View> {

	private int viewId;

	@Override
	public void perform() {
		final ViewFactory viewFactory = ViewFactory.getInstance();
		setOutput(viewFactory.getInstance(viewId));
	}

	void setViewId(final int viewId) {
		this.viewId = viewId;
	}
}