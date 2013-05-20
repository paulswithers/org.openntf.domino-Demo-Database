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

import java.util.Date;
import java.util.LinkedList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import name.fraser.neil.plaintext.diff_match_patch;
import name.fraser.neil.plaintext.diff_match_patch.Diff;

import org.openntf.domino.Database;
import org.openntf.domino.DateTime;
import org.openntf.domino.Document;
import org.openntf.domino.Session;
import org.openntf.domino.View;

import com.ibm.xsp.extlib.util.ExtLibUtil;

public class Utils {

	public Utils() {

	}

	public static String doChecks(DateTime dt1, DateTime dt2) {
		StringBuilder s = new StringBuilder();
		s.append("Comparing Date 1 " + dt1.toJavaDate().toString() + " and Date 2 " + dt2.toJavaDate().toString()
				+ "<br/>");
		if (dt1.isBefore(dt2)) {
			s.append("Date 1 before Date 2");
		} else {
			s.append("Date 1 not before Date 2");
		}
		s.append("<br/>");
		if (dt1.isAfter(dt2)) {
			s.append("Date 1 after Date 2");
		} else {
			s.append("Date 1 not after Date 2");
		}
		s.append("<br/>");
		if (dt1.equals(dt2)) {
			s.append("Date 1 equals Date 2");
		} else {
			s.append("Date 1 not equal to Date 2");
		}
		s.append("<br/>");
		if (dt1.equalsIgnoreDate(dt2)) {
			s.append("Date 1 same time as Date 2");
		} else {
			s.append("Date 1 not same time as Date 2");
		}
		s.append("<br/>");
		if (dt1.equalsIgnoreTime(dt2)) {
			s.append("Date 1 same date as Date 2");
		} else {
			s.append("Date 1 not same date as Date 2");
		}
		return s.toString();
	}

	public static String runComparison(String s1, String s2) {
		s1 = s1.replace("<br/>", "\n");
		s2 = s2.replace("<br/>", "\n");
		diff_match_patch dmp = new diff_match_patch();
		LinkedList<Diff> ll = dmp.diff_main(s1, s2);
		dmp.diff_cleanupEfficiency(ll);
		return dmp.diff_prettyHtml(ll);
	}

	public static void handleException(Throwable e) {
		FacesContext.getCurrentInstance().addMessage("", new FacesMessage(e.getLocalizedMessage()));
	}

	public static void performanceTest() {
		try {
			String retVal = "";
			Date date = new Date();
			retVal += "<br/>Starting standard version..." + date.toString();
			lotus.domino.View origContactsView = ExtLibUtil.getCurrentDatabase().getView("AllContacts");
			lotus.domino.Document contact = origContactsView.getFirstDocument();
			while (contact != null) {
				lotus.domino.DateTime dt = ExtLibUtil.getCurrentSession().createDateTime("Today");
				contact.replaceItemValue("testDate", dt);
				contact.save(true, false);
				dt.recycle();
				lotus.domino.Document nextContact = origContactsView.getNextDocument(contact);
				contact.recycle();
				contact = nextContact;
			}
			date = new Date();
			retVal += "<br/>Finished standard version..." + date.toString();
			date = new Date();
			retVal += "<br/>Starting OpenNTF version..." + date.toString();
			Session currSess = (Session) ExtLibUtil.resolveVariable(FacesContext.getCurrentInstance(), "opensession");
			Database currDb = (Database) ExtLibUtil.resolveVariable(FacesContext.getCurrentInstance(), "opendatabase");
			View contactsView = currDb.getView("AllContacts");
			for (Document doc : contactsView.getAllDocuments()) {
				DateTime dt = currSess.createDateTime(new Date());
				doc.replaceItemValue("testDate", dt);
				doc.save(true, false);
			}
			date = new Date();
			retVal += "<br/>Finished OpenNTF version..." + date.toString();
			ExtLibUtil.getViewScope().put("SSJSTest", retVal);
		} catch (Exception e) {
			e.printStackTrace();
			handleException(e);
		}
	}
}
