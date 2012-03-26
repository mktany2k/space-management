package com.scwcd.framework.command.core;


public interface ICommand<I, O> {
	O execute(final I input);
	
	ICommand<?, ?> create();
}