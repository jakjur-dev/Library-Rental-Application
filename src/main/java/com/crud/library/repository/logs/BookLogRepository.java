package com.crud.library.repository.logs;

import com.crud.library.domain.logs.BookLog;
import org.springframework.data.repository.CrudRepository;

public interface BookLogRepository extends CrudRepository<BookLog, Long> {
}
