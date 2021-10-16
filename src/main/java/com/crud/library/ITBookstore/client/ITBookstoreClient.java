package com.crud.library.ITBookstore.client;


import com.crud.library.ITBookstore.config.ApiConfig;
import com.crud.library.dto.ITBookDto;
import com.crud.library.dto.ITBookSearchDto;
import lombok.RequiredArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClientException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.util.UriComponentsBuilder;

import java.net.URI;
import java.util.*;

@Component
@RequiredArgsConstructor
public class ITBookstoreClient {


    private final RestTemplate restTemplate;
    private final ApiConfig apiConfig;
    private static final Logger LOGGER = LoggerFactory.getLogger(ITBookstoreClient.class);

    public List<ITBookDto> getBooks(String keyword) {

        try {
            ITBookSearchDto response = restTemplate.getForObject(buildUrl(keyword), ITBookSearchDto.class);
            assert response != null;
            return new ArrayList<>(Optional.ofNullable(response.getBooks())
                    .orElse(Collections.emptyList()));
        } catch (RestClientException e) {
            LOGGER.error(e.getMessage(), e);
            return Collections.emptyList();
        }
    }

    private URI buildUrl(String keyword){
        return UriComponentsBuilder.fromHttpUrl(apiConfig.getItBookstoreApiEndpoint() + "/search/" + keyword)
                .build()
                .encode()
                .toUri();
    }
}
