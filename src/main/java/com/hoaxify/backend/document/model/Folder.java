package com.hoaxify.backend.document.model;

import com.hoaxify.backend.common.PersistenceObject;
import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Filter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@Entity
@Table(name = "folder", schema = "kgs")
public class Folder extends PersistenceObject {
    private String name;

    @ManyToOne(optional = true)
    @JoinColumn(name = "parent_id")
    private Folder parentFolder;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, mappedBy = "folder")
    @Filter(name = "activeFilter", condition = "active = :isActive")
    private List<Document> documents = new ArrayList<>();
}
