package com.crud.library.mapper;

import com.crud.library.domain.Book;
import com.crud.library.domain.Title;
import com.crud.library.dto.BookDto;
import com.crud.library.exceptions.TitleNotFoundException;
import com.crud.library.repository.TitleRepository;
import com.crud.library.service.TitleService;
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
class BookMapperTestSuite {

    @Autowired
    private BookMapper bookMapper;

    @Autowired
    private TitleRepository titleRepository;

    @Test
    void mapToBook() throws TitleNotFoundException {
        //Given
        Title title = new Title("Author", "Title8", 1997);
        Long titleId = titleRepository.save(title).getId();
        BookDto bookDto = new BookDto(title.getTitle(), "available", "image", LocalDate.now());

        //When
        Book book = bookMapper.mapToBook(bookDto);

        //Then
        assertEquals(titleId, book.getTitle().getId());
        assertEquals("available", book.getStatus());
        assertEquals("image", book.getImage());
        assertEquals(LocalDate.now(), book.getReleaseDate());
    }

    @Test
    void mapToBookDtoList() {
        //Given
        Title title = new Title("Author", "Title9", 1997);
        List<Book> bookList = new ArrayList<>();
        bookList.add(new Book(title, "available", "image", LocalDate.now()));
        bookList.add(new Book(title, "available", "image", LocalDate.now()));
        bookList.add(new Book(title, "available", "image", LocalDate.now()));

        //When
        List<BookDto> bookDtoList = bookMapper.mapToBookDtoList(bookList);

        //Then
        assertEquals(3, bookDtoList.size());
        assertEquals(title.getTitle(), bookDtoList.get(1).getTitle());
        assertEquals("available", bookDtoList.get(1).getStatus());
        assertEquals("image", bookDtoList.get(1).getImage());
        assertEquals(LocalDate.now(), bookDtoList.get(1).getReleaseDate());
    }
}