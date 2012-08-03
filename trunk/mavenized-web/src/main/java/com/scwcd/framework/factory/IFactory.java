package com.scwcd.framework.factory;


public interface IFactory<K, V> {
	void register(final K key, final V value);

	V getInstance(final K key);
}