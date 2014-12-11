package com.rexhouy.lib.macro;

import com.rexhouy.type.Type;
import com.rexhouy.type.basic._Macro;
import com.rexhouy.type.basic._Variable;

/**
 * (list arg1 arg2 arg3 ...)<br>
 * (defmacro list (&rest p))
 * @author rexhouy
 */
public class List extends _Macro {

	private String name = "list";
	private Type[] argNames = new Type[]{ new _Variable("&rest"), new _Variable("p")};
	
	@Override
	public String name() {
		return "list";
	}

	@Override
	protected Type[] getArgNames() {
		return argNames;
	}

	@Override
	protected Type evaluateBody() {
		return new _Variable("p").evaluate();
	}

	
}
