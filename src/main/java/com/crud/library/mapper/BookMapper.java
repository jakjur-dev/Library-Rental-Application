package com.crud.library.mapper;

import com.crud.library.domain.Book;
import com.crud.library.dto.BookDto;
import com.crud.library.exceptions.TitleNotFoundException;
import com.crud.library.repository.TitleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookMapper {

    private final TitleRepository titleRepository;

    @Autowired
    public BookMapper(TitleRepository titleRepository) {
        this.titleRepository = titleRepository;
    }

    public Book mapToBook(final BookDto bookDto) throws TitleNotFoundException {
        return new Book(
                bookDto.getId(),
                titleRepository.findByTitle(bookDto.getTitle()).orElseThrow(TitleNotFoundException::new),
                bookDto.getStatus()
        );
    }

    public BookDto mapToBookDto(final Book book) {
        return new BookDto(
                book.getId(),
                book.getTitle().getTitle(),
                book.getStatus()
        );
    }

    public List<BookDto> mapToBookDtoList(final List<Book> bookList) {
        return bookList.stream()
                .map(book -> new BookDto(
                        book.getId(),
                        book.getTitle().getTitle(),
                        book.getStatus()
                ))
                .collect(Collectors.toList());
    }
}
