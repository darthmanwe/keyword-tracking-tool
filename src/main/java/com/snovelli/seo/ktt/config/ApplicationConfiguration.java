package com.snovelli.seo.ktt.config;

import com.google.api.client.googleapis.auth.oauth2.GoogleCredential;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.jackson2.JacksonFactory;
import com.google.api.services.webmasters.Webmasters;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.io.IOException;
import java.util.Collections;

/**
 * Created by Salvatore on 25/05/2016.
 */
@Configuration
public class ApplicationConfiguration {
    private static String OAUTH_SCOPE = "https://www.googleapis.com/auth/webmasters.readonly";

    @Bean
    public GoogleCredential googleCredential() throws IOException {
        return GoogleCredential.fromStream(ClassLoader.getSystemResourceAsStream("auth.json"))
                .createScoped(Collections.singleton(OAUTH_SCOPE));
    }


    @Bean
    public Webmasters getWebmasters(GoogleCredential credential){
        // Create a new authorized API client
        return new Webmasters.Builder(new NetHttpTransport(), new JacksonFactory(), credential)
                .setApplicationName("WebmastersCommandLine")
                .build();
    }
}


