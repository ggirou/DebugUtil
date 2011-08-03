/**
 * 
 */
package org.java.util.debug.impl;

import java.util.Arrays;
import java.util.List;

import org.java.util.debug.AbstractEvaluatorTest;
import org.java.util.debug.impl.FieldEvaluator;
import org.java.util.debug.util.ObjectFactory;
import org.java.util.debug.util.TestObject;
import org.junit.runners.Parameterized.Parameters;

/**
 * @author GGirou
 * 
 */
public class FieldEvaluatorTest extends AbstractEvaluatorTest {
	public FieldEvaluatorTest(FieldEvaluator evaluator, Object input, Object expected) {
		super(evaluator, input, expected);
	}

	@Parameters
	public static List<Object[]> data() {
		return Arrays.asList(new Object[][] { //
						{ ObjectFactory.createFieldEvaluator("privateField", TestObject.class), new TestObject(), TestObject.PRIVATE_FIELD_VALUE }, //
						{ ObjectFactory.createFieldEvaluator("protectedField", TestObject.class), new TestObject(), TestObject.PROTECTED_FIELD_VALUE }, //
						{ ObjectFactory.createFieldEvaluator("packageField", TestObject.class), new TestObject(), TestObject.PACKAGE_FIELD_VALUE }, //
						{ ObjectFactory.createFieldEvaluator("publicField", TestObject.class), new TestObject(), TestObject.PUBLIC_FIELD_VALUE }, //
						{ ObjectFactory.createFieldEvaluator("otherMethod", TestObject.class), new TestObject(),
								String.format("[Evaluation failed for %s %s]", TestObject.class, "otherMethod") }, //
						{ ObjectFactory.createFieldEvaluator("inexistentField", TestObject.class), new TestObject(),
								String.format("[Evaluation failed for %s %s]", TestObject.class, "inexistentField") }, //
				});
	}
}
