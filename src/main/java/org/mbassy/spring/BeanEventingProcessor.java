package org.mbassy.spring;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanPostProcessor;

/**
 * TODO. Insert class description here
 * <p/>
 * User: benni
 * Date: 2/8/12
 * Time: 4:08 PM
 */
public class BeanEventingProcessor  implements BeanPostProcessor{

	@Override
	public Object postProcessBeforeInitialization(Object o, String s) throws BeansException {
		return o;
	}

	@Override
	public Object postProcessAfterInitialization(Object bean, String s) throws BeansException {

		// exclude scoped proxies
		// This looks like a quick fix but it is really meant that we only
		// want to have all proxies because these will delegate to the right scoped bean
		/*
        if(bean.getClass().getCanonicalName().contains("CGLIB")
				|| bean.getClass().isAnnotationPresent(Observer.class)) {
			MessageBus.getGlobalBus().subscribe(bean);
		}  */
		return bean;
	}
}
