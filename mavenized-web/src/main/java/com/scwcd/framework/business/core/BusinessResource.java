package com.scwcd.framework.business.core;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import java.util.Collections;
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

    private static Map<String, String> createMap(final List<String> strings) {
        Map<String, String> map = Maps.newHashMapWithExpectedSize(strings.size());

        for (String string : strings) {
            final String[] pair = string.split(",");
            map.put(pair[0], pair[1]);
        }

        return map;
    }

    public static Map<String, String> getCode(final int codeId) {
        final ResourceBundle resourceBundle = ResourceBundle.getBundle(RESOURCE);
        final String string = resourceBundle.getString(String.valueOf(codeId));

        return createMap(Collections.singletonList(string));
    }

    public static Map<String, String> getCode(final int[] codeIds) {
        final ResourceBundle resourceBundle = ResourceBundle.getBundle(RESOURCE);
        List<String> strings = Lists.newArrayListWithExpectedSize(codeIds.length);
        for (int codeId : codeIds) {
            final String string = resourceBundle.getString(String.valueOf(codeId));
            strings.add(string);
        }

        return createMap(strings);
    }
}