package com.github.wesleyegberto.jcachetests.expirypolicy;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

import javax.cache.Cache;
import javax.cache.CacheManager;
import javax.cache.Caching;
import javax.cache.configuration.CacheEntryListenerConfiguration;
import javax.cache.configuration.FactoryBuilder;
import javax.cache.configuration.MutableCacheEntryListenerConfiguration;
import javax.cache.configuration.MutableConfiguration;
import javax.cache.expiry.CreatedExpiryPolicy;
import javax.cache.expiry.Duration;
import javax.enterprise.inject.Produces;
import javax.inject.Qualifier;

import com.github.wesleyegberto.jcachetests.entity.Person;
import com.github.wesleyegberto.jcachetests.listeners.CacheModificationListener;

public class LimitedCacheExposer {
	private static final String CACHE_NAME = "LIMITED_CACHE";

	@Produces
	@LimitedCache
	public Cache<Integer, Person> produce() {
		CacheManager manager = Caching.getCachingProvider().getCacheManager();

		Cache<Integer, Person> cache = manager.getCache(CACHE_NAME, Integer.class, Person.class);
		if(cache == null) {
			MutableConfiguration<Integer, Person> configuration = new MutableConfiguration<>();
			configuration.setTypes(Integer.class, Person.class);
			configuration.setExpiryPolicyFactory(CreatedExpiryPolicy.factoryOf(Duration.ONE_MINUTE));

			cache = manager.createCache(CACHE_NAME, configuration);

			CacheEntryListenerConfiguration<Integer, Person> listenersConfiguration = new MutableCacheEntryListenerConfiguration<>(
					FactoryBuilder.factoryOf(CacheModificationListener.class), null, false, true);
			cache.registerCacheEntryListener(listenersConfiguration);
		}

		return cache;
	}
}

@Target({ ElementType.FIELD, ElementType.METHOD })
@Retention(RetentionPolicy.RUNTIME)
@Qualifier
@interface LimitedCache {
}
