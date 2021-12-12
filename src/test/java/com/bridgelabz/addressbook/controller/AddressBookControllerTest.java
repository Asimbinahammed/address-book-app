package com.bridgelabz.addressbook.controller;

import com.bridgelabz.addressbook.dto.AddressDto;
import com.bridgelabz.addressbook.entity.ResponseEntity;
import com.bridgelabz.addressbook.services.AddressBookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.when;

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

    @Test
    void given2AddressBookDtoAddedIntoRepository_whenCalledGetAllAddress_shouldReturnListOfAddressBookDto() {
        List<AddressDto> addressDtoList = new ArrayList<>();
        AddressDto addressDto = new AddressDto();
        addressDto.setName("Asim");
        addressDto.setAddress("128");
        addressDto.setCity("kochi");
        addressDto.setState("kerala");
        addressDto.setPhoneNumber("9876543210");
        addressDto.setZip("123456");
        addressDtoList.add(addressDto);
        AddressDto addressDto2 = new AddressDto();
        addressDto2.setName("appu");
        addressDto2.setAddress("WYN");
        addressDto2.setCity("TVM");
        addressDto2.setState("kerala");
        addressDto2.setPhoneNumber("0987654321");
        addressDto2.setZip("123456");
        addressDtoList.add(addressDto2);

        when(addressBookService.getAllAddress()).thenReturn(addressDtoList);
        List<AddressDto> actualResponse = addressBookController.getAllAddress();
        for (int i = 0; i < actualResponse.size(); i++) {
            Assertions.assertEquals(addressDtoList.get(i).getName(), actualResponse.get(i).getName());
            Assertions.assertEquals(addressDtoList.get(i).getAddress(), actualResponse.get(i).getAddress());
            Assertions.assertEquals(addressDtoList.get(i).getCity(), actualResponse.get(i).getCity());
            Assertions.assertEquals(addressDtoList.get(i).getState(), actualResponse.get(i).getState());
            Assertions.assertEquals(addressDtoList.get(i).getPhoneNumber(), actualResponse.get(i).getPhoneNumber());
            Assertions.assertEquals(addressDtoList.get(i).getZip(), actualResponse.get(i).getZip());
        }
    }
    
}
