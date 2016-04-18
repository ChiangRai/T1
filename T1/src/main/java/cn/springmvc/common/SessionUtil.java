package cn.springmvc.common;


import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.Resource;
import javax.servlet.http.HttpSession;

public class SessionUtil {

	public static boolean setSession(HttpSession session, String name, Object ob) throws Exception {
		session.setAttribute(name, ob);
		return true;
	}

	public static boolean removeSession(HttpSession session, String name) throws Exception {
		session.removeAttribute(name);
		return true;
	}

	public static Object getSession(HttpSession session, String name) throws Exception {
		return session.getAttribute(name);
	}

	public static List<String> getSessionAttributeNames(HttpSession session) throws Exception {
		Enumeration<String> e = session.getAttributeNames();
		List<String> list = new ArrayList<String>();
		while (e.hasMoreElements()) {
			list.add(e.nextElement().toString());
		}
		return list;
	}
}
