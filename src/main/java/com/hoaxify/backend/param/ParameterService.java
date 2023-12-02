package com.hoaxify.backend.param;

import java.util.Map;
import java.util.Optional;

public interface ParameterService {

  Optional<String> getBySectionAndName(String section, String name);

  Map<String, String> getBySection(String section);
}
