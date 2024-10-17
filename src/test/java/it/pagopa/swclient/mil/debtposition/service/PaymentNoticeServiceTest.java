package it.pagopa.swclient.mil.debtposition.service;

import io.quarkus.test.InjectMock;
import io.quarkus.test.junit.QuarkusTest;
import io.smallrye.mutiny.Uni;
import io.smallrye.mutiny.helpers.test.UniAssertSubscriber;
import it.pagopa.swclient.mil.debtposition.client.GpdPaymentsPullClient;
import it.pagopa.swclient.mil.debtposition.model.PaymentNoticesResponse;
import jakarta.ws.rs.InternalServerErrorException;
import jakarta.inject.Inject;
import org.eclipse.microprofile.rest.client.inject.RestClient;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestInstance;
import org.mockito.Mockito;

import java.util.Collections;
import java.util.List;

import static it.pagopa.swclient.mil.debtposition.util.TestData.FISCAL_CODE;
import static org.mockito.ArgumentMatchers.any;

@QuarkusTest
@TestInstance(TestInstance.Lifecycle.PER_CLASS)
class PaymentNoticeServiceTest {

    @InjectMock
    @RestClient
    GpdPaymentsPullClient gpdPaymentsPullClient;

    @Inject
    PaymentNoticeService paymentNoticeService;

    static List<PaymentNoticesResponse> paymentNoticesResponses;

    @BeforeAll
    static void createTestObjects() {
        PaymentNoticesResponse paymentNoticeResponse = new PaymentNoticesResponse();
        paymentNoticesResponses = Collections.singletonList(paymentNoticeResponse);
    }

    @Test
    void testGetPaymentNotices_Success() {
        Mockito.when(gpdPaymentsPullClient.getPaymentNotices(any(String.class)))
                .thenReturn(Uni.createFrom().item(paymentNoticesResponses));

        Uni<List<PaymentNoticesResponse>> result = paymentNoticeService.getPaymentNotices(FISCAL_CODE);

        result.subscribe()
                .with(notices -> Assertions.assertEquals(paymentNoticesResponses, notices));
    }

    @Test
    void testGetPaymentNotices_Error() {
        Mockito.when(gpdPaymentsPullClient.getPaymentNotices(any(String.class)))
                .thenReturn(Uni.createFrom().failure(new InternalServerErrorException()));

        Uni<List<PaymentNoticesResponse>> result = paymentNoticeService.getPaymentNotices(FISCAL_CODE);

        result.subscribe()
                .withSubscriber(UniAssertSubscriber.create())
                .assertFailedWith(InternalServerErrorException.class);
    }
}
