package com.bridgelabz.addressbook.controller;

import com.bridgelabz.addressbook.entity.ResponseEntity;
import com.bridgelabz.addressbook.services.AddressBookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

@ExtendWith(MockitoExtension.class)
public class AddressBookControllerTest {
    @InjectMocks
    private AddressBookController addressBookController;
    @Mock
    private AddressBookService addressBookService;

    @Test
    void whenGetWelcomeMessageCalled_shouldReturnResponseEntity() {
        String successMessage = "Success, Welcome to Address book app";
        ResponseEntity responseEntity = addressBookController.getWelcomeMessage();
        Assertions.assertEquals(successMessage, responseEntity.getMessage());
    }
}
