package com.crud.library.dto;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.rsocket.context.LocalRSocketServerPort;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties(ignoreUnknown = true)
public class ITBookDto {

    private Long id;

    @JsonProperty("title")
    private String title;

    @JsonProperty("subtitle")
    private String subtitle;

    private boolean ebook;

    @JsonProperty("isbn13")
    private String isbn13;

    @JsonProperty("image")
    private String image;

    @JsonProperty("url")
    private String url;

    public ITBookDto(String title, String subtitle, boolean ebook, String isbn13, String image, String url) {
        this.title = title;
        this.subtitle = subtitle;
        this.ebook = ebook;
        this.isbn13 = isbn13;
        this.image = image;
        this.url = url;
    }
}
