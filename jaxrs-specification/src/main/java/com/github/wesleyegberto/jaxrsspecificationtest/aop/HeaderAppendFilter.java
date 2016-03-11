package com.github.wesleyegberto.jaxrsspecificationtest.aop;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.container.PreMatching;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * Example of filter request which is executed AFTER the endpoint.
 *
 * Global default: by defaul all request to endpoints are filtered.
 *
 * @author Wesley Egberto
 */
@Provider
public class HeaderAppendFilter implements ContainerRequestFilter {
    @Override
    public void filter(ContainerRequestContext reqCtx) throws IOException {
        System.out.println("[HeaderAppendFilter] Endpoint's Base URI: " + reqCtx.getUriInfo().getBaseUri());
    }
}
