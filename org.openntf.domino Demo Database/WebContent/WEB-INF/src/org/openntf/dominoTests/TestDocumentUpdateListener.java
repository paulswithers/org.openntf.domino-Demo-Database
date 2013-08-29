package org.openntf.dominoTests;

import java.util.Map;

import org.openntf.domino.ext.Database.Events;
import org.openntf.domino.types.IDatabaseEvent;
import org.openntf.domino.types.IDatabaseListener;

import com.ibm.xsp.extlib.util.ExtLibUtil;

public class TestDocumentUpdateListener implements IDatabaseListener {

	public boolean eventHappened(IDatabaseEvent event) {
		try {
			Integer docsUpdated;
			Map<String, Object> appScope = ExtLibUtil.getApplicationScope();
			if (appScope.containsKey("docsUpdatedCount")) {
				docsUpdated = (Integer) appScope.get("docsUpdatedCount");
				docsUpdated += 1;
			} else {
				docsUpdated = 1;
			}
			appScope.put("docsUpdatedCount", docsUpdated);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

	public Events[] getEventTypes() {
		Events[] eventList = new Events[] { Events.AFTER_UPDATE_DOCUMENT };
		return eventList;
	}

}
