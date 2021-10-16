package com.crud.library.repository;

import com.crud.library.domain.Reader;
import com.crud.library.domain.Rental;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Repository
@Transactional
public interface RentalRepository extends CrudRepository<Rental, Long> {

    List<Rental> findAllByReaderAndStatus(Reader reader, String status);

    @Override
    List<Rental> findAll();

    @Query(nativeQuery = true)
    List<Rental> retrieveDueRentalsOfReader(@Param("READER_ID") Long readerId);
}
