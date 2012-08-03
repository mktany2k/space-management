package com.scwcd.framework.sql.core;


import java.util.HashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.scwcd.framework.factory.IFactory;


public class DAOFactory implements IFactory<Class<?>, IDataAccessObject> {

	private static final Logger LOGGER = LoggerFactory.getLogger(DAOFactory.class);

	private static final DAOFactory INSTANCE = new DAOFactory();

	private HashMap<Class<?>, IDataAccessObject> m_RegisteredDAO = new HashMap<Class<?>, IDataAccessObject>();

	public static DAOFactory getInstance() {
		return INSTANCE;
	}

	@Override
	public void register(final Class<?> clazz, final IDataAccessObject dao) {
		LOGGER.info("Registering [" + clazz + "] with [" + dao + "]");
		m_RegisteredDAO.put(clazz, dao);
	}

	@Override
	public IDataAccessObject getInstance(final Class<?> clazz) {
		final IDataAccessObject dao = m_RegisteredDAO.get(clazz);
		if (dao == null) {
			throw new IllegalArgumentException("[" + clazz.getCanonicalName() + "] not registered");
		}
		return dao;
	}

	@Override
	public String toString() {
		return m_RegisteredDAO.toString();
	}
}