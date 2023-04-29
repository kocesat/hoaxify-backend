package com.hoaxify.backend.continueflag;

import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Transactional(readOnly = true)
@RequiredArgsConstructor
@Service
public class StudentService {
  public static final int QUERY_SIZE = 10;

  private final StudentRepository repository;

  public StudentResponse getStudents(StudentRequest request) {
    final var studentList = repository.findByLastId(request.getLastId(), QUERY_SIZE);
    final var totalCount = repository.countByLastId(request.getLastId());
    final boolean isContinue = totalCount > studentList.size();
    StudentResponse response = StudentResponse.builder()
      .hasMore(isContinue)
      .students(studentList)
      .build();
    return response;
  }

  @Transactional(isolation = Isolation.READ_COMMITTED, propagation = Propagation.REQUIRED, rollbackFor = Throwable.class)
  public Student create(Student student) {
    student.setCreated(LocalDateTime.now());
    return repository.save(student);
  }
}
