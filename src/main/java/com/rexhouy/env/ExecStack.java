package com.rexhouy.env;

import com.rexhouy.type.Type;

public interface ExecStack extends Iterable<String> {

	public Type find(String name);
	
	public void bind(String name, Type value);
	
}
