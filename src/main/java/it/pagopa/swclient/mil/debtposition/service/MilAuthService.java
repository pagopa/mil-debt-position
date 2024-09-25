package it.pagopa.swclient.mil.debtposition.service;

import io.smallrye.mutiny.Uni;
import it.pagopa.swclient.mil.debtposition.client.MilAuthClient;
import it.pagopa.swclient.mil.debtposition.model.TokenInfoRequest;
import it.pagopa.swclient.mil.debtposition.model.TokenInfoResponse;
import it.pagopa.swclient.mil.debtposition.model.TokenResponse;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

@ApplicationScoped
public class MilAuthService {

    @RestClient
    MilAuthClient milAuthClient;

    @ConfigProperty(name = "quarkus.mil-auth-client.id")
    String clientId;

    @ConfigProperty(name = "quarkus.mil-auth-secret.id")
    String clientSecret;

    private static final String GRANT_TYPE = "client_credentials";

    public Uni<TokenResponse> getToken(String requestId) {
        return milAuthClient.getToken(requestId, GRANT_TYPE, clientId, clientSecret)
                .onFailure()
                .transform(error -> error)
                .onItem()
                .transform(tokenResponse -> tokenResponse);
    }

    public Uni<TokenInfoResponse> getTokenInfo(String requestId, String token, TokenInfoRequest request) {
        return milAuthClient.getFiscalCode(requestId, token, request)
                .onFailure()
                .transform(error -> error)
                .onItem()
                .transform(tokenInfoResponse -> tokenInfoResponse);
    }
}
