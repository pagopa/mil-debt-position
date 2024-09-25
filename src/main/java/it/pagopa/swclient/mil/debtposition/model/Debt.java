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
public class Debt {
    private Long paTaxCode;
    private Long noticeNumber;
    private Long amount;
    private String description;
    private LocalDate dueDate;
}
