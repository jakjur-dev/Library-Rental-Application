package com.crud.library.mapper;

import com.crud.library.domain.Reader;
import com.crud.library.dto.ReaderDto;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class ReaderMapperTestSuite {

    @Autowired
    private ReaderMapper readerMapper;

    @Test
    void mapToReader() {
        //Given
        ReaderDto readerDto = new ReaderDto("Name", "Surname", LocalDate.now(), "email3@gmail.com","password",false);

        //When
        Reader reader = readerMapper.mapToReader(readerDto);

        //Then
        assertEquals("Name", reader.getName());
        assertEquals("Surname", reader.getSurname());
        assertEquals("email3@gmail.com", reader.getEmail());
        assertEquals("password", reader.getPassword());
        assertFalse(reader.isAdmin());
    }

    @Test
    void mapToReaderDto() {
        //Given
        Reader reader = new Reader("Name", "Surname", LocalDate.now(), "email4@gmail.com","password",false);

        //When
        ReaderDto readerDto = readerMapper.mapToReaderDto(reader);

        //Then
        assertEquals("Name", readerDto.getName());
        assertEquals("Surname", readerDto.getSurname());
        assertEquals("email4@gmail.com", readerDto.getEmail());
        assertEquals("password", readerDto.getPassword());
        assertFalse(readerDto.isAdmin());
    }

    @Test
    void mapToReaderDtoList() {
        //Given
        List<Reader> readerList = new ArrayList<>();
        readerList.add(new Reader("Name", "Surname", LocalDate.now(), "email5@gmail.com","password",false));
        readerList.add(new Reader("Name", "Surname", LocalDate.now(), "email6@gmail.com","password",false));
        readerList.add(new Reader("Name", "Surname", LocalDate.now(), "email7@gmail.com","password",false));

        //When
        List<ReaderDto> readerDtoList = readerMapper.mapToReaderDtoList(readerList);

        //Then
        assertEquals(3, readerDtoList.size());
    }
}