package com.crud.library.ITBookstore.client;

import com.crud.library.ITBookstore.config.ApiConfig;
import com.crud.library.dto.*;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.actuate.endpoint.ApiVersion;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
public class GoogleBooksClient {

    @Bean
    public RestTemplate googleRestTemplate(RestTemplateBuilder builder){
        return builder.build();
    }

    @Autowired
    @Qualifier("googleRestTemplate")
    private RestTemplate googleRestTemplate;

    private final ApiConfig apiConfig;
    private static final Logger LOGGER = LoggerFactory.getLogger(GoogleBooksClient.class);

    public List<GoogleItemDto> getBooks(String keyword) {

        try {
            GoogleBookSearchDto response = googleRestTemplate.getForObject(buildUrl(keyword), GoogleBookSearchDto.class);
            assert response != null;
            return new ArrayList<>(Optional.ofNullable(response.getItems())
                    .orElse(Collections.emptyList()));
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    private URI buildUrl(String keyword){
        return UriComponentsBuilder.fromHttpUrl(apiConfig.getGoogleBooksApiEndpoint() + "/volumes")
                .queryParam("key", apiConfig.getGoogleBooksApiKey())
                .queryParam("q", keyword)
                .queryParam("filter", "ebooks")
                .build()
                .encode()
                .toUri();
    }
}
