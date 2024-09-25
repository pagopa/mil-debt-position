package it.pagopa.swclient.mil.debtposition.resource;

import io.quarkus.logging.Log;
import io.smallrye.mutiny.Uni;
import it.pagopa.swclient.mil.bean.CommonHeader;
import it.pagopa.swclient.mil.bean.Errors;
import it.pagopa.swclient.mil.debtposition.model.Debt;
import it.pagopa.swclient.mil.debtposition.model.DebtPosition;
import it.pagopa.swclient.mil.debtposition.model.TokenInfoRequest;
import it.pagopa.swclient.mil.debtposition.service.MilAuthService;
import it.pagopa.swclient.mil.debtposition.service.PaymentNoticeService;
import it.pagopa.swclient.mil.debtposition.utils.ErrorCodes;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

import java.util.List;

@Path("/paymentNotices")
public class PaymentNoticeResource {

    private final MilAuthService milAuthService;

    private final PaymentNoticeService paymentNoticeService;

    public PaymentNoticeResource(MilAuthService milAuthService, PaymentNoticeService paymentNoticeService) {
        this.milAuthService = milAuthService;
        this.paymentNoticeService = paymentNoticeService;
    }

    @GET
    @Path("/")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Uni<Response> getPaymentNotices(@Valid @BeanParam CommonHeader headers,
                                           @HeaderParam("Authorization")
                                           @NotNull(message = ErrorCodes.ERROR_AUTHORIZATION_MUST_NOT_BE_NULL_MSG) String accessToken) {
        if (!accessToken.startsWith("Bearer ")) {
            return Uni.createFrom().item(Response.status(Response.Status.UNAUTHORIZED)
                    .entity(new Errors(ErrorCodes.ERROR_UNAUTHORIZED, ErrorCodes.ERROR_UNAUTHORIZED_MSG)).build());
        }

        return milAuthService.getToken(headers.getRequestId())
                .onFailure()
                .transform(error -> {
                    Log.errorf(error, "PaymentNoticeResource -> getToken: unexpected error during get token for requestId [%s]", headers.getRequestId());

                    return new InternalServerErrorException(Response
                            .status(Response.Status.INTERNAL_SERVER_ERROR)
                            .entity(new Errors(ErrorCodes.ERROR_GENERIC_TOKEN, ErrorCodes.ERROR_GENERIC_TOKEN_MSG))
                            .build());
                })
                .onItem()
                .transformToUni(tokenResponse -> {
                    Log.debugf("PaymentNoticeResource -> getToken: token retrieved correctly");

                    TokenInfoRequest tokenInfoRequest = TokenInfoRequest.builder()
                            .token(accessToken.substring("Bearer ".length())).build();

                    return milAuthService.getTokenInfo(headers.getRequestId(), tokenResponse.getToken(), tokenInfoRequest)
                            .onFailure()
                            .transform(error -> {
                                Log.errorf(error, "PaymentNoticeResource -> getTokenInfo: unexpected error during get fiscal code for requestId [%s]", headers.getRequestId());

                                return new InternalServerErrorException(Response
                                        .status(Response.Status.INTERNAL_SERVER_ERROR)
                                        .entity(new Errors(ErrorCodes.ERROR_GENERIC_TOKEN_INFO, ErrorCodes.ERROR_GENERIC_TOKEN_INFO_MSG))
                                        .build());
                            })
                            .onItem()
                            .transformToUni(tokenInfoResponse -> {
                                Log.debugf("PaymentNoticeResource -> getTokenInfo: fiscal code retrieved correctly");

                                return paymentNoticeService.getPaymentNotices(tokenInfoResponse.getFiscalCode())
                                        .onFailure()
                                        .transform(error -> {
                                            Log.errorf(error, "PaymentNoticeService -> getPaymentNotices: unexpected error during get payment notices by fiscal code");

                                            return new InternalServerErrorException(Response
                                                    .status(Response.Status.INTERNAL_SERVER_ERROR)
                                                    .entity(new Errors(ErrorCodes.ERROR_GENERIC_PAYMENT_NOTICES, ErrorCodes.ERROR_GENERIC_PAYMENT_NOTICES_MSG))
                                                    .build());
                                        })
                                        .onItem()
                                        .transform(paymentNoticesResponses -> {
                                            Log.debugf("PaymentNoticeService -> getPaymentNotices: payment notices retrieved correctly");

                                            List<Debt> debts = paymentNoticesResponses.stream()
                                                    .flatMap(paymentNotice -> paymentNotice.getPaymentOptions().stream())
                                                    .flatMap(paymentOption -> paymentOption.getInstallments().stream())
                                                    .map(installment -> new Debt(
                                                            installment.getPaTaxCode(),
                                                            installment.getNav(),
                                                            installment.getAmount(),
                                                            installment.getDescription(),
                                                            installment.getDueDate()))
                                                    .toList();

                                            return Response
                                                    .status(Response.Status.OK)
                                                    .entity(DebtPosition.builder().debts(debts).build())
                                                    .build();
                                        });
                            });
                });
    }
}
