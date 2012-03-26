package com.scwcd.process.ajax.core;


import com.scwcd.component.view.core.View;
import com.scwcd.component.view.factory.ViewFactory;
import com.scwcd.framework.business.core.AbstractBusinessService;


class ServiceSelectView extends AbstractBusinessService<View> {

	private int viewId;

	@Override
	public void perform() {
		final ViewFactory viewFactory = ViewFactory.getInstance();
		final View view = viewFactory.getInstance(viewId);
		setOutput(view);
	}

	void setViewId(final int viewId) {
		this.viewId = viewId;
	}
}