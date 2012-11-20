package org.mbassy.test.base;


import org.junit.After;
import org.junit.Before;

import java.util.Collection;
import java.util.Random;

/**
 * User: benni
 * Date: 2/2/12
 * Time: 5:36 PM
 */
public abstract class UnitTest {

    private Random random = new Random();

	@Before
	public void setUp() {

	}

	@After
	public void tearDown() {

	}

	public void fail(String message) {
		org.junit.Assert.fail(message);
	}

	public void fail() {
		org.junit.Assert.fail();
	}

	public void assertTrue(Boolean condition) {
		org.junit.Assert.assertTrue(condition);
	}

	public void assertTrue(String message, Boolean condition) {
		org.junit.Assert.assertTrue(message, condition);
	}

	public void assertFalse(Boolean condition) {
		org.junit.Assert.assertFalse(condition);
	}

	public void assertNull(Object object) {
		org.junit.Assert.assertNull(object);
	}

	public void assertNotNull(Object object) {
		org.junit.Assert.assertNotNull(object);
	}

	public void assertFalse(String message, Boolean condition) {
		org.junit.Assert.assertFalse(message, condition);
	}

	public void assertEquals(Object expected, Object actual) {
		org.junit.Assert.assertEquals(expected, actual);
	}

	public <T extends Collection> void assertNotEmpty(T collection) {
		org.junit.Assert.assertTrue(collection != null && !collection.isEmpty());
	}

    protected <T> T getRandomElement(T[] values){
        int opIndex = Math.abs(random.nextInt()) % values.length;
        return values[opIndex];
    }

    protected void pause(long timeInMs){
        try {
            Thread.currentThread().sleep(timeInMs);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    protected void pause(){
         pause(10);
    }

    protected Random getRandom(){
        return random;
    }
}
