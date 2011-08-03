package org.java.util.debug.util;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

import org.java.util.debug.annotation.DebuggerDisplay;
import org.java.util.debug.impl.SpelEvaluator;

@DebuggerDisplay(expression = "privateField=#{privateField}, protectedField=#{protectedField}", evaluatorClass = SpelEvaluator.class)
public class TestObject {
	public static final String PRIVATE_FIELD_VALUE = "abc123";
	public static final String PROTECTED_FIELD_VALUE = "def456";
	public static final String PACKAGE_FIELD_VALUE = "ghi789";
	public static final boolean PUBLIC_FIELD_VALUE = true;
	public static final String OTHER_METHOD_VALUE = "otherMethod123";
	public static final String SUFFIX_METHOD_VALUE = "suffixMethod.";

	private String privateField = PRIVATE_FIELD_VALUE;
	protected String protectedField = PROTECTED_FIELD_VALUE;
	String packageField = PACKAGE_FIELD_VALUE;
	public boolean publicField = PUBLIC_FIELD_VALUE;
	public TestObject childTestObject;
	public double doubleField = 123.456d;
	public Date dateField = new GregorianCalendar(2011, Calendar.JUNE, 03).getTime();

	public TestObject() {
		super();
	}

	public TestObject(TestObject childTestObject) {
		super();
		this.childTestObject = childTestObject;
	}

	@SuppressWarnings("unused")
	private String getPrivateField() {
		return privateField;
	}

	protected String getProtectedField() {
		return protectedField;
	}

	String getPackageField() {
		return packageField;
	}

	public boolean isPublicField() {
		return publicField;
	}

	public String otherMethod() {
		return OTHER_METHOD_VALUE;
	}

	@SuppressWarnings("unused")
	private String privateSuffixMethod(int value) {
		return SUFFIX_METHOD_VALUE + value;
	}

	public String suffixMethod(int value) {
		return SUFFIX_METHOD_VALUE + value;
	}

	public int add(int a, int b) {
		return a + b;
	}
}
