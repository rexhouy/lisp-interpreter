package com.rexhouy.type.basic;

import com.rexhouy.type.Type;

import junit.framework.TestCase;

public class ArgumentsTest extends TestCase {

	Arguments args;

	@Override
	protected void tearDown() throws Exception {
		args = null;
	}

	/**
	 * (a (b 3) (c 4))
	 */
	public void testDefaultValue() {
		_Variable arg0 = new _Variable("a");

		_List arg1 = new _List();
		arg1.add(new _Variable("b"));
		arg1.add(new _Numeric("3"));

		_List arg2 = new _List();
		arg2.add(new _Variable("c"));
		arg2.add(new _Numeric("4"));

		args = new Arguments(new Type[] { arg0, arg1, arg2 });

		assertEquals(3, args.args.size());
		assertEquals("a", args.args.get(0).name);
		assertNull(args.args.get(0).getValue());
		assertEquals("b", args.args.get(1).name);
		assertEquals(3, args.args.get(1).getValue().evaluate());
		assertEquals("c", args.args.get(2).name);
		assertEquals(4, args.args.get(2).getValue().evaluate());

		args.setValues(new Type[] { new _Numeric("1"), new _Numeric("2") });
		assertEquals(1, args.args.get(0).getValue().evaluate());
		assertEquals(2, args.args.get(1).getValue().evaluate());
		assertEquals(4, args.args.get(2).getValue().evaluate());

	}

	/**
	 * (a &key b c)
	 */
	public void testKeyParam() {
		_Variable arg0 = new _Variable("a");

		_Variable arg1 = new _Variable("&key");

		_Variable arg2 = new _Variable("b");

		_Variable arg3 = new _Variable("c");

		args = new Arguments(new Type[] { arg0, arg1, arg2, arg3 });

		assertEquals(3, args.args.size());
		assertEquals("a", args.args.get(0).name);
		assertEquals("b", args.args.get(1).name);
		assertEquals("c", args.args.get(2).name);

		args.setValues(new Type[] { new _Numeric("1"), new _Symbol(":c"),
				new _Numeric("3"), new _Symbol(":b"), new _Numeric("2") });
		assertEquals(1, args.args.get(0).getValue().evaluate());
		assertEquals(2, args.args.get(1).getValue().evaluate());
		assertEquals(3, args.args.get(2).getValue().evaluate());
	}

	/**
	 * (a &rest b)
	 */
	public void testRestParam() {
		_Variable arg0 = new _Variable("a");
		_Variable arg1 = new _Variable("&rest");
		_Variable arg2 = new _Variable("b");

		args = new Arguments(new Type[] { arg0, arg1, arg2 });

		assertEquals(2, args.args.size());
		assertEquals("a", args.args.get(0).name);
		assertEquals("b", args.args.get(1).name);

		args.setValues(new Type[] { new _Numeric("1"), new _Numeric("2"),
				new _Numeric("3") });
		assertEquals(1, args.args.get(0).getValue().evaluate());
		_List b = (_List) args.args.get(1).getValue();
		assertEquals(2, b.size());
		assertEquals(2, b.get(0).evaluate());
		assertEquals(3, b.get(1).evaluate());
	}

	/**
	 * (a &key (b 2) c &rest d)<br>
	 * (1 :c 3 4 5)
	 */
	public void testMixed() {
		_Variable arg0 = new _Variable("a");
		_Variable arg1 = new _Variable("&key");

		_List arg2 = new _List();
		arg2.add(new _Variable("b"));
		arg2.add(new _Numeric("2"));

		_Variable arg3 = new _Variable("c");
		_Variable arg4 = new _Variable("&rest");
		_Variable arg5 = new _Variable("d");

		args = new Arguments(new Type[] { arg0, arg1, arg2, arg3, arg4, arg5 });
		
		args.setValues(new Type[] { new _Numeric("1"), new _Symbol(":c"),
				new _Numeric("3"), new _Numeric("4"), new _Numeric("5") });
		
		assertEquals(1, args.args.get(0).getValue().evaluate());
		assertEquals(2, args.args.get(1).getValue().evaluate());
		assertEquals(3, args.args.get(2).getValue().evaluate());
		
		_List d = (_List) args.args.get(3).getValue();
		assertEquals(2, d.size());
		assertEquals(4, d.get(0).evaluate());
		assertEquals(5, d.get(1).evaluate());
	}

}
