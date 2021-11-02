package com.crud.library.repository.logs;

import com.crud.library.domain.logs.RentalLog;
import org.springframework.data.repository.CrudRepository;

public interface RentalLogRepository extends CrudRepository<RentalLog, Long> {
}
