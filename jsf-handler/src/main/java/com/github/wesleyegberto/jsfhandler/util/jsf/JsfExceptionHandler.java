package com.github.wesleyegberto.jsfhandler.util.jsf;

import java.io.IOException;
import java.util.Iterator;

import javax.enterprise.event.Event;
import javax.faces.FacesException;
import javax.faces.context.ExceptionHandler;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.inject.Inject;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import com.github.wesleyegberto.jsfhandler.business.control.ExceptionEvent;

public class JsfExceptionHandler extends ExceptionHandlerWrapper {

	private ExceptionHandler wrapped;
	private Log logger = LogFactory.getLog(JsfExceptionHandler.class);
	@Inject
	private Event<ExceptionEvent> eventFirer;
	
	public JsfExceptionHandler(ExceptionHandler wrapped) {
		this.wrapped = wrapped;
	}

	@Override
	public ExceptionHandler getWrapped() {
		return wrapped;
	}

	@Override
	public void handle() throws FacesException {
		logger.info("JsfExceptionHandler.handle");
		Iterator<ExceptionQueuedEvent> iterator = getUnhandledExceptionQueuedEvents().iterator();
		ExceptionQueuedEvent event = null;
		Throwable exception = null;
		boolean handled = false;

		while (iterator.hasNext()) {
			handled = false;
			event = iterator.next();
			exception = getExceptionToHandle(event.getContext().getException());
			
			if (exception instanceof NumberFormatException) {
				logger.warn("NumberFormatException handled");
				handled = true;
			} else if (exception instanceof NullPointerException) {
				logger.warn("NumberFormatException handled");
				handled = true;
			}

			if (handled) {
				iterator.remove();
				eventFirer.fire(new ExceptionEvent(event.getSource().getClass(), exception));
				redirect("/error.xhtml");
			}
		}
		getWrapped().handle();
	}

	private Exception getExceptionToHandle(Throwable exception) {
		if (exception instanceof NumberFormatException || exception instanceof NullPointerException) {
			return (Exception) exception;
		} else if (exception.getCause() != null) {
			return getExceptionToHandle(exception.getCause());
		}
		return null;
	}

	private void redirect(String page) {
		try {
			FacesContext facesContext = FacesContext.getCurrentInstance();
			ExternalContext externalContext = facesContext.getExternalContext();
			final String contextPath = externalContext.getRequestContextPath();

			externalContext.redirect(contextPath + page);
			facesContext.responseComplete();
		} catch (IOException e) {
			throw new FacesException(e);
		}
	}
}
