/**
 * 
 */
package org.java.util.debug.util;

import org.java.util.debug.impl.SpelEvaluator;

/**
 * @author GGirou
 * 
 */
public final class ObjectFactory {
	public static SpelEvaluator createSpringELEvaluator(String expression, Class<?> targetType) {
		return createSpringELEvaluator(expression, "%s", targetType);
	}

	public static SpelEvaluator createSpringELEvaluator(String expression, String format, Class<?> targetType) {
		SpelEvaluator evaluator = new SpelEvaluator();
		evaluator.setExpression(expression);
		evaluator.setFormat(format);
		evaluator.setTargetType(targetType);
		return evaluator;
	}
}
