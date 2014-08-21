package com.github.wesleyegberto.toyshop.business.catalog.entity;

import java.io.Serializable;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Access(AccessType.FIELD)
@Table(name = "PRODUCT")
@NamedQueries({
	@NamedQuery(name = Product.GET_ALL, query = "select p from Product p"),
})
public class Product implements Serializable {
	private static final long serialVersionUID = 4222956223964381356L;
	
	@Id	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Column(nullable = false)
	private String name;
	@Column(length = 2000, nullable = false)
	private String description;
	@Column(nullable = false)
	private String category;
	@Column(nullable = false)
	private String picture;
	@Column(nullable = false)
	private double price;
	@Column(nullable = false)
	private int stockQnty;
	
	/* constants with JPA queries' name (to avoid errors) */
	public static final String GET_ALL = "Product.getAll";
	
	
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

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public double getPrice() {
		return price;
	}

	public void setPrice(double price) {
		this.price = price;
	}

	public int getStockQnty() {
		return stockQnty;
	}

	public void setStockQnty(int stockQnty) {
		this.stockQnty = stockQnty;
	}

}
