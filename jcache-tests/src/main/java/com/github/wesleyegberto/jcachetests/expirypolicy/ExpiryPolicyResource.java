package com.github.wesleyegberto.jcachetests.expirypolicy;

import java.util.concurrent.atomic.AtomicInteger;

import javax.cache.Cache;
import javax.inject.Inject;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.github.wesleyegberto.jcachetests.entity.Person;

@Path("expirypolicy")
public class ExpiryPolicyResource {
	private static AtomicInteger lastId = new AtomicInteger(0);

	@Inject
	@LimitedCache
	Cache<Integer, Person> peopleCache;
	
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void create(@Valid @NotNull Person person) {
		int newId = lastId.incrementAndGet();
		person.setId(newId);;
		peopleCache.put(newId, person);
	}
	
	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response getById(@PathParam("id") int id) {
		Person person = peopleCache.get(id);
		
		if(person == null)
			return Response.status(Status.NOT_FOUND).build();
		
		return Response.ok(person).build();
	}
}
