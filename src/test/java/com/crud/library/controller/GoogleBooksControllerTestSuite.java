package com.crud.library.controller;

import com.crud.library.dto.GoogleBookDto;
import com.crud.library.dto.ISBNDto;
import com.crud.library.dto.ImageLinksDto;
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

import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig
@WebMvcTest(GoogleBooksController.class)
public class GoogleBooksControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private BookstoreFacade bookstoreFacade;

    @Test
    void testSearchGoogleBooks() throws Exception {
        //Given
        ISBNDto isbnDto = new ISBNDto("ISBN13", "1234");
        ImageLinksDto imageLinksDto = new ImageLinksDto("Small Thumbnail", "Thumbnail");
        GoogleBookDto googleBookDto = new GoogleBookDto(2L, "Title", "Subtitle", List.of(isbnDto), imageLinksDto, "Info Link");
        List<GoogleBookDto> googleBookDtoList = List.of(googleBookDto);

        when(bookstoreFacade.fetchGoogleBooksBooks("keyword", 1L)).thenReturn(googleBookDtoList);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/google/{keyword}", "keyword")
                        .param("readerId", "1")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8"))
                .andExpect(status().isOk())
                .andDo(MockMvcResultHandlers.print())
                .andExpect(jsonPath("$[0].id", Matchers.is(2)))
                .andExpect(jsonPath("$[0].title", Matchers.is("Title")))
                .andExpect(jsonPath("$[0].subtitle", Matchers.is("Subtitle")))
                .andExpect(jsonPath("$[0].industryIdentifiers[0].type", Matchers.is("ISBN13")))
                .andExpect(jsonPath("$[0].industryIdentifiers[0].identifier", Matchers.is("1234")))
                .andExpect(jsonPath("$[0].imageLinks.smallThumbnail", Matchers.is("Small Thumbnail")))
                .andExpect(jsonPath("$[0].imageLinks.thumbnail", Matchers.is("Thumbnail")))
                .andExpect(jsonPath("$[0].infoLink", Matchers.is("Info Link")));
    }

}
