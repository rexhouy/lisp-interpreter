package com.rexhouy.lib.macro;

import com.rexhouy.type.Type;
import com.rexhouy.type.basic._Macro;

/**
 * (list arg1 arg2 arg3 ...)
 * @author rexhouy
 */
public class List extends _Macro {

	private String name = "list";
	private Type[] argNames = new Type[]{};
	
	@Override
	public String name() {
		return "list";
	}

	@Override
	protected Type[] getArgNames() {
		return null;
	}

	@Override
	protected Type evaluateBody() {
		// TODO Auto-generated method stub
		return null;
	}

	
}
