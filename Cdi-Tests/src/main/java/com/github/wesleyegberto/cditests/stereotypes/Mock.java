package com.github.wesleyegberto.cditests.stereotypes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.annotation.Priority;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Stereotype;

@Stereotype
@Alternative
@Priority(value = javax.interceptor.Interceptor.Priority.APPLICATION)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
public @interface Mock {
}
