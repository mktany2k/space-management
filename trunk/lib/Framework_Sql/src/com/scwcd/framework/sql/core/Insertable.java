package com.scwcd.framework.sql.core;


public interface Insertable<I> extends IDataAccessObject {
	void doInsert(final I object);
}