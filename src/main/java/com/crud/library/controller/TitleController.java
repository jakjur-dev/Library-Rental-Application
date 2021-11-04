package com.crud.library.controller;

import com.crud.library.domain.Title;
import com.crud.library.dto.TitleDto;
import com.crud.library.exceptions.TitleNotFoundException;
import com.crud.library.exceptions.TitleNotUniqueException;
import com.crud.library.mapper.TitleMapper;
import com.crud.library.service.TitleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class TitleController {

    private final TitleService titleService;
    private final TitleMapper titleMapper;

    @PostMapping(value = "/titles", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addTitle(@RequestBody TitleDto titleDto) {
        titleService.saveTitle(titleMapper.mapToTitle(titleDto));
    }

    @GetMapping(value = "/titles")
    public List<TitleDto> getTitles() {
        return titleMapper.mapToTitleDtoList(titleService.getAllTitles());
    }

    @PutMapping(value = "/titles")
    public void changeTitleName(@RequestBody TitleDto titleDto) {
        titleService.saveTitle(titleMapper.mapToTitle(titleDto));
    }
}
