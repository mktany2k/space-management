package com.scwcd.framework.sql.core;


public interface Selectable<I extends IParameter, O> extends IDataAccessObject {
	O doSelect(final I object);
}