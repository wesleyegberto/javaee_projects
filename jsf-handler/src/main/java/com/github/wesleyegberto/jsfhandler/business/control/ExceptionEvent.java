package com.github.wesleyegberto.jsfhandler.business.control;
import java.io.Serializable;

public class ExceptionEvent implements Serializable {
	private static final long serialVersionUID = -521780759702807935L;

	private Class<?> sourceClass;
	private Throwable exception;

	public ExceptionEvent(Class<?> sourceClass, Throwable exception) {
		this.sourceClass = sourceClass;
		this.exception = exception;
	}

	public Class<?> getSourceClass() {
		return sourceClass;
	}

	public Throwable getException() {
		return exception;
	}

	@Override
	public String toString() {
		return "ExceptionEvent [sourceClass=" + sourceClass + ", exception=" + exception + "]";
	}

}
