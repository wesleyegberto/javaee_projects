package com.github.wesleyegberto.business.person.entity;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;
import javax.validation.groups.Default;

import com.github.wesleyegberto.beanvalidation.FullValidation;
import com.github.wesleyegberto.beanvalidation.MinimumValidation;

@Entity
@Table(name = "PERSON")
@NamedQuery(name = "Person.getAll", query = "SELECT p FROM Person p")
public class Person {
	@Id
	@GeneratedValue
	private long id;

	@NotNull(groups = {Default.class, MinimumValidation.class, FullValidation.class})
	private String name;

	@Temporal(TemporalType.DATE)
	@NotNull(groups = {FullValidation.class})
	private Date birthDate;

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	@Override
	public String toString() {
		return "Person [id=" + id + ", name=" + name + ", birthDate=" + birthDate + "]";
	}

	
}
