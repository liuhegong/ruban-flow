package cn.com.yusys.yusp.workflow.util;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
public class ApplicationContextUtil implements ApplicationContextAware {
	private static ApplicationContext applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		if (ApplicationContextUtil.applicationContext == null) {
			ApplicationContextUtil.applicationContext = applicationContext;
		}
	}

	public static ApplicationContext getApplicationContext() {
		return applicationContext;
	}

	public static Object getBean(String name) {
		return getApplicationContext().getBean(name);
	}

	public static <T> T getBean(Class<T> clazz) {
		return getApplicationContext().getBean(clazz);
	}
}
