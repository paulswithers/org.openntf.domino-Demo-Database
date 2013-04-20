package org.openntf.dominoTests;

import java.io.Serializable;
import java.util.Date;

import org.openntf.domino.DateTime;
import org.openntf.domino.Session;
import org.openntf.domino.utils.Factory;

import com.ibm.xsp.extlib.util.ExtLibUtil;

public class SessionBean implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public SessionBean() {

	}

	public void runDateTimes() {
		Session s = Factory.fromLotus(ExtLibUtil.getCurrentSession(), org.openntf.domino.Session.class, null);
		Date d = new Date();
		DateTime dt = s.createDateTime(d);
		DateTime dt2 = s.createDateTime(d);
		StringBuilder sb = new StringBuilder();
		sb.append(Utils.doChecks(dt, dt2));
		sb.append("<br/><br/>");
		dt.adjustHour(1);
		sb.append(Utils.doChecks(dt, dt2));
		sb.append("<br/><br/>");
		dt.adjustDay(-1);
		sb.append(Utils.doChecks(dt, dt2));
		sb.append("<br/><br/>");
		dt.adjustHour(-1);
		sb.append(Utils.doChecks(dt, dt2));
		ExtLibUtil.getViewScope().put("datesTestJava", sb.toString());
	}
}
