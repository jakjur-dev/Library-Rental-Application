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
public interface BookRepository extends CrudRepository<Book, Long> {

    List<Book> findAllByTitle(Title title);
    List<Book> findAllByTitleIdAndStatus(Long titleId, String status);

    @Override
    List<Book> findAll();
}
