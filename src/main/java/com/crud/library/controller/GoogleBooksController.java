package com.crud.library.controller;

import com.crud.library.dto.GoogleBookDto;
import com.crud.library.dto.GoogleItemDto;
import com.crud.library.dto.ITBookDto;
import com.crud.library.service.GoogleBooksService;
import com.crud.library.service.ITBookstoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class GoogleBooksController {

    @Autowired
    private GoogleBooksService googleBooksService;

    @RequestMapping(method = RequestMethod.GET, value = "/google/{keyword}")
    public List<GoogleItemDto> searchBookstore(@PathVariable String keyword, @RequestParam Long readerId) {
        return googleBooksService.fetchBooks(keyword, readerId);
    }
}
