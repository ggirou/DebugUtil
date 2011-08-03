/**
 * 
 */
package org.java.util.debug;

import java.lang.annotation.Annotation;
import java.util.Locale;

import org.fest.assertions.Assertions;
import org.java.util.debug.annotation.DebuggerDisplay;
import org.java.util.debug.annotation.DebuggerDisplayBuilder;
import org.java.util.debug.util.TestObject;
import org.junit.Before;
import org.junit.Test;

/**
 * @author GGirou
 * 
 */
public class DebuggerDisplayEngineTest {
	// DebuggerDisplayEngine engine;

	@Before
	public void setUp() {
		// engine = new DebuggerDisplayEngine();
		Locale.setDefault(Locale.ENGLISH);
	}

	public static DebuggerDisplay createDebuggerDisplay(final String value, final Class<? extends DebuggerDisplayEvaluator> evaluatorClass) {
		return createDebuggerDisplay(value, evaluatorClass, "%s");
	}

	public static DebuggerDisplay createDebuggerDisplay(final String value, final Class<? extends DebuggerDisplayEvaluator> evaluatorClass, final String format) {
		return createDebuggerDisplay(DebuggerDisplay.class, value, evaluatorClass, format);
	}

	public static DebuggerDisplay createDebuggerDisplay(final Class<? extends Annotation> annotationType, final String value,
			final Class<? extends DebuggerDisplayEvaluator> evaluatorClass, final String format) {
		return new DebuggerDisplay() {
			public Class<? extends Annotation> annotationType() {
				return annotationType;
			}

			public String expression() {
				return value;
			}

			public String format() {
				return format;
			}

			public Class<? extends DebuggerDisplayEvaluator> evaluatorClass() {
				return evaluatorClass;
			}
		};
	}

	public static DebuggerDisplayBuilder createDebuggerDisplayBuilder(final DebuggerDisplay[] values) {
		return createDebuggerDisplayBuilder(values, "");
	}

	public static DebuggerDisplayBuilder createDebuggerDisplayBuilder(final DebuggerDisplay[] values, final String separator) {
		return createDebuggerDisplayBuilder(DebuggerDisplayBuilder.class, values, separator);
	}

	public static DebuggerDisplayBuilder createDebuggerDisplayBuilder(final Class<? extends Annotation> annotationType, final DebuggerDisplay[] values,
			final String separator) {
		return new DebuggerDisplayBuilder() {
			public Class<? extends Annotation> annotationType() {
				return annotationType;
			}

			public DebuggerDisplay[] values() {
				return values;
			}

			public String separator() {
				return separator;
			}
		};
	}

	@Test
	public void shouldReturnNull() throws Exception {
		// SETUP
		Object obj = null;

		// ACTION
		Object ouput = DebuggerDisplayEngine.display(obj);

		// CHECK
		Assertions.assertThat(ouput).isNull();
	}

	@Test
	public void shouldReturnToString() throws Exception {
		// SETUP
		Object obj = new Object();

		// ACTION
		Object ouput = DebuggerDisplayEngine.display(obj);

		// CHECK
		Assertions.assertThat(ouput).isEqualTo(obj);
	}

	@Test
	public void shouldDisplayObjectValues() throws Exception {
		// SETUP
		TestObject input = new TestObject(new TestObject());

		// ACTION
		Object ouput = DebuggerDisplayEngine.display(input);

		// CHECK
		Assertions
				.assertThat(ouput)
				.isEqualTo(
						"Toto, abc123, def456, ghi789, true, 123.46, 03/06/2011, abc123, def456, ghi789, true, [Toto, abc123, def456, ghi789, true, 123.46, 03/06/2011, abc123, def456, ghi789, true, [null]]");
	}
}
