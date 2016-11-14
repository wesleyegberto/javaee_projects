package com.github.wesleyegberto.jcachetests.cacheannotations;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.atomic.AtomicInteger;

import javax.cache.annotation.CacheDefaults;
import javax.cache.annotation.CacheKey;
import javax.cache.annotation.CacheRemove;
import javax.cache.annotation.CacheResult;
import javax.ejb.Stateless;

import com.github.wesleyegberto.jcachetests.entity.Person;

@CacheDefaults(cacheName = "peopleCache")
@Stateless
public class PeopleManager {
	private static AtomicInteger lastId = new AtomicInteger(0);
	private static List<Person> database = Collections.synchronizedList(new ArrayList<Person>());
	
	public void createPerson(Person person) {
		int newId = lastId.incrementAndGet();
		person.setId(newId);
		System.out.println("Adding " + newId);
		database.add(person);
	}
	
	@CacheResult
	public Person getById(@CacheKey int id) {
		System.out.println("Getting " + id);
		return database.stream().filter(p -> p.getId() == id).findFirst().orElse(null);
	}
	
	public void updatePerson(Person person) {
		Optional<Person> persistedPerson = database.stream().filter(p -> p.getId() == person.getId()).findFirst();
		if(persistedPerson.isPresent())
			persistedPerson.get().setName(person.getName());
	}
	
	@CacheRemove
	public void deletePerson(@CacheKey int id) {
		System.out.println("Removing");
		database.removeIf(p -> p.getId() == id);
	}

	public List<Person> fetchAll() {
		return Collections.unmodifiableList(database);
	}
}
