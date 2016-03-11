package com.github.wesleyegberto.jaxrsspecificationtest.aop;

import com.github.wesleyegberto.jaxrsspecificationtest.business.boundary.PersonResources;

import javax.annotation.Priority;
import javax.ws.rs.DELETE;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.DynamicFeature;
import javax.ws.rs.container.ResourceInfo;
import javax.ws.rs.core.FeatureContext;
import javax.ws.rs.ext.Provider;

/**
 * Will add a filter if the request is a DELETE.
 * It is executed when the server is started and the endpoints are being registered.
 *
 * @author Wesley Egberto
 */
@Provider
@Priority(Priorities.AUTHENTICATION)
public class DynamicBinder implements DynamicFeature {

    @Override
    public void configure(ResourceInfo resInf, FeatureContext featureCtx) {
        System.out.println("[DynamicBinder] verifying: " + resInf.getResourceMethod().getName());

        if(PersonResources.class.equals(resInf.getResourceClass()) &&
                resInf.getResourceMethod().isAnnotationPresent(DELETE.class)) {
            System.out.println("[DynamicBinder] registered for " + resInf.getResourceMethod().getName());
            // Use new object if the class isn't annotated
            featureCtx.register(new HeaderAdminValidatorFilter());
        }
    }
}
