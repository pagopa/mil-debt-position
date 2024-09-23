package it.pagopa.swclient.mil.debtposition.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.*;

@RegisterForReflection
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class TokenRequest {
    @JsonProperty("grant_type")
    private String grantType;

    @JsonProperty("client_id")
    private long clientId;

    @JsonProperty("client_secret")
    private String clientSecret;
}
