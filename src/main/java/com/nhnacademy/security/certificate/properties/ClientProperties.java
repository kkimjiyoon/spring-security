package com.nhnacademy.security.certificate.properties;

import lombok.Getter;
import lombok.Setter;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "client")
@Getter
@Setter
public class ClientProperties {
    private String id;
    private String secret;
}
