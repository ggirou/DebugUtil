/**
 * 
 */
package org.java.util.debug.impl;

import java.util.Arrays;
import java.util.List;

import org.java.util.debug.AbstractEvaluatorTest;
import org.java.util.debug.impl.DefaultBuilderEvaluator;
import org.java.util.debug.util.ObjectFactory;
import org.java.util.debug.util.TestObject;
import org.junit.runners.Parameterized.Parameters;

/**
 * @author GGirou
 * 
 */
public class DefaultBuilderEvaluatorTest extends AbstractEvaluatorTest {
	public DefaultBuilderEvaluatorTest(DefaultBuilderEvaluator evaluator, Object input, Object expected) {
		super(evaluator, input, expected);
	}

	@Parameters
	public static List<Object[]> data() {
		Object[][] data = new Object[][] { //
		{ ObjectFactory.createDefaultBuilderEvaluator(""), new TestObject(), "" }, //
				{ ObjectFactory.createDefaultBuilderEvaluator(", "), new TestObject(), "" }, //
				{ ObjectFactory.createDefaultBuilderEvaluator(", ", //
						ObjectFactory.createStaticStringEvaluator("123")), //
						new TestObject(), "123" }, //
				{ ObjectFactory.createDefaultBuilderEvaluator(", ", //
						ObjectFactory.createStaticStringEvaluator("123"), //
						ObjectFactory.createStaticStringEvaluator("456")), //
						new TestObject(), "123, 456" }, //
				{ ObjectFactory.createDefaultBuilderEvaluator("++", //
						ObjectFactory.createStaticStringEvaluator("123"), //
						ObjectFactory.createStaticStringEvaluator("456"), //
						ObjectFactory.createStaticStringEvaluator("789")), //
						new TestObject(), "123++456++789" }, //
		};
		return Arrays.asList(data);
	}
}
