package ru.spb.alina.trailingzeroscalculator.service;

public interface CalculatorService {

  /**
   * Counts trailing zeros in a factorial of a given number.
   *
   * @param value number to count trailing zeros in its factorial
   * @return count of trailing zeros
   */
  long countFactorialTrailingZeros(long value);
}
