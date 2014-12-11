package com.rexhouy.lib.macro;

import junit.framework.TestCase;

import com.rexhouy.lib.function.Plus;
import com.rexhouy.type.Type;
import com.rexhouy.type.basic._Function;
import com.rexhouy.type.basic._List;
import com.rexhouy.type.basic._Numeric;

public class ListTest extends TestCase {

	List list = new List();
	
	public void test() {
		Type ret = list.evaluate(new Plus(), new _Numeric("1"), new _Numeric("2"));
		assertEquals(_List.class, ret.type());
		_List retList = (_List) ret;
		assertEquals(3, retList.size());
		assertEquals(_Function.class, retList.get(0).type());
		assertEquals(1, retList.get(1).evaluate());
		assertEquals(2, retList.get(2).evaluate());
	}
	
}
