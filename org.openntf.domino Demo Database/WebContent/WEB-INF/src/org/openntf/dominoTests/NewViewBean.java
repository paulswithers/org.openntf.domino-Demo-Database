package org.openntf.dominoTests;

import java.io.Serializable;

import javax.faces.context.FacesContext;

import org.openntf.domino.Database;
import org.openntf.domino.View;
import org.openntf.domino.ViewEntry;

import com.ibm.xsp.extlib.util.ExtLibUtil;

public class NewViewBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getProcessView() {
		StringBuilder sb = new StringBuilder();
		Database db = (Database) ExtLibUtil.resolveVariable(FacesContext.getCurrentInstance(), "opendatabase");
		View view = db.getView("allStates");
		for (ViewEntry entry : view.getAllEntries()) {
			sb.append(entry.getNoteID() + "<br/>"); // Do whatever it is you actually want to get done
		}
		return sb.toString();
	}

}
