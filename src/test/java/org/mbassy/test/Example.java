package org.mbassy.test;

import org.mbassy.spring.Transaction;
import org.mbassy.test.scenario.BaseTest;

/**
 * Todo: Add javadoc
 *
 * @author bennidi
 *         Date: 11/27/12
 */
public class Example extends BaseTest {


    public void postMessageConditionally() {

        // post the object only if a transaction exists and completes with rollback
        bus.post(new Object()).after(Transaction.Completed().withRollBack());

        // post the object only if a transaction exists and commits succesfully
        bus.post(new Object()).after(Transaction.Completed().successfully());


        // post the object only if a transaction exists but regardless of its outcome
        bus.post(new Object()).after(Transaction.Completed().withAnyStatus());
        bus.post(new Object()).after(Transaction.Completion()); // same same

        // post the object according to the specified transactinoal condition or immediately if none is present
        bus.post(new Object()).after(Transaction.Completion().OrIfNoTransactionAvailable());
        bus.post(new Object()).after(Transaction.Completed().withRollBack().OrIfNoTransactionAvailable());
        bus.post(new Object()).after(Transaction.Completed().successfully().OrIfNoTransactionAvailable());
        bus.post(new Object()).after(Transaction.Completed().withAnyStatus().OrIfNoTransactionAvailable());
        bus.post(new Object()).after(Transaction.Completion().OrIfNoTransactionAvailable()); // same same




        // post the object before transaction completes (regardless of status)
        bus.post(new Object()).before(Transaction.Completion());
        // or immediately if none is present
        bus.post(new Object()).before(Transaction.Completion().OrIfNoTransactionAvailable());

        // compile error: the outcome is only available after completion so its not possible to refer
        // to any transaction specific outcome when scheduling a message to be published in "Before"-Phase
        //bus.post(new Object()).before(Transaction.Completed().successfully());




    }

}
