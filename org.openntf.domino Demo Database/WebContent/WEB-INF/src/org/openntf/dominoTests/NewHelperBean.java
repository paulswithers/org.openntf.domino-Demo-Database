package org.openntf.dominoTests;

import java.io.Serializable;

import org.openntf.domino.Database;
import org.openntf.domino.DocumentCollection;
import org.openntf.domino.Session;
import org.openntf.domino.View;
import org.openntf.domino.email.DominoEmail;
import org.openntf.domino.helpers.DocumentSyncHelper;
import org.openntf.domino.utils.Factory;

import com.ibm.xsp.extlib.util.ExtLibUtil;

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
		Utils.addAllListeners(currDb);
		java.util.Map<Object, String> syncMap = new java.util.HashMap<Object, String>();
		syncMap.put("Key", "State");
		syncMap.put("Name", "StateName");
		syncMap.put("@Now", "LastSync");
		DocumentSyncHelper helper = new DocumentSyncHelper(DocumentSyncHelper.Strategy.CREATE_AND_REPLACE, syncMap,
				currDb.getServer(), currDb.getFilePath(), "AllContactsByState", "Key");
		View states = currDb.getView("AllStates");
		DocumentCollection sourceCollection = states.getAllDocuments();
		helper.process(sourceCollection);
		ExtLibUtil.getViewScope().put("javaTest", "Done");
	}

	public void sendSimpleEmail() {
		DominoEmail myEmail = new DominoEmail();
		myEmail.createSimpleEmail("pwithers@intec.co.uk", "", "", "OpenNTF Domino API Email",
				"this is an email from the OpenNTF Domino API", "");
	}
}
