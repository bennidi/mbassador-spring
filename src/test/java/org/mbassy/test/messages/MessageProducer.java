package org.mbassy.test.messages;

import org.mbassy.spring.Transaction;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 *
 * The message producer is configured with a set of messages - each message being scheduled for publication
 * under certain conditions (transactional).
 *
 * @author bennidi
 * Date: 11/14/12
 */
public class MessageProducer {

    private Map<Transaction.GenericTransaction, Object> expectedMessages = new HashMap<Transaction.GenericTransaction, Object>();

    private List<Transaction.UnparametrizedTransaction> before = new LinkedList<Transaction.UnparametrizedTransaction>();

    private List<Transaction.GenericTransaction> after = new LinkedList<Transaction.GenericTransaction>();


    public List<Transaction.UnparametrizedTransaction> getBefore(){
       return before;
    }


    public List<Transaction.GenericTransaction> getAfter(){
        return  after;
    }


    public MessageProducer after(Transaction.GenericTransaction transaction, Object expectedMessage){
        after.add(transaction);
        expectedMessages.put(transaction, expectedMessage);
        return this;
    }

    public MessageProducer before(Transaction.UnparametrizedTransaction transaction, Object expectedMessage){
        before.add(transaction);
        expectedMessages.put(transaction, expectedMessage);
        return this;
    }

    public Object getExpectedMessage(Transaction.GenericTransaction transaction){
        return expectedMessages.get(transaction);
    }


}
