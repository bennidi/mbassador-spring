package org.mbassy.test.util;

import org.mbassy.spring.TransactionAwareMessageBus;
import org.springframework.stereotype.Service;

/**
 * Custom Transaction Event Bus configured as Spring bean
 * @author bennidi
 * Date: 11/20/12
 */
@Service
public class MessageBusBean extends TransactionAwareMessageBus {
}
