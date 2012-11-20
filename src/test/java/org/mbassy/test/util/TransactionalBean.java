package org.mbassy.test.util;

import org.mbassy.spring.Transaction;
import org.mbassy.spring.TransactionalEventBus;
import org.mbassy.test.util.EventBusBean;
import org.mbassy.test.util.Outcome;
import org.mbassy.test.util.TransactionalEvents;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * User: benni
 * Date: 11/12/12
 */
@Service
public class TransactionalBean {



    @Autowired
    private EventBusBean bus;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void runTransactional(TransactionalEvents events, Outcome outcome){
        postExpectedMessages(events);
        produceOutcome(outcome);
    }

    private void produceOutcome(Outcome expectedOutcome){
        switch (expectedOutcome){
            case Commit:break;
            case Rollback: throw new RuntimeException(); // provoke transaction rollback
        }
    }

    private void postExpectedMessages(TransactionalEvents events){
        for(Transaction.UnparametrizedTransaction tx : events.getBefore()){
            bus.post(events.getExpectedMessage(tx)).before(tx);
        }

        for(Transaction.GenericTransaction tx : events.getAfter()){
            bus.post(events.getExpectedMessage(tx)).after(tx);
        }
    }
}
