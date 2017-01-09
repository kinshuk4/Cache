package com.vaani.cache.expiry;

import java.util.Map;
import java.util.concurrent.Callable;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

public class ExpiryCacheImpl<K, V> implements ExpiryCache<K, V> {

	private Map<K, V> cacheMap = new ConcurrentHashMap<>();
	ScheduledExecutorService executorService;
	private final static Logger logger = Logger.getLogger(ExpiryCacheImpl.class
			.getName());

	public ExpiryCacheImpl() {
		executorService = Executors.newSingleThreadScheduledExecutor();
		logger.info("Cache initialized");
	}

	@Override
	public void put(final K key, V value, final int ttl, final TimeUnit timeUnit) {
		cacheMap.put(key, value);
		executorService.schedule(new Callable<String>() {
			@Override
			public String call() {
				logger.info("Cache " + key.toString() + ", getting expired + "
						+ ttl + ":" + timeUnit);
				cacheMap.remove(key);
				return null;
			}
		}, ttl, timeUnit);
	}

	@Override
	public V get(K key) {
		return cacheMap.get(key);
	}

	@Override
	public void destroy() {
		logger.info("shutting down cache");
		cacheMap.clear();
		executorService.shutdownNow();
	}

}
