package com.crud.library.service;

import com.crud.library.domain.Book;
import com.crud.library.domain.Title;
import com.crud.library.domain.logs.BookLog;
import com.crud.library.exceptions.BookNotFoundException;
import com.crud.library.exceptions.TitleNotFoundException;
import com.crud.library.repository.BookRepository;
import com.crud.library.repository.TitleRepository;
import com.crud.library.repository.logs.BookLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;
    private final TitleRepository titleRepository;
    private final BookLogRepository bookLogRepository;

    public void saveBook(final Book book) {
        repository.save(book);
    }

    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    public List<Book> findAllByKeyword(String keyword) {

        List<Title> titles = titleRepository.retrieveTitleByString(keyword);
        List<Book> books = new ArrayList<>();
        titles.forEach(title -> books.addAll(repository.findAllByTitle(title)));

        bookLogRepository.save(new BookLog(keyword, LocalDate.now(), books.size()));

        return books;
    }
}
