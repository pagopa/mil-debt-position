package it.pagopa.swclient.mil.debtposition.client;

import io.smallrye.mutiny.Uni;
import it.pagopa.swclient.mil.debtposition.model.TokenInfoRequest;
import it.pagopa.swclient.mil.debtposition.model.TokenInfoResponse;
import it.pagopa.swclient.mil.debtposition.model.TokenResponse;
import it.pagopa.swclient.mil.debtposition.utils.ErrorCodes;
import it.pagopa.swclient.mil.debtposition.utils.RegexPatterns;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Pattern;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

@RegisterRestClient(configKey = "mil-auth-rest-api")
public interface MilAuthClient {

    @POST
    @Path("/token")
    @Consumes(MediaType.APPLICATION_FORM_URLENCODED)
    Uni<TokenResponse> getToken(
            @HeaderParam("RequestId") @NotNull(message = ErrorCodes.ERROR_REQUESTID_MUST_NOT_BE_NULL_MSG)
            @Pattern(regexp = RegexPatterns.REQUEST_ID_PATTERN)
            String requestId,
            @FormParam("grant_type") @NotNull(message = ErrorCodes.ERROR_GRANT_TYPE_MUST_NOT_BE_NULL_MSG)
            String grantType,
            @FormParam("client_id") @NotNull(message = ErrorCodes.ERROR_CLIENT_ID_MUST_NOT_BE_NULL_MSG)
            String clientId,
            @FormParam("client_secret") @NotNull(message = ErrorCodes.ERROR_SECRET_ID_MUST_NOT_BE_NULL_MSG)
            String clientSecret);

    @POST
    @Path("/token_info")
    Uni<TokenInfoResponse> getFiscalCode(
            @HeaderParam("RequestId") @NotNull(message = ErrorCodes.ERROR_REQUESTID_MUST_NOT_BE_NULL_MSG)
            String requestId,
            @HeaderParam("Authorization")  @NotNull(message = ErrorCodes.ERROR_AUTHORIZATION_MUST_NOT_BE_NULL_MSG)
            String authorization,
            TokenInfoRequest tokenInfoRequest);
}
