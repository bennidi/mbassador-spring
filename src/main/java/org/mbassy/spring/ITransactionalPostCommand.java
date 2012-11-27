package org.mbassy.spring;

import org.mbassy.IMessageBus;

/**
 * Created with IntelliJ IDEA.
 * @author bennidi
 * Date: 11/12/12
 * Time: 8:47 PM
 * To change this template use File | Settings | File Templates.
 */
public interface ITransactionalPostCommand extends IMessageBus.IPostCommand{

    public void after(Transaction.GenericTransaction condition);

    public void before(Transaction.UnparametrizedTransaction condition);

}
