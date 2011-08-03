/**
 * 
 */
package org.java.util.debug;

import org.fest.assertions.Assertions;
import org.java.util.debug.annotation.DebuggerDisplay;
import org.junit.Test;

/**
 * @author GGirou
 * 
 */
public class DebuggerDisplayTest {
	@DebuggerDisplay(expression = "\"123abc\"")
	class StaticStringDisplay {

	}

	@Test
	public void shouldStaticStringDisplay() {
		// Runtime.getRuntime().exec("").toString()
		// ProcessBuilder processBuilder = new java.lang.ProcessBuilder();
		// processBuilder.
		// org.eclipse.
		// new EvaluationContext(null, defaultVariable)

		// INPUT
		StaticStringDisplay input = new StaticStringDisplay();
		DebuggerDisplay output = input.getClass().getAnnotation(DebuggerDisplay.class);

//		EvaluationService evaluationService = new EvaluationService();
//		IEvaluationContext currentState = evaluationService.getCurrentState();
//		EvaluationContext evaluationContext = new EvaluationContext(currentState, input);
//		evaluationContext.
//		new EvaluationResult()
//		evaluationService.C
		

		// CHECK
		Assertions.assertThat(output).isNotNull();
		Assertions.assertThat(output.expression()).isEqualTo("\"123abc\"");
	}

	@DebuggerDisplay(expression = "")
	class SimpleObject {

	}
}
