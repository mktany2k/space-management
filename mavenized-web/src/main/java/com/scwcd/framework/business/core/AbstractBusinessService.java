package com.scwcd.framework.business.core;


import java.util.ArrayList;
import java.util.List;
import java.util.Map;


public abstract class AbstractBusinessService<O> implements BusinessService {

	private O object;
	private List<Integer> codeIds;

	protected AbstractBusinessService() {
		codeIds = new ArrayList<Integer>();
	}

	@Override
	public final O getOutput() {
		return object;
	}

	@Override
	public final Map<String, String> getRcCodes() {
		int[] _codeIds = new int[codeIds.size()];
		for (int i = 0; i < _codeIds.length; i++) {
			_codeIds[i] = codeIds.get(i);
		}
		return BusinessResource.getInstance().getCode(_codeIds);
	}

	@Override
	public abstract void perform();

	protected final void setOutput(final O object) {
		this.object = object;
	}

	protected final void setRcCode(final int codeId) {
		codeIds.add(codeId);
	}
}