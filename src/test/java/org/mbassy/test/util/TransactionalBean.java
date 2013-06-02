package org.mbassy.test.util;

import org.mbassy.spring.Transaction;
import org.mbassy.test.messages.MessageProducer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

/**
 * This spring managed bean is used as an entry point for message publication.
 * It will publish a given set of messages and ensure that, depending on the desired
 * outcome, the enclosing transaction will either commit or rollback.
 *
 * @author bennidi
 * Date: 11/12/12
 */
@Service
public class TransactionalBean {

    @Autowired
    private MessageBusBean bus;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public void postMessageTransactional(MessageProducer events, Outcome outcome){
        postExpectedMessages(events);
        produceOutcome(outcome);
    }

    private void produceOutcome(Outcome expectedOutcome){
        switch (expectedOutcome){
            case Commit:break;
            case Rollback: throw new RuntimeException(); // provoke transaction rollback
        }
    }

    private void postExpectedMessages(MessageProducer messages){
        for(Transaction.UnparametrizedTransaction tx : messages.getBefore()){
            bus.post(messages.getExpectedMessage(tx)).before(tx);
        }

        for(Transaction.GenericTransaction tx : messages.getAfter()){
            bus.post(messages.getExpectedMessage(tx)).after(tx);
        }
    }

    public void postMessagesWithTransaction(MessageProducer events){
        postExpectedMessages(events);
    }
}
