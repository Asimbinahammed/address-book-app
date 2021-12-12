package com.bridgelabz.addressbook.services;

import com.bridgelabz.addressbook.builder.AddressBuilder;
import com.bridgelabz.addressbook.dto.AddressDto;
import com.bridgelabz.addressbook.entity.Address;
import com.bridgelabz.addressbook.repository.AddressBookRepository;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Purpose : To demonstrate business logic which implements all the methods in controller layer
 *
 * @author ASIM AHAMMED
 * @version : 0.0.1-SNAPSHOT
 * @since 2021-12-03
 */
@Service
public class AddressBookService {

    @Autowired
    private AddressBookRepository addressBookRepository;
    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private AddressBuilder addressBuilder;


    public String ADDED_DATA_SUCCESSFULLY = "ADDED atm into database";
    public String UPDATED_DATA_SUCCESSFULLY = "UPDATED atm in database";
    public String DELETED_DATA_SUCCESSFULLY = "DELETED atm from database";

    /**
     * purpose : To list all address in database
     *
     * @return list : address has all its entities.
     */
    public List<AddressDto> getAllAddress() {
        return addressBookRepository
                .findAll()
                .stream()
                .map(addressBookData -> {
                    return modelMapper.map(addressBookData, AddressDto.class);
                })
                .collect(Collectors.toList());
    }

    /**
     * Purpose : To add address ino database.
     *
     * @param addressDto : New address entry with all its entities.
     * @return String : Success message for adding data into database.
     */
    public String addAddress(AddressDto addressDto) {
        Address addressBookData = modelMapper.map(addressDto, Address.class);
        addressBookRepository.save(addressBookData);
         return ADDED_DATA_SUCCESSFULLY;
    }

    /**
     * Purpose : To update Address entry with new one using id.
     *
     * @param id : Database id.
     * @param addressDto : New Address entry.
     * @return String : Success message for updating data in database.
     */
    public String updateAddress(int id, AddressDto addressDto) {
        Address address = findDetails(id);
        address = addressBuilder.buildAddressEntity(addressDto, address);
        addressBookRepository.save(address);
        return UPDATED_DATA_SUCCESSFULLY;
    }

    /**
     * purpose : To find entry by id if it presents in database
     *
     * @param id : Database id.
     * @return address : Data which has address details or throwing exception
     */
    public Address findDetails(int id) {
        return addressBookRepository.findById(id).orElseThrow(EntityNotFoundException::new);
    }

}
