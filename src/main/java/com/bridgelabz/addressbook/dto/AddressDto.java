package com.bridgelabz.addressbook.dto;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

/**
 * Purpose : To declare input and their regex for validations.
 *
 * @author ASIM AHAMMED
 * @version : 0.0.1-SNAPSHOT
 * @since 2021-12-03
 */
@Data
public class AddressDto {
    @Size(min = 3, message = "Name should have atleast 3 characters")
    @Pattern(regexp = "[a-zA-Z]+[\\s]?[a-zA-Z ]+$",
            message = "Name Only contains alphabets")
    private String name;
    @Size(min = 3, message = "Address should have atleast 3 characters")
    @NotNull
    private String address;
    @Size(min = 3, message = "City should have atleast 3 characters")
    private String city;
    @Size(min = 3, message = "State should have atleast 3 characters")
    @Pattern(regexp = "[a-zA-Z]+[\\s]?[a-zA-Z ]+$",
            message = "State Only contains alphabets")
    private String state;
    @Pattern(regexp = "^[0-9]{10}",
            message = "Phone number should be exactly 10 digit")
    private String phoneNumber;
    @Pattern(regexp = "^[0-9]{6}",
            message = "Zip code should be exactly 6 digit")
    private String zip;
}

