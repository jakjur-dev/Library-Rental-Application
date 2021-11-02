package com.crud.library.mapper;

import com.crud.library.domain.Reader;
import com.crud.library.dto.ReaderDto;
import com.crud.library.dto.RentalDto;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class ReaderMapper {

    public Reader mapToReader(final ReaderDto readerDto) {
        return new Reader(
                readerDto.getName(),
                readerDto.getSurname(),
                LocalDate.now(),
                readerDto.getEmail(),
                readerDto.getPassword(),
                false
        );
    }

    public ReaderDto mapToReaderDto(final Reader reader) {
        return new ReaderDto(
                reader.getId(),
                reader.getName(),
                reader.getSurname(),
                reader.getAccountCreationDate(),
                reader.getEmail(),
                reader.getPassword(),
                reader.isAdmin()
        );
    }

    public List<ReaderDto> mapToReaderDtoList(final List<Reader> readerList) {
        return readerList.stream()
                .map(reader -> new ReaderDto(
                        reader.getId(),
                        reader.getName(),
                        reader.getSurname(),
                        reader.getAccountCreationDate(),
                        reader.getEmail(),
                        reader.getPassword(),
                        reader.isAdmin()
                ))
                .collect(Collectors.toList());
    }
}
