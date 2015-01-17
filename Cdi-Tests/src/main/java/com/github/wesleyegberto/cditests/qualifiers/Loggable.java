package com.github.wesleyegberto.cditests.qualifiers;

import static java.lang.annotation.ElementType.METHOD;
import static java.lang.annotation.ElementType.TYPE;

import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.inject.Qualifier;
import javax.interceptor.InterceptorBinding;

import static java.lang.annotation.ElementType.FIELD;
import static java.lang.annotation.ElementType.PARAMETER;

@Target({ TYPE, METHOD, FIELD, PARAMETER })
@Retention(RetentionPolicy.RUNTIME)
@Qualifier
@InterceptorBinding
public @interface Loggable {}
