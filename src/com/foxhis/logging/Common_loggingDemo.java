package com.foxhis.logging;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;


/**
 * commons-logging是一个日志的加载器，他自己一般不产生日志，他可以指定日志框去实现日志
  * 其核心配置文件为，classpath下的commons-logging.properties
 * @author Administrator
 *
 */
public class Common_loggingDemo {

	private static final Log LOGGER = LogFactory.getLog(Common_loggingDemo.class);
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
        LOGGER.info("info");
	}

}
