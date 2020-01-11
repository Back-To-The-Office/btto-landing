package com.btto.landing.model;

import org.hibernate.validator.constraints.Length;
import org.springframework.lang.Nullable;

import javax.validation.Valid;
import javax.validation.constraints.Email;

@SuppressWarnings("unused")
@Valid
public final class SubscriberRequest {
    @Length(min =1, max = 255, message = "name length should be between 1 and 255 characters")
    private String name;
    @Email(message = "email format is incorrect")
    private String email;
    @Length(max=2500)
    @Nullable
    private String message;

    public String getName() {
        return name;
    }

    public String getEmail() {
        return email;
    }

    @Nullable
    public String getMessage() {
        return message;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setMessage(@Nullable String message) {
        this.message = message;
    }
}
