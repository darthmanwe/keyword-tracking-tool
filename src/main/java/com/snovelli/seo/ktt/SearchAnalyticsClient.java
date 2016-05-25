package com.snovelli.seo.ktt;

import com.google.api.services.webmasters.Webmasters;
import com.google.api.services.webmasters.model.ApiDimensionFilter;
import com.google.api.services.webmasters.model.ApiDimensionFilterGroup;
import com.google.api.services.webmasters.model.SearchAnalyticsQueryRequest;
import com.google.api.services.webmasters.model.SearchAnalyticsQueryResponse;
import com.snovelli.seo.ktt.model.DeviceType;

import java.io.IOException;
import java.time.Period;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Locale;

/**
 * Created by Salvatore on 25/05/2016.
 */

public class SearchAnalyticsClient {


    private final String websiteURL;


    private final Webmasters apiClient;


    public SearchAnalyticsClient(String websiteURL, Webmasters apiClient) {
        this.websiteURL = websiteURL;
        this.apiClient = apiClient;
    }


    public SearchAnalyticsQueryResponse performRequest(
            Period period,
            String query,
            Locale locale,
            DeviceType deviceType) throws IOException {

        SearchAnalyticsQueryRequest queryRequest = new SearchAnalyticsQueryRequest();


        queryRequest
                .setStartDate("2016-04-01")
                .setEndDate("2016-05-01")
                .setDimensionFilterGroups(buildFilterGroups(query, locale, deviceType))
                .setDimensions(Arrays.asList("date", "page", "country", "device"))
        //.setRowLimit(100)
        ;


        Webmasters.Searchanalytics searchAnalytics = apiClient.searchanalytics();


        Webmasters.Searchanalytics.Query saQuery = searchAnalytics.query(websiteURL, queryRequest);
        SearchAnalyticsQueryResponse response = saQuery.execute();


        System.out.println("Found " + response.getRows().size() + " rows");
        System.out.println(response.toPrettyString());

        return response;

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