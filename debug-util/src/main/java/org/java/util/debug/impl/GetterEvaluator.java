package org.java.util.debug.impl;

import java.lang.reflect.Method;

import org.java.util.debug.DebuggerDisplayEngine;
import org.java.util.debug.DebuggerDisplayEvaluator;

public class GetterEvaluator extends DebuggerDisplayEvaluator {
	private boolean cached = false;
	private Method method = null;

	@Override
	public Object evaluate(Object obj) throws Exception {
		String name = getExpression();
		if (!cached) {
			cached = true;
			method = getGetter(obj, name);
			if (method != null) {
				method.setAccessible(true);
			} else {
				throw new NoSuchMethodException(obj.getClass().getName() + "." + name);
			}
		}
		return DebuggerDisplayEngine.display(method.invoke(obj));
	}

	protected Method getMethod(Object obj, String name) {
		try {
			return obj.getClass().getDeclaredMethod(name);
		} catch (Exception e) {
			return null;
		}
	}

	protected Method getGetter(Object obj, String name) {
		String capitalizedName = name.substring(0, 1).toUpperCase() + name.substring(1);
		Method method;
		if ((method = getMethod(obj, name)) != null //
				|| (method = getMethod(obj, "get" + capitalizedName)) != null //
				|| (method = getMethod(obj, "is" + capitalizedName)) != null) {
			return method;
		} else {
			return null;
		}
	}
}
