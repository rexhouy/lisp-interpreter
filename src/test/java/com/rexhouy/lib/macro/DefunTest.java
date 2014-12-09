package com.rexhouy.lib.macro;

import org.junit.Assert;
import org.junit.Test;

import com.rexhouy.lib.function.Plus;
import com.rexhouy.type.Type;
import com.rexhouy.type.basic._Function;
import com.rexhouy.type.basic._List;
import com.rexhouy.type.basic._Numeric;
import com.rexhouy.type.basic._Variable;

public class DefunTest {

	Defun defun = new Defun();
	
	@Test
	public void testEvaluate() {
		_Variable name = new _Variable("increase");
		
		_List argList = new _List();
		argList.add(new _Variable("a"));
		
		_List body = new _List();
		body.add(new Plus());
		body.add(new _Variable("a"));
		body.add(new _Numeric("1"));
		
		Type func = defun.evaluate(name, argList, body);
		Assert.assertEquals(_Function.class, func.type());
		
		Type ret = (Type) func.evaluate(new _Numeric("2"));
		Assert.assertEquals(_Numeric.class, ret.type());
		Assert.assertEquals(3, ret.evaluate());
	}
	
}
