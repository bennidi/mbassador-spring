package org.mbassy.test.listeners;

import org.junit.Assert;
import net.engio.mbassy.listener.Handler;

import java.util.HashSet;
import java.util.Set;

/**
*
 * This listener can be used to keep track of all messages published to a message bus.
 *
* @author bennidi
*/
public class MessageTrackingListener {

    private Set<Object> expectedMessages = new HashSet<Object>();

    private Set<Object> handledMessages = new HashSet<Object>();

    public MessageTrackingListener addExpectedMessage(Object message){
        expectedMessages.add(message);
        return this;
    }

    @Handler
    public synchronized void handle(Object message){
        handledMessages.add(message);
    }

    public void validate(){
        Assert.assertEquals(expectedMessages.size(), handledMessages.size());
        for(Object expected: expectedMessages){
            Assert.assertTrue(handledMessages.contains(expected));
        }
    }

}
