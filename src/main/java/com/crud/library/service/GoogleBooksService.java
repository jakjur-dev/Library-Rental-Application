package com.crud.library.service;

import com.crud.library.ITBookstore.client.GoogleBooksClient;
import com.crud.library.domain.ITBook;
import com.crud.library.dto.GoogleItemDto;
import com.crud.library.dto.ITBookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GoogleBooksService {

    private final GoogleBooksClient googleBooksClient;

    public List<GoogleItemDto> fetchBooks(String keyword) {
        return googleBooksClient.getBooks(keyword);
    }
}
