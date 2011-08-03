/**
 * 
 */
package org.java.util.debug.impl;

import java.util.Arrays;
import java.util.List;

import org.java.util.debug.AbstractEvaluatorTest;
import org.java.util.debug.impl.GetterEvaluator;
import org.java.util.debug.util.ObjectFactory;
import org.java.util.debug.util.TestObject;
import org.junit.runners.Parameterized.Parameters;

/**
 * @author GGirou
 * 
 */
public class GetterEvaluatorTest extends AbstractEvaluatorTest {
	public GetterEvaluatorTest(GetterEvaluator evaluator, Object input, Object expected) {
		super(evaluator, input, expected);
	}

	@Parameters
	public static List<Object[]> data() {
		return Arrays.asList(new Object[][] { //
						{ ObjectFactory.createGetterEvaluator("privateField", TestObject.class), new TestObject(), TestObject.PRIVATE_FIELD_VALUE }, //
						{ ObjectFactory.createGetterEvaluator("protectedField", TestObject.class), new TestObject(), TestObject.PROTECTED_FIELD_VALUE }, //
						{ ObjectFactory.createGetterEvaluator("packageField", TestObject.class), new TestObject(), TestObject.PACKAGE_FIELD_VALUE }, //
						{ ObjectFactory.createGetterEvaluator("publicField", TestObject.class), new TestObject(), TestObject.PUBLIC_FIELD_VALUE }, //
						{ ObjectFactory.createGetterEvaluator("publicField", "Test=%s", TestObject.class), new TestObject(),
								"Test=" + TestObject.PUBLIC_FIELD_VALUE }, //
						{ ObjectFactory.createGetterEvaluator("otherMethod", TestObject.class), new TestObject(), TestObject.OTHER_METHOD_VALUE }, //
						{ ObjectFactory.createGetterEvaluator("suffixMethod", TestObject.class), new TestObject(),
								String.format("[Evaluation failed for %s %s]", TestObject.class, "suffixMethod") }, //
						{ ObjectFactory.createGetterEvaluator("add", TestObject.class), new TestObject(),
								String.format("[Evaluation failed for %s %s]", TestObject.class, "add") }, //
						{ ObjectFactory.createGetterEvaluator("inexistentGetter", TestObject.class), new TestObject(),
								String.format("[Evaluation failed for %s %s]", TestObject.class, "inexistentGetter") }, //
				});
	}
}
