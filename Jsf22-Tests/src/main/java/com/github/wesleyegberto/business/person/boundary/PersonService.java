package com.github.wesleyegberto.business.person.boundary;

import java.util.List;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.TypedQuery;

import com.github.wesleyegberto.business.person.entity.Person;

@Stateless
public class PersonService {
	
	@PersistenceContext
	private EntityManager em;
	
	public void createPerson(Person person) {
		em.persist(person);
	}
	
	public void updatePerson(Person person) {
		Person merge = em.merge(person);
		System.out.println(merge);
	}
	
	public List<Person> fetchAll() {
		TypedQuery<Person> query = em.createNamedQuery("Person.getAll", Person.class);
		return query.getResultList();
	}
}
