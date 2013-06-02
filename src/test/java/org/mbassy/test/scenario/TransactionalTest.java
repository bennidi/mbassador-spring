package org.mbassy.test.scenario;

import org.junit.Test;
import org.mbassy.spring.Transaction;
import org.mbassy.test.listeners.MessageTrackingListener;
import org.mbassy.test.messages.MessageProducer;
import org.mbassy.test.util.Outcome;

/**
 * @author bennidi
 * Date: 11/12/12
 *
 */
public class TransactionalTest extends BaseTest {


    @Test
    public void transactionCommit(){

        MessageProducer scheduledMessages = new MessageProducer();
        Object transactionCommitted = new Object();
        Object transactionCompleted = new Object();

        scheduledMessages.after(Transaction.Commit(), transactionCommitted);
        scheduledMessages.before(Transaction.Completion(), transactionCompleted);

        // check publication with successful commit
        MessageTrackingListener listener = new MessageTrackingListener()
                .addExpectedMessage(transactionCommitted)
                .addExpectedMessage(transactionCompleted);
        bus.subscribe(listener);
        bean.postMessageTransactional(scheduledMessages, Outcome.Commit);
        listener.validate();

    }

    @Test
    public void transactionRolledBack(){

        MessageProducer eventsToPublish = new MessageProducer();
        Object rolledBack = new Object();
        Object beforeCompletion = new Object();

        eventsToPublish.after(Transaction.Completed().withRollBack(), rolledBack);
        eventsToPublish.before(Transaction.Completion(), beforeCompletion);

        // these messages will not be published since the transaction never commits
        eventsToPublish.after(Transaction.Completed().successfully(), new Object());
        eventsToPublish.after(Transaction.Commit(), new Object());

        MessageTrackingListener listener = new MessageTrackingListener()
                .addExpectedMessage(rolledBack)
                .addExpectedMessage(beforeCompletion);

        bus.subscribe(listener);
        try{
            bean.postMessageTransactional(eventsToPublish, Outcome.Rollback);
            fail();
        }
        catch (Exception e){
            //expected exception rolls back transaction
        }

        listener.validate();

    }


}
