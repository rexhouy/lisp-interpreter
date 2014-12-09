package com.rexhouy.env;

import com.rexhouy.executor.ListExecutor;


public class _System {

	static final Context ctx;
	
	static {
		ctx = new Context(new ListExecutor());
	}
	
	public static Context getContext() {
		return ctx;
	}
	
}
