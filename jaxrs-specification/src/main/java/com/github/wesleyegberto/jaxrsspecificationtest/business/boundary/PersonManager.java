package com.github.wesleyegberto.jaxrsspecificationtest.business.boundary;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import javax.ejb.Singleton;

import com.github.wesleyegberto.jaxrsspecificationtest.business.entity.Person;

@Singleton
public class PersonManager {
	private List<Person> people = new ArrayList<>();

	private int fakeId = 0;
	
	public PersonManager() {
		people.add(new Person(++fakeId, "Bruce Banner", LocalDate.now()));
		people.add(new Person(++fakeId, "Tony Stark", LocalDate.now()));
		people.add(new Person(++fakeId, "Bruce Wayne", LocalDate.now()));
		people.add(new Person(++fakeId, "Clark Kent", LocalDate.now()));
		people.add(new Person(++fakeId, "Peter Park", LocalDate.now()));
	}

	public Person searchPersonById(int id) {
		Optional<Person> person = people.stream().filter(p -> p.getId() == id).findFirst();
		
		if(person.isPresent())
			return person.get();
		return null;
	}
		
	public List<Person> fetchAll() {
		return people;
	}
	
	public void create(Person person) {
		person.setId(++fakeId);
		people.add(person);
	}

	public void delete(int id) {
		Optional<Person> person = people.stream().filter(p -> p.getId() == id).findFirst();

		if(person.isPresent()) {
            people.remove(person.get());
        }
	}
}