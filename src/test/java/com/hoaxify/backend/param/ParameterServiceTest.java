package com.hoaxify.backend.param;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@ExtendWith(MockitoExtension.class)
class ParameterServiceTest {

  @Mock
  private ParameterRepo parameterRepo;

  @InjectMocks
  private ParameterServiceImpl parameterService;

  @Test
  void getParameters() {
    List<ParameterItem> parameters = List.of(
      new ParameterItem("App", "FEATURE_FLAG_1", "true"),
      new ParameterItem("App", "FEATURE_FLAG_2", "true"),
      new ParameterItem("App", "FEATURE_FLAG_3", "false")
    );

    Map<String, ParameterItem> expected = new HashMap<>();
    parameters.stream()
      .forEach(p -> expected.put(p.getName(), p));

    Mockito.when(parameterRepo.findBySection("App"))
      .thenReturn(parameters);

    Map<String, ParameterItem> actual = parameterService.getParameters("App");

    Assertions.assertTrue(actual.size() == expected.size());
    actual.keySet().forEach(k -> {
      ParameterItem actualItem = actual.get(k);
      Assertions.assertTrue(expected.containsKey(k));
      ParameterItem expectedItem = expected.get(k);
      Assertions.assertEquals(expectedItem.getSection(), actualItem.getSection());
      Assertions.assertEquals(expectedItem.getName(), actualItem.getName());
      Assertions.assertEquals(expectedItem.getValue(), actualItem.getValue());
    });

  }

}
