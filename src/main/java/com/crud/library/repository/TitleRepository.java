package com.crud.library.repository;

import com.crud.library.domain.Book;
import com.crud.library.domain.Title;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface TitleRepository extends CrudRepository<Title, Long> {

    Title findByTitle(String title);

    @Override
    List<Title> findAll();
}
