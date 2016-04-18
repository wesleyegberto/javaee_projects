package com.github.wesleyegberto.jsfhandler.business.boundary;

public class NegocioException extends Exception {
	private static final long serialVersionUID = -1421882678879864139L;

	public NegocioException(String message, Throwable cause) {
		super(message, cause);
	}

	public NegocioException(String message) {
		super(message);
	}

	public NegocioException(Throwable cause) {
		super(cause);
	}

}
