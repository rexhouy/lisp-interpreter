package com.rexhouy.lib.function;

import org.junit.Assert;
import org.junit.Test;

import com.rexhouy.lib.function.Plus;
import com.rexhouy.type.Type;
import com.rexhouy.type.basic._Numeric;

public class PlusTest {

	Plus plus = new Plus();
	
	@Test
	public void testValue() {
		Type ret = plus.evaluate(new _Numeric("1"), new _Numeric("2"));
		Assert.assertEquals(_Numeric.class, ret.type());
		Assert.assertEquals(3, ret.evaluate());
	}
	
}
