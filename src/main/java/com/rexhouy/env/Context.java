package com.rexhouy.env;

import java.util.Iterator;
import java.util.Stack;

import com.rexhouy.executor.Executor;
import com.rexhouy.type.Type;

public class Context {
	
	Stack<ExecStack> ctx = new Stack<ExecStack>();
	Executor currentExecutor;
	
	public Context(Executor executor) {
		ctx.push(new RootStack());
		ctx.push(new GlobalStack());
		currentExecutor = executor;
	}

	public Type<?> find(String name) {
		for (int i = ctx.size() - 1; i >= 0; i--) {
			Type ret = ctx.get(i).find(name);
			if (ret != null) {
				return ret;
			}
		}
		return null;
	}
	
	public ExecStack newExecStack() {
		ExecStack stack = new ComExecStack();
		ctx.push(stack);
		return stack;
	}
	
	public void bind(String name, Type value) {
		ctx.lastElement().bind(name, value);
	}
	
	public void bindGlobal(String name, Type value) {
		ctx.get(1).bind(name, value);
	}
	
	public void exitExecStack() {
		ctx.pop();
	}
	
	public Executor getCurrentExecutor() {
		return currentExecutor;
	}
	
	/**
	 * Used for closure
	 * <br>
	 * Save current context variables into a single stack.
	 * @return
	 */
	public ExecStack saveCurrentContext() {
		ExecStack savePoint = new ComExecStack();
		for (int i = 2; i < ctx.size(); i++) {
			ExecStack stack = ctx.get(i);
			for(Iterator<String> itor = stack.iterator(); itor.hasNext();) {
				String key = itor.next();
				savePoint.bind(key, stack.find(key));
			}
		}
		return savePoint;
	}
	
}
