package org.openntf.dominoTests;

import java.util.Map;

import org.openntf.domino.ext.Database.Events;
import org.openntf.domino.types.IDatabaseEvent;
import org.openntf.domino.types.IDatabaseListener;

import com.ibm.xsp.extlib.util.ExtLibUtil;

public class TestDocumentCreateListener implements IDatabaseListener {

	public boolean eventHappened(IDatabaseEvent event) {
		try {
			Integer docsCreated;
			Map<String, Object> appScope = ExtLibUtil.getApplicationScope();
			if (appScope.containsKey("docsCreatedCount")) {
				docsCreated = (Integer) appScope.get("docsCreatedCount");
				docsCreated += 1;
			} else {
				docsCreated = 1;
			}
			appScope.put("docsCreatedCount", docsCreated);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Events[] getEventTypes() {
		Events[] eventList = new Events[] { Events.AFTER_CREATE_DOCUMENT };
		return eventList;
	}

}
