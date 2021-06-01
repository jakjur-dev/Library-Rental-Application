package com.crud.library.repository;

import com.crud.library.domain.Reader;
import com.crud.library.domain.Rental;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface RentalRepository extends CrudRepository<Rental, Long> {

    List<Rental> findAllByReaderAndReturnDate(Reader reader, LocalDate returnDate);

    @Override
    List<Rental> findAll();
}
