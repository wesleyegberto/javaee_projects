package com.github.wesleyegberto.jcachetests.producer;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.CacheEntryListenerConfiguration;
import javax.cache.configuration.FactoryBuilder;
import javax.cache.configuration.MutableCacheEntryListenerConfiguration;
import javax.cache.configuration.MutableConfiguration;
import javax.enterprise.inject.Produces;

import com.github.wesleyegberto.jcachetests.cdi.qualifiers.CustomCache;
import com.github.wesleyegberto.jcachetests.entity.Person;
import com.github.wesleyegberto.jcachetests.listeners.CacheModificationListener;

public class CacheProducer {

	private static final String CACHE_NAME = "PERSON_CACHE";
	
	@Produces
	@CustomCache
	public Cache<Integer, Person> produces() {
		CacheManager manager = Caching.getCachingProvider().getCacheManager();
		
		Cache<Integer, Person> cache = manager.getCache(CACHE_NAME, Integer.class, Person.class);
		if(cache == null) {
			MutableConfiguration<Integer, Person> configuration = new MutableConfiguration<>();
			configuration.setTypes(Integer.class, Person.class);
			configuration.setStatisticsEnabled(true);
				
			cache = manager.createCache(CACHE_NAME, configuration);
			
			CacheEntryListenerConfiguration<Integer, Person> listenersConfiguration
				= new MutableCacheEntryListenerConfiguration<>(FactoryBuilder.factoryOf(CacheModificationListener.class), null, false, true);
			cache.registerCacheEntryListener(listenersConfiguration);
		}
		
		return cache;
	}
}
