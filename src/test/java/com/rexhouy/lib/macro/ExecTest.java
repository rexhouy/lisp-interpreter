package com.rexhouy.lib.macro;

import org.junit.Test;

import junit.framework.TestCase;

import com.rexhouy.lib.function.Plus;
import com.rexhouy.type.Type;
import com.rexhouy.type.basic._List;
import com.rexhouy.type.basic._Numeric;

public class ExecTest extends TestCase {

	@Test
	public void test() {
		Exec exec = new Exec();
		_List list = new _List();
		list.add(new Plus());
		list.add(new _Numeric("1"));
		list.add(new _Numeric("2"));
		Type ret = exec.evaluate(list);
		assertNotNull(ret);
		assertEquals(3, ret.evaluate());
	}
	
}
