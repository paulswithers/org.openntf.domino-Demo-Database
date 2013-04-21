package org.openntf.dominoTests;

import java.util.LinkedList;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;

import name.fraser.neil.plaintext.diff_match_patch;
import name.fraser.neil.plaintext.diff_match_patch.Diff;

import org.openntf.domino.DateTime;

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

}
