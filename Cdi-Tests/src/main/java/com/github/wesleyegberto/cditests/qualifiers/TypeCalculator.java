package com.github.wesleyegberto.cditests.qualifiers;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.PARAMETER;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.enterprise.util.Nonbinding;
import javax.inject.Qualifier;

@Target({TYPE, METHOD, FIELD, PARAMETER})
@Retention(RetentionPolicy.RUNTIME)
@Qualifier
public @interface TypeCalculator {
	// member used to specify the injection
	TypeCalculatorEnum type() default TypeCalculatorEnum.LOW;

	// we can use @Nonbinding to avoid the use of this member on injecting
	// use default to turn it non-required
	@Nonbinding String currency() default "BRL";
}
