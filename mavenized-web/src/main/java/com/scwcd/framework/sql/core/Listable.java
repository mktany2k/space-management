package com.scwcd.framework.sql.core;


import java.util.List;


public interface Listable<O> extends IDataAccessObject {
	List<O> doList();
}