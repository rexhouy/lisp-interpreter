package com.rexhouy.executor;

import java.util.ArrayList;
import java.util.List;

import com.rexhouy.type.Type;
import com.rexhouy.type.basic._Function;
import com.rexhouy.type.basic._List;
import com.rexhouy.type.basic._Macro;
import com.rexhouy.type.basic._Variable;

/**
 * Not tested
 * 
 * @author rexhouy
 *
 */
public class TreeExecutor implements Executor {

	@Override
	public Type execute(_List code) {
		Node tree = list2Tree(code);
		return evaluate(tree);
	}
	
	Type evaluate(Node node) {
		if (node.value.type() == _Function.class) {
			Type[] args = node.children.stream().map((item) -> {
				return evaluate(item);
			}).toArray(Type[]::new);
			return (Type) node.value.evaluate(args);
		} else if (node.value.type() == _Macro.class) {
			Type[] args = node.children.stream().map((item) -> {
				return item.value;
			}).toArray(Type[]::new);
			return (Type) node.value.evaluate(args);
		} else if (node.value.type() == _Variable.class) {
			return (Type) node.value.evaluate();
		} else {
			return (Type) node.value;
		}
	}
	
	Node list2Tree(Type t) {
		Node root = new Node();
		if (t.type() == _List.class) {
			root.value = ((_List) t).car();
			for (Type item : ((_List) t).cdr()) {
				root.children.add(list2Tree(item));
			}
		} else {
			root.value = t;
		}
		return root;
	}

	class Node {
		Type value;
		List<Node> children = new ArrayList<TreeExecutor.Node>();
	}
	
}
