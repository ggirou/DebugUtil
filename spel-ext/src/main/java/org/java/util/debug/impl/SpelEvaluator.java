package org.java.util.debug.impl;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Arrays;
import java.util.List;

import org.java.util.debug.DebuggerDisplayEngine;
import org.java.util.debug.DebuggerDisplayEvaluator;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.MethodFilter;
import org.springframework.expression.common.TemplateParserContext;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.expression.spel.support.ReflectiveMethodResolver;
import org.springframework.expression.spel.support.ReflectivePropertyAccessor;
import org.springframework.expression.spel.support.StandardEvaluationContext;
import org.springframework.util.StringUtils;

public class SpelEvaluator extends DebuggerDisplayEvaluator {
	private final class DeclaredMethodsFilter implements MethodFilter {
		private final List<Method> declaredMethods;

		public DeclaredMethodsFilter(Method[] declaredMethods) {
			this.declaredMethods = Arrays.asList(declaredMethods);
		}

		public List<Method> filter(List<Method> methods) {
			return declaredMethods;
		}
	}

	private final class AccessiblePropertyAccessor extends ReflectivePropertyAccessor {
		@Override
		protected Field findField(String name, Class<?> clazz, boolean mustBeStatic) {
			Field[] fields = clazz.getDeclaredFields();
			for (Field field : fields) {
				if (field.getName().equals(name) && (!mustBeStatic || Modifier.isStatic(field.getModifiers()))) {
					field.setAccessible(true);
					return field;
				}
			}
			return null;
		}

		@Override
		protected Method findGetterForProperty(String propertyName, Class<?> clazz, boolean mustBeStatic) {
			Method[] ms = clazz.getDeclaredMethods();
			// Try "get*" method...
			String getterName = "get" + StringUtils.capitalize(propertyName);
			for (Method method : ms) {
				if (method.getName().equals(getterName) && method.getParameterTypes().length == 0
						&& (!mustBeStatic || Modifier.isStatic(method.getModifiers()))) {
					method.setAccessible(true);
					return method;
				}
			}
			// Try "is*" method...
			getterName = "is" + StringUtils.capitalize(propertyName);
			for (Method method : ms) {
				if (method.getName().equals(getterName) && method.getParameterTypes().length == 0 && boolean.class.equals(method.getReturnType())
						&& (!mustBeStatic || Modifier.isStatic(method.getModifiers()))) {
					method.setAccessible(true);
					return method;
				}
			}
			return null;
		}
	}

	private boolean cached = false;
	private TemplateParserContext parserContext = new TemplateParserContext();
	private Expression parseExpression = null;
	private StandardEvaluationContext context = null;

	@Override
	public Object evaluate(Object obj) throws Exception {
		if (!cached) {
			cached = true;
			ExpressionParser parser = new SpelExpressionParser();
			parseExpression = parser.parseExpression(getExpression(), parserContext);
			context = new StandardEvaluationContext();
			ReflectiveMethodResolver methodResolver = new ReflectiveMethodResolver();
			methodResolver.registerMethodFilter(getTargetType(), new DeclaredMethodsFilter(getTargetType().getDeclaredMethods()));
			context.addMethodResolver(methodResolver);
			context.addPropertyAccessor(new AccessiblePropertyAccessor());
		}
		context.setRootObject(obj);
		return DebuggerDisplayEngine.display(parseExpression.getValue(context));
	}
}
