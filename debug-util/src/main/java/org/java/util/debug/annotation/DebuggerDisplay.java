/**
 * 
 */
package org.java.util.debug.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import org.java.util.debug.DebuggerDisplayEvaluator;
import org.java.util.debug.impl.StaticStringEvaluator;

/**
 * @author GGirou
 * 
 */
@Inherited
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface DebuggerDisplay {
	String expression();

	String format() default "";

	Class<? extends DebuggerDisplayEvaluator> evaluatorClass() default StaticStringEvaluator.class;
}
