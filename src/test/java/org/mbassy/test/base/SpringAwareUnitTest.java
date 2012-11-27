package org.mbassy.test.base;

import org.junit.runner.RunWith;
import org.mbassy.test.base.UnitTest;
import org.mbassy.test.base.WebContextEmulator;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestExecutionListeners;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.DependencyInjectionTestExecutionListener;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author bennidi
 * Date: 2/2/12
 * Time: 5:36 PM
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations="classpath*:META-INF/common-applicationContext.xml")
@TestExecutionListeners(listeners = {WebContextEmulator.class, DependencyInjectionTestExecutionListener.class}, inheritListeners = true)
@TransactionConfiguration
@Transactional
public abstract class SpringAwareUnitTest extends UnitTest{


}
