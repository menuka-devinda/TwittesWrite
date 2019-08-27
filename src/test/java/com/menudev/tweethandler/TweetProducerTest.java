package com.menudev.tweethandler;

import junit.framework.Test;
import junit.framework.TestCase;
import junit.framework.TestSuite;

public class TweetProducerTest extends TestCase{
	/**
	 * Create the test case
	 *
	 * @param testName name of the test case
	 */
	public TweetProducerTest(String testName) {
		super(testName);
	}

	/**
	 * @return the suite of tests being tested
	 */
	public static Test suite() {
		return new TestSuite(TweetProducerTest.class);
	}
	
	public void testProduceTweets() {
//		TweetProducer tweetProducer = new TweetProducer();
//		assertEquals(tweetProducer.getLisofTweet().stream().count(),16);
	}

}
