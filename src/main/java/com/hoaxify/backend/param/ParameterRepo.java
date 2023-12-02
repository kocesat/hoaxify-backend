package com.hoaxify.backend.param;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ParameterRepo extends JpaRepository<Parameter, Long> {

  List<Parameter> findBySectionAndName(String section, String name);

  List<Parameter> findBySection(String section);
}
