package com.hoaxify.backend.continueflag;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("students")
@RequiredArgsConstructor
public class StudentController {
  private final StudentService service;

  @PostMapping("/list")
  public StudentResponse list(@RequestBody @Validated StudentRequest request) {
    return service.getStudents(request);
  }

  @PostMapping
  public ResponseEntity<Student> create(@RequestBody @Validated Student student){
    final Student studentInDb = service.create(student);
    return ResponseEntity.ok(studentInDb);
  }
}
