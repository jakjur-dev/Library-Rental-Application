package com.crud.library.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.List;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ITBookSearchDto {

    @JsonProperty("error")
    private String error;

    @JsonProperty("total")
    private String total;

    @JsonProperty("page")
    private String page;

    @JsonProperty("books")
    private List<ITBookDto> books;
}
