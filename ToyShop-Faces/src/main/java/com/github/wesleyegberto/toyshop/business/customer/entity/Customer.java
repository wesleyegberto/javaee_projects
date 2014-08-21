package com.github.wesleyegberto.toyshop.business.customer.entity;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import org.hibernate.validator.constraints.Email;
import org.hibernate.validator.constraints.Length;
import org.hibernate.validator.constraints.NotEmpty;

import com.github.wesleyegberto.toyshop.business.security.control.Encryptor;

@Entity
@Table(name = "CUSTOMER", uniqueConstraints = @UniqueConstraint(columnNames = { "email" }))
@Access(AccessType.FIELD)
@NamedQuery(name = Customer.GET_BY_IDENTIFY, query = "select c from Customer c where c.email=?1 and c.password=?2")
public class Customer {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@NotEmpty 
	private String name;
	 @NotEmpty @Email 
	private String email;
	@NotEmpty @Length(min = 6, message = "Password too short, must be at least 6 characters")
	private String password;
	private String address;
	private String city;
	private String zipCode;

	public static final String GET_BY_IDENTIFY = "Customer.getByIdentify";

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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = Encryptor.encrypt(password);
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	
	

}
