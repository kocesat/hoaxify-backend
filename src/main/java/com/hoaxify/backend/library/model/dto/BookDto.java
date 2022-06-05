package com.hoaxify.backend.library.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hoaxify.backend.library.model.Book;
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
        return dto;
    }
}
