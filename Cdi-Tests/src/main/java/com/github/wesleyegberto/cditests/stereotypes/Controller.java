package com.github.wesleyegberto.cditests.stereotypes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.annotation.Priority;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Stereotype;
import javax.inject.Named;

@Stereotype
@Priority(value = javax.interceptor.Interceptor.Priority.APPLICATION)
@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.TYPE)
@Named
@RequestScoped
public @interface Controller {
}
