package com.crud.library.service;

import com.crud.library.ITBookstore.client.ITBookstoreClient;
import com.crud.library.domain.ITBook;
import com.crud.library.dto.ITBookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class ITBookstoreService {

    private final ITBookstoreClient itBookstoreClient;

    public List<ITBookDto> fetchBooks(String keyword) {
        return itBookstoreClient.getBooks(keyword);
    }
}
