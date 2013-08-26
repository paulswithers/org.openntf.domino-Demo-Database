package org.openntf.dominoTests;

import java.io.Serializable;

import org.openntf.domino.Database;
import org.openntf.domino.DocumentCollection;
import org.openntf.domino.NoteCollection;
import org.openntf.domino.Session;
import org.openntf.domino.View;
import org.openntf.domino.helpers.DocumentSyncHelper;
import org.openntf.domino.utils.Factory;

public class NewHelperBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public NewHelperBean() {

	}

	public void syncDatabases() {
		Session s = Factory.getSession();
		Database currDb = s.getCurrentDatabase();
		java.util.Map<Object, String> syncMap = new java.util.HashMap<Object, String>();
		syncMap.put("Key", "State");
		syncMap.put("Name", "StateName");
		syncMap.put("@Now", "LastSync");
		DocumentSyncHelper helper = new DocumentSyncHelper(DocumentSyncHelper.Strategy.CREATE_AND_REPLACE, syncMap,
				currDb.getServer(), currDb.getFilePath(), "AllContactsByState", "State");
		View states = currDb.getView("AllStates");
		View allThreads = currDb.getView("AllThreadsByAuthor");
		DocumentCollection result = allThreads.getAllDocumentsByKey("BLAHNO", true);
		NoteCollection nc = currDb.createNoteCollection(false);
		nc.setSelectDocuments(true);
		nc.setSelectionFormula(states.getSelectionFormula());
		nc.buildCollection();
		int[] nids = nc.getNoteIDs();
		for (int nid : nids) {
			result.merge(nid);
		}
		//DocumentCollection sourceCollection = states.getAllDocuments();
		System.out.println(result.getCount());
		helper.process(result);
	}
}
