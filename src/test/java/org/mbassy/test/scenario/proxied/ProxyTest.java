package org.mbassy.test.scenario.proxied;

import org.junit.Test;
import org.mbassy.test.base.UnitTest;
import org.mbassy.test.scenario.BaseTest;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * This test will do some basic checks to ensure that listeners that are proxied using byte code generation (cglib)
 * work the same as standard listeners
 *
 * @author bennidi
 *         Date: 1/18/13
 */
public class ProxyTest extends BaseTest{

    @Autowired
    private ProxiedListener listener;

    @Test
    public void testProxied(){
        bus.subscribe(listener);
        SimpleMessage message = new SimpleMessage();
        System.out.println(listener.getClass());
        assertFalse(message.isReceiver(listener.getUuid()));
        bus.post(message).now();
        assertTrue(message.isReceiver(listener.getUuid()));
    }

}
