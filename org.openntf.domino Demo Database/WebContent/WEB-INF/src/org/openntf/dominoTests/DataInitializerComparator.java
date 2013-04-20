package org.openntf.dominoTests;

import japa.parser.JavaParser;
import japa.parser.ast.CompilationUnit;

import java.io.InputStream;
import java.io.Serializable;
import java.lang.reflect.Method;
import java.util.ArrayList;

import com.ibm.xsp.extlib.util.ExtLibUtil;

public class DataInitializerComparator implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Object dataInitializerMethods;
	private String lotusMethodText;
	private String ourMethodText;

	public DataInitializerComparator() {

	}

	public Object getDataInitializerMethods() {
		ArrayList<String> retVal_ = new ArrayList<String>();
		try {
			Class<?> dataInit = Class.forName("extlib.DataInitializerOpenNTF");
			Method[] methods = dataInit.getDeclaredMethods();
			for (Method method : methods) {
				retVal_.add(method.getName());
			}
		} catch (Throwable e) {
			Utils.handleException(e);
		}
		return retVal_.toArray();
	}

	public String getLotusMethodText() {
		String retVal_ = "";
		try {
			InputStream in = Utils.class.getResourceAsStream("DataInitializer");
			CompilationUnit cu;
			cu = JavaParser.parse(in);
			// visit and print the methods names
			ExtendedDumpVisitor dv = new ExtendedDumpVisitor();
			dv.setSearchMethodName((String) ExtLibUtil.getViewScope().get("methodName"));
			dv.visit(cu, null);
			retVal_ = dv.getSource();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retVal_;
	}

	public String getOurMethodText() {
		String retVal_ = "";
		try {
			InputStream in = Utils.class.getResourceAsStream("DataInitializerOpenNTF");
			CompilationUnit cu;
			cu = JavaParser.parse(in);
			// visit and print the methods names
			ExtendedDumpVisitor dv = new ExtendedDumpVisitor();
			dv.setSearchMethodName((String) ExtLibUtil.getViewScope().get("methodName"));
			dv.visit(cu, null);
			retVal_ = dv.getSource();
		} catch (Throwable e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return retVal_;
	}

}
