package com.rexhouy.lib.macro;

import com.rexhouy.env.ExecStack;
import com.rexhouy.env._System;
import com.rexhouy.type.Type;
import com.rexhouy.type.basic._Function;
import com.rexhouy.type.basic._List;
import com.rexhouy.type.basic._Macro;
import com.rexhouy.type.basic._Variable;

/**
 * 
 * 
 * (defun name (arg1 arg2) (body))
 * 
 * TODO closure context management should move to Context.
 * 
 * @author rexhouy
 *
 */
public class Defun extends _Macro {

	private String name = "defun";
	private Type[] argNames = new Type[] { new _Variable("name"),
			new _Variable("args"), new _Variable("body") };
	private ExecStack env;

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
		_Function func = new _Function() {

			@Override
			public String name() {
				_Variable nameArg = (_Variable) (env == null 
						? argNames[0].evaluate() 
						: env.find(((_Variable) argNames[0]).name()));
				return nameArg.name();
			}

			@Override
			protected Type[] getArgNames() {
				_List args = (_List) (env == null 
						? argNames[1].evaluate()
						: env.find(((_Variable) argNames[1]).name()));
				return args.toArray(new Type[args.size()]);
			}

			@Override
			protected Type evaluateBody() {
				_List body = (_List) (env == null 
						? argNames[2].evaluate()
						: env.find(((_Variable) argNames[2]).name()));
				return _System.getContext().getCurrentExecutor().execute(body);
			}

		};
		_System.getContext().bindGlobal(func.name(), func);
		/**
		 * When execution the function, the program has already left the current
		 * execution stack. It is necessary to treat this macro as a closure, so
		 * that binded arguments can be found.
		 */
		env = _System.getContext().saveCurrentContext();
		return func;
	}
}
