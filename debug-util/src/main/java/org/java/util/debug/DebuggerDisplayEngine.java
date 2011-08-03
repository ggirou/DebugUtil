/**
 * 
 */
package org.java.util.debug;

import java.util.HashMap;
import java.util.Map;

import org.java.util.debug.annotation.DebuggerDisplay;
import org.java.util.debug.annotation.DebuggerDisplayBuilder;
import org.java.util.debug.impl.DefaultBuilderEvaluator;

/**
 * @author GGirou
 * 
 */
public class DebuggerDisplayEngine {
	private static Map<Class<?>, Evaluator> cacheEvaluators = new HashMap<Class<?>, Evaluator>();

	public static Object display(Object obj) {
		DebuggerDisplayBuilder displayBuilder;
		DebuggerDisplay display;
		if (obj == null) {
			return null;
		} else {
			Class<? extends Object> objClass = obj.getClass();
			Evaluator evaluator = null;
			if (!cacheEvaluators.containsKey(objClass)) {
				if ((displayBuilder = objClass.getAnnotation(DebuggerDisplayBuilder.class)) != null) {
					evaluator = instanciateEvaluator(displayBuilder, objClass);
				} else if ((display = objClass.getAnnotation(DebuggerDisplay.class)) != null) {
					evaluator = instanciateEvaluator(display, objClass);
				}
				cacheEvaluators.put(objClass, evaluator);
			} else {
				evaluator = cacheEvaluators.get(objClass);
			}

			if (evaluator == null) {
				return obj;
			} else {
				return evaluator.display(obj);
			}
		}
	}

	protected static Evaluator instanciateEvaluator(DebuggerDisplayBuilder displayBuilder, Class<?> targetClass) {
		DefaultBuilderEvaluator evaluator = new DefaultBuilderEvaluator();
		evaluator.setSeparator(displayBuilder.separator());
		evaluator.setEvaluators(instanciateEvaluator(displayBuilder.values(), targetClass));
		return evaluator;
	}

	protected static Evaluator[] instanciateEvaluator(DebuggerDisplay[] displays, Class<?> targetClass) {
		Evaluator[] evaluators = new Evaluator[displays.length];
		for (int i = 0; i < displays.length; i++) {
			evaluators[i] = instanciateEvaluator(displays[i], targetClass);
		}
		return evaluators;
	}

	protected static Evaluator instanciateEvaluator(DebuggerDisplay display, Class<?> targetClass) {
		DebuggerDisplayEvaluator evaluator;
		try {
			evaluator = display.evaluatorClass().getConstructor().newInstance();
		} catch (Exception e) {
			evaluator = new ErrorEvaluator();
		}
		evaluator.setExpression(display.expression());
		evaluator.setFormat(display.format());
		evaluator.setTargetType(targetClass);
		return evaluator;
	}
}
