/**
 * 
 */
package org.java.util.debug;

import java.util.Locale;

import org.fest.assertions.Assertions;
import org.java.util.debug.Evaluator;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

/**
 * @author GGirou
 * 
 */
@RunWith(value = Parameterized.class)
public abstract class AbstractEvaluatorTest {
	protected Evaluator evaluator;
	protected Object input;
	protected Object expected;

	public AbstractEvaluatorTest(Evaluator evaluator, Object input, Object expected) {
		super();
		this.evaluator = evaluator;
		this.input = input;
		this.expected = expected;
	}

	@Before
	public void setUp() {
		// engine = new DebuggerDisplayEngine();
		Locale.setDefault(Locale.ENGLISH);
	}

	@Test
	public void ShouldEvaluate() throws Exception {
		// ACTION
		Object ouput = evaluator.display(input);

		// CHECK
		Assertions.assertThat(ouput).isEqualTo(expected);
	}
}
