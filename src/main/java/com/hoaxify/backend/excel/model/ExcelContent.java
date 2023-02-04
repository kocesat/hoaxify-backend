package com.hoaxify.backend.excel.model;

import com.hoaxify.backend.common.PersistenceObject;
import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Data
@Entity
@Table(schema = "hoaxify", name = "excel_contents")
public class ExcelContent extends PersistenceObject {
    @Column(name = "title")
    private String title;

    @Column(name = "description")
    private String description;

    @Column(name = "published")
    private boolean published;
}
