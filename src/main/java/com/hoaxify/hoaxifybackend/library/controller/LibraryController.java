package com.hoaxify.hoaxifybackend.library.controller;

import com.hoaxify.hoaxifybackend.library.Author;
import com.hoaxify.hoaxifybackend.library.AuthorDto;
import com.hoaxify.hoaxifybackend.library.Book;
import com.hoaxify.hoaxifybackend.library.BookDto;
import com.hoaxify.hoaxifybackend.library.repo.AuthorRepository;
import com.hoaxify.hoaxifybackend.library.repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/library")
public class LibraryController {

    @Autowired
    private AuthorRepository authorRepository;
    @Autowired
    private BookRepository bookRepository;

    @PostMapping("/author")
    public Author addAuthor(@RequestBody AuthorDto authorDto) {
        return authorRepository.save(Author.newInstance(authorDto));
    }

    @GetMapping("/author")
    public List<AuthorDto> getAuthors() {
        return authorRepository.findAllWithBooks().stream().map(AuthorDto::newInstance).collect(Collectors.toList());
    }

    @GetMapping("/book")
    public List<BookDto> getBooks() {
        return bookRepository.findAll().stream().map(BookDto::newInstance).collect(Collectors.toList());
    }

    @PostMapping("/book")
    public BookDto addAuthor(@RequestBody BookDto bookDto) {
        Author author = authorRepository.findById(bookDto.getAuthorId()).orElseThrow(IllegalArgumentException::new);
        Book book = new Book();
        book.setTitle(bookDto.getTitle());
        book.setAuthor(author);
        return BookDto.newInstance(bookRepository.save(book));
    }
}
