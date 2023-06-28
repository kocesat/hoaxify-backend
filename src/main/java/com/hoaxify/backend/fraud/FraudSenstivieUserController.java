package com.hoaxify.backend.fraud;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;

@RestController
@RequiredArgsConstructor
@RequestMapping("fraud/user")
public class FraudSenstivieUserController {
  private final FraudSensitiveUserService fraudSensitiveUserService;
  @PostMapping
  public ResponseEntity<UserUpdateDto> updateUser(@RequestBody @Valid UserUpdateDto dto) {
    return ResponseEntity.ok(fraudSensitiveUserService.updateUser(dto));
  }
}
