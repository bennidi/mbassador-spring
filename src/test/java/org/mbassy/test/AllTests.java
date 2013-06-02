package org.mbassy.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.mbassy.test.scenario.NonTransactionalTest;
import org.mbassy.test.scenario.ProxiedListenerTest;
import org.mbassy.test.scenario.TransactionalTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TransactionalTest.class,
        NonTransactionalTest.class,
        ProxiedListenerTest.class
})
public class AllTests {
}
