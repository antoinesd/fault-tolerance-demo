
package org.eclipse.microprofile.ft.servicea;
import javax.ws.rs.GET;
import javax.ws.rs.Path;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient
@Path("/")
public interface ServiceBClient{

    @GET
    public String callMe();
}