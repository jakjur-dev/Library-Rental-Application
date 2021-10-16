package com.crud.library.repository;

import com.crud.library.domain.Book;
import com.crud.library.domain.ITBook;
import com.crud.library.domain.Reader;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.List;

@Repository
@Transactional
public interface ITBookRepository extends CrudRepository<ITBook, Long> {

    List<ITBook> findByReaderIdAndEbook(Long readerId, boolean ebook);
}
