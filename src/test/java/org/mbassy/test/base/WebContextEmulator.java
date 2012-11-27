package org.mbassy.test.base;

import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.config.Scope;
import org.springframework.context.support.GenericApplicationContext;
import org.springframework.context.support.SimpleThreadScope;
import org.springframework.test.context.TestContext;
import org.springframework.test.context.support.AbstractTestExecutionListener;


/**
 * Emulates (poorly) the scopes as available in application that run in servlet container environment
 * It merely makes the scopes available such that bean wiring does not throw any exceptions but
 * it does not handle request and session scope creating as defined in the servlet spec.
 * <p/>
 * @author bennidi
 * Date: 2/21/12
 * Time: 12:19 PM
 */
public class WebContextEmulator extends AbstractTestExecutionListener {

    @Override
    public void prepareTestInstance(TestContext testContext) throws Exception {

        if (testContext.getApplicationContext() instanceof GenericApplicationContext) {
            GenericApplicationContext context = (GenericApplicationContext) testContext.getApplicationContext();
            ConfigurableListableBeanFactory beanFactory = context.getBeanFactory();
            Scope requestScope = new SimpleThreadScope();
            beanFactory.registerScope("request", requestScope);
            Scope sessionScope = new SimpleThreadScope();
            beanFactory.registerScope("session", sessionScope);
        }
    }

}
