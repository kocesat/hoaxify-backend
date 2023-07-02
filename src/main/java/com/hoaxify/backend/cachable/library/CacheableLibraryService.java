package com.hoaxify.backend.cachable.library;

import com.hoaxify.backend.common.HoaxifyException;
import com.hoaxify.backend.library.model.Author;
import com.hoaxify.backend.library.model.Book;
import com.hoaxify.backend.library.model.dto.BookDto;
import com.hoaxify.backend.library.repo.AuthorRepository;
import com.hoaxify.backend.library.repo.BookRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Collections;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class CacheableLibraryService {

  private final BookRepository bookRepository;
  private final AuthorRepository authorRepository;
  private final CacheManager cacheManager;

  public List<Book> getBooks() {
    return bookRepository.findAll();
  }

  @Cacheable(cacheNames = "booksByAuthorId", key = "{#authorId}")
  public List<Book> getBooksByAuthorId(Long authorId) throws HoaxifyException {
    if (authorId == null) {
      return Collections.emptyList();
    }

    final Author author = authorRepository.findById(authorId)
      .orElseThrow(() -> new HoaxifyException("Author not found"));

    return bookRepository.findByAuthor(author);
  }

  @CacheEvict(cacheNames = {"booksByAuthorId"}, key = "{#bookDto.authorId}")
  public Book addBook(BookDto bookDto) throws HoaxifyException {
    final Author author = authorRepository.findById(bookDto.getAuthorId())
      .orElseThrow(() -> new HoaxifyException("Author not found"));
    Book book = new Book();
    book.setTitle(bookDto.getTitle());
    book.setAuthor(author);
    return bookRepository.save(book);
  }


  @Scheduled(fixedDelay = 10*60*1000)
  public void clearCache() {
    Collection<String> cacheNames = cacheManager.getCacheNames();
    cacheNames.forEach(name -> {
      Cache cache = cacheManager.getCache(name);
      if (cache != null) {
        cache.clear();
      }
    });

  }
}
