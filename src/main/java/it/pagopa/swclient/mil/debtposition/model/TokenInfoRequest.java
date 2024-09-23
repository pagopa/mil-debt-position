package it.pagopa.swclient.mil.debtposition.model;

import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.*;

@RegisterForReflection
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TokenInfoRequest {
    private String token;
}
