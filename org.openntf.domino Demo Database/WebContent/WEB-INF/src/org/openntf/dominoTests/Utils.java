package org.openntf.dominoTests;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

public class Utils {

	public Utils() {

	}

	public static void handleException(Throwable e) {
		FacesContext.getCurrentInstance().addMessage("", new FacesMessage(e.getLocalizedMessage()));
	}

}
