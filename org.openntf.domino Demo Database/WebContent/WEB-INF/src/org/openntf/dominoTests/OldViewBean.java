package org.openntf.dominoTests;

import java.io.Serializable;

import lotus.domino.Database;
import lotus.domino.View;
import lotus.domino.ViewEntry;
import lotus.domino.ViewEntryCollection;

import com.ibm.xsp.extlib.util.ExtLibUtil;

public class OldViewBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public String getProcessView() {
		Database db = null;
		View view = null;
		ViewEntryCollection collection = null;
		ViewEntry currentEntry = null;
		ViewEntry nextEntry = null;
		StringBuilder sb = new StringBuilder();
		try {
			db = ExtLibUtil.getCurrentDatabase();
			view = db.getView("allStates");
			collection = view.getAllEntries();
			currentEntry = collection.getFirstEntry();
			while (currentEntry != null) {
				nextEntry = collection.getNextEntry(currentEntry);
				try {
					sb.append(currentEntry.getNoteID() + "<br/>"); // Do whatever it is you actually want to get done
				} catch (lotus.domino.NotesException ne1) {
					ne1.printStackTrace();
				} finally {
					currentEntry.recycle();
				}
				currentEntry = nextEntry;
			}
		} catch (lotus.domino.NotesException ne) {
			ne.printStackTrace();
		} finally {
			try {
				collection.recycle();
			} catch (lotus.domino.NotesException ne) {

			}
		}
		return sb.toString();
	}

}
