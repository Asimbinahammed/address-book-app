package com.bridgelabz.addressbook.dto;


import lombok.Data;

import javax.persistence.Column;

/**
 * Purpose : To declare input and their regex for validations.
 *
 * @author ASIM AHAMMED
 * @version : 0.0.1-SNAPSHOT
 * @since 2021-12-03
 */
@Data
public class AddressDto {
    private String name;
    private String address;
    private String city;
    private String state;
    private String phoneNumber;
    private int zip;
}

