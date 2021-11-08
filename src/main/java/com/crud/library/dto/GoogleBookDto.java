package com.crud.library.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class GoogleBookDto {

    private Long id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("subtitle")
    private String subtitle;

    @JsonProperty("industryIdentifiers")
    private List<ISBNDto> industryIdentifiers;

    @JsonProperty("imageLinks")
    private ImageLinksDto imageLinks;

    @JsonProperty("infoLink")
    private String infoLink;

    public void setId(Long id) {
        this.id = id;
    }
}
