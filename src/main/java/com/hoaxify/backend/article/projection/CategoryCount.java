package com.hoaxify.backend.article.projection;

import com.hoaxify.backend.article.model.ArticleCategory;
import com.hoaxify.backend.article.utils.ArticleCategoryConverter;

import javax.persistence.Convert;

public interface CategoryCount {
    @Convert(converter = ArticleCategoryConverter.class)
    ArticleCategory getCategory();
    int getCount();
}
