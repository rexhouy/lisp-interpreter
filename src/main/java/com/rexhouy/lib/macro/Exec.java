package com.rexhouy.lib.macro;

import com.rexhouy.env._System;
import com.rexhouy.type.Type;
import com.rexhouy.type.basic._List;
import com.rexhouy.type.basic._Macro;
import com.rexhouy.type.basic._Variable;

/**
 * (exec (list + 1 2))
 * 
 * @author rexhouy
 *
 */
public class Exec extends _Macro {

	private String name = "exec";
	private Type[] argNames = new Type[] { new _Variable("list") };
	
	@Override
	public String name() {
		return name;
	}

	@Override
	protected Type[] getArgNames() {
		return argNames;
	}

	@Override
	protected Type evaluateBody() {
		return _System.getContext().getCurrentExecutor().execute((_List)argNames[0].evaluate());
	}

	
	
}
