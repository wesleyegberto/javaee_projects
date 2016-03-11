package com.github.wesleyegberto.jaxrsspecificationtest.aop;

import com.github.wesleyegberto.jaxrsspecificationtest.config.Audited;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

/**
 * Example of filter request which is executed BEFORE the endpoint when has @PreMatching annotation.
 *
 * Named binding: With a qualifier it becomes named binding (like interceptors).
 *
 * @author Wesley Egberto
 */
@Provider
// When I uncomment @PreMatching the method PersonResources.deletePerson() is intercepted even without @Autited
//@PreMatching
// So to do the same as @PreMatching I used @Priority
@Priority(Priorities.AUTHORIZATION)
@Audited
public class HeaderValidatorFilter implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext reqCtx) throws IOException {
        System.out.println("[HeaderValidatorFilter] Validating user: " + reqCtx.getHeaderString("Authorization"));
        // check whether the Authorization header was sent
		if(reqCtx.getHeaderString("Authorization") == null) {
			// abort the chaining request
			reqCtx.abortWith(Response.status(Status.FORBIDDEN).build());
		} else if(!reqCtx.getHeaderString("Authorization").contains("Odair")) { // validate the authorization
			reqCtx.abortWith(Response.status(Status.FORBIDDEN).entity("Invalid user").build());
		}
	}

}
