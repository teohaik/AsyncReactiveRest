package gr.teohaik.async.rest;

import gr.teohaik.async.rest.config.RequestForWorkEvent;
import javax.enterprise.concurrent.ManagedExecutorService;
import java.util.concurrent.CompletableFuture;
import java.util.function.Supplier;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.enterprise.event.Event;
import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.container.AsyncResponse;
import javax.ws.rs.container.Suspended;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

/**
 *
 * @author Theodore Chaikalis
 */
@Stateless
@Path("async")
public class AsyncRestService {

    @Resource
    ManagedExecutorService mes;

    
    @Inject
    private Event<RequestForWorkEvent> workEvent;

    @GET
    @Path("testget")
    @Produces({MediaType.TEXT_HTML})
    public void testGetAsync(@Suspended AsyncResponse asyncResponse) {
        System.out.println("Test Rest Method started. Time = "+System.currentTimeMillis());

        Runnable trigger1Runnable = () -> trigger1();
        Runnable trigger2Runnable = () -> trigger2();
        Supplier responseSupplier = () -> Response.ok("work Scheduled with Completable Future", MediaType.TEXT_HTML).build();
        
        CompletableFuture
                .runAsync(trigger1Runnable)
                .thenRun(trigger2Runnable)
                .supplyAsync(responseSupplier)
                .thenApply((asyncResp) -> asyncResponse.resume(asyncResp));

    }

    public void trigger1() {
        workEvent.fireAsync(new RequestForWorkEvent(1));
    }

    public void trigger2() {
        workEvent.fireAsync(new RequestForWorkEvent(2));
    }

}
