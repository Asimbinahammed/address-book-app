package com.bridgelabz.addressbook.controller.services;

import com.bridgelabz.addressbook.builder.AddressBuilder;
import com.bridgelabz.addressbook.dto.AddressDto;
import com.bridgelabz.addressbook.entity.Address;
import com.bridgelabz.addressbook.repository.AddressBookRepository;
import com.bridgelabz.addressbook.services.AddressBookService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import javax.persistence.EntityNotFoundException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AddressBookServiceTest {
    @InjectMocks
    private AddressBookService addressBookService;
    @Mock
    private AddressBookRepository addressBookRepository;
    @Mock
    private ModelMapper modelMapper;
    @Mock
    private AddressBuilder addressBuilder;

    @Test
    void given2AddressDtoAddedInRepository_whenCalledGetAllAddress_shouldReturnListOfAddressBook() {
        List<AddressDto> addressDtoList = new ArrayList<>();
        AddressDto addressDto = new AddressDto();
        addressDto.setName("Asim");
        addressDto.setAddress("12b");
        addressDto.setCity("kochi");
        addressDto.setState("kerala");
        addressDto.setPhoneNumber("9876543210");
        addressDto.setZip("123456");
        addressDtoList.add(addressDto);
        AddressDto addressDto2 = new AddressDto();
        addressDto2.setName("appu");
        addressDto2.setAddress("12bc");
        addressDto2.setCity("TVM");
        addressDto2.setState("kerala");
        addressDto2.setPhoneNumber("0987654321");
        addressDto2.setZip("123456");
        addressDtoList.add(addressDto2);

        List<Address> addressList = new ArrayList<>();
        Address address = new Address();
        address.setId(1);
        address.setName("Asim");
        address.setAddress("12b");
        address.setCity("kochi");
        address.setState("kerala");
        address.setPhoneNumber("9876543210");
        address.setZip("123456");
        address.setCreatedOn(LocalDateTime.now());
        address.setUpdatedOn(LocalDateTime.now());
        addressList.add(address);
        Address address2 = new Address();
        address2.setId(2);
        address2.setName("appu");
        address2.setAddress("12bc");
        address2.setCity("TVM");
        address2.setState("kerala");
        address2.setPhoneNumber("kerala");
        address2.setZip("123456");
        address2.setCreatedOn(LocalDateTime.now());
        address2.setUpdatedOn(LocalDateTime.now());
        addressList.add(address2);

        when(addressBookRepository.findAll()).thenReturn(addressList);
        when(modelMapper.map(addressList.get(0), AddressDto.class)).thenReturn(addressDto);
        when(modelMapper.map(addressList.get(1), AddressDto.class)).thenReturn(addressDto2);
        List<AddressDto> actualListOfAddress = addressBookService.getAllAddress();
        Assertions.assertEquals(2, actualListOfAddress.size());
        Assertions.assertEquals(addressDtoList, actualListOfAddress);
    }

    @Test
    void givenAddressDto_whenCalledAddAddress_shouldReturnSuccessMessage() {
        String expectedMessage = "ADDED atm into database";
        AddressDto addressDto = new AddressDto();
        addressDto.setName("Asim");
        addressDto.setAddress("12b");
        addressDto.setCity("kochi");
        addressDto.setState("kerala");
        addressDto.setPhoneNumber("9876543210");
        addressDto.setZip("123456");

        Address address = new Address();
        address.setId(1);
        address.setName("Asim");
        address.setAddress("12b");
        address.setCity("kochi");
        address.setState("kerala");
        address.setPhoneNumber("9876543210");
        address.setZip("123456");
        address.setCreatedOn(LocalDateTime.now());
        address.setUpdatedOn(LocalDateTime.now());

        when(modelMapper.map(addressDto, Address.class)).thenReturn(address);
        String actualMessage = addressBookService.addAddress(addressDto);
        verify(addressBookRepository, times(1)).save(address);
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void givenIdAndAddressDto_whenCalledUpdateAddress_shouldThrowEntityNotFoundException() {
        int id = 1;
        AddressDto addressDto = new AddressDto();
        addressDto.setName("Asim");
        addressDto.setAddress("12b");
        addressDto.setCity("kochi");
        addressDto.setState("kerala");
        addressDto.setPhoneNumber("9876543210");
        addressDto.setZip("123456");

        Address address = new Address();
        address.setId(1);
        address.setName("Asim");
        address.setAddress("12b");
        address.setCity("kochi");
        address.setState("kerala");
        address.setPhoneNumber("9876543210");
        address.setZip("123456");
        address.setCreatedOn(LocalDateTime.now());
        address.setUpdatedOn(LocalDateTime.now());

        when(addressBookRepository.findById(id)).thenReturn(Optional.empty());
        Assertions.assertThrows(EntityNotFoundException.class, () -> addressBookService.updateAddress(id, addressDto)); 
    }

    @Test
    void givenIdAndAddressDto_whenCalledUpdateAddress_shouldReturnSuccessMessage() {
        int id = 1;
        String expectedMessage = "ADDED atm into database";
        AddressDto addressDto = new AddressDto();
        addressDto.setName("Asim");
        addressDto.setAddress("12b");
        addressDto.setCity("kochi");
        addressDto.setState("kerala");
        addressDto.setPhoneNumber("9876543210");
        addressDto.setZip("123456");

        Address address = new Address();
        address.setId(1);
        address.setName("Asim");
        address.setAddress("12b");
        address.setCity("kochi");
        address.setState("kerala");
        address.setPhoneNumber("9876543210");
        address.setZip("123456");
        address.setCreatedOn(LocalDateTime.now());
        address.setUpdatedOn(LocalDateTime.now());

        when(addressBookRepository.findById(id)).thenReturn(Optional.of(address));
        when(addressBuilder.buildAddressEntity(addressDto, address)).thenReturn(address);
        String actualMessage = addressBookService.updateAddress(id, addressDto);
        verify(addressBookRepository, times(1)).save(address);
        Assertions.assertEquals(expectedMessage, actualMessage);
    }
}

