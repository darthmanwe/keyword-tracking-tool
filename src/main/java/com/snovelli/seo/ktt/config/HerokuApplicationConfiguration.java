package com.snovelli.seo.ktt.config;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.webmasters.Webmasters;
import com.snovelli.seo.ktt.service.DummyKeywordTrackingService;
import com.snovelli.seo.ktt.service.GoogleKeywordTrackingService;
import com.snovelli.seo.ktt.service.KeywordTrackingService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.io.IOException;
import java.util.Collections;

@Configuration
public class HerokuApplicationConfiguration {

    private static String OAUTH_SCOPE = "https://www.googleapis.com/auth/webmasters.readonly";

    @Value("${website.url}")
    String websiteURL;

    @Bean
    @Profile(Constants.SPRING_PROFILE_HEROKU)
    public GoogleCredential googleCredential() throws IOException {
        return GoogleCredential.fromStream(ClassLoader.getSystemResourceAsStream("auth.json"))
                .createScoped(Collections.singleton(OAUTH_SCOPE));
    }


    @Bean
    @Profile(Constants.SPRING_PROFILE_HEROKU)
    public Webmasters getWebmasters(GoogleCredential credential) {
        // Create a new authorized API client
        return new Webmasters.Builder(new NetHttpTransport(), new JacksonFactory(), credential)
                .setApplicationName("WebmastersCommandLine")
                .build();
    }


    @Bean
    @Profile(Constants.SPRING_PROFILE_HEROKU)
    public KeywordTrackingService getGoogleKeywordTrackingService(Webmasters apiClient) {
        return new GoogleKeywordTrackingService(websiteURL, apiClient);
    }


    @Bean
    @Profile({Constants.SPRING_PROFILE_UNIT_TESTING, Constants.SPRING_PROFILE_DEVELOPMENT})
    public KeywordTrackingService getDummyKeywordTrackingService() {
        return new DummyKeywordTrackingService();
    }


}


