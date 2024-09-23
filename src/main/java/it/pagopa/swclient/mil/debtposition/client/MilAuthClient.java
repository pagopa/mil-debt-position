package it.pagopa.swclient.mil.debtposition.client;

import io.smallrye.mutiny.Uni;
import it.pagopa.swclient.mil.debtposition.model.TokenInfoRequest;
import it.pagopa.swclient.mil.debtposition.model.TokenInfoResponse;
import it.pagopa.swclient.mil.debtposition.model.TokenResponse;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "mil-auth-rest-api")
public interface MilAuthClient {

    @POST
    @Path("/token")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    Uni<TokenResponse> getToken(
            @HeaderParam("RequestId")
            @NotNull String requestId,
            @FormParam("grant_type") String grantType,
            @FormParam("client_id") String clientId,
            @FormParam("client_secret") String clientSecret);

    @POST
    @Path("/token_info")
    Uni<TokenInfoResponse> getFiscalCode(
            @HeaderParam("RequestId") @NotNull String requestId,
            @HeaderParam("Authorization") @NotNull String authorization,
            TokenInfoRequest tokenInfoRequest);
}
