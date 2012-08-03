package com.scwcd.framework.business.core;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.ResourceBundle;


public class BusinessResource {

	private static final BusinessResource INSTANCE = new BusinessResource();

	private static final String RESOURCE = "com.scwcd.framework.business.resource.rccodes_en";

	private BusinessResource() {
	}

	public static BusinessResource getInstance() {
		return INSTANCE;
	}

	private Map<String, String> createMap(final List<String> strings) {
		Map<String, String> map = new HashMap<String, String>();

		for (String string : strings) {
			final String[] pair = string.split(",");
			map.put(pair[0], pair[1]);
		}

		return map;
	}
	
	public Map<String, String> getCode(final int codeId) {
		final ResourceBundle resourceBundle = ResourceBundle.getBundle(RESOURCE);
		final String string = resourceBundle.getString(String.valueOf(codeId));
		
		ArrayList<String> strings = new ArrayList<String>();
		strings.add(string);
		return createMap(strings);
	}
	
	public Map<String, String> getCode(final int[] codeIds) {
		ArrayList<String> strings = new ArrayList<String>();
		for (int codeId : codeIds) {
			final ResourceBundle resourceBundle = ResourceBundle.getBundle(RESOURCE);
			final String string = resourceBundle.getString(String.valueOf(codeId));
			strings.add(string);
		}

		return createMap(strings);
	}
}