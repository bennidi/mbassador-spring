package org.mbassy.test.util;

import org.junit.Assert;
import net.engio.mbassy.listener.Handler;

import java.util.HashSet;
import java.util.Set;

/**
* Created with IntelliJ IDEA.
* @author bennidi
* Date: 11/20/12
* Time: 1:09 PM
* To change this template use File | Settings | File Templates.
*/
public class ExpectedMessagesListener {

    private Set<Object> expectedMessages = new HashSet<Object>();

    private Set<Object> handledMessages = new HashSet<Object>();

    public ExpectedMessagesListener addExpectedMessage(Object message){
        expectedMessages.add(message);
        return this;
    }

    @Handler
    public void handle(Object message){
        handledMessages.add(message);
    }

    public void validate(){
        Assert.assertEquals(expectedMessages.size(), handledMessages.size());
        for(Object expected: expectedMessages){
            Assert.assertTrue(handledMessages.contains(expected));
        }
    }

}
