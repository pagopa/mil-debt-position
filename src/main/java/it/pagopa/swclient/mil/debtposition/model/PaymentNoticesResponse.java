package it.pagopa.swclient.mil.debtposition.model;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.*;

import java.util.List;

@RegisterForReflection
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Builder
public class PaymentNoticesResponse {
    private List<PaymentOption> paymentOptions;
}
