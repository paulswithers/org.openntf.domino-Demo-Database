package org.openntf.dominoTests;

import japa.parser.ast.body.MethodDeclaration;
import japa.parser.ast.stmt.BlockStmt;
import japa.parser.ast.stmt.CatchClause;
import japa.parser.ast.stmt.Statement;
import japa.parser.ast.stmt.TryStmt;
import japa.parser.ast.visitor.VoidVisitorAdapter;

import java.util.List;

public class SpecificMethodVisitor extends VoidVisitorAdapter<Object> {
	private class SourcePrinter {

		private int level = 0;

		private boolean indented = false;

		private final StringBuilder buf = new StringBuilder();

		public void indent() {
			level++;
		}

		public void unindent() {
			level--;
		}

		private void makeIndent() {
			for (int i = 0; i < level; i++) {
				buf.append("    ");
			}
		}

		public void print(String arg) {
			if (!indented) {
				makeIndent();
				indented = true;
			}
			buf.append(arg);
		}

		public void printLn(String arg) {
			print(arg);
			printLn();
		}

		public void printLn() {
			buf.append("<br/>");
			indented = false;
		}

		public String getSource() {
			return buf.toString();
		}

		@Override
		public String toString() {
			return getSource();
		}
	}

	private final SourcePrinter printer = new SourcePrinter();
	private String searchMethodName = "";

	public String getSource() {
		return printer.getSource();
	}

	public String getSearchMethodName() {
		return searchMethodName;
	}

	public void setSearchMethodName(String searchMethodName) {
		this.searchMethodName = searchMethodName;
	}

	@Override
	public void visit(MethodDeclaration n, Object arg) {
		if ("".equals(getSearchMethodName())) {
			if (n.getBody() == null) {
				printer.print(";");
			} else {
				BlockStmt bm = n.getBody();
				List<Statement> smList = bm.getStmts();
				for (Statement sm : smList) {
					printer.printLn(sm.toString());
				}
				n.getBody().accept(this, arg);
			}
		} else {
			if (n.getName().equals(getSearchMethodName())) {
				if (n.getBody() == null) {
					printer.print(";");
				} else {
					BlockStmt bm = n.getBody();
					List<Statement> smList = bm.getStmts();
					for (Statement sm : smList) {
						printer.printLn(sm.toString());
					}
					n.getBody().accept(this, arg);
				}
			}
		}
	}

	@Override
	public void visit(TryStmt n, Object arg) {
		printer.print("try ");
		n.getTryBlock().accept(this, arg);
		if (n.getCatchs() != null) {
			for (CatchClause c : n.getCatchs()) {
				c.accept(this, arg);
			}
		}
		if (n.getFinallyBlock() != null) {
			printer.print(" finally ");
			n.getFinallyBlock().accept(this, arg);
		}
	}

}
