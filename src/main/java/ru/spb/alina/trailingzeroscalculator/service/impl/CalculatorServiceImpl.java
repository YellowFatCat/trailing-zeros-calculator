package ru.spb.alina.trailingzeroscalculator.service.impl;

import org.springframework.stereotype.Service;
import ru.spb.alina.trailingzeroscalculator.exception.NegativeValueException;
import ru.spb.alina.trailingzeroscalculator.service.CalculatorService;

@Service
public class CalculatorServiceImpl implements CalculatorService {

  @Override
  public long countFactorialTrailingZeros(long value) {

    if (value < 0) {
      throw new NegativeValueException();
    }

    if (value == 0) {
      return 0;
    }

    long result = 0;

    while (value > 0) {
      value /= 5;
      result += value;

    }

    return result;
  }
}
