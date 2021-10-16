package com.crud.library.controller;

import com.crud.library.domain.Rental;
import com.crud.library.dto.RentalDto;
import com.crud.library.exceptions.BookNotFoundException;
import com.crud.library.exceptions.BookRentedException;
import com.crud.library.exceptions.ReaderNotFoundException;
import com.crud.library.exceptions.RentalNotFoundException;
import com.crud.library.mapper.RentalMapper;
import com.crud.library.service.RentalService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class RentalController {

    private final RentalService rentalService;
    private final RentalMapper rentalMapper;

    @GetMapping(value = "/rentals")
    public List<RentalDto> getRentals() {
        List<Rental> rentals = rentalService.getAllRentals();
        return rentalMapper.mapToRentalDtoList(rentals);
    }

    @PostMapping(value = "/rentals/{bookId}")
    public void rentBook(@PathVariable Long bookId, @RequestParam Long readerId) throws BookNotFoundException, ReaderNotFoundException, BookRentedException {
        rentalService.rentBook(bookId, readerId);
    }

    @GetMapping(value = "/rentals/{readerId}")
    public List<RentalDto> getUserRentals(@PathVariable Long readerId) throws ReaderNotFoundException {
        List<Rental> rentals = rentalService.findAllActiveRentalsOfReader(readerId);
        return rentalMapper.mapToRentalDtoList(rentals);
    }

    @PutMapping(value = "/rentals/{rentalId}")
    public void returnBook(@PathVariable Long rentalId) throws RentalNotFoundException {
        rentalService.returnBook(rentalId);
    }
}
