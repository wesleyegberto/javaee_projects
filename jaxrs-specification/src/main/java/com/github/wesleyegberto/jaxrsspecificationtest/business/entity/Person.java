package com.github.wesleyegberto.jaxrsspecificationtest.business.entity;

import java.time.LocalDate;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.adapters.XmlJavaTypeAdapter;

import com.github.wesleyegberto.jaxrsspecificationtest.util.adapters.LocalDateAdapter;

/**
 * @author Wesley Egberto
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Person {
	private int id;
	private String name;
	@XmlJavaTypeAdapter(LocalDateAdapter.class)
	private LocalDate birthDate;

	public Person() {
	}

	public Person(String name, LocalDate birthDate) {
		this.name = name;
		this.birthDate = birthDate;
	}

	public Person(int id, String name, LocalDate birthDate) {
		this(name, birthDate);
		this.id = id;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDate getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(LocalDate birthDate) {
		this.birthDate = birthDate;
	}
}
