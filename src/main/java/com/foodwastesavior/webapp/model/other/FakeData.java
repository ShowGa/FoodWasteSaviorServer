package com.foodwastesavior.webapp.model.other;

import lombok.Data;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.List;

@Data
@Component
public class FakeData {

    private final List<String> restaurantNames = Arrays.asList(
            "美味寶盒", "鮮味坊", "星空餐廳", "食尚小館", "香氣四溢",
            "樂享味道", "味覺之旅", "傳奇廚房", "風味天地", "食光樂園"
    );


    private final List<String> emails = Arrays.asList(
            "poke1@example.com", "poke2@example.com", "poke3@example.com", "poke4@example.com", "poke5@example.com",
            "poke6@example.com", "poke7@example.com", "poke8@example.com", "poke9@example.com", "poke10@example.com"
    );

    private final List<String> coverImageUrls = Arrays.asList(
            "https://res.cloudinary.com/dcybgix51/image/upload/v1735651107/food1_jielom.jpg", "https://res.cloudinary.com/dcybgix51/image/upload/v1735651161/food2_myptxo.jpg", "https://res.cloudinary.com/dcybgix51/image/upload/v1735651184/food3_ybjujt.jpg",
            "https://res.cloudinary.com/dcybgix51/image/upload/v1735651370/food4_d5rbob.jpg", "https://res.cloudinary.com/dcybgix51/image/upload/v1735651402/food5_zo4cy9.jpg", "https://res.cloudinary.com/dcybgix51/image/upload/v1735651434/food6_d1wl70.jpg",
            "https://res.cloudinary.com/dcybgix51/image/upload/v1735651459/food7_ruddnc.jpg", "https://res.cloudinary.com/dcybgix51/image/upload/v1735651487/food8_utcih5.jpg", "https://res.cloudinary.com/dcybgix51/image/upload/v1735651512/food9_cdkxrd.jpg",
            "https://res.cloudinary.com/dcybgix51/image/upload/v1735651533/food10_duuyqb.jpg"
    );

    private final List<String> logoImageUrls = Arrays.asList(
            "https://example.com/logo1.jpg", "https://example.com/logo2.jpg", "https://example.com/logo3.jpg",
            "https://example.com/logo4.jpg", "https://example.com/logo5.jpg", "https://example.com/logo6.jpg",
            "https://example.com/logo7.jpg", "https://example.com/logo8.jpg", "https://example.com/logo9.jpg",
            "https://example.com/logo10.jpg"
    );

    private final List<String> abouts = Arrays.asList(
            "這是一家專注於為您提供創意美食的餐廳，每一道菜品都經過精心設計，帶來獨特的風味體驗。我們選用新鮮食材，注重健康和營養，讓您在品嚐美味的同時，感受到身心的滿足和愉悅。",
            "鮮味坊致力於呈現最純粹的美食風味，每一道菜都蘊含著海洋的鮮美。我們選用頂級食材，並運用創新手法進行料理，帶給您一場味覺的盛宴，無論是獨享還是與好友共享，都是美好的選擇。",
            "在星空餐廳，您將體驗到不僅僅是美食，更是一場與星空相伴的浪漫旅程。我們提供的每道菜品都是大自然的贈禮，與您共享美味的同時，也能享受恬靜的氛圍和輕鬆的用餐時光。",
            "食尚小館以其別具一格的設計和創新的菜單吸引了眾多食客。我們將世界各地的經典美食融合，並加以改良，創造出既時尚又不失家常味的料理。每一口都讓人感受到溫暖與滿足。",
            "香氣四溢是一個將食物和藝術結合的地方。無論是獨特的調味還是精緻的擺盤，我們都力求每一位客人都能享受到完美的用餐體驗。這裡的每道菜品都充滿了愛與創意，讓您每次光臨都會有驚喜。",
            "樂享味道是一家現代化的餐廳，專注於打造美味、健康又具有創意的料理。我們的菜單每月都會更新，挑選當季最鮮美的食材，將多樣化的風味呈現給您，無論是清淡還是濃郁的口味都能滿足您的需求。",
            "味覺之旅是您踏上美食探索之路的最佳選擇。這裡提供來自全球的特色料理，從傳統到創新，滿足各種不同口味需求。每一口都是一次新的體驗，帶您領略不同文化和風味的魅力。",
            "傳奇廚房傳承了多年的經典美食，我們的廚師團隊融合傳統技藝與現代創意，為您打造出既有歷史沉澱，又充滿新鮮感的料理。無論是經典佳肴還是新穎菜式，總有一道能打動您的心。",
            "風味天地是讓您在這裡品嚐到世界各地美味的地方。我們精心挑選來自不同國度的食材，創造出多樣化的料理選擇，讓每一位食客都能在這裡找到屬於自己的味道。",
            "食光樂園是一個將美味與娛樂結合的地方，這裡有著豐富多樣的菜單和趣味十足的用餐體驗。無論是家庭聚餐、朋友聚會，還是浪漫約會，我們都能為您提供一個難忘的美食之旅。"
    );


}
