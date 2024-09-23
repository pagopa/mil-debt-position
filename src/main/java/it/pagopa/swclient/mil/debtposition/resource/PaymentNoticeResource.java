package it.pagopa.swclient.mil.debtposition.resource;

import io.smallrye.mutiny.Uni;
import it.pagopa.swclient.mil.bean.CommonHeader;
import jakarta.validation.Valid;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.jwt.JsonWebToken;

@Path("/paymentNotices")
public class PaymentNoticeResource {

    @ConfigProperty(name = "mil.auth.client-id")
    String clientId;

    @ConfigProperty(name = "mil.auth.client-secret")
    String clientSecret;

    private final JsonWebToken jwt;

    public PaymentNoticeResource(JsonWebToken jwt) {
        this.jwt = jwt;
    }

    @GET
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> getPaymentNotices(@Valid @BeanParam CommonHeader headers) {

        return null;
    }
}
