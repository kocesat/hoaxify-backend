package com.hoaxify.backend.article.repository;

import com.hoaxify.backend.article.model.Article;
import com.hoaxify.backend.article.projection.CategoryCount;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ArticleRepository extends JpaRepository<Article, Integer> {

    @Query(value =
            "select count(1) as count, category " +
            "from hoaxify.articles " +
            "group by category", nativeQuery = true)
    List<CategoryCount> findCategoryCounts();
}
