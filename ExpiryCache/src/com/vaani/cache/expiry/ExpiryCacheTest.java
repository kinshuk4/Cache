package com.vaani.cache.expiry;

import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class ExpiryCacheTest {

	private static ExpiryCache<String, Integer> cache = new ExpiryCacheImpl<>();
	private final static Logger logger = Logger.getLogger(ExpiryCacheTest.class
			.getName());

	public static void main(String[] args) throws InterruptedException {
		cache.put("a", 1, 2, TimeUnit.SECONDS);
		cache.put("b", 2, 5000, TimeUnit.MILLISECONDS);

		logger.info("Sleeping for 3 seconds");
		Thread.sleep(3 * 1000);

		testAExpired();
		testBExists();
		Thread.sleep(7 * 1000);
		cache.destroy();
	}

	private static void testAExpired() {
		Integer a = cache.get("a");
		if (a == null) {
			logger.info("*** Cache a has expired ***");
		} else {
			logger.severe("Test case A failed");
		}
	}

	private static void testBExists() {
		Integer b = cache.get("b");
		if (b != null) {
			logger.info("*** b exists:" + b + "***");
		} else {
			logger.severe("Test case B failed");
		}
	}
}
