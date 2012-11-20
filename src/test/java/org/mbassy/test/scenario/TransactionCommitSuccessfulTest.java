package org.mbassy.test.scenario;

import org.junit.Test;
import org.mbassy.spring.Transaction;
import org.mbassy.test.base.SpringAwareUnitTest;
import org.mbassy.test.scenario.BaseTest;
import org.mbassy.test.util.*;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * User: benni
 * Date: 11/12/12
 */
public class TransactionCommitSuccessfulTest extends BaseTest {


    @Test
    public void scenario1(){

        TransactionalEvents eventsToPublish = new TransactionalEvents();
        Object transactionCommitted = new Object();
        Object transactionCompleted = new Object();

        eventsToPublish.after(Transaction.Commit(), transactionCommitted);
        eventsToPublish.before(Transaction.Completion(), transactionCompleted);

        ExpectedMessagesListener listener = new ExpectedMessagesListener()
                .addExpectedMessage(transactionCommitted)
                .addExpectedMessage(transactionCompleted);

        bus.subscribe(listener);

        bean.runTransactional(eventsToPublish, Outcome.Commit);

        listener.validate();

    }


}
