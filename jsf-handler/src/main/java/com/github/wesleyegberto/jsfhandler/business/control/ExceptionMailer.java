package com.github.wesleyegberto.jsfhandler.business.control;

import javax.enterprise.event.Observes;

public class ExceptionMailer {
	public void handleException(@Observes ExceptionEvent event) {
		System.out.println("[Observer] Got an exception");
		System.out.println("[Observer] Mailing: " + event + " - " + event.getException().getMessage());
	}
}
