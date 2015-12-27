package org.mbassy.test.scenario;

import org.mbassy.test.base.SpringAwareUnitTest;
import org.mbassy.test.util.MessageBusBean;
import org.mbassy.test.util.TransactionalBean;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * Created with IntelliJ IDEA.
 * @author bennidi
 * Date: 11/20/12
 * Time: 1:32 PM
 * To change this template use File | Settings | File Templates.
 */
abstract public class BaseTest extends SpringAwareUnitTest {

    @Autowired
    protected TransactionalBean bean;

    @Autowired
    protected MessageBusBean bus;
}
