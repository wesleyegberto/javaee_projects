package com.github.wesleyegberto.cditests.extensions;

import java.lang.annotation.Annotation;
import java.lang.reflect.Type;
import java.util.HashSet;
import java.util.Set;

import javax.enterprise.inject.spi.AnnotatedType;
import javax.enterprise.util.AnnotationLiteral;

import com.github.wesleyegberto.cditests.stereotypes.Controller;

@SuppressWarnings({ "rawtypes", "serial" })
public class AnnotatedTypeControllerWrapper implements AnnotatedType {
	private AnnotatedType wrapped;

	AnnotatedTypeControllerWrapper(AnnotatedType original) {
		this.wrapped = original;
	}

	@Override
	public Set<Annotation> getAnnotations() {
		Set<Annotation> annotations = new HashSet<>(wrapped.getAnnotations());
		annotations.add(new AnnotationLiteral<Controller>() {});
		return annotations;
	}

	@Override
	public Class getJavaClass() {
		return wrapped.getJavaClass();
	}

	@Override
	public Type getBaseType() {
		return wrapped.getBaseType();
	}

	@Override
	public Set<Type> getTypeClosure() {
		return wrapped.getTypeClosure();
	}

	@Override
	public <T extends Annotation> T getAnnotation(Class<T> annotationType) {
		return wrapped.getAnnotation(annotationType);
	}

	@Override
	public boolean isAnnotationPresent(Class<? extends Annotation> annotationType) {
		return wrapped.isAnnotationPresent(annotationType);
	}

	@Override
	public Set getConstructors() {
		return wrapped.getConstructors();
	}

	@Override
	public Set getMethods() {
		return wrapped.getMethods();
	}

	@Override
	public Set getFields() {
		return wrapped.getFields();
	}

}
