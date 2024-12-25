package com.foodwastesavior.webapp.controller;

import com.foodwastesavior.webapp.model.dto.LatitudeAndLongitudeDTO;
import com.foodwastesavior.webapp.model.entity.Address;
import com.foodwastesavior.webapp.response.ApiResponse;
import com.foodwastesavior.webapp.service.AddressService;
import org.apache.coyote.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/address")
public class AddressController {

    AddressService addressService;

    @Autowired
    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @PostMapping("/user-change-position")
    public ResponseEntity<ApiResponse<LatitudeAndLongitudeDTO>> changeUserLanAndLong(@RequestBody LatitudeAndLongitudeDTO latitudeAndLongitudeDTO, @RequestHeader("Authorization") String authorizationHeader) {
        String jwt = authorizationHeader.substring(7);

        LatitudeAndLongitudeDTO result = addressService.changeUserLanAndLong(latitudeAndLongitudeDTO, jwt);

        return ResponseEntity.ok(ApiResponse.success(200, "Update user position successfully", result));
    }
}
