package com.crud.library.service;

import com.crud.library.ITBookstore.client.GoogleBooksClient;
import com.crud.library.domain.Book;
import com.crud.library.domain.Title;
import com.crud.library.exceptions.TitleNotUniqueException;
import com.crud.library.repository.BookRepository;
import com.crud.library.repository.TitleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;


import java.time.LocalDate;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class BookServiceTestSuite {

    @Autowired
    private BookService bookService;

    @Autowired
    private TitleService titleService;

    @Test
    public void testGetAllKeyword() {
        //Given
        Title title = new Title("Author", "Book", 1997);
        titleService.saveTitle(title);
        Book book = new Book(title, "available","image", LocalDate.now());
        bookService.saveBook(book);

        //When
        List<Book> books = bookService.findAllByKeyword("Boo");

        //Then
        Assertions.assertEquals(1, books.size());
    }
}
