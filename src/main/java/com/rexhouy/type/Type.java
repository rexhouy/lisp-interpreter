package com.rexhouy.type;

public interface Type<T> {
	
	public T evaluate(Type... args);
	
	public Class<? extends Type> type();
	
}
