package com.github.wesleyegberto.jaxrsspecificationtest.aop;

import java.io.IOException;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;
import javax.ws.rs.ext.Provider;

import com.github.wesleyegberto.jaxrsspecificationtest.config.AdminAudited;

/**
 * Will be added dynamically to the resource.
 */
@Provider
//@PreMatching If we use PreMatching the NamedBinding will not be considered
// So instead we use @Priority
@Priority(Priorities.AUTHORIZATION)
@AdminAudited
public class HeaderAdminValidatorFilter implements ContainerRequestFilter {

	@Override
	public void filter(ContainerRequestContext reqCtx) throws IOException {
		System.out.println("[HeaderAdminValidatorFilter] Validating user admin: " + reqCtx.getHeaderString("Authorization"));
		if(reqCtx.getHeaderString("Authorization") == null || !reqCtx.getHeaderString("Authorization").contains("Wesley")) {
			reqCtx.abortWith(Response.status(Status.FORBIDDEN).build());
		}
	}

}
