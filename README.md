DebugUtils
==========

Java Annotations & Implementations that help you to define what YOU want to display in your favorite debugger.

IDE Configuration
-----------------

First, configure your IDE debugger to display your custom values.

### Eclipse
* Open the Eclipse's preferences : Window > Preferences
* Go to Java > Debug > Detail Formatters
* Add a new entry for the java.lang.Object and set the following snippet
	org.java.util.debug.DebuggerDisplayEngine.display(this);
* Set "As a label for all variables" for the "Show variables details" option

### NetBeans
TODO

### IntelliJ
TODO

Customize your display
----------------------

### Simples examples
```java
	import org.java.util.debug.annotation.DebuggerDisplay;
	import org.java.util.debug.impl.FieldEvaluator;
	import org.java.util.debug.impl.GetterEvaluator;
	import org.java.util.debug.impl.StaticStringEvaluator;

	// Will display the string "My first custom display" in the debugger
	@DebuggerDisplay(expression = "My first custom display", evaluatorClass = StaticStringEvaluator.class)
	public class YourObject {
		// ...
	}

	// Will display the value of the "anyField" field in the debugger
	@DebuggerDisplay(expression = "anyField", evaluatorClass = FieldEvaluator.class)
	public class YourObject {
		private String anyField;
		// ...
	}

	// Will display the value of the "anyGetter" getter in the debugger
	@DebuggerDisplay(expression = "anyGetter", evaluatorClass = GetterEvaluator.class)
	public class YourObject {
		// ...
		int getAnyGetter(){
			// ...
		}
	}
```

### Usual example
Will evaluate and concat the values
```java
	import org.java.util.debug.annotation.DebuggerDisplay;
	import org.java.util.debug.annotation.DebuggerDisplayBuilder;
	import org.java.util.debug.impl.FieldEvaluator;
	import org.java.util.debug.impl.GetterEvaluator;
	import org.java.util.debug.impl.StaticStringEvaluator;

	@DebuggerDisplayBuilder(values = {
		@DebuggerDisplay(expression = "privateField", evaluatorClass = FieldEvaluator.class)
		@DebuggerDisplay(expression = "protectedField", evaluatorClass = FieldEvaluator.class)
		@DebuggerDisplay(expression = "packageField", evaluatorClass = FieldEvaluator.class)
		@DebuggerDisplay(expression = "publicField", evaluatorClass = FieldEvaluator.class)
		@DebuggerDisplay(expression = "doubleField", evaluatorClass = FieldEvaluator.class, format = "%.2f")
		@DebuggerDisplay(expression = "dateField", evaluatorClass = FieldEvaluator.class, format = "%1$td/%1$tm/%1$tY")
		@DebuggerDisplay(expression = "privateField", evaluatorClass = GetterEvaluator.class)
		@DebuggerDisplay(expression = "protectedField", evaluatorClass = GetterEvaluator.class)
		@DebuggerDisplay(expression = "packageField", evaluatorClass = GetterEvaluator.class)
		@DebuggerDisplay(expression = "publicField", evaluatorClass = GetterEvaluator.class)
		@DebuggerDisplay(expression = "childTestObject", evaluatorClass = FieldEvaluator.class, format = "[%s]")
	}, separator = ", ")
	public class YourObject {
		// ...
	}
```


### Spring Expression Language (SpEl) example
```java
	import org.java.util.debug.annotation.DebuggerDisplay;
	import org.java.util.debug.impl.SpelEvaluator;

	@DebuggerDisplay(expression = "privateField=#{privateField}, protectedField=#{protectedField}", evaluatorClass = SpelEvaluator.class)
	public class YourObject {
		// ...
	}
```
