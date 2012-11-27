package org.mbassy.test;

import org.mbassy.spring.Transaction;
import org.mbassy.test.scenario.BaseTest;

/**
 * Todo: Add javadoc
 *
 * @author bennidi
 *         Date: 11/27/12
 */
public class Example extends BaseTest{


    public void sendConditionalEvent(){

       // post the object only if a transaction exists and completes with rollback
       bus.post(new Object()).after(Transaction.Completed().withRollBack());

       // post the object only if a transaction exists and commits succesfully
       bus.post(new Object()).after(Transaction.Completed().successfully());

        // post the object before transaction completes (regardless of status) or immediately
        // if none is present
       bus.post(new Object()).before(Transaction.Completion().OrIfNoTransactionAvailable());


   }

}
