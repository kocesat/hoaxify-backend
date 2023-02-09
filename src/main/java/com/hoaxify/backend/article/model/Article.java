package com.hoaxify.backend.article.model;

import com.hoaxify.backend.approval.enums.ObjectGroup;
import com.hoaxify.backend.common.Approvable;
import com.hoaxify.backend.common.PersistenceObject;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@RequiredArgsConstructor
@Entity
@Table(name = "articles", schema = "hoaxify")
public class Article extends PersistenceObject implements Approvable {
    private String title;

    private ArticleCategory category = ArticleCategory.SPORT;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Tag> tags = new ArrayList<>();

    @Override
    public ObjectGroup getObjectGroup() {
        return ObjectGroup.ARTICLE;
    }
}
