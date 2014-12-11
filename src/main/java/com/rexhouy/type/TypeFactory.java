package com.rexhouy.type;

import java.util.regex.Pattern;

import com.rexhouy.env._System;
import com.rexhouy.type.basic._Bool;
import com.rexhouy.type.basic._Numeric;
import com.rexhouy.type.basic._String;
import com.rexhouy.type.basic._Symbol;
import com.rexhouy.type.basic._Variable;


public class TypeFactory {

	public static Type<?> gen(String value) {
		if (isNum(value)) {
			return new _Numeric(value);
		}
		if (isString(value)) {
			return new _String(value);
		}
		if (isBool(value)) {
			return new _Bool(value);
		}
		if (isSymbol(value)) {
			return new _Symbol(value);
		}
		Type<?> ret = _System.getContext().find(value);
		return ret == null ? new _Variable(value) : ret;
	}
	
	static boolean isNum(String value) {
		return Pattern.compile("\\d+").matcher(value).matches();
	}
	
	static boolean isString(String value) {
		return Pattern.compile("^[\"'].*[\"']$").matcher(value).matches();
	}
	
	static boolean isBool(String value) {
		return value.equals("T") || value.equals("nil");
	}
	
	static boolean isSymbol(String value) {
		return value.startsWith(":");
	}
}
