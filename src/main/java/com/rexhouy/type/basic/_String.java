package com.rexhouy.type.basic;

import com.rexhouy.type.Type;

public class _String implements Type<String> {

	String value;
	
	public _String(String value) {
		this.value = value.substring(1, value.length()-1); // Remove "
	}
	
	@Override
	public String evaluate(Type... args) {
		return value;
	}

	@Override
	public Class<? extends Type> type() {
		return _String.class;
	}
	
}
