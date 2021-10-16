package com.crud.library.ITBookstore.config;

import lombok.Getter;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Getter
@Component
public class ApiConfig {

    @Value("${itBookstore.api.endpoint.prod}")
    private String itBookstoreApiEndpoint;

    @Value("https://www.googleapis.com/books/v1")
    private String googleBooksApiEndpoint;

    @Value("AIzaSyAzcD3uL1Sj9-e-sXiHxAepds3ZXkBNfFo")
    private String googleBooksApiKey;
}
