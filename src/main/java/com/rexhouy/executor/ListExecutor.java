package com.rexhouy.executor;

import com.rexhouy.type.Type;
import com.rexhouy.type.basic._Function;
import com.rexhouy.type.basic._List;
import com.rexhouy.type.basic._Macro;
import com.rexhouy.type.basic._Variable;

public class ListExecutor implements Executor {

	@Override
	public Type execute(_List code) {
		return processList(code);
	}
	
	Type processList(_List code) {
		Type item = code.car();
		if (item.type() == _Function.class) {
			Type[] args = code.cdr().stream().map((element) -> {
				return evaluate(element);
			}).toArray(Type[]::new);
			return (Type) item.evaluate(args);
		} else if (item.type() == _Macro.class) {
			return (Type) item.evaluate(code.cdr());
		} else {
			// This list must have only one element.
			if (code.size() > 1) {
				throw new RuntimeException("Invalid code: " + code.toString());
			}
			return item;
		}
	}
	
	Type evaluate(Type code) {
		if (code.type() == _List.class) {
			return processList((_List) code);
		} else if (code.type() == _Variable.class) {
			return (Type) code.evaluate();
		}
		return code;
	}
	
}
