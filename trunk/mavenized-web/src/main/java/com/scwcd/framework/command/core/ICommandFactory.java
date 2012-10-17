package com.scwcd.framework.command.core;


import com.scwcd.framework.factory.IFactory;


public interface ICommandFactory<V extends ICommand<?, ?>> extends IFactory<String, V> {
	
}