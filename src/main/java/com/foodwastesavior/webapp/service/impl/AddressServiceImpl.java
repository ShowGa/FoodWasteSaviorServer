package com.foodwastesavior.webapp.service.impl;

import com.foodwastesavior.webapp.exception.NotFoundException;
import com.foodwastesavior.webapp.model.dto.LatitudeAndLongitudeDTO;
import com.foodwastesavior.webapp.model.entity.Address;
import com.foodwastesavior.webapp.model.entity.User;
import com.foodwastesavior.webapp.repository.AddressRepository;
import com.foodwastesavior.webapp.repository.UserRepository;
import com.foodwastesavior.webapp.service.AddressService;
import com.foodwastesavior.webapp.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;
    private final UserRepository userRepository;

    @Autowired
    public AddressServiceImpl(AddressRepository addressRepository, UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    @Override
    public LatitudeAndLongitudeDTO changeUserLanAndLong(LatitudeAndLongitudeDTO latitudeAndLongitudeDTO, String jwt) {
        // verify token
        String subjectInfo = JwtUtil.validateToken(jwt);

        // find user
        User foundUser = userRepository.findByEmail(subjectInfo).orElseThrow(() -> new NotFoundException("找不到使用者資訊!無法更新定位"));

        // find address
        Address foundUserAddress = addressRepository.findByUserId(foundUser.getUserId()).orElseThrow(() -> new NotFoundException("找不到相關地址資訊，無法更新。"));

        foundUserAddress.setLongitude(latitudeAndLongitudeDTO.getLongitude());
        foundUserAddress.setLatitude(latitudeAndLongitudeDTO.getLatitude());

        Address updatedAddress = addressRepository.save(foundUserAddress);

        return new LatitudeAndLongitudeDTO(updatedAddress.getLongitude(), updatedAddress.getLatitude());
    }
}
