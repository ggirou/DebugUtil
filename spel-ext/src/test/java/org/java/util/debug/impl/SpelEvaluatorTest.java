/**
 * 
 */
package org.java.util.debug.impl;

import java.util.Arrays;
import java.util.List;

import org.java.util.debug.AbstractEvaluatorTest;
import org.java.util.debug.impl.SpelEvaluator;
import org.java.util.debug.util.ObjectFactory;
import org.java.util.debug.util.TestObject;
import org.junit.runners.Parameterized.Parameters;

/**
 * @author GGirou
 * 
 */
public class SpelEvaluatorTest extends AbstractEvaluatorTest {
	public SpelEvaluatorTest(SpelEvaluator evaluator, Object input, Object expected) {
		super(evaluator, input, expected);
	}

	@Parameters
	public static List<Object[]> data() {
		SpelEvaluator reusedEvaluator = ObjectFactory.createSpringELEvaluator("123 #{publicField} 456", TestObject.class);
		TestObject testObject = new TestObject();
		testObject.publicField = false;
		return Arrays.asList(new Object[][] { //
						{ ObjectFactory.createSpringELEvaluator("123 #{privateField} 456", TestObject.class), new TestObject(),
								"123 " + TestObject.PRIVATE_FIELD_VALUE + " 456" }, //
						{ ObjectFactory.createSpringELEvaluator("123 #{protectedField} 456", TestObject.class), new TestObject(),
								"123 " + TestObject.PROTECTED_FIELD_VALUE + " 456" }, //
						{ ObjectFactory.createSpringELEvaluator("123 #{packageField} 456", TestObject.class), new TestObject(),
								"123 " + TestObject.PACKAGE_FIELD_VALUE + " 456" }, //
						{ reusedEvaluator, new TestObject(), "123 " + Boolean.toString(TestObject.PUBLIC_FIELD_VALUE) + " 456" }, //
						{ reusedEvaluator, testObject, "123 " + Boolean.FALSE + " 456" }, //
						{ ObjectFactory.createSpringELEvaluator("123 #{otherMethod()} 456", TestObject.class), new TestObject(),
								"123 " + TestObject.OTHER_METHOD_VALUE + " 456" }, //
						{ ObjectFactory.createSpringELEvaluator("123 #{suffixMethod(456)} 789", TestObject.class), new TestObject(),
								"123 " + TestObject.SUFFIX_METHOD_VALUE + "456 789" }, //
						{ ObjectFactory.createSpringELEvaluator("123 #{privateSuffixMethod(456)} 789", TestObject.class), new TestObject(),
								"123 " + TestObject.SUFFIX_METHOD_VALUE + "456 789" }, //
						{ ObjectFactory.createSpringELEvaluator("123 #{add(7,8)} 789", TestObject.class), new TestObject(), "123 15 789" }, //
				});
	}
}
