package com.rexhouy.type.basic;

import com.rexhouy.type.Type;

public class _Bool implements Type<Boolean> {

	boolean value;
	
	public _Bool(String value) {
		this.value = "T".equals(value) ? true : false;
	}
	
	@Override
	public Boolean evaluate(Type... args) {
		return value;
	}

	@Override
	public Class<? extends Type> type() {
		return _Bool.class;
	}
	
}
