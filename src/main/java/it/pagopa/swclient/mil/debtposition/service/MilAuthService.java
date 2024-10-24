package it.pagopa.swclient.mil.debtposition.service;

import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import it.pagopa.swclient.mil.debtposition.client.MilAuthClient;
import it.pagopa.swclient.mil.debtposition.model.TokenInfoRequest;
import it.pagopa.swclient.mil.debtposition.model.TokenInfoResponse;
import it.pagopa.swclient.mil.debtposition.model.TokenResponse;
import it.pagopa.swclient.mil.debtposition.utils.WebAppExcUtils;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.config.inject.ConfigProperty;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.time.Instant;

@ApplicationScoped
public class MilAuthService {

    @RestClient
    MilAuthClient milAuthClient;

    @ConfigProperty(name = "quarkus.mil-auth-client.id")
    String clientId;

    @ConfigProperty(name = "quarkus.mil-auth-secret.id")
    String clientSecret;

    private static final String GRANT_TYPE = "client_credentials";

    private TokenResponse cachedAccessToken;

    public Uni<TokenResponse> getToken(String requestId) {
        if (cachedAccessToken != null && cachedAccessToken.getExpiresIn() > Instant.now().getEpochSecond()) {
            return Uni.createFrom().item(cachedAccessToken);
        }

        return milAuthClient.getToken(requestId, GRANT_TYPE, clientId, clientSecret)
                .onFailure()
                .transform(error -> error)
                .onItem()
                .transform(tokenResponse -> {
                    cachedAccessToken = tokenResponse;
                    return tokenResponse;
                });
    }

    public Uni<TokenInfoResponse> getTokenInfo(String requestId, String token, TokenInfoRequest request) {
        return milAuthClient.getFiscalCode(requestId, token, request)
                .onFailure(WebAppExcUtils::isUnauthorizedOrForbidden)
                .recoverWithUni(() -> {
                    Log.debug("Token expired or invalid, attempting to obtain a new token.");
                    cachedAccessToken = null;

                    return getToken(requestId)
                            .onItem()
                            .transformToUni(newToken -> milAuthClient.getFiscalCode(requestId, newToken.getToken(), request));
                })
                .onItem()
                .transform(tokenInfoResponse -> tokenInfoResponse);
    }
}
