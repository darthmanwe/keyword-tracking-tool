package com.snovelli.seo.ktt.controller;

import com.google.api.services.webmasters.model.ApiDataRow;
import com.google.api.services.webmasters.model.SearchAnalyticsQueryResponse;
import com.snovelli.seo.ktt.domain.DeviceType;
import com.snovelli.seo.ktt.domain.KeywordPosition;
import com.snovelli.seo.ktt.repository.KeywordPositionRepository;
import com.snovelli.seo.ktt.service.KeywordTrackingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

/**
 * Created by Salvatore on 25/05/2016.
 */
@RestController
public class KeywordTrackingController {

    @Autowired
    KeywordTrackingService searchAnalyticsClient;

    @Autowired
    KeywordPositionRepository repository;

    @RequestMapping("/keyword-position")
    public List<KeywordPosition> getKeywordPosition(@RequestParam String query) throws IOException {


        List<KeywordPosition> keywordPositions = searchAnalyticsClient.performRequest(
                LocalDate.of(2016, 4, 1), LocalDate.of(2016, 5, 1),
                query,
                Locale.ITALY,
                DeviceType.DESKTOP);

        //repository.save(keywordPositions);


        return keywordPositions;
    }




}
