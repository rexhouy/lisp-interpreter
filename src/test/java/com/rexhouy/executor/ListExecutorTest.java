package com.rexhouy.executor;

import org.junit.Assert;
import org.junit.Test;

import com.rexhouy.type.Type;
import com.rexhouy.type.TypeFactory;
import com.rexhouy.type.basic._List;

public class ListExecutorTest {

	ListExecutor listExecutor = new ListExecutor();
	
	@Test
	public void testProcessListSimple() {
		_List code = new _List();
		code.add(TypeFactory.gen("+"));
		code.add(TypeFactory.gen("1"));
		code.add(TypeFactory.gen("2"));
		Type ret = listExecutor.processList(code);
		Assert.assertEquals(3, ret.evaluate());
	}
	
}

