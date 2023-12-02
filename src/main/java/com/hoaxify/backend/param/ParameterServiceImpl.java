package com.hoaxify.backend.param;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
@Slf4j
@RequiredArgsConstructor
public class ParameterServiceImpl implements ParameterService{

  private final ParameterRepo parameterRepo;

  @Override
  public Optional<String> getBySectionAndName(String section, String name) {
    List<Parameter> params = parameterRepo.findBySectionAndName(section, name);
    if (params.isEmpty()) {
      return Optional.empty();
    }
    return Optional.of(params.get(0).getValue());
  }

  @Override
  public Map<String, String> getBySection(String section) {
    List<Parameter> params = parameterRepo.findBySection(section);
    Map<String, String> paramMap = new HashMap<>();
    for (Parameter param : params) {
      paramMap.put(param.getName(), param.getValue());
    }
    return paramMap;
  }
}
