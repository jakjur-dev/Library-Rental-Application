package com.crud.library.controller;

import com.crud.library.domain.Book;
import com.crud.library.domain.Reader;
import com.crud.library.domain.Rental;
import com.crud.library.domain.Title;
import com.crud.library.dto.ReaderDto;
import com.crud.library.dto.RentalDto;
import com.crud.library.exceptions.ReaderNotFoundException;
import com.crud.library.mapper.ReaderMapper;
import com.crud.library.mapper.RentalMapper;
import com.crud.library.service.ReaderService;
import com.crud.library.service.RentalService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import java.time.LocalDate;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;

@SpringJUnitWebConfig
@WebMvcTest(RentalController.class)
class RentalControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RentalService rentalService;

    @MockBean
    private RentalMapper rentalMapper;

    @Test
    void getRentals() throws Exception {
        Title title = new Title("Author", "Title14", 1997);
        Book book = new Book(title, "available","image", LocalDate.now());
        Reader reader = new Reader("Name", "Surname", LocalDate.now(), "email18@gmail.com","password",false);
        List<Rental> rentalList = List.of(new Rental(book, reader, LocalDate.now(), LocalDate.now(), "active"));
        List<RentalDto> rentalDtoList = List.of(new RentalDto(1L, 2L, LocalDate.now(), LocalDate.now(), "active"));

        when(rentalService.getAllRentals()).thenReturn(rentalList);
        when(rentalMapper.mapToRentalDtoList(rentalList)).thenReturn(rentalDtoList);

        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/rentals")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void getUserRentals() throws Exception {
        Title title = new Title("Author", "Title15", 1997);
        Book book = new Book(title, "available","image", LocalDate.now());
        Reader reader = new Reader(1L,"Name", "Surname", LocalDate.now(), "email19@gmail.com","password",false);
        List<Rental> rentalList = List.of(new Rental(book, reader, LocalDate.now(), LocalDate.now(), "active"));
        List<RentalDto> rentalDtoList = List.of(new RentalDto(1L, 2L, LocalDate.now(), LocalDate.now(), "active"));

        when(rentalService.findAllActiveRentalsOfReader(1L)).thenReturn(rentalList);
        when(rentalMapper.mapToRentalDtoList(rentalList)).thenReturn(rentalDtoList);

        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/rentals/{readerId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)));
    }
}