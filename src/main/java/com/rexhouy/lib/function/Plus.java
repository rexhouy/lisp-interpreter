package com.rexhouy.lib.function;

import com.rexhouy.type.Type;
import com.rexhouy.type.basic._Function;
import com.rexhouy.type.basic._Numeric;
import com.rexhouy.type.basic._Variable;

/**
 * (+ a b)
 * 
 * @author rexhouy
 */
public class Plus extends _Function {

	private String name = "+";
	private Type[] argNames = new Type[] { new _Variable("a"), new _Variable("b") };

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
		int value = 0;
		for (Type arg : argNames) {
			_Numeric operand = (_Numeric) arg.evaluate();
			value += operand.evaluate();
		}
		return new _Numeric(String.valueOf(value));
	}

}
