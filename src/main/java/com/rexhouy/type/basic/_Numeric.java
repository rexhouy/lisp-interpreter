package com.rexhouy.type.basic;

import com.rexhouy.type.Type;

public class _Numeric implements Type<Integer> {

	private int value;
	
	public _Numeric(String value) {
		this.value = Integer.parseInt(value);
	}

	public Integer evaluate(Type... args) {
		return value;
	}

	@Override
	public Class<? extends Type> type() {
		return _Numeric.class;
	}
	
}
