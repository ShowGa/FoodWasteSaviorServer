package com.foodwastesavior.webapp.utils;

import java.util.Random;

public class OrderConfirmCodeGenerator {
    public static String generateOrderId() {
        long timestamp = System.currentTimeMillis();  // 當前時間戳
        int randomNum = new Random().nextInt(1000);   // 隨機生成一個數字，範圍是 0-999
        return "ORD-" + timestamp + "-" + randomNum;  // 合併時間戳和隨機數字生成識別碼
    }
}
