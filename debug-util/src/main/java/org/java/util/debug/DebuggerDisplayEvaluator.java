/**
 * 
 */
package org.java.util.debug;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author GGirou
 * 
 */
public abstract class DebuggerDisplayEvaluator implements Evaluator {
	private static final Logger LOGGER = Logger.getLogger(DebuggerDisplayEvaluator.class.toString());

	private String expression;

	private String format;

	private Class<?> targetType;

	protected abstract Object evaluate(Object obj) throws Exception;

	public Object display(Object obj) {
		try {
			return format(evaluate(obj));
		} catch (Exception e) {
			String msg = String.format("[Evaluation failed for %s %s]", targetType, expression);
			LOGGER.log(Level.WARNING, msg + "\n" + e.toString());
			return msg;
		}
	}

	protected Object format(Object obj) {
		if (format == null || format.length() == 0) {
			return obj;
		} else {
			return String.format(format, obj);
		}
	}

	/**
	 * @return the expression
	 */
	public String getExpression() {
		return expression;
	}

	/**
	 * @param expression
	 *            the expression to set
	 */
	public void setExpression(String expression) {
		this.expression = expression;
	}

	/**
	 * @return the format
	 */
	public String getFormat() {
		return format;
	}

	/**
	 * @param format
	 *            the format to set
	 */
	public void setFormat(String format) {
		this.format = format;
	}

	/**
	 * @return the targetType
	 */
	public Class<?> getTargetType() {
		return targetType;
	}

	/**
	 * @param targetType
	 *            the targetType to set
	 */
	public void setTargetType(Class<?> targetType) {
		this.targetType = targetType;
	}
}
