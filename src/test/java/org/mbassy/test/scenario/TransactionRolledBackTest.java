package org.mbassy.test.scenario;

import org.junit.Test;
import org.mbassy.spring.Transaction;
import org.mbassy.test.listeners.MessageTrackingListener;
import org.mbassy.test.messages.MessageProducer;
import org.mbassy.test.util.Outcome;

/**
 * Created with IntelliJ IDEA.
 * @author bennidi
 * Date: 11/12/12
 * Time: 8:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class TransactionRolledBackTest extends BaseTest {


    @Test
    public void scenario1(){

        MessageProducer eventsToPublish = new MessageProducer();
        Object transactionCommitted = new Object();
        Object transactionCompleted = new Object();

        eventsToPublish.after(Transaction.Commit(), transactionCommitted);
        eventsToPublish.before(Transaction.Completion(), transactionCompleted);

        MessageTrackingListener listener = new MessageTrackingListener()
                .addExpectedMessage(transactionCommitted)
                .addExpectedMessage(transactionCompleted);

        bus.subscribe(listener);

        bean.postMessageTransactional(eventsToPublish, Outcome.Commit);

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
        }
        catch (Exception e){
            //expected exception rolls back transaction
        }

        listener.validate();

    }


}
