package com.bridgelabz.addressbook.integration;

import com.bridgelabz.addressbook.controller.AddressBookController;
import com.bridgelabz.addressbook.dto.AddressDto;
import com.bridgelabz.addressbook.services.AddressBookService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.util.ArrayList;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@ExtendWith(SpringExtension.class)
@ActiveProfiles("test")
@WebMvcTest(AddressBookController.class)
public class AddressBookControllerIT {

    @Autowired
    private MockMvc mockMvc;
    @MockBean
    private AddressBookService addressBookService;

    @Test
    void getAllAddressTest() throws Exception {
        when(addressBookService.getAllAddress()).thenReturn(new ArrayList<>());
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/address"))
                .andExpect(status().isOk());
    }

    @Test
    void addAddressTest() throws Exception {
        when(addressBookService.addAddress(any())).thenReturn("success");
        mockMvc.perform(MockMvcRequestBuilders
                        .post("/api/address")
                        .content("{\"name\":\"Anu\",\"address\":\"kerala\",\"city\":\"kochi\"," +
                                "\"state\":\"kerala\",\"phoneNumber\":\"9876543210\",\"zip\":\"987654\"}")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void updateAddressTest() throws Exception {
        int id = 1;
        AddressDto addressDto = new AddressDto();
        addressDto.setName("Asim");
        addressDto.setAddress("third floor");
        addressDto.setCity("kochi");
        addressDto.setState("kerala");
        addressDto.setPhoneNumber("9876543210");
        addressDto.setZip("123456");
        when(addressBookService.updateAddress(id, addressDto)).thenReturn("Success");
        mockMvc.perform(MockMvcRequestBuilders
                        .put("/api/address/1")
                        .content("{\"name\":\"Asim\",\"address\":\"third floor\",\"city\":\"kochi\"," +
                                "\"state\":\"kerala\",\"phoneNumber\":\"9876543210\",\"zip\":\"123456\"}")
                        .contentType(MediaType.APPLICATION_JSON_VALUE))
                .andExpect(status().isOk());
    }

    @Test
    void deleteAddressTest() throws Exception {
        int id = 1;
        when(addressBookService.deleteAddress(id)).thenReturn("Success");
        mockMvc.perform(MockMvcRequestBuilders
                        .delete("/api/address/1"))
                .andExpect(status().isOk());
    }

    @Test
    void getAddressTest() throws Exception {
        int id = 1;
        AddressDto addressDto = new AddressDto();
        addressDto.setName("Asim");
        addressDto.setAddress("third floor");
        addressDto.setCity("kochi");
        addressDto.setState("kerala");
        addressDto.setPhoneNumber("9876543210");
        addressDto.setZip("123456");
        when(addressBookService.getAddress(id)).thenReturn(addressDto);
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/address/1"))
                .andExpect(status().isOk());
    }

}
