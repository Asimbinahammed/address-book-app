package com.bridgelabz.addressbook.builder;

import com.bridgelabz.addressbook.dto.AddressDto;
import com.bridgelabz.addressbook.entity.Address;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Purpose : This is builder class which converts address dto to into entity.
 *
 * @author ASIM AHAMMED
 * @version : 0.0.1-SNAPSHOT
 * @since 2021-12-14
 */
@Component
public class AddressBuilder {
    @Autowired
    private ModelMapper modelMapper;

    /**
     * Purpose : To convert payroll dto into payroll entity.
     *
     * @param addressDto : address dto which is to be converted.
     * @param address : address book entity which will be overwritten.
     * @return address : converted address book entity.
     */    public Address buildAddressEntity(AddressDto addressDto, Address address) {
        modelMapper.map(addressDto, address);
        return address;
    }
}
