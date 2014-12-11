package com.rexhouy.lib.macro;

import junit.framework.TestCase;

import org.junit.Test;

import com.rexhouy.env._System;
import com.rexhouy.lib.function.Plus;
import com.rexhouy.type.Type;
import com.rexhouy.type.basic._List;
import com.rexhouy.type.basic._Macro;
import com.rexhouy.type.basic._Numeric;
import com.rexhouy.type.basic._Variable;

public class DefMacroTest extends TestCase {

	DefMacro defmacro;

	/**
	 * (defmacro test (a b) (+ a b))
	 * <br>
	 * (test (+ 1 1) 2)
	 */
	@Test
	public void testEvaluate() {
		defmacro = new DefMacro();
		
		_Variable name = new _Variable("test");
		_List argList = new _List();
		argList.add(new _Variable("a"));
		argList.add(new _Variable("b"));
		
		_List body = new _List();
		body.add(new Plus());
		body.add(new _Variable("a"));
		body.add(new _Variable("b"));
		
		Type ret = defmacro.evaluate(name, argList, body);
		assertEquals(_Macro.class, ret.type());
		_Macro m = (_Macro) ret;
		
		_List a = new _List();
		a.add(new Plus());
		a.add(new _Numeric("1"));
		a.add(new _Numeric("1"));
		_Numeric b = new _Numeric("2");
		ret = m.evaluate(a, b);
		assertEquals(4, ret.evaluate());
	}

}
