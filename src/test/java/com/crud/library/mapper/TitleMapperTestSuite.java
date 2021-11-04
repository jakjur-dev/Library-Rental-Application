package com.crud.library.mapper;

import com.crud.library.domain.Title;
import com.crud.library.dto.TitleDto;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.objenesis.instantiator.gcj.GCJInstantiator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest
class TitleMapperTestSuite {

    @Autowired
    private TitleMapper titleMapper;

    @Test
    void mapToTitle() {
        //Given
        TitleDto titleDto = new TitleDto("Author", "Title4", 1997);

        //When
        Title title = titleMapper.mapToTitle(titleDto);

        //Then
        assertEquals("Author", title.getAuthor());
        assertEquals("Title4", title.getTitle());
        assertEquals(1997, title.getPublicationYear());
    }

    @Test
    void mapToTitleDtoList() {
        //Given
        List<Title> titleList = new ArrayList<>();
        titleList.add(new Title("Author", "Title5", 1997));
        titleList.add(new Title("Author", "Title6", 1997));
        titleList.add(new Title("Author", "Title7", 1997));

        //When
        List<TitleDto> titleDtoList = titleMapper.mapToTitleDtoList(titleList);

        //Then
        assertEquals(3, titleDtoList.size());
        assertEquals("Author", titleDtoList.get(1).getAuthor());
        assertEquals(1997, titleDtoList.get(1).getPublicationYear());
        assertEquals("Title6", titleDtoList.get(1).getTitle());
    }
}