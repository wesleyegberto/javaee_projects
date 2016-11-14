package com.github.wesleyegberto.jcachetests.cacheannotations;

import java.util.List;

import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.github.wesleyegberto.jcachetests.entity.Person;

@Path("/people")
public class PeopleResource {

	@Inject
	private PeopleManager peopleManager;
	
	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Person> fetchAll() {
		return peopleManager.fetchAll();
	}
	
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void create(@Valid @NotNull Person person) {
		peopleManager.createPerson(person);
	}
	
	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response getById(@PathParam("id") int id) {
		Person person = peopleManager.getById(id);
		
		if(person == null)
			return Response.status(Status.NOT_FOUND).build();
		
		return Response.ok(person).build();
	}

	@PUT
	@Path("{id}")
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void update(@PathParam("id") int id, @Valid @NotNull Person person) {
		person.setId(id);
		peopleManager.updatePerson(person);
	}
	
	@DELETE
	@Path("{id}")
	public void delete(@PathParam("id") int id) {
		peopleManager.deletePerson(id);
	}
}
