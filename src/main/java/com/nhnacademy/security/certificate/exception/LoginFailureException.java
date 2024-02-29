package com.nhnacademy.security.certificate.exception;

public class LoginFailureException extends Exception {
    public LoginFailureException(String name) {
        super("login failed: name=" + name);
    }

}
