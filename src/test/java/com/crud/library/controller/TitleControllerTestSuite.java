package com.crud.library.controller;

import com.crud.library.domain.Title;
import com.crud.library.dto.TitleDto;
import com.crud.library.mapper.TitleMapper;
import com.crud.library.service.TitleService;
import com.google.gson.Gson;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.context.annotation.Bean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.web.SpringJUnitWebConfig;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.anyList;

import java.util.List;

import static org.mockito.Mockito.doReturn;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@SpringJUnitWebConfig
@WebMvcTest(TitleController.class)
public class TitleControllerTestSuite {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private TitleService titleService;

    @MockBean
    private TitleMapper titleMapper;

    @Test
    void testAddTitle() throws Exception {
        //Given
        TitleDto newTitle = new TitleDto("Author", "Title", 2000);
        Gson gson = new Gson();
        String jsonContent = gson.toJson(newTitle);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .post("/v1/titles")
                        .contentType(MediaType.APPLICATION_JSON)
                        .characterEncoding("UTF-8")
                        .content(jsonContent))
                .andExpect(status().is(200));
    }

    @Test
    void getTitles() throws Exception {
        //Given
        List<Title> titleList = List.of(new Title("Author", "Title12", 1997));
        List<TitleDto> titleDtoList = List.of(new TitleDto("Author", "Title13", 1997));

        when(titleService.getAllTitles()).thenReturn(titleList);
        when(titleMapper.mapToTitleDtoList(titleList)).thenReturn(titleDtoList);

        //When & Then
        mockMvc
                .perform(MockMvcRequestBuilders
                        .get("/v1/titles")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$", hasSize(1)));
    }

}
