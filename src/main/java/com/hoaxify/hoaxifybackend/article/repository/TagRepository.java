package com.hoaxify.hoaxifybackend.article.repository;

import com.hoaxify.hoaxifybackend.article.model.Tag;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagRepository extends JpaRepository<Tag, Integer> {
}
