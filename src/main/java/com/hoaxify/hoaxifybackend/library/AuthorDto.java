package com.hoaxify.hoaxifybackend.library;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class AuthorDto {
    private String name;
    private int age;
    private List<BookDto> books;

    public static AuthorDto newInstance(Author author) {
        AuthorDto dto = new AuthorDto();
        dto.name = author.getName();
        dto.age = author.getAge();
        dto.books = author.getBooks().stream().map(BookDto::newInstance).collect(Collectors.toList());
        return dto;
    }
}
