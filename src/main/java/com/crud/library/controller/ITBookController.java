package com.crud.library.controller;

import com.crud.library.domain.ITBook;
import com.crud.library.dto.ITBookDto;
import com.crud.library.exceptions.ReaderNotFoundException;
import com.crud.library.mapper.ITBookMapper;
import com.crud.library.service.ITBookService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin("*")
@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class ITBookController {

    @Autowired
    private ITBookService itBookService;

    @Autowired
    private ITBookMapper itBookMapper;

    @RequestMapping(method = RequestMethod.GET, value = "/watchlist/{readerId}")
    public List<ITBookDto> getReaderWatchlist(@PathVariable Long readerId) {
        List<ITBook> readerBooks = itBookService.getAllBookstoreITBooksByReader(readerId);
        return itBookMapper.mapToITBookDtoList(readerBooks);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/watchlist/e/{readerId}")
    public List<ITBookDto> getReaderEbooks(@PathVariable Long readerId) {
        List<ITBook> readerBooks = itBookService.getAllEbooksITBooksByReader(readerId);
        return itBookMapper.mapToITBookDtoList(readerBooks);
    }

    @PostMapping(value = "/watchlist/{readerId}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addITBook(@RequestBody ITBookDto iTBookDto, @PathVariable Long readerId) throws ReaderNotFoundException {
        ITBook itBook = itBookMapper.mapToITBook(iTBookDto, readerId);
        itBookService.saveITBook(itBook);
    }

    @DeleteMapping(value = "/watchlist/{bookId}")
    public void deleteITBook(@PathVariable Long bookId){
        itBookService.deleteITBook(bookId);
    }
}
