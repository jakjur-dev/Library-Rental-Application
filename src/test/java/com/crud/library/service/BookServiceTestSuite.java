package com.crud.library.service;

import com.crud.library.domain.Book;
import com.crud.library.domain.Reader;
import com.crud.library.domain.Title;
import com.crud.library.exceptions.BookNotFoundException;
import com.crud.library.repository.BookRepository;
import com.crud.library.repository.TitleRepository;
import org.apache.tomcat.jni.Local;
import org.assertj.core.api.Assert;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;


import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class BookServiceTestSuite {

    @Autowired
    private BookService bookService;

    @Autowired
    private TitleService titleService;

    @Autowired
    private TitleRepository titleRepository;

    @Autowired
    private BookRepository bookRepository;

    @Test
    public void testGetAllByStatus(){
        //Given
        Title title = new Title("Author", "Title", 1997);
        titleService.saveTitle(title);
        Book book = new Book(title, "available");
        bookService.saveBook(book);

        //When
        List<Book> books = bookService.findAllByTitleIdAndStatus(title.getId(),"available");

        //Then
        Assertions.assertEquals(1, books.size());

        //Cleanup
        bookRepository.deleteById(book.getId());
        titleRepository.deleteById(title.getId());
    }

    @Test
    public void testSetStatus() throws BookNotFoundException {
        //Given
        Title title = new Title("Author", "Title", 1997);
        titleService.saveTitle(title);
        Book book = new Book(title, "available");
        bookService.saveBook(book);

        //When
        bookService.setStatus(book.getId(), "rented");
        List<Book> books = bookService.findAllByTitleIdAndStatus(title.getId(),"rented");

        //Then
        Assertions.assertEquals(1, books.size());

        //Cleanup
        bookRepository.deleteById(book.getId());
        titleRepository.deleteById(title.getId());
    }
}
