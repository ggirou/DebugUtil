/**
 * 
 */
package org.java.util.debug;

/**
 * @author GGirou
 * 
 */
public abstract class DebuggerDisplayBuilderEvaluator implements Evaluator {
	private String separator;
	private Evaluator[] evaluators;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.java.util.debug.Evaluator#evaluate(java.lang.Object)
	 */
	public abstract Object display(Object obj);

	/**
	 * @return the separator
	 */
	public String getSeparator() {
		return separator;
	}

	/**
	 * @param separator
	 *            the separator to set
	 */
	public void setSeparator(String separator) {
		this.separator = separator;
	}

	/**
	 * @return the evaluators
	 */
	public Evaluator[] getEvaluators() {
		return evaluators;
	}

	/**
	 * @param evaluators
	 *            the evaluators to set
	 */
	public void setEvaluators(Evaluator[] evaluators) {
		this.evaluators = evaluators;
	}
}
