/**
 * 
 */
package org.java.util.debug.impl;

import java.util.Arrays;
import java.util.List;

import org.java.util.debug.AbstractEvaluatorTest;
import org.java.util.debug.impl.StaticStringEvaluator;
import org.java.util.debug.util.ObjectFactory;
import org.java.util.debug.util.TestObject;
import org.junit.runners.Parameterized.Parameters;

/**
 * @author GGirou
 * 
 */
public class StaticStringEvaluatorTest extends AbstractEvaluatorTest {
	private final static String staticString = "static String";

	public StaticStringEvaluatorTest(StaticStringEvaluator evaluator, Object input, Object expected) {
		super(evaluator, input, expected);
	}

	@Parameters
	public static List<Object[]> data() {
		return Arrays.asList(new Object[][] { //
				{ ObjectFactory.createStaticStringEvaluator(staticString, TestObject.class), new TestObject(), staticString }, //
				});
	}
}
