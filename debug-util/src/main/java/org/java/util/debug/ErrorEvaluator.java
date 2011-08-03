/**
 * 
 */
package org.java.util.debug;

/**
 * @author GGirou
 * 
 */
class ErrorEvaluator extends DebuggerDisplayEvaluator {
//	public ErrorEvaluator(String expression, String format, Class<?> targetType) {
//		setExpression(expression);
//		setFormat(format);
//		setTargetType(targetType);
//	}

	@Override
	public Object evaluate(Object obj) throws Exception {
		return String.format("[Evaluation failed for %s %s]", getTargetType(), getExpression());
	}
}
