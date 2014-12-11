package com.rexhouy.type.basic;

import com.rexhouy.type.Type;


/**
 * _Macro share the same execution procedure with _Function, the procedure is
 * defined in {@link ComExecutable}.
 * @see {@link ComExecutable}
 * 
 * @author rexhouy
 *
 */
public abstract class _Macro extends ComExecutable {
	
	@Override
	public Class<? extends Type> type() {
		return _Macro.class;
	}
	
	@Override
	public String toString() {
		return "macro#"+name();
	}

}
