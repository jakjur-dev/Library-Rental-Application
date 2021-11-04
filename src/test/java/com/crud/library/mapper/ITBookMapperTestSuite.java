package com.crud.library.mapper;

import com.crud.library.domain.ITBook;
import com.crud.library.domain.Reader;
import com.crud.library.dto.ITBookDto;
import com.crud.library.exceptions.ReaderNotFoundException;
import com.crud.library.repository.ReaderRepository;
import com.crud.library.service.ReaderService;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
class ITBookMapperTestSuite {

    @Autowired
    private ITBookMapper itBookMapper;

    @Autowired
    private ReaderRepository readerRepository;

    @Test
    void mapToITBook() throws ReaderNotFoundException {
        //Given
        Reader reader = new Reader("Name", "Surname", LocalDate.now(), "email8@gmail.com","password",false);
        Long readerId = readerRepository.save(reader).getId();
        ITBookDto itBookDto = new ITBookDto("Title", "Subtitle", false, "isbn", "image", "url");

        //When
        ITBook itBook = itBookMapper.mapToITBook(itBookDto, readerId);

        //Then
        assertEquals("Title", itBook.getTitle());
        assertEquals("Subtitle", itBook.getSubtitle());
        assertFalse(itBook.isEbook());
        assertEquals("isbn", itBook.getIsbn13());
        assertEquals("image", itBook.getImage());
        assertEquals("url", itBook.getUrl());
    }

    @Test
    void mapToITBookDtoList() {
        //Given
        List<ITBook> itBookList = new ArrayList<>();
        itBookList.add(new ITBook("Title", "Subtitle", false, "isbn", "image", "url", new Reader()));
        itBookList.add(new ITBook("Title", "Subtitle", false, "isbn", "image", "url", new Reader()));
        itBookList.add(new ITBook("Title", "Subtitle", false, "isbn", "image", "url", new Reader()));

        //When
        List<ITBookDto> itBookDtoList = itBookMapper.mapToITBookDtoList(itBookList);

        //Then
        assertEquals(3, itBookDtoList.size());
        assertEquals("Title", itBookDtoList.get(1).getTitle());
        assertEquals("Subtitle", itBookDtoList.get(1).getSubtitle());
        assertFalse(itBookDtoList.get(1).isEbook());
        assertEquals("isbn", itBookDtoList.get(1).getIsbn13());
        assertEquals("image", itBookDtoList.get(1).getImage());
        assertEquals("url", itBookDtoList.get(1).getUrl());
    }
}