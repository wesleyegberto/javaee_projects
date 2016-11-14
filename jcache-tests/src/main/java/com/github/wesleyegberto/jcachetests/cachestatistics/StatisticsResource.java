package com.github.wesleyegberto.jcachetests.cachestatistics;

import java.lang.management.ManagementFactory;
import java.util.Set;
import java.util.concurrent.atomic.AtomicInteger;

import javax.annotation.PostConstruct;
import javax.cache.Cache;
import javax.ejb.Singleton;
import javax.inject.Inject;
import javax.management.AttributeNotFoundException;
import javax.management.InstanceNotFoundException;
import javax.management.MBeanException;
import javax.management.MBeanServer;
import javax.management.MalformedObjectNameException;
import javax.management.ObjectName;
import javax.management.ReflectionException;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.Consumes;
import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.github.wesleyegberto.jcachetests.cdi.qualifiers.CustomCache;
import com.github.wesleyegberto.jcachetests.entity.Person;

@Path("statistics")
@Singleton
public class StatisticsResource {
	private static AtomicInteger lastId = new AtomicInteger(0);

	@Inject
	@CustomCache
	Cache<Integer, Person> peopleCache;

	private ObjectName objectName;
	private MBeanServer mBeanServer;

	@PostConstruct
	public void init() {
		mBeanServer = ManagementFactory.getPlatformMBeanServer();
		try {
			String name = "javax.cache:type=CacheStatistics,CacheManager=\""
					+ (peopleCache.getCacheManager().getURI().toString())
					+ "\",Cache=\"" + peopleCache.getName() + "\"";
			//System.out.println("Object name: " + name);
			objectName = new ObjectName(name);
		} catch(MalformedObjectNameException e) {
			e.printStackTrace();
		}
	}

	@POST
	@Consumes({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public void create(@Valid @NotNull Person person) {
		int newId = lastId.incrementAndGet();
		person.setId(newId);
		peopleCache.put(newId, person);
	}

	@GET
	@Path("{id: \\d}")
	@Produces({ MediaType.APPLICATION_XML, MediaType.APPLICATION_JSON })
	public Response getById(@PathParam("id") int id) {
		Person person = peopleCache.get(id);

		if(person == null)
			return Response.status(Status.NOT_FOUND).build();

		return Response.ok(person).build();
	}

	@DELETE
	@Path("{id: \\d}")
	public void delete(@PathParam("id") int id) {
		peopleCache.remove(id);
	}
	
	@GET
	@Path("/hits")
	public Response getCacheHits() {
		try {
			Object hits = mBeanServer.getAttribute(objectName, "CacheHits");
			return Response.ok(hits).build();
		} catch(AttributeNotFoundException | InstanceNotFoundException | MBeanException | ReflectionException e) {
			return Response.serverError().entity(e.toString()).build();
		}
	}
	
	@GET
	@Path("/misses")
	public Response getCacheMisses() {
		try {
			Object misses = mBeanServer.getAttribute(objectName, "CacheMisses");
			return Response.ok(misses).build();
		} catch(AttributeNotFoundException | InstanceNotFoundException | MBeanException | ReflectionException e) {
			return Response.serverError().entity(e.toString()).build();
		}
	}
}
