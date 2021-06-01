package com.crud.library.service;

import com.crud.library.domain.Book;
import com.crud.library.exceptions.BookNotFoundException;
import com.crud.library.repository.BookRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;

    public Book saveBook(final Book book) {
        return repository.save(book);
    }

    public Book setStatus(Long bookId, String status) throws BookNotFoundException {

        Book book = repository.findById(bookId).orElseThrow(BookNotFoundException::new);
        book.setStatus(status);
        return repository.save(book);
    }

    public List<Book> findAllByTitleIdAndStatus(Long titleId, String status){
        return repository.findAllByTitleIdAndStatus(titleId, status);
    }

    public List<Book> getAllBooks() {
        return repository.findAll();
    }
}
