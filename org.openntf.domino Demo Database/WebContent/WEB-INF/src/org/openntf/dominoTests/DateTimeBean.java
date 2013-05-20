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
import java.util.Date;
import java.util.Random;

import org.openntf.domino.Database;
import org.openntf.domino.DateTime;
import org.openntf.domino.Document;
import org.openntf.domino.Session;
import org.openntf.domino.View;
import org.openntf.domino.ViewEntry;
import org.openntf.domino.ViewNavigator;
import org.openntf.domino.utils.Factory;

import com.ibm.xsp.extlib.util.ExtLibUtil;

public class DateTimeBean implements Serializable {

	/**
	 *
	 */
	private static final long serialVersionUID = 1L;

	public DateTimeBean() {

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

	public String getDateTimeIsBeforeTest() {
		String retVal_ = "";
		Session s = Factory.fromLotus(ExtLibUtil.getCurrentSession(), org.openntf.domino.Session.class, null);
		Database currDb = s.getCurrentDatabase();
		View threadsByDate = currDb.getView("AllThreadsByDate");
		ViewNavigator vNav = threadsByDate.createViewNav();
		vNav.setEntryOptions(org.openntf.domino.ViewNavigator.VN_ENTRYOPT_NOCOLUMNVALUES);
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(35);
		ViewEntry firstEnt = vNav.getNth(randomInt);
		while (!firstEnt.isDocument()) {
			firstEnt = vNav.getNext();
		}
		randomInt = randomGenerator.nextInt(35);
		ViewEntry secondEnt = vNav.getNth(randomInt);
		while (!secondEnt.isDocument()) {
			secondEnt = vNav.getNext();
		}
		Document firstDoc = firstEnt.getDocument();
		Document secondDoc = secondEnt.getDocument();
		String firstDt = firstDoc.getFirstItem("Date").getText();
		String secondDt = secondDoc.getFirstItem("Date").getText();
		DateTime firstDate = s.createDateTime(firstDt);
		DateTime secondDate = s.createDateTime(secondDt);
		retVal_ = "Comparing " + firstDt + " (" + firstDoc.getUniversalID() + ") with " + secondDt + " ("
				+ secondDoc.getUniversalID() + ")...";
		if (firstDate.isBefore(secondDate)) {
			retVal_ += "first before second";
		} else {
			retVal_ += "first NOT before second";
		}
		retVal_ += "..........................................................................................";
		retVal_ += "Comparing " + secondDt + " (" + secondDoc.getUniversalID() + ") with " + firstDt + " ("
				+ firstDoc.getUniversalID() + ")...";
		if (secondDate.isBefore(firstDate)) {
			retVal_ += "second before first";
		} else {
			retVal_ += "second NOT before first";
		}
		return retVal_;
	}

	public String getDateTimeIsAfterTest() {
		String retVal_ = "";
		Session s = Factory.fromLotus(ExtLibUtil.getCurrentSession(), org.openntf.domino.Session.class, null);
		Database currDb = s.getCurrentDatabase();
		View threadsByDate = currDb.getView("AllThreadsByDate");
		ViewNavigator vNav = threadsByDate.createViewNav();
		vNav.setEntryOptions(org.openntf.domino.ViewNavigator.VN_ENTRYOPT_NOCOLUMNVALUES);
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(35);
		ViewEntry firstEnt = vNav.getNth(randomInt);
		while (!firstEnt.isDocument()) {
			firstEnt = vNav.getNext();
		}
		randomInt = randomGenerator.nextInt(35);
		ViewEntry secondEnt = vNav.getNth(randomInt);
		while (!secondEnt.isDocument()) {
			secondEnt = vNav.getNext();
		}
		Document firstDoc = firstEnt.getDocument();
		Document secondDoc = secondEnt.getDocument();
		String firstDt = firstDoc.getFirstItem("Date").getText();
		String secondDt = secondDoc.getFirstItem("Date").getText();
		DateTime firstDate = s.createDateTime(firstDt);
		DateTime secondDate = s.createDateTime(secondDt);
		retVal_ = "Comparing " + firstDt + " (" + firstDoc.getUniversalID() + ") with " + secondDt + " ("
				+ secondDoc.getUniversalID() + ")...";
		if (firstDate.isAfter(secondDate)) {
			retVal_ += "first after second";
		} else {
			retVal_ += "first NOT after second";
		}
		retVal_ += "..........................................................................................";
		retVal_ += "Comparing " + secondDt + " (" + secondDoc.getUniversalID() + ") with " + firstDt + " ("
				+ firstDoc.getUniversalID() + ")...";
		if (secondDate.isAfter(firstDate)) {
			retVal_ += "second after first";
		} else {
			retVal_ += "second NOT after first";
		}
		return retVal_;
	}

	public String getDateTimeEqualsTest() {
		String retVal_ = "";
		Session s = Factory.fromLotus(ExtLibUtil.getCurrentSession(), org.openntf.domino.Session.class, null);
		Database currDb = s.getCurrentDatabase();
		View threads = currDb.getView("AllThreads");
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(100);
		Document firstDoc = threads.getNthDocument(randomInt);
		randomInt = randomGenerator.nextInt(100);
		Document secondDoc = threads.getNthDocument(randomInt);
		String firstDt = firstDoc.getFirstItem("Date").getText();
		String secondDt = secondDoc.getFirstItem("Date").getText();
		DateTime firstDate = s.createDateTime(firstDt);
		DateTime secondDate = s.createDateTime(secondDt);
		retVal_ = "Comparing " + firstDt + " (" + firstDoc.getUniversalID() + ") with " + secondDt + " ("
				+ secondDoc.getUniversalID() + ")...";
		if (firstDate.equals(secondDate)) {
			retVal_ += "first is the same date/time as second";
		} else {
			retVal_ += "first is NOT the same date/time as second";
		}
		return retVal_;
	}

	public String getDateTimeEqualsIgnoreDateTest() {
		String retVal_ = "";
		Session s = Factory.fromLotus(ExtLibUtil.getCurrentSession(), org.openntf.domino.Session.class, null);
		Database currDb = s.getCurrentDatabase();
		View threads = currDb.getView("AllThreads");
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(100);
		Document firstDoc = threads.getNthDocument(randomInt);
		randomInt = randomGenerator.nextInt(100);
		Document secondDoc = threads.getNthDocument(randomInt);
		String firstDt = firstDoc.getFirstItem("Date").getText();
		String secondDt = secondDoc.getFirstItem("Date").getText();
		DateTime firstDate = s.createDateTime(firstDt);
		DateTime secondDate = s.createDateTime(secondDt);
		retVal_ = "Comparing " + firstDt + " (" + firstDoc.getUniversalID() + ") with " + secondDt + " ("
				+ secondDoc.getUniversalID() + ")...";
		if (firstDate.equalsIgnoreDate(secondDate)) {
			retVal_ += "first is the same time as second";
		} else {
			retVal_ += "first is NOT the same time as second";
		}
		return retVal_;
	}

	public String getDateTimeEqualsIgnoreTimeTest() {
		String retVal_ = "";
		Session s = Factory.fromLotus(ExtLibUtil.getCurrentSession(), org.openntf.domino.Session.class, null);
		Database currDb = s.getCurrentDatabase();
		View threads = currDb.getView("AllThreads");
		Random randomGenerator = new Random();
		int randomInt = randomGenerator.nextInt(100);
		Document firstDoc = threads.getNthDocument(randomInt);
		randomInt = randomGenerator.nextInt(100);
		Document secondDoc = threads.getNthDocument(randomInt);
		String firstDt = firstDoc.getFirstItem("Date").getText();
		String secondDt = secondDoc.getFirstItem("Date").getText();
		DateTime firstDate = s.createDateTime(firstDt);
		DateTime secondDate = s.createDateTime(secondDt);
		retVal_ = "Comparing " + firstDt + " (" + firstDoc.getUniversalID() + ") with " + secondDt + " ("
				+ secondDoc.getUniversalID() + ")...";
		if (firstDate.equalsIgnoreTime(secondDate)) {
			retVal_ += "first is the same date as second";
		} else {
			retVal_ += "first is NOT the same date as second";
		}
		return retVal_;
	}
}
