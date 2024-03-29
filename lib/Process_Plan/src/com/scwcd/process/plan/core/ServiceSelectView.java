package com.scwcd.process.plan.core;


import java.util.List;
import com.scwcd.component.view.core.View;
import com.scwcd.component.view.factory.ViewFactory;
import com.scwcd.framework.business.core.AbstractBusinessService;


class ServiceSelectView extends AbstractBusinessService<List<View>> {

	@Override
	public void perform() {
		setOutput(ViewFactory.getInstance().list());
	}
}