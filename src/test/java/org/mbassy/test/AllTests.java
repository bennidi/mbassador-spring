package org.mbassy.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.mbassy.test.scenario.NonTransactionalTest;
import org.mbassy.test.scenario.proxied.ProxyTest;
import org.mbassy.test.scenario.transactional.TransactionCommitSuccessfulTest;
import org.mbassy.test.scenario.transactional.TransactionRolledBackTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TransactionCommitSuccessfulTest.class,
        TransactionRolledBackTest.class,
        NonTransactionalTest.class,
        ProxyTest.class
})
public class AllTests {
}
