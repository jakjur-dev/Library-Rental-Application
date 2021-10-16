package com.crud.library.controller;

import com.crud.library.domain.Book;
import com.crud.library.dto.BookDto;
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

    @GetMapping("/books/{titleId}")
    public List<BookDto> getAllByTitleIdAndStatus(@PathVariable Long titleId, @RequestParam String status) {
        List<Book> books = bookService.findAllByTitleIdAndStatus(titleId, status);
        return bookMapper.mapToBookDtoList(books);
    }

    @GetMapping("/books/search/{keyword}")
    public List<BookDto> getAllByKeyword(@PathVariable String keyword) {
        List<Book> books = bookService.findAllByKeyword(keyword);
        return bookMapper.mapToBookDtoList(books);
    }

    @GetMapping("/books")
    public List<BookDto> getAllByTitle(@RequestParam String title) throws TitleNotFoundException {
        List<Book> books = bookService.findAllByTitle(title);
        return bookMapper.mapToBookDtoList(books);
    }

    @PostMapping(value = "/books", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addBook(@RequestBody BookDto bookDto) throws TitleNotFoundException {
        Book book = bookMapper.mapToBook(bookDto);
        bookService.saveBook(book);
    }

    @PutMapping("/books/{bookId}")
    public void updateBookStatus(@PathVariable Long bookId, @RequestParam String status) throws BookNotFoundException {
        bookService.setStatus(bookId, status);
    }

    @GetMapping(value = "/books/all")
    public List<BookDto> getBooks() {
        List<Book> books = bookService.getAllBooks();
        return bookMapper.mapToBookDtoList(books);
    }
}
