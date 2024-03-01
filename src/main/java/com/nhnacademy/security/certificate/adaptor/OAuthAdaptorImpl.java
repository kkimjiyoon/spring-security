package com.nhnacademy.security.certificate.adaptor;

import org.springframework.web.client.RestTemplate;

public class OAuthAdaptorImpl implements OAuthAdaptor {

    private final RestTemplate restTemplate;

    public OAuthAdaptorImpl(RestTemplate restTemplate) {
        this.restTemplate = restTemplate;
    }


    @Override
    public String getCode() {
        return null;
    }

    @Override
    public String getAccessToken() {
        return null;
    }
}
