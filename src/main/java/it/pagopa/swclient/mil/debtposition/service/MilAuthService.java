package it.pagopa.swclient.mil.debtposition.service;

import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import it.pagopa.swclient.mil.bean.Errors;
import it.pagopa.swclient.mil.debtposition.client.MilAuthClient;
import it.pagopa.swclient.mil.debtposition.model.TokenInfoRequest;
import it.pagopa.swclient.mil.debtposition.model.TokenInfoResponse;
import it.pagopa.swclient.mil.debtposition.model.TokenResponse;
import it.pagopa.swclient.mil.debtposition.utils.ErrorCodes;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.ws.rs.InternalServerErrorException;
import jakarta.ws.rs.core.Response;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class MilAuthService {

    @RestClient
    MilAuthClient milAuthClient;

    @ConfigProperty(name = "mil_auth_client_id")
    String clientId;

    @ConfigProperty(name = "mil_auth_client_secret")
    String clientSecret;

    private static final String GRANT_TYPE = "client_credentials";

    public Uni<TokenResponse> getToken(String requestId) {
        return milAuthClient.getToken(requestId, GRANT_TYPE, clientId, clientSecret)
                .onFailure()
                .transform(err -> {
                    Log.errorf(err, "MilAuthService -> getToken: unexpected error during get token for requestId [%s]", requestId);

                    return new InternalServerErrorException(Response
                            .status(Response.Status.INTERNAL_SERVER_ERROR)
                            .entity(new Errors(ErrorCodes.ERROR_GENERIC_TOKEN, ErrorCodes.ERROR_GENERIC_TOKEN_MSG))
                            .build());
                })
                .onItem()
                .transform(tokenResponse -> {
                    Log.debugf("MilAuthService -> getToken: token retrieved correctly [%s]", tokenResponse);

                    return tokenResponse;
                });
    }

    public Uni<TokenInfoResponse> getTokenInfo(String requestId, String token, TokenInfoRequest request) {
        return milAuthClient.getFiscalCode(requestId, token, request)
                .onFailure()
                .transform(err -> {
                    Log.errorf(err, "MilAuthService -> getTokenInfo: unexpected error during get fiscal code for requestId [%s]", requestId);

                    return new InternalServerErrorException(Response
                            .status(Response.Status.INTERNAL_SERVER_ERROR)
                            .entity(new Errors(ErrorCodes.ERROR_GENERIC_TOKEN_INFO, ErrorCodes.ERROR_GENERIC_TOKEN_INFO_MSG))
                            .build());
                })
                .onItem()
                .transform(tokenInfoResponse -> {
                    Log.debugf("MilAuthService -> getTokenInfo: fiscal code retrieved correctly [%s]", tokenInfoResponse);

                    return tokenInfoResponse;
                });
    }
}
