package it.pagopa.swclient.mil.debtposition.service;

import io.smallrye.mutiny.Uni;
import it.pagopa.swclient.mil.debtposition.client.GpdPaymentsPullClient;
import it.pagopa.swclient.mil.debtposition.model.PaymentNoticesResponse;
import jakarta.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.rest.client.inject.RestClient;

import java.util.List;

@ApplicationScoped
public class PaymentNoticeService {

    @RestClient
    GpdPaymentsPullClient gpdPaymentsPullClient;

    public Uni<List<PaymentNoticesResponse>> getPaymentNotices(String fiscalCode) {
        return gpdPaymentsPullClient.getPaymentNotices(fiscalCode)
                .onFailure()
                .transform(error -> error)
                .onItem()
                .transform(paymentNoticesResponse -> paymentNoticesResponse);
    }
}
