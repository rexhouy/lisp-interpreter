package com.rexhouy.type.basic;

import com.rexhouy.env._System;
import com.rexhouy.type.Type;

public class _Variable implements Type<Type> {

	String name;
	
	public _Variable(String value) {
		this.name = value;
	}
	
	public String name() {
		return name;
	}
	
	@Override
	public Type evaluate(Type... args) {
		return _System.getContext().find(name);
	}

	@Override
	public Class<? extends Type> type() {
		return _Variable.class;
	}

	
	
}
