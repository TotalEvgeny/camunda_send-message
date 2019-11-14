package com.bpmn2.camunda.service.usersmanagement;

import com.bpmn2.camunda.entity.usersmanagement.Address;
import com.bpmn2.camunda.repository.usersmanagement.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressService {

    @Autowired
    private final AddressRepository addressRepository;

    public AddressService(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    public void createAddress(Address address) {
        addressRepository.save(address);
    }
}
