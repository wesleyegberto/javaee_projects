package com.github.wesleyegberto.jaxrsspecificationtest.aop;

import com.github.wesleyegberto.jaxrsspecificationtest.config.AdminAudited;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * Will be added dynamically to the resource.
 */
// When I uncomment the lines bellow the dynamic binding doesn't work
//@Provider
//@PreMatching
//@AdminAudited
// Did the same as HeaderValidatorFilter, I used @Priority to replace @PreMatching
@Priority(Priorities.AUTHORIZATION)
public class HeaderAdminValidatorFilter implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext reqCtx) throws IOException {
		System.out.println("[HeaderAdminValidatorFilter] Validating user admin: " + reqCtx.getHeaderString("Authorization"));
		if(reqCtx.getHeaderString("Authorization") == null || !reqCtx.getHeaderString("Authorization").contains("Wesley")) {
			reqCtx.abortWith(Response.status(Status.FORBIDDEN).build());
		}
	}

}
