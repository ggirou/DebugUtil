package org.java.util.debug.impl;

import org.java.util.debug.DebuggerDisplayEvaluator;

public class StaticStringEvaluator extends DebuggerDisplayEvaluator {
	@Override
	public Object evaluate(Object obj) throws Exception {
		return getExpression();
	}
}
