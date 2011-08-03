package org.java.util.debug.impl;

import java.lang.reflect.Field;

import org.java.util.debug.DebuggerDisplayEngine;
import org.java.util.debug.DebuggerDisplayEvaluator;

public class FieldEvaluator extends DebuggerDisplayEvaluator {
	private boolean cached = false;
	private Field field = null;

	@Override
	public Object evaluate(Object obj) throws Exception {
		if (!cached) {
			cached = true;
			field = getTargetType().getDeclaredField(getExpression());
			field.setAccessible(true);
		}
		return DebuggerDisplayEngine.display(field.get(obj));
	}
}
