package com.hoaxify.backend.continueflag;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentRepository extends JpaRepository<Student, Long> {

  @Query(value = "select * from hoaxify.students " +
    "where id > :lastId " +
    "limit :querySize",
    nativeQuery = true)
  List<Student> findByLastId(Long lastId, int querySize);

  @Query(value = "select count(1) from hoaxify.students " +
    "where id > :lastId ",
    nativeQuery = true)
  int countByLastId(Long lastId);
}
