package com.crud.library.mapper;

import com.crud.library.domain.Book;
import com.crud.library.domain.Reader;
import com.crud.library.domain.Rental;
import com.crud.library.domain.Title;
import com.crud.library.dto.RentalDto;
import com.crud.library.exceptions.BookNotFoundException;
import com.crud.library.exceptions.ReaderNotFoundException;
import com.crud.library.repository.BookRepository;
import com.crud.library.repository.ReaderRepository;
import com.crud.library.repository.TitleRepository;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class RentalMapperTestSuite {

    @Autowired
    private RentalMapper rentalMapper;

    @Autowired
    private ReaderRepository readerRepository;

    @Autowired
    private BookRepository bookRepository;

    @Autowired
    private TitleRepository titleRepository;

    @Test
    void mapToRental() throws BookNotFoundException, ReaderNotFoundException {
        //Given
        Title title = new Title("Author", "Title10", 1997);
        titleRepository.save(title);
        Book book = new Book(title, "available","image", LocalDate.now());
        Long bookId = bookRepository.save(book).getId();
        Reader reader = new Reader("Name", "Surname", LocalDate.now(), "email9@gmail.com","password",false);
        Long readerId = readerRepository.save(reader).getId();
        RentalDto rentalDto = new RentalDto(bookId, readerId, LocalDate.now(), LocalDate.now(), "active");

        //When
        Rental rental = rentalMapper.mapToRental(rentalDto);

        //Then
        assertEquals(bookId, rental.getBook().getId());
        assertEquals(readerId, rental.getReader().getId());
        assertEquals(LocalDate.now(), rental.getRentDate());
        assertEquals(LocalDate.now(), rental.getReturnDate());
        assertEquals("active", rental.getStatus());
    }

    @Test
    void mapToRentalDto() {
        //Given
        Title title = new Title("Author", "Title11", 1997);
        Book book = new Book(title, "available","image", LocalDate.now());
        Reader reader = new Reader("Name", "Surname", LocalDate.now(), "email10@gmail.com","password",false);
        Rental rental = new Rental(book, reader,  LocalDate.now(),  LocalDate.now(), "active");

        //When
        RentalDto rentalDto = rentalMapper.mapToRentalDto(rental);

        //Then
        assertEquals("Title11", rentalDto.getBookTitle());
        assertEquals(LocalDate.now(), rentalDto.getRentDate());
        assertEquals(LocalDate.now(), rentalDto.getReturnDate());
        assertEquals("active", rentalDto.getStatus());
    }

    @Test
    void mapToRentalDtoList() {
    }
}