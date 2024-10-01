package it.pagopa.swclient.mil.debtposition.client;

import io.smallrye.mutiny.Uni;
import it.pagopa.swclient.mil.debtposition.model.PaymentNoticesResponse;
import it.pagopa.swclient.mil.debtposition.utils.ErrorCodes;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.HeaderParam;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import org.eclipse.microprofile.rest.client.annotation.ClientHeaderParam;
import org.eclipse.microprofile.rest.client.inject.RegisterRestClient;

import java.util.List;

@RegisterRestClient(configKey = "gpd-payments-pull-rest-api")
public interface GpdPaymentsPullClient {

    @POST
    @Path("/payment-notices/v1")
    @ClientHeaderParam(name = "Ocp-Apim-Subscription-Key", value = "${ocp-rest-client.apim-subscription-key}", required = false)
    Uni<List<PaymentNoticesResponse>> getPaymentNotices(
            @HeaderParam("x-tax-code") @NotNull(message = ErrorCodes.ERROR_FISCAL_CODE_MUST_NOT_BE_NULL_MSG)
            String fiscalCode);
}
