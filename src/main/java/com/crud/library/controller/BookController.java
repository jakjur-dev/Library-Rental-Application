package com.crud.library.controller;

import com.crud.library.domain.Book;
import com.crud.library.dto.BookDto;
import com.crud.library.dto.TitleDto;
import com.crud.library.exceptions.BookNotFoundException;
import com.crud.library.exceptions.TitleNotFoundException;
import com.crud.library.mapper.BookMapper;
import com.crud.library.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class BookController {

    private final BookService bookService;
    private final BookMapper bookMapper;

    @GetMapping("/books/search/{keyword}")
    public List<BookDto> getAllByKeyword(@PathVariable String keyword) {
        List<Book> books = bookService.findAllByKeyword(keyword);
        return bookMapper.mapToBookDtoList(books);
    }

    @PostMapping(value = "/books", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addBook(@RequestBody BookDto bookDto) throws TitleNotFoundException {
        Book book = bookMapper.mapToBook(bookDto);
        bookService.saveBook(book);
    }

    @PutMapping("/books")
    public void updateBookStatus(@RequestBody BookDto bookDto) throws TitleNotFoundException {
        bookService.saveBook(bookMapper.mapToBook(bookDto));
    }

    @GetMapping(value = "/books/all")
    public List<BookDto> getBooks() {
        List<Book> books = bookService.getAllBooks();
        return bookMapper.mapToBookDtoList(books);
    }
}
