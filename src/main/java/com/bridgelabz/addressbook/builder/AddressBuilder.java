package com.bridgelabz.addressbook.builder;

import com.bridgelabz.addressbook.dto.AddressDto;
import com.bridgelabz.addressbook.entity.Address;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class AddressBuilder {
    @Autowired
    private ModelMapper modelMapper;

    public Address buildAddressEntity(AddressDto addressDto, Address address) {
        modelMapper.map(addressDto, address);
        return address;
    }
}
