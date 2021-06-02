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

    public List<Title> getAllTitles() {
        return repository.findAll();
    }
}
