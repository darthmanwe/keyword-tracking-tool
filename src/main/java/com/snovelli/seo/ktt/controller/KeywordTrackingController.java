package com.snovelli.seo.ktt.controller;

import com.google.api.services.webmasters.model.SearchAnalyticsQueryResponse;
import com.snovelli.seo.ktt.service.KeywordTrackingService;
import com.snovelli.seo.ktt.domain.DeviceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Locale;

/**
 * Created by Salvatore on 25/05/2016.
 */
@RestController
public class KeywordTrackingController {

    @Autowired
    KeywordTrackingService searchAnalyticsClient;

    @RequestMapping("/keyword-position")
    public SearchAnalyticsQueryResponse getKeywordPosition(@RequestParam String query) throws IOException {
        return searchAnalyticsClient.performRequest(
                LocalDate.of(2016, 4, 1), LocalDate.of(2016, 5, 1),
                query,
                Locale.ITALY,
                DeviceType.DESKTOP);
    }


}
