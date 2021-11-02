package com.crud.library.repository;

import com.crud.library.domain.Book;
import com.crud.library.domain.Reader;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface ReaderRepository extends CrudRepository<Reader, Long> {

    Reader findByNameAndSurname(String name, String surname);

    Optional<Reader> findReaderByEmailAndPassword(String email, String password);

    @Override
    List<Reader> findAll();
}
