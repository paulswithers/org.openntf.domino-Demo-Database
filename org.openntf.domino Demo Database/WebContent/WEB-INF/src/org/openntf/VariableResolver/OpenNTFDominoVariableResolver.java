package org.openntf.VariableResolver;

import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.faces.context.FacesContext;
import javax.faces.el.EvaluationException;
import javax.faces.el.VariableResolver;

import org.openntf.domino.impl.DateTime;
import org.openntf.domino.impl.Session;
import org.openntf.domino.utils.Factory;

import com.ibm.xsp.extlib.util.ExtLibUtil;

public class OpenNTFDominoVariableResolver extends VariableResolver {

	private final VariableResolver delegate;
	private final static Logger log_ = Logger.getLogger(OpenNTFDominoVariableResolver.class.getName());
	private final static Logger log2_ = Logger.getLogger("com.ibm.xsp.domino");

	public OpenNTFDominoVariableResolver(VariableResolver resolver) {
		delegate = resolver;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Object resolveVariable(FacesContext context, String name) throws EvaluationException {
		if ("sessionOpenNTF".equals(name)) {
			try {
				//				ArrayList<Handler> handlers = new ArrayList<Handler>();
				//				OpenLogHandler ol = new OpenLogHandler();
				//				ol.setLogDbPath("OpenLog.nsf");
				//				handlers.add(ol);
				//				LogUtils.setupLogger(OpenNTFDominoVariableResolver.class.getName(), handlers, false, Level.FINE);

				log2_.log(Level.INFO, "Message to find on screen", new Throwable());
				//				LogUtils.loadLoggerConfig(false, "");
				//				log2_.log(Level.WARNING, "This is a test", new Throwable());
				//				Handler consoleHandler = new org.openntf.domino.logging.DefaultConsoleHandler();
				//				ArrayList<Handler> handlers = new ArrayList<Handler>();
				//				handlers.add(consoleHandler);
				//				Logger loggerToModify = LogUtils.getLogger(OpenNTFDominoVariableResolver.class.getName());
				//				for (Handler newHandler : handlers) {
				//					loggerToModify.addHandler(newHandler);
				//				}
				//				LogUtils.setNewLevel(Level.FINE, loggerToModify);
				//
				//				if (LogUtils.setupLogger(OpenNTFDominoVariableResolver.class.getName(), handlers, false, Level.FINER)) {
				//					log_.log(Level.FINE, "", new Throwable());
				//				}
				//				log_.log(Level.WARNING, "Message to find on screen", new Throwable());
			} catch (Throwable e) {
				System.out.println("Test");
			}
			lotus.domino.Session s;
			s = ExtLibUtil.getCurrentSession();
			Session sess = Factory.fromLotus(s, org.openntf.domino.Session.class, null);

			Date d = new Date();
			DateTime dt = sess.createDateTime(d);
			DateTime dt2 = sess.createDateTime(d);
			dt.adjustDay(1);
			if (dt.isBefore(dt2)) {
				System.out.println("Test before");
			} else {
				System.out.println("Test after");
			}
			return sess;
			//	} else if ("database".equals(name)) {
			//return Factory.getSession().getCurrentDatabase();
		}
		return delegate.resolveVariable(context, name);
	}
}
