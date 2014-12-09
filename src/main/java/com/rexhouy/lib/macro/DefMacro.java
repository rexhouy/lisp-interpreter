package com.rexhouy.lib.macro;

import com.rexhouy.env.ExecStack;
import com.rexhouy.env._System;
import com.rexhouy.type.Type;
import com.rexhouy.type.basic._List;
import com.rexhouy.type.basic._Macro;
import com.rexhouy.type.basic._Variable;

/**
 * (defmacro name (arg1 arg2) body)
 * 
 * TODO not tested yet
 * 
 * @author rexhouy
 *
 */
public class DefMacro extends _Macro {

	private String name = "defmacro";
	private Type[] argNames = new Type[] { new _Variable("name"),
			new _Variable("args"), new _Variable("body") };
	private ExecStack env;

	@Override
	protected Type[] getArgNames() {
		return argNames;
	}

	/**
	 * A macro returns another macro.
	 * 
	 * @return
	 */
	@Override
	protected Type evaluateBody() {
		_Macro macro = new _Macro() {

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
		_System.getContext().bindGlobal(name, macro);
		/**
		 * When execution the function, the program has already left the current
		 * execution stack. It is necessary to treat this macro as a closure, so
		 * that binded arguments can be found.
		 */
		env = _System.getContext().saveCurrentContext();
		return macro;
	}

	@Override
	public String name() {
		return name;
	}

}
