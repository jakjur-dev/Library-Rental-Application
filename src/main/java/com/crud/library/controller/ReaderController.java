package com.crud.library.controller;

import com.crud.library.domain.Reader;
import com.crud.library.domain.Title;
import com.crud.library.dto.ReaderDto;
import com.crud.library.dto.TitleDto;
import com.crud.library.exceptions.ReaderNotFoundException;
import com.crud.library.mapper.ReaderMapper;
import com.crud.library.service.ReaderService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("/v1")
@RequiredArgsConstructor
public class ReaderController {

    private final ReaderService readerService;
    private final ReaderMapper readerMapper;

    @PostMapping(value = "/readers", consumes = MediaType.APPLICATION_JSON_VALUE)
    public void addReader(@RequestBody ReaderDto readerDto) {
        readerService.saveReader(readerMapper.mapToReader(readerDto));
    }

    @GetMapping(value = "/readers")
    public List<ReaderDto> getReaders() {
        return readerMapper.mapToReaderDtoList(readerService.getAllReaders());
    }

    @GetMapping(value = "/readers/login")
    public ReaderDto loginReader(@RequestParam String email, @RequestParam String password) throws ReaderNotFoundException {
        return readerMapper.mapToReaderDto(readerService.getReaderByEmailAndPassword(email, password));
    }
}
