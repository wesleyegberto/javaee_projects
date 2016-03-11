package com.github.wesleyegberto.jaxrsspecificationtest.aop;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import javax.ws.rs.ext.Provider;
import java.io.IOException;

/**
 * @author Wesley Egberto
 */
@Provider
public class ResponseCompressFilter implements ContainerResponseFilter {
    @Override
    public void filter(ContainerRequestContext reqCtx, ContainerResponseContext respCtx) throws IOException {
        System.out.println("[ResponseCompressFilter] Response status: " + respCtx.getStatus());

        String authorizationHeader = reqCtx.getHeaderString("Authorization");
        if(authorizationHeader != null) {
            respCtx.getHeaders().add("Authorization-Response", authorizationHeader + " has logged in");
        }
        // Compress the response if it accepts
        String encondingHeader = reqCtx.getHeaderString("Accept-Encoding");
        if(encondingHeader != null && encondingHeader.contains("gzip")) {
            respCtx.getHeaders().add("Content-Encoding", "gzip");
            // the compression logic must be in an interceptor
        }
    }
}
