package com.rexhouy.reader;

import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class ListParserTest {

	@Test
	public void testParseSimple() {
		List<String> ret = ListParser.parse("(+ 1 1)");
		assertList(ret, "(", "+", "1", "1", ")");
	}
	
	@Test
	public void testParseString() {
		List<String> ret = ListParser.parse("(concat 2 \"te st\" 1)");
		assertList(ret, "(", "concat", "2", "\"te st\"", "1", ")");
	}
	
	@Test
	public void testParseMultiBlank() {
		List<String> ret = ListParser.parse("( + 1   2 ) ");
		assertList(ret, "(", "+", "1", "2", ")");
	}
	
	@Test
	public void testParseNesting() {
		List<String> ret = ListParser.parse("( + 1   (- 3 2) ) ");
		assertList(ret, "(", "+", "1", "(", "-", "3", "2", ")", ")");
	}
	
	private void assertList(List<String> list, String... elements) {
		Assert.assertEquals(elements.length, list.size());
		for (int i = 0; i < list.size(); i++) {
			Assert.assertEquals(elements[i], list.get(i));
		}
	}
	
}
