package it.pagopa.swclient.mil.debtposition.resource;

import io.quarkus.test.InjectMock;
import io.quarkus.test.common.http.TestHTTPEndpoint;
import io.quarkus.test.junit.QuarkusTest;
import io.quarkus.test.security.TestSecurity;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import io.smallrye.mutiny.Uni;
import it.pagopa.swclient.mil.debtposition.model.*;
import it.pagopa.swclient.mil.debtposition.service.MilAuthService;
import it.pagopa.swclient.mil.debtposition.service.PaymentNoticeService;
import it.pagopa.swclient.mil.debtposition.util.TestData;
import jakarta.ws.rs.InternalServerErrorException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static io.restassured.RestAssured.given;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;

@QuarkusTest
@TestHTTPEndpoint(PaymentNoticeResource.class)
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PaymentNoticeResourceTest {

    @InjectMock
    MilAuthService milAuthService;

    @InjectMock
    PaymentNoticeService paymentNoticeService;

    static TokenResponse tokenResponse;
    static TokenInfoResponse tokenInfoResponse;
    static TokenInfoRequest tokenInfoRequest;
    static Debt debt;
    static PaymentNoticesResponse paymentNoticesResponse;
    static Map<String, String> validMilHeaders;

    @BeforeAll
    static void createTestObjects() {
        tokenResponse = TestData.getTokenResponse();
        tokenInfoRequest = TestData.getTokenInfoRequest();
        tokenInfoResponse = TestData.getTokenInfoResponse();
        paymentNoticesResponse = TestData.getPaymentNoticesResponse();
        debt = TestData.getDebt();
        validMilHeaders = TestData.getMilHeaders();
    }

    @Test
    @TestSecurity(user = "testUser", roles = { "mil_debt_position_admin" })
    void testGetPaymentNotices_Success() {
        Mockito.when(milAuthService.getToken(TestData.REQUEST_ID))
                .thenReturn(Uni.createFrom().item(tokenResponse));

        Mockito.when(milAuthService.getTokenInfo(eq(TestData.REQUEST_ID), eq(TestData.VALID_TOKEN), any(TokenInfoRequest.class)))
                .thenReturn(Uni.createFrom().item(tokenInfoResponse));

        Mockito.when(paymentNoticeService.getPaymentNotices(TestData.FISCAL_CODE))
                .thenReturn(Uni.createFrom().item(Collections.singletonList(paymentNoticesResponse)));

        Response response = given()
                .contentType(ContentType.JSON)
                .headers(validMilHeaders)
                .when()
                .get("/")
                .then()
                .extract()
                .response();

        Assertions.assertEquals(200, response.statusCode());
    }

    @Test
    void testGetPaymentNotices_Unauthorized() {
        Map<String, String> invalidHeaders = new HashMap<>(validMilHeaders);
        invalidHeaders.put("Authorization", "Invalid token");

        Response response = given()
                .contentType(ContentType.JSON)
                .headers(invalidHeaders)
                .when()
                .get("/")
                .then()
                .extract()
                .response();

        Assertions.assertEquals(401, response.statusCode());
    }

    @Test
    @TestSecurity(user = "testUser", roles = { "mil_debt_position_admin" })
    void testGetPaymentNotices_GetTokenInternalServerError() {
        Mockito.when(milAuthService.getToken(TestData.REQUEST_ID))
                .thenReturn(Uni.createFrom().failure(new InternalServerErrorException()));

        Response response = given()
                .contentType(ContentType.JSON)
                .headers(validMilHeaders)
                .when()
                .get("/")
                .then()
                .extract()
                .response();

        Assertions.assertEquals(500, response.statusCode());
    }

    @Test
    @TestSecurity(user = "testUser", roles = { "mil_debt_position_admin" })
    void testGetPaymentNotices_GetTokenInfoInternalServerError() {
        Mockito.when(milAuthService.getToken(TestData.REQUEST_ID))
                .thenReturn(Uni.createFrom().item(tokenResponse));

        Mockito.when(milAuthService.getTokenInfo(eq(TestData.REQUEST_ID), eq(TestData.VALID_TOKEN), any(TokenInfoRequest.class)))
                .thenReturn(Uni.createFrom().failure(new InternalServerErrorException()));

        Response response = given()
                .contentType(ContentType.JSON)
                .headers(validMilHeaders)
                .when()
                .get("/")
                .then()
                .extract()
                .response();

        Assertions.assertEquals(500, response.statusCode());
    }

    @Test
    @TestSecurity(user = "testUser", roles = { "mil_debt_position_admin" })
    void testGetPaymentNotices_GetPaymentNoticeInternalServerError() {
        Mockito.when(milAuthService.getToken(TestData.REQUEST_ID))
                .thenReturn(Uni.createFrom().item(tokenResponse));

        Mockito.when(milAuthService.getTokenInfo(eq(TestData.REQUEST_ID), eq(TestData.VALID_TOKEN), any(TokenInfoRequest.class)))
                .thenReturn(Uni.createFrom().item(tokenInfoResponse));

        Mockito.when(paymentNoticeService.getPaymentNotices(TestData.FISCAL_CODE))
                .thenReturn(Uni.createFrom().failure(new InternalServerErrorException()));

        Response response = given()
                .contentType(ContentType.JSON)
                .headers(validMilHeaders)
                .when()
                .get("/")
                .then()
                .extract()
                .response();

        Assertions.assertEquals(500, response.statusCode());
    }
}
