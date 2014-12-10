package com.rexhouy.type.basic;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.rexhouy.env._System;
import com.rexhouy.type.Type;

/**
 * Handle arguments <br>
 * <p>
 * There are 4 type of arguments supported:
 * <ul>
 * <li>Positioned arguments(The most common one)
 * <li>Argument with default value
 * <li>Key argument
 * <li>Indefinite number of arguments.
 * </ul>
 * 
 * <p>
 * Following is an argument list example.<br>
 * <code>
 * (common-arg (arg-with-default-value default-value) &key (key-arg-with-default-value default-value) &rest rest-args)
 * </code>
 * 
 * @author rexhouy
 *
 */
class Arguments {

	List<Argument> args;

	public enum ArgType {
		common, key, rest
	}

	public Arguments(Type[] args) {
		this.args = new ArrayList<Argument>();
		processArgList(args);
	}

	public void bind(Type[] values) {
		try {
			setValues(values);
			bind2Ctx();
		} catch (Exception e) {
			throw new RuntimeException("Parameter Binding Error.", e);
		}
	}

	private void bind2Ctx() {
		args.stream().forEach((arg) -> {
			_System.getContext().bind(arg.name, arg.getValue());
		});
	}

	void setValues(Type[] values) {
		int currentPos = 0;
		boolean startKey = false;
		for (int i = 0; i < values.length; i++) {
			Type value = values[i];
			if (value.type() == _Symbol.class) {
				// Key argument (:arg-name arg-value)
				Argument arg = findKeyArg((_Symbol) value);
				arg.value = new Type[] { values[++i] };
				startKey = true;
			} else if (startKey || args.get(currentPos).type == ArgType.rest) {
				// Everything after key arguments are considered a rest.
				// Put rest values into this.
				Argument arg = startKey ? findRestArg() : args.get(currentPos);
				arg.value = new Type[values.length - i];
				System.arraycopy(values, i, arg.value, 0,
						values.length - i);
				break;
			} else {
				args.get(currentPos).value = new Type[] { values[i] };
				currentPos++;
			}
		}
	}

	private Argument findKeyArg(_Symbol name) {
		return args.stream().filter((arg) -> {
			return arg.name.equals(name.name());
		}).findFirst().get();
	}
	
	private Argument findRestArg() {
		return args.stream().filter((arg) -> {
			return arg.type == ArgType.rest;
		}).findFirst().get();
	}

	/**
	 * Initialize argument list.
	 * 
	 * 
	 * 
	 * @param args
	 */
	private void processArgList(Type[] args) {
		boolean startKey = false;
		for (int i = 0; i < args.length; i++) {
			if (args[i].type() == _List.class) {
				// Argument with default value. (name value)
				Type arg = args[i];
				String name = ((_Variable) ((_List) arg).car()).name();
				Type value = ((_List) arg).get(1);
				this.args.add(new Argument(name, startKey ? ArgType.key
						: ArgType.common, value));
				continue;
			}
			String name = ((_Variable) args[i]).name();
			if (name.equals("&rest")) {
				// The next one is the rest arguments.
				this.args.add(new Argument(((_Variable) args[++i]).name(),
						ArgType.rest));
			} else if (name.equals("&key")) {
				// Key argument start
				startKey = true;
			} else {
				// Argument without default value.
				this.args.add(new Argument(name, startKey ? ArgType.key
						: ArgType.common));
			}
		}
	}

	class Argument {
		String name;
		Type[] value;
		ArgType type;

		public Argument(String name, ArgType type, Type... values) {
			this.name = name;
			this.value = values;
			this.type = type;
		}

		public Type getValue() {
			if (type == ArgType.rest) {
				_List ret = new _List();
				ret.addAll(Arrays.asList(value));
				return ret;
			}
			return value.length > 0 ? value[0] : null;
		}
	}

}
