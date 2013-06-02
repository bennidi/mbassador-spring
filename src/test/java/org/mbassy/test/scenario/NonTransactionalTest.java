package org.mbassy.test.scenario;

import org.junit.Test;
import org.mbassy.spring.Transaction;
import org.mbassy.test.listeners.MessageTrackingListener;
import org.mbassy.test.messages.MessageProducer;

/**
 * @author bennidi
 * Date: 11/12/12
 */
public class NonTransactionalTest extends BaseTest {


    @Test
    public void scenario1(){

        MessageProducer eventsToPublish = new MessageProducer();
        Object transactionCommitted = new Object();
        Object transactionCompleted = new Object();

        eventsToPublish.after(Transaction.Commit().OrIfNoTransactionAvailable(), transactionCommitted);
        eventsToPublish.before(Transaction.Completion().OrIfNoTransactionAvailable(), transactionCompleted);
        eventsToPublish.before(Transaction.Completion(), new Object());

        MessageTrackingListener listener = new MessageTrackingListener()
                .addExpectedMessage(transactionCommitted)
                .addExpectedMessage(transactionCompleted);

        bus.subscribe(listener);

        bean.postMessagesWithTransaction(eventsToPublish);

        listener.validate();

    }


}
