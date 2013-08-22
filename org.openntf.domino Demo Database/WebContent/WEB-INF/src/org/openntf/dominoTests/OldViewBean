package org.openntf.dominoTests;

/*
 	Copyright 2013 Paul Withers Licensed under the Apache License, Version 2.0
	(the "License"); you may not use this file except in compliance with the
	License. You may obtain a copy of the License at

	http://www.apache.org/licenses/LICENSE-2.0 Unless required by applicable law
	or agreed to in writing, software distributed under the License is distributed
	on an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either
	express or implied. See the License for the specific language governing
	permissions and limitations under the License
	
*/

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

	public void processView() {
		Database db = null;
		View view = null;
		ViewEntryCollection collection = null;
		ViewEntry currentEntry = null;
		ViewEntry nextEntry = null;
		StringBuilder sb = new StringBuilder();
		try {
			db = ExtLibUtil.getCurrentDatabase();
			view = db.getView("allStates");
			view.setAutoUpdate(false);
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
		ExtLibUtil.getViewScope().put("javaTest", sb.toString());
	}

}
