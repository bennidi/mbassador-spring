package org.mbassy.test;

import org.junit.runner.RunWith;
import org.junit.runners.Suite;
import org.mbassy.test.scenario.TransactionCommitSuccessfulTest;
import org.mbassy.test.scenario.TransactionRolledBackTest;

@RunWith(Suite.class)
@Suite.SuiteClasses({
        TransactionCommitSuccessfulTest.class,
        TransactionRolledBackTest.class
})
public class AllTests {
}
