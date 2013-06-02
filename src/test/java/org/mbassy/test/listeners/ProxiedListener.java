package org.mbassy.test.listeners;

import net.engio.mbassy.listener.Handler;
import org.mbassy.test.messages.ListenerTrackingMessage;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

/**
 * This class will be proxied by cglib to attach transactional characteristics to its
 * public methods
 *
 * @author bennidi
 *         Date: 1/18/13
 */
@Service
@Transactional
public class ProxiedListener {

    private String uuid = UUID.randomUUID().toString();

    @Handler
    public void handle(ListenerTrackingMessage message){
      message.markReceived(uuid);
    }


    public String getUuid(){
        return uuid;
    }

}
