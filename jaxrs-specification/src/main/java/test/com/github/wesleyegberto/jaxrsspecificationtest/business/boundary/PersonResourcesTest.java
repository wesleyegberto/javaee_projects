package test.com.github.wesleyegberto.jaxrsspecificationtest.business.boundary;

import org.junit.Before;
import org.junit.Test;

import javax.ws.rs.client.*;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import static javax.ws.rs.core.Response.Status;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

/**
 * @author Wesley Egberto
 */
public class PersonResourcesTest {

    private WebTarget target;

    @Before
    public void init() {
        Client client = ClientBuilder.newClient();
        target = client.target("http://localhost:8080/jaxrs-specification/resources/");
    }

    @Test
    public void testGetPersonByIdWithoutAuthorization() throws Exception {
        Invocation request = target.path("person/{id}").resolveTemplate("id", 1)
                                    .request(MediaType.APPLICATION_JSON_TYPE).buildGet();
        Response response = request.invoke();
        assertThat(response.getStatus(), is(Status.FORBIDDEN.getStatusCode()));
    }

    @Test
    public void testGetPersonByIdWithAuthorization() throws Exception {
        final int ID = 1;
        final String USER = "Odair";
        Invocation request = target.path("person/{id}").resolveTemplate("id", ID)
                // Filter to add a header before the request
                .register((ClientRequestFilter) cliReqCtx -> cliReqCtx.getHeaders().add("Authorization", USER))
                // Filter to assert the authorization header (is executed before the return to the caller)
                .register((ClientResponseFilter) (cliReqCtx, cliRespCtx) -> {
                    assertThat(cliRespCtx.getStatus(), is(Status.OK.getStatusCode()));
                    assertThat(cliRespCtx.getHeaderString("Authorization-Response"), is(USER + " has logged in"));
                })
                .request(MediaType.APPLICATION_XML).buildGet();
        Response response = request.invoke();
        assertThat(response.getStatus(), is(Status.OK.getStatusCode()));
    }

    @Test
    public void testDeletePersonWithoutAuthorization() throws Exception {
        Invocation request = target.path("person/{id}").resolveTemplate("id", 2)
                .request(MediaType.APPLICATION_JSON_TYPE).buildDelete();
        Response response = request.invoke();
        assertThat(response.getStatus(), is(Status.FORBIDDEN.getStatusCode()));
    }

    @Test
    public void testDeletePersonWithAuthorization() throws Exception {
        final int ID = 2;
        final String USER = "Wesley";
        Invocation request = target.path("person/{id}").resolveTemplate("id", ID)
                .register((ClientRequestFilter) cliReqCtx -> cliReqCtx.getHeaders().add("Authorization", USER))
                .request(MediaType.APPLICATION_XML).buildDelete();
        Response response = request.invoke();
        assertThat(response.getStatus(), is(Status.OK.getStatusCode()));
    }
}