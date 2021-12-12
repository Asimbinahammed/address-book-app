package com.bridgelabz.addressbook.controller;

import com.bridgelabz.addressbook.dto.AddressDto;
import com.bridgelabz.addressbook.entity.ResponseEntity;
import com.bridgelabz.addressbook.services.AddressBookService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * Purpose : To demonstrate different HTTP methods
 *
 * @author ASIM AHAMMED
 * @version : 0.0.1-SNAPSHOT
 * @since 2021-12-03
 */
@RestController
@RequestMapping(value = "/addressbook")
public class AddressBookController {
    public String welcomeMessage = "Success, Welcome to Address book app";

    @Autowired
    private AddressBookService addressBookService;

    /**
     * Purpose : To print welcome message.
     * @return ResponseEntity : Having welcomes message.
     */
    @RequestMapping(value = {"", "/"})
    public ResponseEntity getAllData() {
        return new ResponseEntity(welcomeMessage,null , HttpStatus.OK);
    }

    /**
     * Purpose : To get list of all addresses from database.
     * @return List : List of address.
     */
    @GetMapping(value = "/address")
    public List<AddressDto> getAllAddress() {
        return addressBookService.getAllAddress();
    }

    /**
     * Purpose : To add address into database.
     * @param addressDto : Having input data to be added into database.
     * @return ResponseEntity : Having success message, Added data &
     *                          success status response code indicates that the request has succeeded.
     */
    @PostMapping(value = "/address")
    public ResponseEntity addAddress(@Valid @RequestBody AddressDto addressDto) {
        String successMessage = addressBookService.addAddress(addressDto);
        return new ResponseEntity(successMessage, addressDto, HttpStatus.OK);
    }

}
