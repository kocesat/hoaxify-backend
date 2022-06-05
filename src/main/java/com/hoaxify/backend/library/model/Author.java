package com.hoaxify.backend.library.model;

import com.hoaxify.backend.library.model.dto.AuthorDto;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
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

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "author", orphanRemoval = true)
    private Set<Book> books = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<AuthorRole> roles = new ArrayList<>();

    public static Author newInstance(AuthorDto dto) {
        Author author = new Author();
        author.setName(dto.getName());
        author.setAge(dto.getAge());
        return author;
    }

}
