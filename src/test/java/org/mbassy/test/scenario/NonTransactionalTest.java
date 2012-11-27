package org.mbassy.test.scenario;

import org.junit.Test;
import org.mbassy.spring.Transaction;
import org.mbassy.test.util.ExpectedMessagesListener;
import org.mbassy.test.util.Outcome;
import org.mbassy.test.util.TransactionalEvents;

/**
 * @author bennidi
 * Date: 11/12/12
 */
public class NonTransactionalTest extends BaseTest {


    @Test
    public void scenario1(){

        TransactionalEvents eventsToPublish = new TransactionalEvents();
        Object transactionCommitted = new Object();
        Object transactionCompleted = new Object();

        eventsToPublish.after(Transaction.Commit().OrIfNoTransactionAvailable(), transactionCommitted);
        eventsToPublish.before(Transaction.Completion().OrIfNoTransactionAvailable(), transactionCompleted);
        eventsToPublish.before(Transaction.Completion(), new Object());

        ExpectedMessagesListener listener = new ExpectedMessagesListener()
                .addExpectedMessage(transactionCommitted)
                .addExpectedMessage(transactionCompleted);

        bus.subscribe(listener);

        bean.runWithoutTransaction(eventsToPublish);

        listener.validate();

    }


}
