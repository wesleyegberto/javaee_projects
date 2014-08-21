package com.github.wesleyegberto.toyshop.presentation.jsf;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class FacesUtil {

	private FacesUtil() {
	}

	public static void addInfoMessage(String message) {
		FacesMessage faceMsg = new FacesMessage(FacesMessage.SEVERITY_INFO, message, null);
		FacesContext.getCurrentInstance().addMessage(null, faceMsg);
	}
	
	public static void addWarnMessage(String message) {
		FacesMessage faceMsg = new FacesMessage(FacesMessage.SEVERITY_WARN, message, null);
		FacesContext.getCurrentInstance().addMessage(null, faceMsg);
	}

	public static void addErrorMessage(String message) {
		FacesMessage faceMsg = new FacesMessage(FacesMessage.SEVERITY_ERROR, message, null);
		FacesContext.getCurrentInstance().addMessage(null, faceMsg);
	}
}
