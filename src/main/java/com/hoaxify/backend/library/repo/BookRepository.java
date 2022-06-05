package com.hoaxify.backend.library.repo;

import com.hoaxify.backend.library.model.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookRepository extends JpaRepository<Book, Long> {

    @Override
    @Query("from Book b join fetch b.author")
    List<Book> findAll();
}
