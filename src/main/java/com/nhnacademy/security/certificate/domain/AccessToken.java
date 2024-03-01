package com.nhnacademy.security.certificate.domain;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class AccessToken {

    @JsonProperty("access_token")
    private String accessToken;
}
