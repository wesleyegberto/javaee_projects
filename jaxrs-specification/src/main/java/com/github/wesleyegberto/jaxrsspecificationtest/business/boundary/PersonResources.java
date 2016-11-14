package com.github.wesleyegberto.jaxrsspecificationtest.business.boundary;

import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.List;

import javax.inject.Inject;
import javax.ws.rs.Consumes;
import javax.ws.rs.FormParam;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.github.wesleyegberto.jaxrsspecificationtest.business.entity.Person;
import com.github.wesleyegberto.jaxrsspecificationtest.config.AdminAudited;
import com.github.wesleyegberto.jaxrsspecificationtest.config.Audited;

/**
 * @author Wesley Egberto
 */
@Path("/person")
public class PersonResources {

	@Inject
	private PersonManager personManager;

	@GET
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public List<Person> getPeople() {
		return personManager.fetchAll();
	}

	@GET
	@Path("/{id: \\d+}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Audited
	public Response getPersonById(@PathParam("id") int id) {
        System.out.println("Getting " + id);
        Person person = personManager.searchPersonById(id);
		if(person == null)
			return Response.status(Status.NOT_FOUND).build();

		return Response.ok(person).build();
	}


	@POST
    @Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @Audited
	public Response createPerson(@FormParam("name") String name, @FormParam("birthDate") String birthDate) {
		if(name == null || birthDate == null)
			return Response.status(Status.BAD_REQUEST).entity("Name and birth date are required").build();

		try {
			Person person = new Person(name, LocalDate.parse(birthDate));
			personManager.create(person);
			return Response.ok("Person created successfully").build();
		} catch(DateTimeParseException ex) {
			return Response.status(Status.BAD_REQUEST).entity("Invalid birth date: " + ex.getMessage()).build();
		}
	}

    //@DELETE it is annotated dynamically
	@Path("/{id: \\d+}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
    @AdminAudited
	public Response deletePerson(@PathParam("id") int id) {
        System.out.println("Deleting " + id);
        personManager.delete(id);
		return Response.ok("Person deleted successfully").build();
	}

}
