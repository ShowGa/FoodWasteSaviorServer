package com.foodwastesavior.webapp.service;

import com.foodwastesavior.webapp.model.dto.LatitudeAndLongitudeDTO;
import com.foodwastesavior.webapp.model.entity.Address;
import java.util.List;

public interface AddressService {
    LatitudeAndLongitudeDTO changeUserLanAndLong(LatitudeAndLongitudeDTO latitudeAndLongitudeDTO, String jwt);
}
