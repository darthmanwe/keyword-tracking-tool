package com.snovelli.seo.ktt.service;

import com.google.api.services.webmasters.Webmasters;
import com.google.api.services.webmasters.model.ApiDimensionFilter;
import com.google.api.services.webmasters.model.ApiDimensionFilterGroup;
import com.google.api.services.webmasters.model.SearchAnalyticsQueryRequest;
import com.google.api.services.webmasters.model.SearchAnalyticsQueryResponse;
import com.snovelli.seo.ktt.domain.DeviceType;

import java.io.IOException;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;


public class KeywordTrackingService {

    private final String websiteURL;
    private final Webmasters apiClient;


    public KeywordTrackingService(String websiteURL, Webmasters apiClient) {
        this.websiteURL = websiteURL;
        this.apiClient = apiClient;
    }


    public SearchAnalyticsQueryResponse performRequest(
            LocalDate start,
            LocalDate end,
            String query,
            Locale locale,
            DeviceType deviceType) throws IOException {

        SearchAnalyticsQueryRequest queryRequest = new SearchAnalyticsQueryRequest();


        queryRequest
                .setStartDate(start.format(ISO_LOCAL_DATE))
                .setEndDate(end.format(ISO_LOCAL_DATE))
                .setDimensionFilterGroups(buildFilterGroups(query, locale, deviceType))
                .setDimensions(Arrays.asList("date", "page", "country", "device"));


        Webmasters.Searchanalytics searchAnalytics = apiClient.searchanalytics();


        Webmasters.Searchanalytics.Query saQuery = searchAnalytics.query(websiteURL, queryRequest);


        return saQuery.execute();

    }


    private List<ApiDimensionFilterGroup> buildFilterGroups(String query, Locale locale, DeviceType deviceType) {


        ApiDimensionFilterGroup filter = new ApiDimensionFilterGroup();
        filter.setFilters(Arrays.asList(
                new ApiDimensionFilter().setDimension("query").setOperator("equals").setExpression(query),
                new ApiDimensionFilter().setDimension("country").setExpression(locale.getISO3Country())
                //,new ApiDimensionFilter().setDimension("device").setExpression(deviceType.getName())
        ));


        return Collections.singletonList(filter);
    }

}