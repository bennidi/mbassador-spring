package org.mbassy.test.util;

import org.mbassy.spring.TransactionalEventBus;
import org.springframework.stereotype.Service;

/**
 * Custom Transaction Event Bus configured as Spring bean
 * User: benni
 * Date: 11/20/12
 */
@Service
public class EventBusBean extends TransactionalEventBus {
}
