package com.hoaxify.hoaxifybackend.library.repo;

import com.hoaxify.hoaxifybackend.library.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Query("select distinct a from Author a left join fetch a.books")
    List<Author> findAllWithBooks();

    @Override
    @Query("select a from Author a left join fetch a.books where a.id = :aLong")
    Optional<Author> findById(Long aLong);
}
