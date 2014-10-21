package com.github.wesleyegberto.toyshop.business.order.entity;

import java.util.Date;
import java.util.List;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.github.wesleyegberto.toyshop.business.catalog.entity.Product;
import com.github.wesleyegberto.toyshop.business.customer.entity.Customer;

@Entity
@Table(name = "WEB_ORDER")
@Access(AccessType.FIELD)
@NamedQueries({
	@NamedQuery(name = Order.GET_ALL, query = "select ord from Order ord"),
	@NamedQuery(name = Order.GET_BY_ID, query = "select o from Order o join fetch o.products where o.id = :id"),
	@NamedQuery(name = Order.GET_BY_PERIOD, query = "select o from Order o where o.orderDate between :initialDate and :finalDate")
})
public class Order {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	@Temporal(TemporalType.TIMESTAMP)
	private Date orderDate;
	private double total;
	@ManyToOne(cascade = CascadeType.PERSIST, optional = false)
	private Customer customer;
	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinTable(name = "WEB_ORDER_PRODUCT", joinColumns = { @JoinColumn(name = "WEB_ORDER_id", referencedColumnName = "id") }, inverseJoinColumns = { @JoinColumn(name = "products_ID", referencedColumnName = "id") })
	private List<Product> products;

	private String address;
	private String city;
	private String zipCode;
	@Enumerated(EnumType.STRING)
	private OrderStatus status = OrderStatus.STARTED;

	public static final String GET_ALL = "Order.getAll";
	public static final String GET_BY_ID = "Order.getById";
	public static final String GET_BY_PERIOD = "Order.getByPeriod";

	public Order() {
		this.orderDate = new Date();
		System.out.println(orderDate);
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public Date getOrderDate() {
		return orderDate;
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Customer getCustomer() {
		return customer;
	}

	public void setCustomer(Customer customer) {
		this.customer = customer;
	}

	public double getTotal() {
		return total;
	}

	public void setTotal(double total) {
		this.total = total;
	}

	public List<Product> getProducts() {
		return products;
	}

	public void setProducts(List<Product> products) {
		this.products = products;
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

	public OrderStatus getStatus() {
		return status;
	}

	public void setStatus(OrderStatus status) {
		this.status = status;
	}

	public boolean isFinished() {
		return status == OrderStatus.FINISHED;
	}

	@Override
	public String toString() {
		return "Order [id=" + id + ", orderDate=" + orderDate + ", total=" + total + ", customer=" + customer.getName() + "]";
	}
	
	
}
