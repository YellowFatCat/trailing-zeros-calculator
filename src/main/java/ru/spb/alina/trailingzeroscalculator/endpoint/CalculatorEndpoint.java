package ru.spb.alina.trailingzeroscalculator.endpoint;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

public interface CalculatorEndpoint {

  /**
   * Counts trailing zeros in a factorial of a given number.
   *
   * @param value number to count trailing zeros in its factorial
   * @return count of trailing zeros
   */
  @RequestMapping(path = "/calculator", method = RequestMethod.GET)
  long countFactorialTrailingZeros(@RequestParam("value") long value);
}
