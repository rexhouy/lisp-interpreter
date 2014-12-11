package com.rexhouy.type.basic;

import com.rexhouy.type.Type;

/**
 * Start with ":",
 * 
 * @author rexhouy
 *
 */
public class _Symbol implements Type<_Symbol> {

	private String value;
	
	public _Symbol(String value) {
		this.value = value.substring(1); // Remove the prefix
	}
	
	@Override
	public _Symbol evaluate(Type... args) {
		return this;
	}

	@Override
	public Class<? extends Type> type() {
		return _Symbol.class;
	}
	
	public String name() {
		return value;
	}
	
	@Override
	public String toString() {
		return ":"+value;
	}

}
