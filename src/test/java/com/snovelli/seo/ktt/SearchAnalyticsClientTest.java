package com.snovelli.seo.ktt;

import com.google.api.services.webmasters.Webmasters;
import com.google.api.services.webmasters.model.ApiDimensionFilter;
import com.google.api.services.webmasters.model.ApiDimensionFilterGroup;
import com.google.api.services.webmasters.model.SearchAnalyticsQueryRequest;

import com.snovelli.seo.ktt.model.DeviceType;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.io.IOException;
import java.time.LocalDate;
import java.util.List;
import java.util.Locale;

import static java.time.format.DateTimeFormatter.ISO_LOCAL_DATE;
import static org.hamcrest.Matchers.equalTo;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.Matchers.nullValue;
import static org.junit.Assert.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Matchers.matches;
import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.verify;


@RunWith(MockitoJUnitRunner.class)
public class SearchAnalyticsClientTest {


    public static final String WEBSITE_URL = "http://www.example.com";
    public static final LocalDate START_DATE = LocalDate.of(2016, 4, 1);
    public static final LocalDate END_DATE = LocalDate.of(2016, 5, 1);
    public static final String TEST_QUERY = "test query";
    public static final Locale BEST_LOCALE = Locale.ITALY;


    @Mock
    Webmasters apiClient;

    @Mock
    Webmasters.Searchanalytics searchAnalytics;

    @Mock
    Webmasters.Searchanalytics.Query query;


    SearchAnalyticsClient sut;

    @Before
    public void init() throws IOException {
        doReturn(searchAnalytics).when(apiClient).searchanalytics();
        doReturn(query).when(searchAnalytics).query(anyString(), any(SearchAnalyticsQueryRequest.class));
        sut = new SearchAnalyticsClient(WEBSITE_URL, apiClient);
    }


    @Test
    public void performRequestTest() throws Exception {


        sut.performRequest(START_DATE, END_DATE, TEST_QUERY, BEST_LOCALE, DeviceType.DESKTOP);


        ArgumentCaptor<SearchAnalyticsQueryRequest> argumentCaptor = ArgumentCaptor.forClass(SearchAnalyticsQueryRequest.class);
        verify(searchAnalytics).query(matches(WEBSITE_URL), argumentCaptor.capture());

        SearchAnalyticsQueryRequest queryRequest = argumentCaptor.getValue();

        assertThat(queryRequest.getStartDate(), is(START_DATE.format(ISO_LOCAL_DATE)));
        assertThat(queryRequest.getEndDate(), is(END_DATE.format(ISO_LOCAL_DATE)));
        assertThat(queryRequest.getRowLimit(), is(nullValue()));

        List<ApiDimensionFilterGroup> filterGroups = queryRequest.getDimensionFilterGroups();
        assertThat(filterGroups.size(), is(1));

        List<ApiDimensionFilter> filters = filterGroups.get(0).getFilters();
        assertThat(filters.size(), is(2));
        assertThat(filters.get(0), equalTo(new ApiDimensionFilter().setDimension("query").setOperator("equals").setExpression(TEST_QUERY)));
        assertThat(filters.get(1), equalTo(new ApiDimensionFilter().setDimension("country").setExpression(BEST_LOCALE.getISO3Country())));


    }


}