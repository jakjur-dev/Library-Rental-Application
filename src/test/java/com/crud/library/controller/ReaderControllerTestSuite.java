package com.crud.library.controller;

import com.crud.library.domain.ITBook;
import com.crud.library.domain.Reader;
import com.crud.library.dto.ITBookDto;
import com.crud.library.dto.ReaderDto;
import com.crud.library.exceptions.ReaderNotFoundException;
import com.crud.library.mapper.ReaderMapper;
import com.crud.library.service.ReaderService;
import org.hamcrest.Matchers;
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
@WebMvcTest(ReaderController.class)
class ReaderControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ReaderService readerService;

    @MockBean
    private ReaderMapper readerMapper;

    @Test
    void getReaders() throws Exception {
        List<Reader> readerList = List.of(new Reader("Name", "Surname", LocalDate.now(), "email4@gmail.com","password",false));
        List<ReaderDto> readerDtoList = List.of(new ReaderDto("Name", "Surname", LocalDate.now(), "email3@gmail.com","password",false));

        when(readerService.getAllReaders()).thenReturn(readerList);
        when(readerMapper.mapToReaderDtoList(readerList)).thenReturn(readerDtoList);

        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/readers")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void loginReader() throws Exception {
        Reader reader = new Reader("Name", "Surname", LocalDate.now(), "email@gmail.com","password",false);
        ReaderDto readerDto = new ReaderDto("Name", "Surname", LocalDate.now(), "email@gmail.com","password",false);

        when(readerService.getReaderByEmailAndPassword("email", "password")).thenReturn(reader);
        when(readerMapper.mapToReaderDto(reader)).thenReturn(readerDto);

        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/readers/login")
                        .param("email", "email")
                        .param("password", "password")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.name",  Matchers.is("Name")))
                .andExpect(jsonPath("$.surname",  Matchers.is("Surname")))
                .andExpect(jsonPath("$.email",  Matchers.is("email@gmail.com")))
                .andExpect(jsonPath("$.password",  Matchers.is("password")))
                .andExpect(jsonPath("$.admin",  Matchers.is(false)));
    }
}