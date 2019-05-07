
package org.eclipse.microprofile.ft.servicea;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Future;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.eclipse.microprofile.faulttolerance.Asynchronous;
import org.eclipse.microprofile.faulttolerance.Fallback;
import org.eclipse.microprofile.faulttolerance.Retry;
import org.eclipse.microprofile.faulttolerance.Timeout;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@Path("/")
@RequestScoped
public class ServiceAEndpoint {

	@Inject @RestClient
	
	ServiceBClient client;
	
	@Inject ServiceAEndpoint self;
	
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	@Timeout(1000)
	@Retry(maxRetries=1)
	@Fallback(fallbackMethod="fallback")
	public String callService() throws Exception {
		return self.hopService().get();
	}
	
	@Asynchronous
	public Future<String> hopService() throws Exception {
		return CompletableFuture.completedFuture(client.callMe());
		
	}
	
	public String fallback() {
		return "I will do it myself from ServiceA.";
	}
	
	
	
}