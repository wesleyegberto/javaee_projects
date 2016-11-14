package com.github.wesleyegberto.jcachetests.listeners;

import javax.cache.event.CacheEntryCreatedListener;
import javax.cache.event.CacheEntryEvent;
import javax.cache.event.CacheEntryListenerException;
import javax.cache.event.CacheEntryRemovedListener;

import com.github.wesleyegberto.jcachetests.entity.Person;

public class CacheModificationListener implements CacheEntryCreatedListener<Integer, Person>,
												  CacheEntryRemovedListener<Integer, Person> {

	@Override
	public void onCreated(Iterable<CacheEntryEvent<? extends Integer, ? extends Person>> events)
			throws CacheEntryListenerException {
		events.forEach(entryEvent -> {
			Person personBeingAdded = entryEvent.getValue();
			System.out.println("[Listener] Creating " + personBeingAdded);
		});
	}
	
	@Override
	public void onRemoved(Iterable<CacheEntryEvent<? extends Integer, ? extends Person>> events)
			throws CacheEntryListenerException {
		events.forEach(entryEvent -> {
			Person personBeingRemoved = entryEvent.getOldValue();
			System.out.println("[Listener] Removing " + personBeingRemoved);
		});
	}


}
