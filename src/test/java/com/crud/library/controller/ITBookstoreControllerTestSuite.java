package com.crud.library.controller;

import com.crud.library.dto.ITBookDto;
import com.crud.library.facade.BookstoreFacade;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig
@WebMvcTest(ITBookstoreController.class)
class ITBookstoreControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookstoreFacade bookstoreFacade;

    @Test
    void searchBookstore() throws Exception {
        List<ITBookDto> itBookDtoList = List.of(new ITBookDto(100L,"Title", "Subtitle", false, "isbn", "image", "url"));

        when(bookstoreFacade.fetchITBookstoreBooks("keyword", 1L)).thenReturn(itBookDtoList);

        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/bookstore/{keyword}", "keyword")
                        .param("readerId", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$[0].id", Matchers.is(100)))
                .andExpect(jsonPath("$[0].ebook", Matchers.is(false)))
                .andExpect(jsonPath("$[0].title", Matchers.is("Title")))
                .andExpect(jsonPath("$[0].subtitle", Matchers.is("Subtitle")))
                .andExpect(jsonPath("$[0].isbn13", Matchers.is("isbn")))
                .andExpect(jsonPath("$[0].image", Matchers.is("image")))
                .andExpect(jsonPath("$[0].url", Matchers.is("url")));
    }
}