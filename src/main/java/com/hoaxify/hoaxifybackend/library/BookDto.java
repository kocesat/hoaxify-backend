package com.hoaxify.hoaxifybackend.library;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookDto {
    private String title;
    private Long authorId;

    public static BookDto newInstance(Book book) {
        BookDto dto = new BookDto();
        dto.title = book.getTitle();
//        dto.authorId = book.getAuthor().getId();
        return dto;
    }
}
