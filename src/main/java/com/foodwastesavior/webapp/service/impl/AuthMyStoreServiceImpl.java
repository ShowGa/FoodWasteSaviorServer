package com.foodwastesavior.webapp.service.impl;

import com.foodwastesavior.webapp.exception.ExistedException;
import com.foodwastesavior.webapp.model.entity.Address;
import com.foodwastesavior.webapp.model.entity.Store;
import com.foodwastesavior.webapp.model.entity.User;
import com.foodwastesavior.webapp.repository.StoreRepository;
import com.foodwastesavior.webapp.request.RegisterMyStoreRequest;
import com.foodwastesavior.webapp.request.RegisterMyStoreRequest.AddressInfo;
import com.foodwastesavior.webapp.response.RegisterMyStoreResponse;
import com.foodwastesavior.webapp.service.AuthMyStoreService;
import com.foodwastesavior.webapp.utils.FirebaseHelper;
import com.foodwastesavior.webapp.utils.FirebaseHelper.FirebaseUserInfo;
import com.foodwastesavior.webapp.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AuthMyStoreServiceImpl implements AuthMyStoreService {

    private final StoreRepository storeRepository;

    private final FirebaseHelper firebaseHelper;

    @Autowired
    public AuthMyStoreServiceImpl(StoreRepository storeRepository, FirebaseHelper firebaseHelper) {
        this.storeRepository = storeRepository;
        this.firebaseHelper = firebaseHelper;
    }

    @Override
    public RegisterMyStoreResponse googleOAuthMyStore(RegisterMyStoreRequest registerMyStoreRequest) {
        // ========= Verify idToken first ========= //
        String idToken = registerMyStoreRequest.getIdToken();

        FirebaseUserInfo firebaseInfo = firebaseHelper.verifyToken(idToken);

        // ========= register logic ========= //
        Optional<Store> foundstore = storeRepository.findByEmail(firebaseInfo.getEmail());

        // global exception response?????
        if (foundstore.isPresent()) {
            throw new ExistedException("您的商店已註冊!您是哪裡有問題?");
        }

        // create store and address
        AddressInfo addressInfo = registerMyStoreRequest.getAddressInfo();

        Store store = createAndSaveMyStoreGoogleOAuth(firebaseInfo.getEmail(), firebaseInfo.getAvatarUrl(), firebaseInfo.getUsername(), addressInfo);

        // sign token

        String jwtToken = JwtUtil.generateToken(store.getEmail(), 30);

        return new RegisterMyStoreResponse(jwtToken, store.getStoreId(), store.getName(), store.getLogoImageUrl());
    }

    /* =============== private method =============== */


    private Store createAndSaveMyStoreGoogleOAuth(String email, String avatar, String storename, AddressInfo addressInfo) {

        Store newStore = new Store();
        newStore.setEmail(email);
        newStore.setName(storename);
        newStore.setLogoImageUrl(avatar);

        Address newAddress = new Address();
        newAddress.setAddressDetail(addressInfo.getAddressDetail());
        newAddress.setCountry(addressInfo.getCountry());
        newAddress.setCity(addressInfo.getCity());
        newAddress.setPostalCode(addressInfo.getPostalCode());
        newAddress.setLatitude(addressInfo.getLatitude());
        newAddress.setLongitude(addressInfo.getLongitude());
        newAddress.setType(Address.AddressType.STORE);
        newAddress.setStore(newStore);

        newStore.setAddress(newAddress);

        return storeRepository.save(newStore);
    }


}
