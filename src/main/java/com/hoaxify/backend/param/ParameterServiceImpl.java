package com.hoaxify.backend.param;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@Slf4j
@RequiredArgsConstructor
public class ParameterServiceImpl implements ParameterService{

  private final ParameterRepo parameterRepo;

  @Override
  public Optional<String> getParameterValue(String section, String name) {
    Map<String, ParameterItem> parameterMap = getParameters(section);
    if (parameterMap.containsKey(name)) {
      return Optional.ofNullable(parameterMap.get(name).getValue());
    }
    return Optional.empty();
  }

  @Override
  public Map<String, ParameterItem> getParameters(String section) {
    if (section == null || section.isEmpty()) {
      return Collections.emptyMap();
    }
    Map<String, ParameterItem> parameterMap = new HashMap<>();
    List<ParameterItem> params = parameterRepo.findBySection(section);
    for (ParameterItem param : params) {
      parameterMap.put(param.getName(), param);
    }
    return parameterMap;
  }
}
