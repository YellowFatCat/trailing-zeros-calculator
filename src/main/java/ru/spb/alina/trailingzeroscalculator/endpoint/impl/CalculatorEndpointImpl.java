package ru.spb.alina.trailingzeroscalculator.endpoint.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.spb.alina.trailingzeroscalculator.endpoint.CalculatorEndpoint;
import ru.spb.alina.trailingzeroscalculator.service.CalculatorService;

@RestController
public class CalculatorEndpointImpl implements CalculatorEndpoint {

  @Autowired
  private CalculatorService calculatorService;

  @ExceptionHandler(java.lang.NumberFormatException.class)
  public ResponseEntity<String> notNumberExceptionHandler(Throwable throwable) {
    return ResponseEntity.badRequest().body("The input value should be a non-negative integer");
  }

  @Override
  public long countFactorialTrailingZeros(@RequestParam("value") long value) {
    return calculatorService.countFactorialTrailingZeros(value);
  }
}
