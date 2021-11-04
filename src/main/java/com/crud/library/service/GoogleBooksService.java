package com.crud.library.service;

import com.crud.library.ITBookstore.client.GoogleBooksClient;
import com.crud.library.domain.ITBook;
import com.crud.library.dto.GoogleBookDto;
import com.crud.library.dto.GoogleItemDto;
import com.crud.library.dto.ITBookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class GoogleBooksService {

    private final GoogleBooksClient googleBooksClient;

    public List<GoogleBookDto> fetchBooks(String keyword) {
        return googleBooksClient.getBooks(keyword).stream().map(GoogleItemDto::getBook).collect(Collectors.toList());
    }
}
