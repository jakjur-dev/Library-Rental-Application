package com.crud.library.service;

import com.crud.library.domain.Book;
import com.crud.library.domain.Title;
import com.crud.library.exceptions.BookNotFoundException;
import com.crud.library.exceptions.TitleNotFoundException;
import com.crud.library.repository.BookRepository;
import com.crud.library.repository.TitleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class BookService {

    private final BookRepository repository;
    private final TitleRepository titleRepository;

    public void saveBook(final Book book) {
        repository.save(book);
    }

    public void setStatus(Long bookId, String status) throws BookNotFoundException {

        Book book = repository.findById(bookId).orElseThrow(BookNotFoundException::new);
        book.setStatus(status);
        repository.save(book);
    }

    public List<Book> findAllByTitleIdAndStatus(Long titleId, String status){
        return repository.findAllByTitleIdAndStatus(titleId, status);
    }

    public List<Book> findAllByTitle(String title) throws TitleNotFoundException {
        return repository.findAllByTitle(titleRepository.findByTitle(title).orElseThrow(TitleNotFoundException::new));
    }

    public List<Book> getAllBooks() {
        return repository.findAll();
    }

    public List<Book> findAllByKeyword(String keyword) {
        List<Title> titles = titleRepository.retrieveTitleByString(keyword);
        List<Book> books = new ArrayList<>();
        titles.forEach(title -> books.addAll(repository.findAllByTitle(title)));
        return books;
    }
}
