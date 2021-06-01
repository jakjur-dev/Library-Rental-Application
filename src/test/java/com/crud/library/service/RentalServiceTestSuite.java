package com.crud.library.service;

import com.crud.library.domain.Book;
import com.crud.library.domain.Reader;
import com.crud.library.domain.Rental;
import com.crud.library.domain.Title;
import com.crud.library.exceptions.*;
import com.crud.library.repository.BookRepository;
import com.crud.library.repository.ReaderRepository;
import com.crud.library.repository.RentalRepository;
import com.crud.library.repository.TitleRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDate;
import java.util.List;

@SpringBootTest
public class RentalServiceTestSuite {

    @Autowired
    private BookService bookService;

    @Autowired
    private TitleService titleService;

    @Autowired
    private RentalService rentalService;

    @Autowired
    private ReaderService readerService;

    @Autowired
    private TitleRepository titleRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private ReaderRepository readerRepository;

    @Autowired
    private RentalRepository rentalRepository;

    @Test
    public void testRentBook() throws BookNotFoundException, ReaderNotFoundException, BookRentedException {
        //Given
        Title title = new Title("Author", "Title", 1997);
        titleService.saveTitle(title);
        Book book = new Book(title, "available");
        bookService.saveBook(book);
        Reader reader = new Reader("Name", "Surname", LocalDate.now());
        readerService.saveReader(reader);

        //When
        Rental rental = rentalService.rentBook(book.getId(), reader.getId());
        List<Book> books = bookService.findAllByTitleIdAndStatus(title.getId(), "rented");
        List<Rental> rentals = rentalService.findAllActiveRentalsOfReader(reader.getId());

        //Then
        Assertions.assertEquals(1, books.size());
        Assertions.assertEquals(1, rentals.size());

        //Cleanup
        rentalRepository.deleteById(rental.getId());
        bookRepository.deleteById(book.getId());
        titleRepository.deleteById(title.getId());
        readerRepository.deleteById(reader.getId());
    }

    @Test
    public void testReturnBook() throws BookNotFoundException, ReaderNotFoundException, RentalNotFoundException, BookRentedException {
        //Given
        Title title = new Title("Author", "Title", 1997);
        titleService.saveTitle(title);
        Book book = new Book(title, "available");
        bookService.saveBook(book);
        Reader reader = new Reader("Name", "Surname", LocalDate.now());
        readerService.saveReader(reader);

        //When
        Rental rental = rentalService.rentBook(book.getId(), reader.getId());
        rentalService.returnBook(rental.getId());
        List<Book> books = bookService.findAllByTitleIdAndStatus(title.getId(), "available");
        List<Rental> rentals = rentalService.findAllActiveRentalsOfReader(reader.getId());

        //Then
        Assertions.assertEquals(1, books.size());
        Assertions.assertEquals(0, rentals.size());
        //Cleanup
        rentalRepository.deleteById(rental.getId());
        bookRepository.deleteById(book.getId());
        titleRepository.deleteById(title.getId());
        readerRepository.deleteById(reader.getId());
    }
}
