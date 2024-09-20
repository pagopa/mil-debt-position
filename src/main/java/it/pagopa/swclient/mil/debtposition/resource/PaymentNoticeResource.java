package it.pagopa.swclient.mil.debtposition.resource;

import io.smallrye.mutiny.Uni;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("/paymentNotices")
public class PaymentNoticeResource {

    @GET
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    //@RolesAllowed({ "mil_papos_admin", "pos_service_provider" })
    public Uni<Response> getPaymentNotces() {

        return null;
    }
}
