package com.crud.library.controller;

import com.crud.library.domain.Reader;
import com.crud.library.domain.Title;
import com.crud.library.dto.ReaderDto;
import com.crud.library.dto.TitleDto;
import com.crud.library.mapper.ReaderMapper;
import com.crud.library.service.ReaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1/reader")
@RequiredArgsConstructor
public class ReaderController {

    private final ReaderService readerService;
    private final ReaderMapper readerMapper;

    @PostMapping(value = "addReader", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addReader(@RequestBody ReaderDto readerDto) {
        Reader reader = readerMapper.mapToReader(readerDto);
        readerService.saveReader(reader);
    }

    @GetMapping(value = "getReaders")
    public List<ReaderDto> getReaders() {
        List<Reader> readers = readerService.getAllReaders();
        return readerMapper.mapToReaderDtoList(readers);
    }
}
