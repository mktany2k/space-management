package com.scwcd.framework.sql.core;


public interface Saveable<I> extends IDataAccessObject {
	void doSave(final I object);
}