package com.rexhouy.type.basic;

import java.util.ArrayList;
import java.util.Iterator;

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
	
	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder("(");
		for (Iterator<Type> itor = iterator(); itor.hasNext();) {
			sb.append(itor.next().toString()).append(" ");
		}
		if (sb.length() > 1) {
			sb.delete(sb.length()-1, sb.length());
		}
		sb.append(")");
		return sb.toString();
	}

}
