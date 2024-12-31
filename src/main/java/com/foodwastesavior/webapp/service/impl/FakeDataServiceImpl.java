package com.foodwastesavior.webapp.service.impl;

import com.foodwastesavior.webapp.model.entity.Address;
import com.foodwastesavior.webapp.model.entity.Store;
import com.foodwastesavior.webapp.model.other.FakeData;
import com.foodwastesavior.webapp.repository.StoreRepository;
import com.foodwastesavior.webapp.service.FakeDataService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FakeDataServiceImpl implements FakeDataService {
    private final StoreRepository storeRepository;

    private final FakeData fakeData;

    public FakeDataServiceImpl(StoreRepository storeRepository, FakeData fakeData) {
        this.storeRepository = storeRepository;
        this.fakeData = fakeData;
    }

    @Transactional
    public void createFakeStores() {
        for (int i = 0; i < 10; i++) {
            Store store = new Store();
            store.setName(fakeData.getRestaurantNames().get(i));
            store.setEmail(fakeData.getEmails().get(i));
            store.setCoverImageUrl(fakeData.getCoverImageUrls().get(i));
            store.setLogoImageUrl("https://res.cloudinary.com/dcybgix51/image/upload/v1735174727/onijs7eugg1wuiq3c1sr.png");
            store.setAbout(fakeData.getAbouts().get(i));
            store.setRatingSum(0.0);
            store.setRatingCount(0);
            store.setRating(0.0);

            Address address = new Address();
            address.setAddressDetail("台北市信義區信義路五段" + (i * 10) + "號");
            address.setCountry("台灣");
            address.setCity("台北市");
            address.setPostalCode(110);
            address.setLatitude(generateRandomLatitude(25.033, 3.0));
            address.setLongitude(generateRandomLongitude(121.5654, 3.0));
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
