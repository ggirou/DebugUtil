/**
 * 
 */
package org.java.util.debug.impl;

import org.java.util.debug.DebuggerDisplayBuilderEvaluator;
import org.java.util.debug.Evaluator;

/**
 * @author GGirou
 * 
 */
public class DefaultBuilderEvaluator extends DebuggerDisplayBuilderEvaluator {
	/*
	 * (non-Javadoc)
	 * 
	 * @see
	 * org.java.util.debug.DebuggerDisplayBuilderEvaluator#evaluate(java.lang
	 * .Object)
	 */
	@Override
	public Object display(Object obj) {
		boolean separate = false;
		StringBuilder s = new StringBuilder();
		for (Evaluator evaluator : getEvaluators()) {
			if (separate) {
				s.append(getSeparator());
			} else {
				separate = true;
			}
			s.append(evaluator.display(obj));
		}
		return s.toString();
	}
}
