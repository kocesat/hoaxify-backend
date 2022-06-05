package com.hoaxify.backend.library.controller;

import com.hoaxify.backend.library.model.Author;
import com.hoaxify.backend.library.model.AuthorRole;
import com.hoaxify.backend.library.model.Book;
import com.hoaxify.backend.library.model.dto.AuthorDto;
import com.hoaxify.backend.library.model.dto.BookDto;
import com.hoaxify.backend.library.repo.AuthorRepository;
import com.hoaxify.backend.library.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/library")
public class LibraryController {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;

    @PostMapping("/author")
    public Author addBook(@RequestBody AuthorDto authorDto) {
        return authorRepository.save(Author.newInstance(authorDto));
    }

    @GetMapping("/author")
    public List<AuthorDto> getAuthors() {
        return authorRepository.findAll().stream().map(AuthorDto::newInstance).collect(Collectors.toList());
    }

    @PostMapping("/author/role")
    public AuthorDto addRole(@RequestParam Long authorId, @RequestParam List<Integer> roles) {
        Author authorInDb = authorRepository.findById(authorId)
                .orElseThrow(IllegalArgumentException::new);
        List<AuthorRole> rolesToAdd = roles.stream()
                .map(c -> AuthorRole.newInstance(authorInDb.getId(), c))
                .collect(Collectors.toList());
        authorInDb.getRoles().addAll(rolesToAdd);
        return AuthorDto.newInstance(authorRepository.save(authorInDb));
    }

    @PutMapping("/author/role")
    public AuthorDto updateRoles(@RequestParam Long authorId, @RequestParam List<Integer> newRoleCodes) {
        Author authorInDb = authorRepository.findById(authorId).orElseThrow(IllegalArgumentException::new);
        Set<AuthorRole> newRoles = newRoleCodes.stream()
                .map(c -> AuthorRole.newInstance(authorId, c))
                .collect(Collectors.toSet());
        authorInDb.getRoles().clear();
        authorInDb.getRoles().addAll(newRoles);
        return AuthorDto.newInstance(authorRepository.save(authorInDb));
    }

    @GetMapping("/book")
    public List<BookDto> getBooks() {
        return bookRepository.findAll().stream().map(BookDto::newInstance).collect(Collectors.toList());
    }

    @PostMapping("/book")
    public BookDto addBook(@RequestBody BookDto bookDto) {
        Author author = authorRepository.findById(bookDto.getAuthorId()).orElseThrow(IllegalArgumentException::new);
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setAuthor(author);
        return BookDto.newInstance(bookRepository.save(book));
    }
}
