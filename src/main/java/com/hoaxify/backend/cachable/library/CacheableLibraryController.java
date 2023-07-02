package com.hoaxify.backend.cachable.library;

import com.hoaxify.backend.common.HoaxifyException;
import com.hoaxify.backend.library.model.Author;
import com.hoaxify.backend.library.model.Book;
import com.hoaxify.backend.library.model.dto.BookDto;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("cacheable-library")
@RequiredArgsConstructor
public class CacheableLibraryController {

  private final CacheableLibraryService cacheableLibraryService;

  @GetMapping("book")
  public ResponseEntity<List<BookDto>> getAllBooks() {
    final List<BookDto> books = cacheableLibraryService.getBooks()
      .stream()
      .map(BookDto::newInstance)
      .collect(Collectors.toList());
    return ResponseEntity.ok(books);
  }

  @GetMapping("book/{authorId}")
  public ResponseEntity<List<BookDto>> getBooksByAuthorId(@PathVariable("authorId") Long authorId) throws HoaxifyException {
    final List<BookDto> books = cacheableLibraryService.getBooksByAuthorId(authorId)
      .stream()
      .map(BookDto::newInstance)
      .collect(Collectors.toList());
    return ResponseEntity.ok(books);
  }

  @PostMapping("/book")
  public BookDto addBook(@RequestBody BookDto bookDto) throws HoaxifyException {
    final Book book = cacheableLibraryService.addBook(bookDto);
    return BookDto.newInstance(book);
  }

}
