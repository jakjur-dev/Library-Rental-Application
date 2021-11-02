package com.crud.library.service;

import com.crud.library.domain.Title;
import com.crud.library.domain.logs.TitleLog;
import com.crud.library.exceptions.TitleNotFoundException;
import com.crud.library.exceptions.TitleNotUniqueException;
import com.crud.library.repository.TitleRepository;
import com.crud.library.repository.logs.TitleLogRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
@RequiredArgsConstructor
public class TitleService {

    private final TitleRepository repository;
    private final TitleLogRepository titleLogRepository;

    public void saveTitle(final Title title)  {
        try {
            repository.save(title);
            titleLogRepository.save(new TitleLog(title.getAuthor(), title.getTitle(), title.getPublicationYear(), LocalDate.now()));
        } catch(Exception e){
            System.out.println("possible duplicated values");
        }


    }

    public List<Title> getAllTitles() {
        return repository.findAll();
    }

}
