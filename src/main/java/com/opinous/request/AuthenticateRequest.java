package com.opinous.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthenticateRequest {
    private String username;
    private String password;
}
