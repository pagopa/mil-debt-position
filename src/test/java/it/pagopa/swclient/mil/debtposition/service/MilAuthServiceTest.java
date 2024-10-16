package it.pagopa.swclient.mil.debtposition.service;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.helpers.test.UniAssertSubscriber;
import it.pagopa.swclient.mil.debtposition.client.MilAuthClient;
import it.pagopa.swclient.mil.debtposition.model.TokenInfoRequest;
import it.pagopa.swclient.mil.debtposition.model.TokenInfoResponse;
import it.pagopa.swclient.mil.debtposition.model.TokenResponse;
import it.pagopa.swclient.mil.debtposition.util.TestData;
import jakarta.ws.rs.InternalServerErrorException;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import java.lang.reflect.Field;
import java.time.Instant;

import static it.pagopa.swclient.mil.debtposition.util.TestData.REQUEST_ID;
import static it.pagopa.swclient.mil.debtposition.util.TestData.VALID_TOKEN;
import static org.mockito.ArgumentMatchers.any;

@QuarkusTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class MilAuthServiceTest {

    @InjectMock
    @RestClient
    MilAuthClient milAuthClient;

    @Inject
    MilAuthService milAuthService;

    static TokenResponse tokenResponse;

    static TokenInfoResponse tokenInfoResponse;

    static TokenInfoRequest tokenInfoRequest;

    @BeforeAll
    static void createTestObjects() {
        tokenResponse = TestData.getTokenResponse();
        tokenInfoRequest = TestData.getTokenInfoRequest();
        tokenInfoResponse = TestData.getTokenInfoResponse();
    }

    @Test
    void testGetToken_Success() {
        Mockito.when(milAuthClient.getToken(any(String.class), any(String.class), any(String.class), any(String.class)))
                .thenReturn(Uni.createFrom().item(tokenResponse));

        Uni<TokenResponse> result = milAuthService.getToken(REQUEST_ID);

        result.subscribe()
                .with(token -> Assertions.assertEquals(tokenResponse, token));
    }

    @Test
    void testGetCachedToken_Success() throws NoSuchFieldException, IllegalAccessException {
        Field cachedTokenField = MilAuthService.class.getDeclaredField("cachedAccessToken");
        cachedTokenField.setAccessible(true);
        cachedTokenField.set(milAuthService, tokenResponse);

        tokenResponse.setExpiresIn(Instant.now().getEpochSecond() + 3600);

        Uni<TokenResponse> result = milAuthService.getToken(REQUEST_ID);

        result.subscribe()
                .with(token -> Assertions.assertEquals(tokenResponse, token));

        Mockito.verify(milAuthClient, Mockito.never())
                .getToken(any(String.class), any(String.class), any(String.class), any(String.class));
    }


    @Test
    void testGetToken_Error() {
        Mockito.when(milAuthClient.getToken(any(String.class), any(String.class), any(String.class), any(String.class)))
                .thenReturn(Uni.createFrom().failure(new InternalServerErrorException()));

        Uni<TokenResponse> result = milAuthService.getToken(REQUEST_ID);

        result.subscribe()
                .withSubscriber(UniAssertSubscriber.create())
                .assertFailedWith(InternalServerErrorException.class);
    }

    @Test
    void testGetTokenInfo_Success() {
        Mockito.when(milAuthClient.getFiscalCode(any(String.class), any(String.class), any(TokenInfoRequest.class)))
                .thenReturn(Uni.createFrom().item(tokenInfoResponse));

        Uni<TokenInfoResponse> result = milAuthService.getTokenInfo(REQUEST_ID, VALID_TOKEN, tokenInfoRequest);

        result.subscribe()
                .with(info -> Assertions.assertEquals(tokenInfoResponse, info));
    }

    @Test
    void testGetTokenInfo_Error() {
        Mockito.when(milAuthClient.getFiscalCode(any(String.class), any(String.class), any(TokenInfoRequest.class)))
                .thenReturn(Uni.createFrom().failure(new InternalServerErrorException()));

        Uni<TokenInfoResponse> result = milAuthService.getTokenInfo(REQUEST_ID, VALID_TOKEN, tokenInfoRequest);

        result.subscribe()
                .withSubscriber(UniAssertSubscriber.create())
                .assertFailedWith(InternalServerErrorException.class);
    }
}
