package com.rexhouy.type.basic;

import java.util.ArrayList;
import java.util.List;

import com.rexhouy.type.Type;

public class _List extends ArrayList<Type> implements Type {

	@Override
	public Type evaluate(Type... args) {
		return this;
	}

	@Override
	public Class type() {
		return _List.class;
	}

	@Override
	public String toString() {
		return super.toString();
	}
	
	public Type car() {
		return this.get(0);
	}
	
	public _List cdr() {
		_List ret = new _List();
		for (int i = 1; i < this.size(); i++) {
			ret.add(this.get(i));
		}
		return ret;
	}

}
