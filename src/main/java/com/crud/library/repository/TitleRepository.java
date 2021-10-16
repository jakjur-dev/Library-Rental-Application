package com.crud.library.repository;

import com.crud.library.domain.Book;
import com.crud.library.domain.Title;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface TitleRepository extends CrudRepository<Title, Long> {

    Optional<Title> findByTitle(String title);

    @Query(nativeQuery = true)
    List<Title> retrieveTitleByString(@Param("KEYWORD") String keyword);

    @Override
    List<Title> findAll();
}
