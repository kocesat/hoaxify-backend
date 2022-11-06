package com.hoaxify.backend.webclient.todo.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Todo {
    private Long id;
    private Integer userId;
    private String title;
    private boolean completed;
}
