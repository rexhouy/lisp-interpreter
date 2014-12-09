package com.rexhouy.env;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import com.rexhouy.type.Type;

public class ComExecStack implements ExecStack {

	private Map<String, Type> values = new HashMap<String, Type>();

	@Override
	public Type find(String name) {
		return values.get(name);
	}

	@Override
	public void bind(String name, Type value) {
		values.put(name, value);
	}

	@Override
	public Iterator<String> iterator() {
		return values.keySet().iterator();
	}

}
