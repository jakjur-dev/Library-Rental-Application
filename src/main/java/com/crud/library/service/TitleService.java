package com.crud.library.service;

import com.crud.library.domain.Book;
import com.crud.library.domain.Title;
import com.crud.library.repository.TitleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class TitleService {

    private final TitleRepository repository;

    public Title saveTitle(final Title title){
        return repository.save(title);
    }

    public List<Book> getAvailableBooksList(Long titleId){
        List<Book> books = repository.findById(titleId).get().getBookList();

        return books.stream()
                .filter(book -> book.getStatus().equals("available"))
                .collect(Collectors.toList());
    }

    public List<Title> getAllTitles() {
        return repository.findAll();
    }
}
