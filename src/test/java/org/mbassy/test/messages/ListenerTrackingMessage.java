package org.mbassy.test.messages;

import net.engio.mbassy.common.StrongConcurrentSet;

import java.util.Collection;

/**
 * Simple message that can keep track of its receivers.
 *
 * @author bennidi
 *         Date: 1/18/13
 */
public class ListenerTrackingMessage {


    private Collection receivers = new StrongConcurrentSet();


    public void markReceived(Object receiver){
        receivers.add(receiver);
    }


    public boolean isReceiver(Object receiver){
        return  receivers.contains(receiver);
    }
}
