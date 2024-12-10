package com.foodwastesavior.webapp.service.impl;

import com.foodwastesavior.webapp.model.entity.Address;
import com.foodwastesavior.webapp.repository.AddressRepository;
import com.foodwastesavior.webapp.service.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }
}
