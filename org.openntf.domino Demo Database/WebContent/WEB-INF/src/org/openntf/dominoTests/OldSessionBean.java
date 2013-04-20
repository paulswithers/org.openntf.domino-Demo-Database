package org.openntf.dominoTests;

import java.io.Serializable;
import java.util.Date;

import lotus.domino.DateTime;
import lotus.domino.NotesException;
import lotus.domino.Session;

import com.ibm.xsp.extlib.util.ExtLibUtil;

public class OldSessionBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OldSessionBean() {

	}

	public void runDateTests() {
		try {
			Session s = ExtLibUtil.getCurrentSession();
			Date d = new Date();
			DateTime dt1 = s.createDateTime(d);
			DateTime dt2 = s.createDateTime(d);
			Date dt1j = dt1.toJavaDate();
			Date dt2j = dt2.toJavaDate();
			if (dt1j.before(dt2j)) {
				System.out.println("Date 1 before Date 2");
			} else {
				System.out.println("Date 1 not before Date 2");
			}
			dt1.recycle();
			dt2.recycle();
		} catch (NotesException ne) {
			//doSomething
		}
	}

}
