package com.foodwastesavior.webapp.service.impl;

import com.foodwastesavior.webapp.model.entity.Address;
import com.foodwastesavior.webapp.model.entity.Store;
import com.foodwastesavior.webapp.repository.StoreRepository;
import com.foodwastesavior.webapp.service.FakeDataService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FakeDataServiceImpl implements FakeDataService {
    private final StoreRepository storeRepository;

    public FakeDataServiceImpl(StoreRepository storeRepository) {
        this.storeRepository = storeRepository;
    }

    @Transactional
    public void createFakeStores() {
        for (int i = 1; i <= 10; i++) {
            Store store = new Store();
            store.setName("好吃Poke" + i);
            store.setEmail("poke" + i + "@example.com");
            store.setCoverImageUrl("https://res.cloudinary.com/dcybgix51/image/upload/v1733270354/samples/food/fish-vegetables.jpg");
            store.setLogoImageUrl("https://res.cloudinary.com/dcybgix51/image/upload/v1735174727/onijs7eugg1wuiq3c1sr.png");
            store.setAbout("這是一家提供美味Poke的商家！");
            store.setRatingSum(0.0);
            store.setRatingCount(0);
            store.setRating(0.0);

            Address address = new Address();
            address.setAddressDetail("台北市信義區信義路五段" + (i * 10) + "號");
            address.setCountry("台灣");
            address.setCity("台北市");
            address.setPostalCode(110);
            address.setLatitude(generateRandomLatitude(25.033, 5.0));
            address.setLongitude(generateRandomLongitude(121.5654, 5.0));
            address.setType(Address.AddressType.STORE);

            // Set the bi-directional relationship
            address.setStore(store);
            store.setAddress(address);

            storeRepository.save(store);
        }
    }

    private Double generateRandomLatitude(Double centerLatitude, Double radiusKm) {
        double radiusInDegrees = radiusKm / 111.0; // 1 degree ~ 111 km
        return centerLatitude + (Math.random() - 0.5) * 2 * radiusInDegrees;
    }

    private Double generateRandomLongitude(Double centerLongitude, Double radiusKm) {
        double radiusInDegrees = radiusKm / (111.0 * Math.cos(Math.toRadians(centerLongitude)));
        return centerLongitude + (Math.random() - 0.5) * 2 * radiusInDegrees;
    }
}
