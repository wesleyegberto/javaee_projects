package com.github.wesleyegberto.presentation.person;

import javax.enterprise.context.RequestScoped;
import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.inject.Inject;
import javax.inject.Named;

import com.github.wesleyegberto.business.person.boundary.PersonService;
import com.github.wesleyegberto.business.person.entity.Person;

@Named
@RequestScoped
public class PersonController {
	@Inject
	private PersonService personService;
	
	private Person person;

	public Person getPerson() {
		if(person == null) {
			person = new Person();
		}
		return person;
	}
	
	public void save() {
		personService.createPerson(person);
		FacesContext.getCurrentInstance().addMessage(null, new FacesMessage("Person saved."));
	}
}
