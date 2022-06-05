package com.hoaxify.backend.user;

import com.hoaxify.backend.common.ApiSuccess;
import com.hoaxify.backend.common.AppConstants;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


@RestController
@RequestMapping(path = AppConstants.USERCONTROLLER_BASE_PATH, produces = "application/json")
public class UserController {
    private UserService userService;
    private static final Logger log = LoggerFactory.getLogger(UserController.class);

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity createUser(@Valid @RequestBody User user) {
        userService.createUser(user);
        return ResponseEntity.ok(new ApiSuccess(201, AppConstants.SUCCESSFULL_REGISTRATION_MESSAGE, AppConstants.USERCONTROLLER_BASE_PATH));
    }
}
