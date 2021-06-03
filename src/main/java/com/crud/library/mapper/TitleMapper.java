package com.crud.library.mapper;

import com.crud.library.domain.Title;
import com.crud.library.dto.TitleDto;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class TitleMapper {

    public Title mapToTitle(final TitleDto titleDto) {
            return new Title(
                    titleDto.getAuthor(),
                    titleDto.getTitle(),
                    titleDto.getPublicationYear()
            );
    }

    public TitleDto mapToTitleDto(final Title title) {
        return new TitleDto(
                title.getId(),
                title.getAuthor(),
                title.getTitle(),
                title.getPublicationYear()
        );
    }

    public List<TitleDto> mapToTitleDtoList(final List<Title> titleList) {
        return titleList.stream()
                .map(title -> new TitleDto(
                        title.getId(),
                        title.getAuthor(),
                        title.getTitle(),
                        title.getPublicationYear()
                ))
                .collect(Collectors.toList());
    }
}
