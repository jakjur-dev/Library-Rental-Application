package com.crud.library.controller;

import com.crud.library.domain.Title;
import com.crud.library.dto.TitleDto;
import com.crud.library.mapper.TitleMapper;
import com.crud.library.service.TitleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/title")
@RequiredArgsConstructor
public class TitleController {

    private final TitleService titleService;
    private final TitleMapper titleMapper;

    @PostMapping(value = "addTitle", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addTitle(@RequestBody TitleDto titleDto) {
        Title title = titleMapper.mapToTitle(titleDto);
        titleService.saveTitle(title);
    }

    @GetMapping(value = "getTitles")
    public List<TitleDto> getTitles() {
        List<Title> titles = titleService.getAllTitles();
        return titleMapper.mapToTitleDtoList(titles);
    }
}
