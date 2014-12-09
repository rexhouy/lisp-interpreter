package com.rexhouy.reader;

import java.util.List;
import java.util.Stack;

import com.rexhouy.type.TypeFactory;
import com.rexhouy.type.basic._List;

public class SingleBlockReader implements Reader {

	@Override
	public _List read(String input) {
		List<String> code = ListParser.parse(input);
		return genList(code);
	}
	
	_List genList(List<String> input) {
		_List root = null;
		Stack<_List> position = new Stack<_List>(); // Use this variable to tack the list position.
		for (String item : input) {
			switch (item) {
			case "(": // Start new list.
				if (root == null) {
					root = new _List();
					position.add(root);
				} else {
					position.push(new _List());
					root.add(position.lastElement());
				}
				break;
			case ")": // End list.
				position.pop(); // Back to parent.
				break;
			default: // List member.
				position.lastElement().add(TypeFactory.gen(item));
				break;
			}
		}
		return root;
	}
	
}
