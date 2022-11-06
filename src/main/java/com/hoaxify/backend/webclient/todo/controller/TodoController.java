package com.hoaxify.backend.webclient.todo.controller;

import com.hoaxify.backend.common.AppConstants;
import com.hoaxify.backend.common.BaseResponse;
import com.hoaxify.backend.webclient.exception.ApiCallException;
import com.hoaxify.backend.webclient.exception.TodoNotFoundEx;
import com.hoaxify.backend.webclient.todo.service.TodoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = AppConstants.TODO_CONTROLLER_BASE_PATH, produces = "application/json")
public class TodoController {
    @Autowired
    private TodoService todoService;

    @GetMapping
    public ResponseEntity<BaseResponse> getAll() throws ApiCallException, TodoNotFoundEx {
        return ResponseEntity.ok(
                BaseResponse.success(
                        todoService.getAll(), null
                )
        );
    }

    @GetMapping("/{id}")
    public ResponseEntity<BaseResponse> getById(@PathVariable Long id) throws ApiCallException, TodoNotFoundEx {
        return ResponseEntity.ok(
                BaseResponse.success(
                        todoService.getById(id), null
                )
        );
    }


}
