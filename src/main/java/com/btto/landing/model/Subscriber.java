package com.btto.landing.model;

import org.hibernate.validator.constraints.Length;

import javax.validation.Valid;
import javax.validation.constraints.Email;

@SuppressWarnings("unused")
@Valid
public final class Subscriber {
    @Length(min =1, max = 255, message = "name length should be between 1 and 255 characters")
    private String name;
    @Email(message = "email format is incorrect")
    private String email;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
