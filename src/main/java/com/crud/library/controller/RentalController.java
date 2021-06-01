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
@RequestMapping("/v1/rental")
@RequiredArgsConstructor
public class RentalController {

    private final RentalService rentalService;
    private final RentalMapper rentalMapper;

    @GetMapping(value = "getRentals")
    public List<RentalDto> getRentals() {
        List<Rental> rentals = rentalService.getAllRentals();
        return rentalMapper.mapToRentalDtoList(rentals);
    }

    @PostMapping(value = "rentBook")
    public void rentBook(@RequestParam Long bookId, @RequestParam Long readerId) throws BookNotFoundException, ReaderNotFoundException, BookRentedException {
        rentalService.rentBook(bookId, readerId);
    }

    @GetMapping(value = "getUserRentals/{readerId}")
    public List<RentalDto> getUserRentals(@PathVariable Long readerId) throws ReaderNotFoundException {
        List<Rental> rentals = rentalService.findAllActiveRentalsOfReader(readerId);
        return rentalMapper.mapToRentalDtoList(rentals);
    }

    @PutMapping(value = "returnBook")
    public void returnBook(@RequestParam Long rentalId) throws RentalNotFoundException {
        rentalService.returnBook(rentalId);
    }
}
