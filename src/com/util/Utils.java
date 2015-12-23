package com.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.context.support.FileSystemXmlApplicationContext;

public class Utils {
	private static  Logger logger = Logger.getLogger(Utils.class);
	
	public static Object getBean(String bean_name) {
//		ApplicationContext context=new ClassPathXmlApplicationContext("dataaccess.xml");
		ApplicationContext context=new FileSystemXmlApplicationContext("D:/bugreport/BUG_REPORT_SYSTEM/WebContent/WEB-INF/dataaccess.xml");
		return context.getBean(bean_name);
	}
	public static Properties getProperties(String property){
        InputStream in = Utils.class.getResourceAsStream("/"+property); 
        Properties prop = new Properties(); 
        try {
			prop.load(in);
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			logger.error("读取配置文件出错！",e1);
		}
        try {
            if(null!=in)
            {
                in.close();
            }
        } catch (IOException e) {
        	logger.error("关闭配置文件出错！",e);
        }
        return prop;
    }
}
