package com.hoaxify.backend.param;

import java.util.Map;
import java.util.Optional;

public interface ParameterService {

  Optional<String> getParameterValue(String section, String name);

  Map<String, ParameterItem> getParameters(String section);
}
