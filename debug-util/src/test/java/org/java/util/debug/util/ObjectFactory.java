/**
 * 
 */
package org.java.util.debug.util;

import org.java.util.debug.Evaluator;
import org.java.util.debug.impl.DefaultBuilderEvaluator;
import org.java.util.debug.impl.FieldEvaluator;
import org.java.util.debug.impl.GetterEvaluator;
import org.java.util.debug.impl.StaticStringEvaluator;

/**
 * @author GGirou
 * 
 */
public final class ObjectFactory {
	public static DefaultBuilderEvaluator createDefaultBuilderEvaluator(String separator, Evaluator... evaluators) {
		DefaultBuilderEvaluator evaluator = new DefaultBuilderEvaluator();
		evaluator.setSeparator(separator);
		evaluator.setEvaluators(evaluators);
		return evaluator;
	}

	public static FieldEvaluator createFieldEvaluator(String expression, Class<?> targetType) {
		return createFieldEvaluator(expression, "", targetType);
	}

	public static FieldEvaluator createFieldEvaluator(String expression, String format, Class<?> targetType) {
		FieldEvaluator evaluator = new FieldEvaluator();
		evaluator.setExpression(expression);
		evaluator.setFormat(format);
		evaluator.setTargetType(targetType);
		return evaluator;
	}

	public static GetterEvaluator createGetterEvaluator(String expression, Class<?> targetType) {
		return createGetterEvaluator(expression, "", targetType);
	}

	public static GetterEvaluator createGetterEvaluator(String expression, String format, Class<?> targetType) {
		GetterEvaluator evaluator = new GetterEvaluator();
		evaluator.setExpression(expression);
		evaluator.setFormat(format);
		evaluator.setTargetType(targetType);
		return evaluator;
	}

	public static StaticStringEvaluator createStaticStringEvaluator(String expression) {
		return createStaticStringEvaluator(expression, "", null);
	}

	public static StaticStringEvaluator createStaticStringEvaluator(String expression, Class<?> targetType) {
		return createStaticStringEvaluator(expression, "", targetType);
	}

	public static StaticStringEvaluator createStaticStringEvaluator(String expression, String format, Class<?> targetType) {
		StaticStringEvaluator evaluator = new StaticStringEvaluator();
		evaluator.setExpression(expression);
		evaluator.setFormat(format);
		evaluator.setTargetType(targetType);
		return evaluator;
	}
}
