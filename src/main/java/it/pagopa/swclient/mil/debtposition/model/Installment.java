package it.pagopa.swclient.mil.debtposition.model;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.*;

import java.time.LocalDate;

@RegisterForReflection
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Installment {
    private Long nav;
    private Long paTaxCode;
    private Long amount;
    private String description;
    private LocalDate dueDate;
}
