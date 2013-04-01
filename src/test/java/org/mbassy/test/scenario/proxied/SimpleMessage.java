package org.mbassy.test.scenario.proxied;

import net.engio.mbassy.common.IConcurrentSet;
import net.engio.mbassy.common.StrongConcurrentSet;

/**
 * Simple message that can keep track of its receivers.
 *
 * @author bennidi
 *         Date: 1/18/13
 */
public class SimpleMessage {


    private IConcurrentSet receivers = new StrongConcurrentSet();


    public void markReceived(Object receiver){
        receivers.add(receiver);
    }


    public boolean isReceiver(Object receiver){
        return  receivers.contains(receiver);
    }
}
