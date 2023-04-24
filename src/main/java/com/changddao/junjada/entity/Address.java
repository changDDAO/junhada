package com.changddao.junjada.entity;

import com.sun.istack.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.Embeddable;

@Embeddable
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Address {
    private String zipcode;
    private String city;
    private String street;
    private String detail;

    public Address(String zipcode, String city, String street, String detail) {
        this.zipcode = zipcode;
        this.city = city;
        this.street = street;
        this.detail = detail;
    }
}
