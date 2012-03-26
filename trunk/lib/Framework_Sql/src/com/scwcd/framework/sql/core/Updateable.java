package com.scwcd.framework.sql.core;


public interface Updateable<I> extends IDataAccessObject {
	void doUpdate(final I object);
}