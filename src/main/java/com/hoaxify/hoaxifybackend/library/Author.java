package com.hoaxify.hoaxifybackend.library;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@Entity
@Table(name = "author", schema = "hoaxify")
public class Author {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private int age;
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author")
    private Set<Book> books = new HashSet<>();

    public static Author newInstance(AuthorDto dto) {
        Author author = new Author();
        author.setName(dto.getName());
        author.setAge(dto.getAge());
        return author;
    }
}
