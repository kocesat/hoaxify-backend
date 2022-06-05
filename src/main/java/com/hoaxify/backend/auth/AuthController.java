package com.hoaxify.backend.auth;

import com.hoaxify.backend.common.ApiSuccess;
import com.hoaxify.backend.common.AppConstants;
import com.hoaxify.backend.common.CurrentUser;
import com.hoaxify.backend.user.User;
import com.hoaxify.backend.user.UserRepository;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import static org.slf4j.LoggerFactory.getLogger;

@RestController
@RequestMapping(value = AppConstants.AUTHCONTROLLER_BASE_PATH)
public class AuthController {

    private static final Logger log = getLogger(AuthController.class);

    @Autowired
    private UserRepository userRepository;

    @PostMapping
    public ResponseEntity handleAuth(@CurrentUser User user){
        ApiSuccess body = new ApiSuccess(200, AppConstants.SUCCESSFULL_LOGIN_MESSAGE, AppConstants.AUTHCONTROLLER_BASE_PATH);
        body.setData(user);
        return ResponseEntity.ok().body(body);
    }
}
