package ru.spb.alina.trailingzeroscalculator.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST, reason="The factorial is defined only for non-negative integers")
public class NegativeValueException extends IllegalArgumentException {}
