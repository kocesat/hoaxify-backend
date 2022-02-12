package com.hoaxify.hoaxifybackend.user;

import com.hoaxify.hoaxifybackend.common.GenericResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping(path = "/api/v1/users", produces = "application/json")
public class UserController {
    private UserService userService;
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity createUser(@Valid @RequestBody User user) {
        userService.createUser(user);
        return ResponseEntity.ok(new GenericResponse(true,  "Başarılı"));
    }
}
