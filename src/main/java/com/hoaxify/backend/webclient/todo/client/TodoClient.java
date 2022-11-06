package com.hoaxify.backend.webclient.todo.client;

import com.hoaxify.backend.webclient.exception.ApiCallException;
import com.hoaxify.backend.webclient.exception.TodoNotFoundEx;
import com.hoaxify.backend.webclient.todo.model.Todo;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;

import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@RequiredArgsConstructor
@Log4j2
public class TodoClient {

    private final WebClient todoWebClient;

    public List<Todo> findAll() throws ApiCallException, TodoNotFoundEx {
        try {
            return todoWebClient.get()
                    .retrieve()
                    .bodyToFlux(Todo.class)
                    .collectList()
                    .block();
        } catch (WebClientResponseException e) {
            handleWebClientResponseException(e);
        } catch (Exception e) {
            handleGenericException(e);
        }
        return Collections.emptyList();
    }

    public Optional<Todo> findById(Long id) throws ApiCallException, TodoNotFoundEx {
        Todo todo = null;
        try {
            todo = todoWebClient.get()
                    .uri(uriBuilder -> uriBuilder.path("{id}").build(id))
                    .retrieve()
                    .bodyToMono(Todo.class)
                    .block();
        } catch (WebClientResponseException e) {
            handleWebClientResponseException(e);
        } catch (Exception e) {
            handleGenericException(e);
        }
        return Optional.of(todo);
    }

    private void handleWebClientResponseException(WebClientResponseException e) throws ApiCallException, TodoNotFoundEx {
        log.error("Error Response Code is {} and Response Body is {}", e.getRawStatusCode(), e.getResponseBodyAsString());
        if (e.getRawStatusCode() == 404 | e.getResponseBodyAsString().equals("")) {
            throw new TodoNotFoundEx();
        }
        throw new ApiCallException();
    }

    private void handleGenericException(Exception e) throws ApiCallException {
        log.error("Exception", e);
        throw new ApiCallException(e.getMessage(), e);
    }
}
