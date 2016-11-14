package com.github.wesleyegberto.jcachetests.setup;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.spi.CachingProvider;

public class SimpleUsage {
	public static void main(String[] args) {
		// Get the default provider which is specified at EHCache jar
		// following the specification (/META-INF/services/javax.cache.spi.CachingProvider)
		CachingProvider provider = Caching.getCachingProvider();
		// Manage the caches
		CacheManager manager = provider.getCacheManager();
		
		// Used to customize the Cache
		MutableConfiguration<String, String> cacheConfig = new MutableConfiguration<>();
		cacheConfig.setTypes(String.class, String.class);
		
		// Creates a cache using the configurations
		Cache<String,String> cache = manager.createCache("First-Cache", cacheConfig);
		
		// Puts a simple value and get it (like Map)
		cache.put("someKey", "someValue");
		System.out.println("Cached value: " + cache.get("someKey"));
	}
}
