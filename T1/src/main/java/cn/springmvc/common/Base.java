package cn.springmvc.common;

import java.io.UnsupportedEncodingException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.liferay.util.JS;


public class Base {

	protected final Logger logger  = LoggerFactory.getLogger(getClass());
	
	protected String goUrl(String url ,String msg) throws UnsupportedEncodingException{
		System.out.println(msg);
		//return "redirect:/temp.jsp?msg=" + new String(msg.getBytes("GBK"),"UTF-8") + "&url=" + url;
		return "redirect:/temp.jsp?msg=" + JS.encodeURIComponent(msg) + "&url=" + url;
	}
	
}
