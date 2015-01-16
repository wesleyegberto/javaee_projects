package com.github.wesleyegberto.presentation.person;

import java.util.List;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.inject.Named;

import com.github.wesleyegberto.business.person.boundary.PersonService;
import com.github.wesleyegberto.business.person.entity.Person;

@Named
@RequestScoped
public class IndexController {

	@Inject
	private PersonService service;

	private List<Person> persons;
	private Person person;

	public List<Person> getPersons() {
		if(persons == null) {
			persons = service.fetchAll();
		}
		return persons;
	}

	public Person getPerson() {
		if(person == null) {
			person = new Person();
		}
		return person;
	}

	public void setPerson(Person person) {
		this.person = person;
	}

	public boolean isEdition() {
		return person != null && person.getId() > 0;
	}

	public void insert() {
		service.createPerson(person);
		person = null;
	}

	public void save() {
		System.out.println("Updating: " + person);
		service.updatePerson(person);
		person = null;
	}
}
