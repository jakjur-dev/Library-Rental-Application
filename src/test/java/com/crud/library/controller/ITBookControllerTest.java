package com.crud.library.controller;

import com.crud.library.domain.ITBook;
import com.crud.library.domain.Reader;
import com.crud.library.domain.Title;
import com.crud.library.dto.ITBookDto;
import com.crud.library.dto.ReaderDto;
import com.crud.library.dto.TitleDto;
import com.crud.library.facade.BookstoreFacade;
import com.crud.library.mapper.ITBookMapper;
import com.crud.library.service.ITBookService;
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
@WebMvcTest(ITBookController.class)
class ITBookControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private ITBookService itBookService;

    @MockBean
    private ITBookMapper itBookMapper;

    @Test
    void getReaderWatchlist() throws Exception {
        List<ITBook> itBookList = List.of(new ITBook("Title", "Subtitle", false, "isbn", "image", "url", new Reader()));
        List<ITBookDto> itBookDtoList = List.of(new ITBookDto("Title", "Subtitle", false, "isbn", "image", "url"));

        when(itBookService.getAllBookstoreITBooksByReader(1L)).thenReturn(itBookList);
        when(itBookMapper.mapToITBookDtoList(itBookList)).thenReturn(itBookDtoList);

        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/watchlist/{readerId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)));
    }

    @Test
    void getReaderEbooks() throws Exception{
        List<ITBook> itBookList = List.of(new ITBook("Title", "Subtitle", true, "isbn", "image", "url", new Reader()));
        List<ITBookDto> itBookDtoList = List.of(new ITBookDto("Title", "Subtitle", true, "isbn", "image", "url"));

        when(itBookService.getAllEbooksITBooksByReader(1L)).thenReturn(itBookList);
        when(itBookMapper.mapToITBookDtoList(itBookList)).thenReturn(itBookDtoList);

        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/watchlist/e/{readerId}", 1L)
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)));
    }
}