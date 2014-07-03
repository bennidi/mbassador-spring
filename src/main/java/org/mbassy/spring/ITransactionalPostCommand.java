package org.mbassy.spring;


import net.engio.mbassy.bus.publication.ISyncAsyncPublicationCommand;

/**
 * Extension interface for the publish method of the message bus interface.
 * Supports publication based on transaction outcomes
 */
public interface ITransactionalPostCommand extends ISyncAsyncPublicationCommand{

    public void after(Transaction.GenericTransaction condition);

    public void before(Transaction.UnparametrizedTransaction condition);

}
