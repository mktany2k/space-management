package com.scwcd.framework.command.util;

import java.util.Collections;
import java.util.Set;

public class CommandUtility {

    public static final int MAX_CACHE = 2;

    public static Set<Integer> newPlanIds() {
        return Collections.synchronizedSet(Collections.<Integer>emptySet());
    }
}