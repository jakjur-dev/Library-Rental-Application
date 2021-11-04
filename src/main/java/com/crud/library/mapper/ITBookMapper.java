package com.crud.library.mapper;

import com.crud.library.domain.Book;
import com.crud.library.domain.ITBook;
import com.crud.library.dto.BookDto;
import com.crud.library.dto.ITBookDto;
import com.crud.library.exceptions.ReaderNotFoundException;
import com.crud.library.repository.ReaderRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class ITBookMapper {

    private final ReaderRepository readerRepository;

    @Autowired
    public ITBookMapper(ReaderRepository readerRepository) {
        this.readerRepository = readerRepository;
    }

    public ITBook mapToITBook(final ITBookDto itBookDto, Long readerId) throws ReaderNotFoundException {
        return new ITBook(
                itBookDto.getTitle(),
                itBookDto.getSubtitle(),
                itBookDto.isEbook(),
                itBookDto.getIsbn13(),
                itBookDto.getImage(),
                itBookDto.getUrl(),
                readerRepository.findById(readerId).orElseThrow(ReaderNotFoundException::new)
        );
    }

    public ITBookDto mapToITBookDto(final ITBook itBook) {
        return new ITBookDto(
                itBook.getId(),
                itBook.getTitle(),
                itBook.getSubtitle(),
                itBook.isEbook(),
                itBook.getIsbn13(),
                itBook.getImage(),
                itBook.getUrl()
        );
    }

    public List<ITBookDto> mapToITBookDtoList(final List<ITBook> iTBookList) {
        return iTBookList.stream()
                .map(this::mapToITBookDto)
                .collect(Collectors.toList());
    }
}
