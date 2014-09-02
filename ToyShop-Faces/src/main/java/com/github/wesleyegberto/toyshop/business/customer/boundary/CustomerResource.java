package com.github.wesleyegberto.toyshop.business.customer.boundary;

import java.net.URI;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.github.wesleyegberto.toyshop.business.customer.entity.Customer;

@Path("customers")
public class CustomerResource {

	@Inject
	private CustomerManager customerManager;

	public CustomerResource() {
		System.out.println("[REST] CustomerResource created");
	}
	
	@Path("{id}")
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Customer getCustomer(@PathParam("id") long id) {
		return customerManager.getCustomerById(id);
	}

	/*
	 * To test:
	 * curl -v -X POST -H "Content-type: application/json" -d
	 * "{\"name\":\"John Cornor\",\"email\":\"john.cornor@antiskynet.com\",\"password\":\"skynetnewbie\"}"
	 * http://localhost:8080/ToyShop-Faces/services/customers/register 
	 */
	@Path("register")
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response registerCustomer(@Valid Customer customer) {
		customerManager.createNewCustomer(customer);
		return Response.created(URI.create("/customers/" + customer.getId())).build();
	}
}
