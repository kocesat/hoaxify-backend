package com.hoaxify.backend.document.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.hoaxify.backend.common.PersistenceObject;
import com.hoaxify.backend.document.model.dto.DocumentDto;
import lombok.*;
import org.hibernate.annotations.FilterDef;
import org.hibernate.annotations.ParamDef;

import javax.persistence.Entity;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Getter
@Setter
@Table(name = "document", schema = "kgs")
@Builder
@RequiredArgsConstructor
@AllArgsConstructor
@FilterDef(name = "activeFilter",
        parameters = {
                @ParamDef(name = "isActive", type = "integer"),
        })
public class Document extends PersistenceObject {

    private String name;

    @Builder.Default
    private int active = 1;

    @ManyToOne
    @JoinColumn(name = "folder_id")
    @JsonIgnore
    private Folder folder;

    public static Document newInstance(DocumentDto dto, Folder folder) {
        Document.DocumentBuilder builder = Document.builder();
        return builder
                .name(dto.getName())
                .active(dto.getActive())
                .folder(folder).build();
    }
}
