package com.hoaxify.backend.library.model.dto;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.hoaxify.backend.library.model.Book;
import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@JsonInclude(JsonInclude.Include.NON_NULL)
public class BookDto {
    private String title;
    private Long authorId;
    private String authorName;

    public static BookDto newInstance(Book book) {
        return BookDto.builder()
          .authorName(book.getAuthor().getName())
          .title(book.getTitle())
          .build();
    }
}
