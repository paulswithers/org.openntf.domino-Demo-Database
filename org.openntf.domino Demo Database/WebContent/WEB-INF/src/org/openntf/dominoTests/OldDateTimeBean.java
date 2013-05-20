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
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Random;

import lotus.domino.Database;
import lotus.domino.DateTime;
import lotus.domino.Document;
import lotus.domino.NotesException;
import lotus.domino.Session;
import lotus.domino.View;
import lotus.domino.ViewEntry;
import lotus.domino.ViewNavigator;

import com.ibm.xsp.extlib.util.ExtLibUtil;

public class OldDateTimeBean implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public OldDateTimeBean() {

	}

	public String getDateTimeIsBeforeTest() {
		String retVal_ = "";
		View threadsByDate = null;
		Document firstDoc = null;
		Document secondDoc = null;
		DateTime firstDate = null;
		DateTime secondDate = null;
		try {
			Session s = ExtLibUtil.getCurrentSession();
			Database currDb = s.getCurrentDatabase();
			threadsByDate = currDb.getView("AllThreadsByDate");
			threadsByDate.setAutoUpdate(false);
			ViewNavigator vNav = threadsByDate.createViewNav();
			vNav.setEntryOptions(lotus.domino.ViewNavigator.VN_ENTRYOPT_NOCOLUMNVALUES);
			Random randomGenerator = new Random();
			int randomInt = randomGenerator.nextInt(35);
			ViewEntry firstEnt = vNav.getNth(randomInt);
			while (!firstEnt.isDocument()) {
				ViewEntry tmpEnt = vNav.getNext();
				firstEnt.recycle();
				firstEnt = tmpEnt;
			}
			randomInt = randomGenerator.nextInt(35);
			ViewEntry secondEnt = vNav.getNth(randomInt);
			while (!secondEnt.isDocument()) {
				ViewEntry tmpEnt = vNav.getNext();
				secondEnt.recycle();
				secondEnt = tmpEnt;
			}
			firstDoc = firstEnt.getDocument();
			secondDoc = secondEnt.getDocument();
			String firstDt = firstDoc.getFirstItem("Date").getText();
			String secondDt = secondDoc.getFirstItem("Date").getText();
			firstDate = s.createDateTime(firstDt);
			secondDate = s.createDateTime(secondDt);
			Date firstDateJ = firstDate.toJavaDate();
			Date secondDateJ = secondDate.toJavaDate();
			retVal_ = "Comparing " + firstDt + " (" + firstDoc.getUniversalID() + ") with " + secondDt + " ("
					+ secondDoc.getUniversalID() + ")...";
			if (firstDateJ.before(secondDateJ)) {
				retVal_ += "first before second";
			} else {
				retVal_ += "first NOT before second";
			}
			retVal_ += "..........................................................................................";
			retVal_ += "Comparing " + secondDt + " (" + secondDoc.getUniversalID() + ") with " + firstDt + " ("
					+ firstDoc.getUniversalID() + ")...";
			if (secondDateJ.before(firstDateJ)) {
				retVal_ += "second before first";
			} else {
				retVal_ += "second NOT before first";
			}
		} catch (NotesException e) {
			// doSomething
		} finally {
			try {
				threadsByDate.recycle();
				firstDoc.recycle();
				secondDoc.recycle();
				firstDate.recycle();
				secondDate.recycle();
			} catch (NotesException e) {
				// doSomething
			}
		}
		return retVal_;
	}

	public String getDateTimeIsAfterTest() {
		String retVal_ = "";
		View threadsByDate = null;
		Document firstDoc = null;
		Document secondDoc = null;
		DateTime firstDate = null;
		DateTime secondDate = null;
		try {
			Session s = ExtLibUtil.getCurrentSession();
			Database currDb = s.getCurrentDatabase();
			threadsByDate = currDb.getView("AllThreadsByDate");
			threadsByDate.setAutoUpdate(false);
			ViewNavigator vNav = threadsByDate.createViewNav();
			vNav.setEntryOptions(lotus.domino.ViewNavigator.VN_ENTRYOPT_NOCOLUMNVALUES);
			Random randomGenerator = new Random();
			int randomInt = randomGenerator.nextInt(35);
			ViewEntry firstEnt = vNav.getNth(randomInt);
			while (!firstEnt.isDocument()) {
				ViewEntry tmpEnt = vNav.getNext();
				firstEnt.recycle();
				firstEnt = tmpEnt;
			}
			randomInt = randomGenerator.nextInt(35);
			ViewEntry secondEnt = vNav.getNth(randomInt);
			while (!secondEnt.isDocument()) {
				ViewEntry tmpEnt = vNav.getNext();
				secondEnt.recycle();
				secondEnt = tmpEnt;
			}
			firstDoc = firstEnt.getDocument();
			secondDoc = secondEnt.getDocument();
			String firstDt = firstDoc.getFirstItem("Date").getText();
			String secondDt = secondDoc.getFirstItem("Date").getText();
			firstDate = s.createDateTime(firstDt);
			secondDate = s.createDateTime(secondDt);
			Date firstDateJ = firstDate.toJavaDate();
			Date secondDateJ = secondDate.toJavaDate();
			retVal_ = "Comparing " + firstDt + " (" + firstDoc.getUniversalID() + ") with " + secondDt + " ("
					+ secondDoc.getUniversalID() + ")...";
			if (firstDateJ.after(secondDateJ)) {
				retVal_ += "first after second";
			} else {
				retVal_ += "first NOT after second";
			}
			retVal_ += "..........................................................................................";
			retVal_ += "Comparing " + secondDt + " (" + secondDoc.getUniversalID() + ") with " + firstDt + " ("
					+ firstDoc.getUniversalID() + ")...";
			if (secondDateJ.after(firstDateJ)) {
				retVal_ += "second after first";
			} else {
				retVal_ += "second NOT after first";
			}
		} catch (NotesException e) {
			// doSomething
		} finally {
			try {
				threadsByDate.recycle();
				firstDoc.recycle();
				secondDoc.recycle();
				firstDate.recycle();
				secondDate.recycle();
			} catch (NotesException e) {
				// doSomething
			}
		}
		return retVal_;
	}

	public String getDateTimeEqualsTest() {
		String retVal_ = "";
		View threads = null;
		Document firstDoc = null;
		Document secondDoc = null;
		DateTime firstDate = null;
		DateTime secondDate = null;
		try {
			Session s = ExtLibUtil.getCurrentSession();
			Database currDb = s.getCurrentDatabase();
			threads = currDb.getView("AllThreads");
			Random randomGenerator = new Random();
			int randomInt = randomGenerator.nextInt(100);
			firstDoc = threads.getNthDocument(randomInt);
			randomInt = randomGenerator.nextInt(100);
			secondDoc = threads.getNthDocument(randomInt);
			String firstDt = firstDoc.getFirstItem("Date").getText();
			String secondDt = secondDoc.getFirstItem("Date").getText();
			firstDate = s.createDateTime(firstDt);
			secondDate = s.createDateTime(secondDt);
			Date firstDateJ = firstDate.toJavaDate();
			Date secondDateJ = secondDate.toJavaDate();
			retVal_ = "Comparing " + firstDt + " (" + firstDoc.getUniversalID() + ") with " + secondDt + " ("
					+ secondDoc.getUniversalID() + ")...";
			if (firstDateJ.equals(secondDateJ)) {
				retVal_ += "first is the same date/time as second";
			} else {
				retVal_ += "first is NOT the same date/time as second";
			}
		} catch (NotesException e) {
			// doSomething
		} finally {
			try {
				threads.recycle();
				firstDoc.recycle();
				secondDoc.recycle();
				firstDate.recycle();
				secondDate.recycle();
			} catch (NotesException e) {
				// doSomething
			}
		}
		return retVal_;
	}

	public String getDateTimeEqualsIgnoreDateTest() {
		String retVal_ = "";
		View threads = null;
		Document firstDoc = null;
		Document secondDoc = null;
		DateTime firstDate = null;
		DateTime secondDate = null;
		try {
			Session s = ExtLibUtil.getCurrentSession();
			Database currDb = s.getCurrentDatabase();
			threads = currDb.getView("AllThreads");
			Random randomGenerator = new Random();
			int randomInt = randomGenerator.nextInt(100);
			firstDoc = threads.getNthDocument(randomInt);
			randomInt = randomGenerator.nextInt(100);
			secondDoc = threads.getNthDocument(randomInt);
			String firstDt = firstDoc.getFirstItem("Date").getText();
			String secondDt = secondDoc.getFirstItem("Date").getText();
			firstDate = s.createDateTime(firstDt);
			secondDate = s.createDateTime(secondDt);
			Calendar c1 = GregorianCalendar.getInstance();
			Calendar c2 = GregorianCalendar.getInstance();
			c1.setTime(firstDate.toJavaDate());
			c1.set(Calendar.DAY_OF_MONTH, 1);
			c1.set(Calendar.MONTH, 0);
			c1.set(Calendar.YEAR, 2000);
			c2.setTime(secondDate.toJavaDate());
			c2.set(Calendar.DAY_OF_MONTH, 1);
			c2.set(Calendar.MONTH, 0);
			c2.set(Calendar.YEAR, 2000);
			retVal_ = "Comparing " + firstDt + " (" + firstDoc.getUniversalID() + ") with " + secondDt + " ("
					+ secondDoc.getUniversalID() + ")...";
			if (c1.equals(c2)) {
				retVal_ += "first is the same time as second";
			} else {
				retVal_ += "first is NOT the same time as second";
			}
		} catch (NotesException e) {
			// doSomething
		} finally {
			try {
				threads.recycle();
				firstDoc.recycle();
				secondDoc.recycle();
				firstDate.recycle();
				secondDate.recycle();
			} catch (NotesException e) {
				// doSomething
			}
		}
		return retVal_;
	}

	public String getDateTimeEqualsIgnoreTimeTest() {
		String retVal_ = "";
		View threads = null;
		Document firstDoc = null;
		Document secondDoc = null;
		DateTime firstDate = null;
		DateTime secondDate = null;
		try {
			Session s = ExtLibUtil.getCurrentSession();
			Database currDb = s.getCurrentDatabase();
			threads = currDb.getView("AllThreads");
			Random randomGenerator = new Random();
			int randomInt = randomGenerator.nextInt(100);
			firstDoc = threads.getNthDocument(randomInt);
			randomInt = randomGenerator.nextInt(100);
			secondDoc = threads.getNthDocument(randomInt);
			String firstDt = firstDoc.getFirstItem("Date").getText();
			String secondDt = secondDoc.getFirstItem("Date").getText();
			firstDate = s.createDateTime(firstDt);
			secondDate = s.createDateTime(secondDt);
			Calendar c1 = GregorianCalendar.getInstance();
			Calendar c2 = GregorianCalendar.getInstance();
			c1.setTime(firstDate.toJavaDate());
			c1.set(Calendar.HOUR_OF_DAY, 0);
			c1.set(Calendar.MINUTE, 0);
			c1.set(Calendar.SECOND, 0);
			c1.set(Calendar.MILLISECOND, 0);
			c2.setTime(secondDate.toJavaDate());
			c2.set(Calendar.HOUR_OF_DAY, 0);
			c2.set(Calendar.MINUTE, 0);
			c2.set(Calendar.SECOND, 0);
			c2.set(Calendar.MILLISECOND, 0);
			retVal_ = "Comparing " + firstDt + " (" + firstDoc.getUniversalID() + ") with " + secondDt + " ("
					+ secondDoc.getUniversalID() + ")...";
			if (c1.equals(c2)) {
				retVal_ += "first is the same date as second";
			} else {
				retVal_ += "first is NOT the same date as second";
			}
		} catch (NotesException e) {
			// doSomething
		} finally {
			try {
				threads.recycle();
				firstDoc.recycle();
				secondDoc.recycle();
				firstDate.recycle();
				secondDate.recycle();
			} catch (NotesException e) {
				// doSomething
			}
		}
		return retVal_;
	}
}
