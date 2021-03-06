package com.bridgelabz.addressbook.services;

import com.bridgelabz.addressbook.builder.AddressBuilder;
import com.bridgelabz.addressbook.dto.AddressDto;
import com.bridgelabz.addressbook.entity.Address;
import com.bridgelabz.addressbook.repository.AddressBookRepository;
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
    void WhenFindDetailsByIdCalled_ThenIfIdIsNotFound_ShouldThrowException() {
        int id = 1;
        when(addressBookRepository.findById(id)).thenReturn(Optional.empty());
        Assertions.assertThrows(EntityNotFoundException.class, () -> addressBookService.findDetails(id));
    }

    @Test
    void givenAddressDto_whenCalledAddAddress_shouldReturnSuccessMessage() {
        String expectedMessage = "ADDED address into database";
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
        String expectedMessage = "UPDATED address in database";
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

    @Test
    void givenId_whenCalledDeleteAddress_shouldReturnSuccessMessage() {
        int id = 1;
        String expectedMessage = "DELETED address from database";
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
        String actualMessage = addressBookService.deleteAddress(id);
        verify(addressBookRepository, times(1)).delete(address);
        Assertions.assertEquals(expectedMessage, actualMessage);
    }

    @Test
    void givenIdAndAddressDto_whenCalledDeleteAddress_shouldThrowEntityNotFoundException() {
        int id = 1;
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
        verify(addressBookRepository, times(0)).delete(address);
        when(addressBookRepository.findById(id)).thenReturn(Optional.empty());
        Assertions.assertThrows(EntityNotFoundException.class, () -> addressBookService.deleteAddress(id));
    }

    @Test
    void givenId_whenCalledFindDetails_shouldReturnAddress() {
        int id = 1;
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
        Address actualAddress = addressBookService.findDetails(id);
        Assertions.assertEquals(address, actualAddress);
    }

    @Test
    void givenId_whenCalledFindDetails_shouldThrowEntityNotFoundException() {
        int id = 1;
        when(addressBookRepository.findById(id)).thenReturn(Optional.empty());
        Assertions.assertThrows(EntityNotFoundException.class, () -> addressBookService.findDetails(id));
    }

    @Test
    void givenId_whenCalledGetAddress_shouldReturnAddressDto() {
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

        when(addressBookRepository.findById(id)).thenReturn(Optional.of(address));
        when(modelMapper.map(address, AddressDto.class)).thenReturn(addressDto);
        AddressDto actualResult = addressBookService.getAddress(id);
        Assertions.assertEquals(addressDto, actualResult);
    }

    @Test
    void givenId_whenCalledGetAddress_shouldThrowEntityNotFoundException() {
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
        Assertions.assertThrows(EntityNotFoundException.class, () -> addressBookService.getAddress(id));
    }
}

