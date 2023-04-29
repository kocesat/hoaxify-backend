package com.hoaxify.backend.scratch;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;
import java.math.RoundingMode;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

class DoubleTests {

  @Test
  void testDoubleSum() {
    float x = Double.valueOf(Double.sum(0.1, 0.7) * 10.0).floatValue();
    assertThat(x).isEqualTo(Float.valueOf(8.0f));
  }

  @Test
  void testDouble() {
    double x = ((0.1 + 0.7) * 10);
    var bd = BigDecimal.valueOf(x).setScale(2, RoundingMode.HALF_UP);
    assertThat(bd).isEqualTo(new BigDecimal("8.00"));
  }

  @Test
  void testBigDecimal() {
    var augend1 = BigDecimal.valueOf(0.1);
    var augend2 = BigDecimal.valueOf(0.7);
    var multiplier = BigDecimal.valueOf(10);

    var result = augend1.add(augend2)
                        .multiply(multiplier)
                        .setScale(2, RoundingMode.HALF_UP);
    assertThat(result).isEqualTo(BigDecimal.valueOf(8.00)
      .setScale(2, RoundingMode.HALF_UP));
  }
}
