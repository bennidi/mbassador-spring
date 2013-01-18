package org.mbassy.test.scenario.transactional;

import org.junit.Test;
import org.mbassy.spring.Transaction;
import org.mbassy.test.base.SpringAwareUnitTest;
import org.mbassy.test.scenario.BaseTest;
import org.mbassy.test.util.TransactionalBean;
import org.mbassy.test.util.EventBusBean;
import org.mbassy.test.util.ExpectedMessagesListener;
import org.mbassy.test.util.Outcome;
import org.mbassy.test.util.TransactionalEvents;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA.
 * @author bennidi
 * Date: 11/12/12
 * Time: 8:09 PM
 * To change this template use File | Settings | File Templates.
 */
public class TransactionRolledBackTest extends BaseTest {


    @Test
    public void transactionRolledBack(){

        TransactionalEvents eventsToPublish = new TransactionalEvents();
        Object rolledBack = new Object();
        Object beforeCompletion = new Object();

        eventsToPublish.after(Transaction.Completed().withRollBack(), rolledBack);
        eventsToPublish.before(Transaction.Completion(), beforeCompletion);

        // these messages will not be published since the transaction never commits
        eventsToPublish.after(Transaction.Completed().successfully(), new Object());
        eventsToPublish.after(Transaction.Commit(), new Object());

        ExpectedMessagesListener listener = new ExpectedMessagesListener()
                .addExpectedMessage(rolledBack)
                .addExpectedMessage(beforeCompletion);

        bus.subscribe(listener);
        try{
            bean.runTransactional(eventsToPublish, Outcome.Rollback);
        }
        catch (Exception e){
            //expected exception rolls back transaction
        }

        listener.validate();

    }


}
