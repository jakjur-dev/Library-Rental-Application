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

    @Test
    public void testRentBook() throws BookNotFoundException, ReaderNotFoundException, BookRentedException, TitleNotUniqueException {
        //Given
        Title title = new Title("Author", "Title", 1997);
        titleService.saveTitle(title);
        Book book = new Book(title, "available","image", LocalDate.now());
        bookService.saveBook(book);
        Reader reader = new Reader("Name", "Surname", LocalDate.now(), "email1@gmail.com","password",false);
        readerService.saveReader(reader);

        //When
        Rental rental = rentalService.rentBook(book.getId(), reader.getId());
        List<Rental> rentals = rentalService.findAllActiveRentalsOfReader(reader.getId());

        //Then
        Assertions.assertEquals(1, rentals.size());
    }

    @Test
    public void testReturnBook() throws BookNotFoundException, ReaderNotFoundException, RentalNotFoundException, BookRentedException, TitleNotUniqueException {
        //Given
        Title title = new Title("Author2", "Title2", 1997);
        titleService.saveTitle(title);
        Book book = new Book(title, "available","image", LocalDate.now());
        bookService.saveBook(book);
        Reader reader = new Reader("Name", "Surname", LocalDate.now(), "email2@gmail.com","password",false);
        readerService.saveReader(reader);

        //When
        Rental rental = rentalService.rentBook(book.getId(), reader.getId());
        rentalService.returnBook(rental.getId());
        List<Rental> rentals = rentalService.findAllActiveRentalsOfReader(reader.getId());

        //Then
        Assertions.assertEquals(0, rentals.size());
    }

//    @Test
//    public void testRetrieveDueBooks() throws TitleNotUniqueException, ReaderNotFoundException {
//        //Given
//        Title title = new Title("Author", "Title", 1997);
//        titleService.saveTitle(title);
//        Book book = new Book(title, "available","image", LocalDate.now());
//        bookService.saveBook(book);
//        Reader reader = new Reader("Name", "Surname", LocalDate.now(), "Jurak2012@gmail.com","password",false);
//        readerService.saveReader(reader);
//        Rental rental = new Rental(book, reader, LocalDate.of(2020,9,9), LocalDate.of(2021,6,9), "active");
//        rentalService.saveRental(rental);
//
//        //When
//        List<Rental> rentals = rentalService.findAllDueRentalsOfReader(reader.getId());
//
//        //Then
//        Assertions.assertEquals(1, rentals.size());
//    }
}
