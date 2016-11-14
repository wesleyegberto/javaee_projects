package com.github.wesleyegberto.jcachetests.integrationbridge;

import java.util.concurrent.atomic.AtomicInteger;

import javax.cache.Cache;
import javax.enterprise.cache.CacheContext;
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

@Path("integrationbridge")
public class PersonResources {

	private static AtomicInteger lastId = new AtomicInteger(0);
	
    @Inject
    @CacheContext("personCacheFile")
	Cache<Integer, Person> peopleCache;
	
	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void create(@Valid @NotNull Person person) {
		int newId = lastId.incrementAndGet();
		person.setId(newId);
		
		System.out.println("Creating " + person);
		peopleCache.put(newId, person);
	}
	
	@GET
	@Path("{id}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response getById(@PathParam("id") int id) {
		System.out.println("Getting " + id);
		Person person = peopleCache.get(id);
		
		if(person == null)
			return Response.status(Status.NOT_FOUND).build();
		
		return Response.ok(person).build();
	}
	
}
