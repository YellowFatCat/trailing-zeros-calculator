package ru.spb.alina.trailingzeroscalculator.service;

import org.junit.Before;
import org.junit.Test;

import ru.spb.alina.trailingzeroscalculator.exception.NegativeValueException;
import ru.spb.alina.trailingzeroscalculator.service.impl.CalculatorServiceImpl;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;

public class CalculatorServiceTest {

  private CalculatorService calculatorService;

  @Before
  public void setUp() {
    calculatorService = new CalculatorServiceImpl();
  }

  @Test
  public void should_countTrailingZeros_when_inputValueIsPositiveAndSmall() {

    // Given
    long inputValue = 6;
    long expectedTrailingZerosCount = 1;

    // When
    long actualTrailingZerosCount = calculatorService.countFactorialTrailingZeros(inputValue);

    // Then
    assertThat(actualTrailingZerosCount).isEqualTo(expectedTrailingZerosCount);
  }

  @Test
  public void should_countTrailingZeros_when_inputValueIsPositiveAndLarge() {

    // Given
    long inputValue = 1000;
    long expectedTrailingZerosCount = 249;

    // When
    long actualTrailingZerosCount = calculatorService.countFactorialTrailingZeros(inputValue);

    // Then
    assertThat(actualTrailingZerosCount).isEqualTo(expectedTrailingZerosCount);
  }

  @Test
  public void should_countTrailingZeros_when_inputValueIsZero() {

    // Given
    long inputValue = 0;
    long expectedTrailingZerosCount = 0;

    // When
    long actualTrailingZerosCount = calculatorService.countFactorialTrailingZeros(inputValue);

    // Then
    assertThat(actualTrailingZerosCount).isEqualTo(expectedTrailingZerosCount);
  }

  @Test
  public void should_throwAnException_when_inputValueIsNegative() {

    // Given
    long inputValue = -10;

    // When
    Throwable thrownException = catchThrowable(
        () -> calculatorService.countFactorialTrailingZeros(inputValue));

    // Then
    assertThat(thrownException).isInstanceOf(NegativeValueException.class);
  }
}