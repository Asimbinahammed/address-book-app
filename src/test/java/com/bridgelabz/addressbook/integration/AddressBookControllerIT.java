package com.bridgelabz.addressbook.integration;

import com.bridgelabz.addressbook.controller.AddressBookController;
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

import java.time.LocalDateTime;
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
    void getAllPayrollTest() throws Exception {
        when(addressBookService.getAllAddress()).thenReturn(new ArrayList<>());
        mockMvc.perform(MockMvcRequestBuilders
                        .get("/api/address"))
                .andExpect(status().isOk());
    }
}
