package com.hoaxify.backend.webclient.todo.service.impl;

import com.hoaxify.backend.webclient.exception.ApiCallException;
import com.hoaxify.backend.webclient.exception.TodoNotFoundEx;
import com.hoaxify.backend.webclient.todo.client.TodoClient;
import com.hoaxify.backend.webclient.todo.model.Todo;
import com.hoaxify.backend.webclient.todo.service.TodoService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class TodoServiceImpl implements TodoService {

    private final TodoClient todoClient;

    @Override
    public Todo getById(Long id) throws ApiCallException, TodoNotFoundEx {
        return todoClient.findById(id).orElseThrow(TodoNotFoundEx::new);
    }

    @Override
    public List<Todo> getAll() throws ApiCallException, TodoNotFoundEx {
        return todoClient.findAll();
    }
}
