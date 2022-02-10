package com.hoaxify.hoaxifybackend.user;

import com.hoaxify.hoaxifybackend.common.GenericResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(path = "/api/v1/users", produces = "application/json")
public class UserController {
    private UserService userService;
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    public GenericResponse createUser(@RequestBody User user) {
        userService.createUser(user);
        GenericResponse response = new GenericResponse(true,  "Başarılı");
        return response;
    }
}
