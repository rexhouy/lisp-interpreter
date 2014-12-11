package com.rexhouy.type.basic;

import com.rexhouy.type.Type;


/**
 * _Function share the same execution procedure with _Macro, the procedure is
 * defined in {@link ComExecutable}.
 * @see {@link ComExecutable}
 * 
 * @author rexhouy
 *
 */
public abstract class _Function extends ComExecutable {
	
	@Override
	public Class<? extends Type> type() {
		return _Function.class;
	}
	
	@Override
	public String toString() {
		return "func#"+name();
	}

}
