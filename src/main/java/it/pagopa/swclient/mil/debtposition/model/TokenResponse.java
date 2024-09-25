package it.pagopa.swclient.mil.debtposition.model;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import io.quarkus.runtime.annotations.RegisterForReflection;
import lombok.*;

@RegisterForReflection
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@JsonInclude(JsonInclude.Include.NON_NULL)
public class TokenResponse {
    @JsonProperty("token_type")
    private String type;

    @JsonProperty("expires_in")
    private Long expiresIn;

    @JsonProperty("access_token")
    private String token;
}
