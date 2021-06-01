package com.crud.library.service;

import com.crud.library.domain.Book;
import com.crud.library.domain.Reader;
import com.crud.library.domain.Rental;
import com.crud.library.exceptions.BookNotFoundException;
import com.crud.library.exceptions.BookRentedException;
import com.crud.library.exceptions.ReaderNotFoundException;
import com.crud.library.exceptions.RentalNotFoundException;
import com.crud.library.repository.BookRepository;
import com.crud.library.repository.ReaderRepository;
import com.crud.library.repository.RentalRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RentalService {

    private final BookRepository bookRepository;

    private final ReaderRepository readerRepository;

    private final RentalRepository repository;

    public Rental saveRental(final Rental rental){
        return repository.save(rental);
    }

    public Rental rentBook(Long bookId, Long readerId) throws BookNotFoundException, ReaderNotFoundException, BookRentedException {

        Book book = bookRepository.findById(bookId).orElseThrow(BookNotFoundException::new);
        Reader reader = readerRepository.findById(readerId).orElseThrow(ReaderNotFoundException::new);

        if (book.getStatus().equals("available")) {
            Rental rental = new Rental(book, reader, LocalDate.now());

            book.setStatus("rented");
            bookRepository.save(book);
            return repository.save(rental);
        } else {
            throw new BookRentedException();
        }

    }

    public Rental returnBook(Long rentalId) throws RentalNotFoundException {
        Rental rental = repository.findById(rentalId).orElseThrow(RentalNotFoundException::new);
        Book book = rental.getBook();

        book.setStatus("available");
        rental.setReturnDate(LocalDate.now());

        bookRepository.save(book);
        return repository.save(rental);
    }

    public List<Rental> findAllActiveRentalsOfReader(Long readerId) throws ReaderNotFoundException {
        Reader reader = readerRepository.findById(readerId).orElseThrow(ReaderNotFoundException::new);
        return repository.findAllByReaderAndReturnDate(reader, null);
    }

    public List<Rental> getAllRentals() {
        return repository.findAll();
    }
}
