package com.foodwastesavior.webapp.controller;

import com.foodwastesavior.webapp.service.FakeDataService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/dev")
public class FakeDataController {

    private final FakeDataService fakeDataService;

    public FakeDataController(FakeDataService fakeDataService) {
        this.fakeDataService = fakeDataService;
    }

    @PostMapping("/generate-stores")
    public ResponseEntity<String> generateFakeStores() {
        fakeDataService.createFakeStores();
        return ResponseEntity.ok("成功生成10個假商家!");
    }
}

