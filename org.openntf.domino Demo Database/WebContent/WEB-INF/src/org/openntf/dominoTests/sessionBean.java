package org.openntf.dominoTests;

import java.io.Serializable;

import javax.faces.context.FacesContext;

import org.openntf.domino.Database;
import org.openntf.domino.Document;
import org.openntf.domino.RichTextItem;
import org.openntf.domino.Session;
import org.openntf.domino.View;

import com.ibm.xsp.extlib.util.ExtLibUtil;

public class sessionBean implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public sessionBean() {

	}

	public boolean appendDocLinkTest() {
		boolean retVal_ = false;
		try {
			Session currSess = (Session) ExtLibUtil.resolveVariable(FacesContext.getCurrentInstance(), "opensession");
			Database currDb = currSess.getCurrentDatabase();
			View vwAllContacts = currDb.getView("AllContacts");
			Document firstDoc = vwAllContacts.getFirstDocument();
			Document secondDoc = vwAllContacts.getNextDocument(firstDoc);
			RichTextItem rtitem = secondDoc.createRichTextItem("Body");
			rtitem.appendText("This is a test");
			rtitem.appendDocLink(firstDoc);
			secondDoc.save(true, false);
			retVal_ = true;
		} catch (Throwable e) {
			Utils.handleException(e);
		}
		return retVal_;
	}

}
