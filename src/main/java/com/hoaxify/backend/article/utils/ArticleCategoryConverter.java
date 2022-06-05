package com.hoaxify.backend.article.utils;

import com.hoaxify.backend.article.model.ArticleCategory;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ArticleCategoryConverter implements AttributeConverter<ArticleCategory, Integer> {

    @Override
    public Integer convertToDatabaseColumn(ArticleCategory category) {
        if (category == null) {
            return null;
        }
        return category.getCode();
    }

    @Override
    public ArticleCategory convertToEntityAttribute(Integer code) {
        return ArticleCategory.getFromCode(code);
    }
}
