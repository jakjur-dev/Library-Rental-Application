package com.crud.library.mapper;

import com.crud.library.domain.Rental;
import com.crud.library.dto.RentalDto;
import com.crud.library.exceptions.BookNotFoundException;
import com.crud.library.exceptions.ReaderNotFoundException;
import com.crud.library.repository.BookRepository;
import com.crud.library.repository.ReaderRepository;
import com.crud.library.repository.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentalMapper {

    private final BookRepository bookRepository;
    private final ReaderRepository readerRepository;

    @Autowired
    public RentalMapper(BookRepository bookRepository, ReaderRepository readerRepository) {
        this.bookRepository = bookRepository;
        this.readerRepository = readerRepository;
    }

    public Rental mapToRental(final RentalDto rentalDto) throws BookNotFoundException, ReaderNotFoundException {
        return new Rental(
                bookRepository.findById(rentalDto.getBookId()).orElseThrow(BookNotFoundException::new),
                readerRepository.findById(rentalDto.getReaderId()).orElseThrow(ReaderNotFoundException::new),
                rentalDto.getRentDate(),
                rentalDto.getReturnDate(),
                rentalDto.getStatus()
        );
    }

    public RentalDto mapToRentalDto(final Rental rental) {
        return new RentalDto(
                rental.getId(),
                rental.getBook().getId(),
                rental.getBook().getTitle().getTitle(),
                rental.getReader().getId(),
                rental.getRentDate(),
                rental.getReturnDate(),
                rental.getStatus()
        );
    }

    public List<RentalDto> mapToRentalDtoList(final List<Rental> rentalList) {
        return rentalList.stream()
                .map(this::mapToRentalDto)
                .collect(Collectors.toList());
    }
}
