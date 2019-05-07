
package org.eclipse.microprofile.ft.servicea;
import javax.ws.rs.GET;

import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;
@RegisterRestClient
public interface ServiceBClient{

    @GET
    public String callMe();
}