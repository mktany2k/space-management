package com.scwcd.framework.business.handler;


import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


public class FloorPlanHandler {

	private static final Logger LOGGER = LoggerFactory.getLogger(FloorPlanHandler.class);

	private static final FloorPlanHandler INSTANCE = new FloorPlanHandler();

	private final HashMap<String, byte[]> m_FloorPlan = new HashMap<>();

	private FloorPlanHandler() {
	}

	public static FloorPlanHandler getInstance() {
		return INSTANCE;
	}

	public void register(final String filename, final byte[] content) {
		LOGGER.info("Registering [" + filename + "] with [" + content.length + "]");
		m_FloorPlan.put(filename, content);
	}

	public byte[] retrieve(final String filename) {
		return m_FloorPlan.get(filename);
	}
}