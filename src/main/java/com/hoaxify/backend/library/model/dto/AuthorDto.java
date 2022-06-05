package com.hoaxify.backend.library.model.dto;

import com.hoaxify.backend.library.enums.Role;
import com.hoaxify.backend.library.model.Author;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
public class AuthorDto {
    private long id;
    private String name;
    private int age;
    private List<BookDto> books = new ArrayList<>();
    private List<String> roles = new ArrayList<>();

    public static AuthorDto newInstance(Author author) {
        AuthorDto dto = new AuthorDto();
        dto.id = author.getId();
        dto.name = author.getName();
        dto.age = author.getAge();
        dto.roles = author.getRoles().stream().map(role -> Role.getFromCode(role.getRole().getCode()).getValue()).collect(Collectors.toList());
        dto.books = author.getBooks().stream().map(BookDto::newInstance).collect(Collectors.toList());
        return dto;
    }
}
