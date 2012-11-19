package com.scwcd.framework.command.core;

import com.google.common.collect.Maps;
import java.util.Map;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ServletCommandFactory implements ICommandFactory<IServletCommand> {

    private static final Logger LOGGER = LoggerFactory.getLogger(ServletCommandFactory.class);
    private static final ServletCommandFactory INSTANCE = new ServletCommandFactory();
    private Map<String, IServletCommand> m_RegisteredCommand = Maps.newHashMap();

    private ServletCommandFactory() {
    }

    public static ServletCommandFactory getInstance() {
        return INSTANCE;
    }

    @Override
    public void register(final String operation, final IServletCommand command) {
        LOGGER.info("Registering [" + operation + "] with [" + command + "]");
        m_RegisteredCommand.put(operation, command);
    }

    public void register(final String[] operations, final IServletCommand command) {
        for (final String operation : operations) {
            LOGGER.info("Registering [" + operation + "] with [" + command + "]");
            m_RegisteredCommand.put(operation, command);
        }
    }

    @Override
    public IServletCommand getInstance(final String operation) {
        IServletCommand command = m_RegisteredCommand.get(operation);
        if (command == null) {
            throw new UnsupportedOperationException(operation);
        }
        return command.create();
    }

    @Override
    public String toString() {
        return m_RegisteredCommand.toString();
    }
}