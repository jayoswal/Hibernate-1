package com.jayoswal.hibernate_demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Service
public class AddressService {

    @Autowired
    private AddressRepo addressRepo;

    @Transactional(propagation = Propagation.REQUIRES_NEW)
    public Address addNew(Address address) {

        Address savedAddr =  addressRepo.save(address);

        return savedAddr;

    }

    @Transactional(propagation = Propagation.MANDATORY)
    public Address addNewMandatory(Address address) {

        Address savedAddr =  addressRepo.save(address);

        return savedAddr;

    }

    @Transactional(propagation = Propagation.NEVER)
    public Address addNewNever(Address address) {

        Address savedAddr =  addressRepo.save(address);

        return savedAddr;

    }
}
