package com.util;

import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.core.io.ClassPathResource;
import org.springframework.web.context.ContextLoader;
import org.springframework.web.context.WebApplicationContext;

public class BeanFactoryUtil {
	private static XmlBeanFactory xbf = null;
	
	
	@SuppressWarnings("deprecation")
	public static synchronized Object getBean(String name){
		try{
			WebApplicationContext context = ContextLoader.getCurrentWebApplicationContext();
			if(context==null){
				if(xbf==null){
					xbf = new XmlBeanFactory(new ClassPathResource("applicationContext.xml"));
				}
				return xbf.getBean(name);
			}else{
				return context.getBean(name);
			}
		}catch(Exception e){
			e.printStackTrace();
			return null;
		}
	}
	
}
