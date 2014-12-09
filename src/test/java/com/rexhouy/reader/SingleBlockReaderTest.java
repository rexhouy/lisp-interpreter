package com.rexhouy.reader;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

import com.rexhouy.type.basic._Function;
import com.rexhouy.type.basic._List;
import com.rexhouy.type.basic._Numeric;

public class SingleBlockReaderTest {
	
	SingleBlockReader reader = new SingleBlockReader();
	
	@Test
	public void testGenListSimple() {
		List<String> input = Arrays.asList(new String[]{
				"(", "+", "1", "(", "+", "3", "2", ")", ")"
		});
		_List code = reader.genList(input);
		Assert.assertEquals(3, code.size());
		assertType(code, _Function.class, _Numeric.class, _List.class);
		assertType((_List)code.get(2), _Function.class, _Numeric.class, _Numeric.class);
	}
	
	@Test
	public void testGenListNesting() {
		List<String> input = Arrays.asList(new String[] {
				"(", "+", "(", "+", "3", "2", ")", "1", ")"
		});
		_List code = reader.genList(input);
		Assert.assertEquals(3, code.size());
		assertType(code, _Function.class, _List.class, _Numeric.class);
		assertType((_List)code.get(1), _Function.class, _Numeric.class, _Numeric.class);
	}
	
	private void assertType(_List code, Class... types) {
		for (int i = 0; i < types.length; i++) {
			Assert.assertEquals(types[i], code.get(i).type());
		}
	}

}
