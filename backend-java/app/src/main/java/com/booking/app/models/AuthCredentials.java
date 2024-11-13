package com.booking.app.models;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import lombok.Data;

@Component
@Scope("prototype")
@Data
public class AuthCredentials {
    
    private String email;
    private String password;
}
