package com.hoaxify.backend.library.repo;

import com.hoaxify.backend.library.model.Author;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AuthorRepository extends JpaRepository<Author, Long> {

    @Override
    @Query("select distinct a from Author a left join fetch a.books left join fetch a.roles ")
    List<Author> findAll();

    @Override
    @Query("select a from Author a left join fetch a.books left join fetch a.roles where a.id = :aLong")
    Optional<Author> findById(Long aLong);
}
