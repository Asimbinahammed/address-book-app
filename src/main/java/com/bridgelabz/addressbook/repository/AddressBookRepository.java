package com.bridgelabz.addressbook.repository;

import com.bridgelabz.addressbook.entity.Address;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Purpose : To used to access & manage database.
 *
 * @author ASIM AHAMMED
 * @version : 0.0.1-SNAPSHOT
 * @since 2021-12-03
 */
public interface AddressBookRepository extends JpaRepository<Address, Integer> {

}

