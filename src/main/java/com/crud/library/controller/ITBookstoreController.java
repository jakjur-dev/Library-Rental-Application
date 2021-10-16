package com.crud.library.controller;

import com.crud.library.dto.ITBookDto;
import com.crud.library.service.ITBookstoreService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class ITBookstoreController {

    @Autowired
    private ITBookstoreService itBookstoreService;

    @RequestMapping(method = RequestMethod.GET, value = "/bookstore/{keyword}")
    public List<ITBookDto> searchBookstore(@PathVariable String keyword, @RequestParam Long readerId) {
        return itBookstoreService.fetchBooks(keyword, readerId);
    }
}
