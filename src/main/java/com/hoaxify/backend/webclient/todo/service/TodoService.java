package com.hoaxify.backend.webclient.todo.service;

import com.hoaxify.backend.webclient.exception.ApiCallException;
import com.hoaxify.backend.webclient.exception.TodoNotFoundEx;
import com.hoaxify.backend.webclient.todo.model.Todo;

import java.util.List;

public interface TodoService {

    Todo getById(Long id) throws ApiCallException, TodoNotFoundEx;

    List<Todo> getAll() throws ApiCallException, TodoNotFoundEx;
}
