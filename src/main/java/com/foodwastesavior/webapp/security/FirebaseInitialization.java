package com.foodwastesavior.webapp.security;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;
import org.springframework.context.annotation.Bean;

import java.io.FileInputStream;

public class FirebaseInitialization {
    @Bean
    public void initialization() {
        try {
            FileInputStream serviceAccount = new FileInputStream("src/main/resources/foodwastesavior-firebase-adminsdk-fiu5y-46643389df.json");

            FirebaseOptions options = new FirebaseOptions.Builder()
                    .setCredentials(GoogleCredentials.fromStream(serviceAccount)).build();

            FirebaseApp.initializeApp(options);
        } catch (Exception error) {
            error.printStackTrace();
        }
    }
}
