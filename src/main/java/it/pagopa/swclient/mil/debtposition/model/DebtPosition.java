package it.pagopa.swclient.mil.debtposition.model;

import com.fasterxml.jackson.annotation.JsonProperty;
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
public class DebtPosition {
    @JsonProperty("debtPosition")
    private List<Debt> debts;
}
