package com.rexhouy.type.basic;

import com.rexhouy.env.ExecStack;
import com.rexhouy.env._System;
import com.rexhouy.type.Type;

/**
 * The <code>ComExecutable</code> class is the parent of _Function and _Macro. <br>
 * The class defines common operations in both _Function and _Macro. <br>
 * 
 * <p>
 * _Function and _Macro share the same execution procedure. The difference
 * between _Function and _Macro is that arguments are evaluated before passed to
 * _Function but arguments are passed to _Macro without evaluation.
 * 
 * <p> 
 * The execution flow.<br>
 * Create new execution stack -->  Bind arguments to execution stack -->  Execute the body with current <code>Executor</code>
 * 
 * <p> 
 * How to use this class?<br>
 * In most cases implements the abstract methods.
 * <br>
 * 
 * TODO Handle named arguments, arguments check.
 * 
 * @author rexhouy
 *
 */
abstract class ComExecutable implements Type<Type> {

	private Type[] argValues;

	private ExecStack stack;
	
	private Arguments args;

	@Override
	public Type<?> evaluate(Type... argValues) {
		try {
			// Execute function in a new stack.
			this.stack = _System.getContext().newExecStack();
			this.argValues = argValues;
			bindArgs();
			return evaluateBody();
		} finally {
			_System.getContext().exitExecStack();
		}
	}

	public abstract String name();

	/**
	 * 
	 * @return
	 */
	protected abstract Type[] getArgNames();

	protected void bindArgs() {
		this.args = new Arguments(getArgNames());
		this.args.bind(argValues);
	}

	protected abstract Type evaluateBody();

	protected Arguments getArgs() {
		return args;
	}

}
