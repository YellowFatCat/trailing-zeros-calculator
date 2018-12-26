package ru.spb.alina.trailingzeroscalculator.endpoint;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import ru.spb.alina.trailingzeroscalculator.exception.NegativeValueException;
import ru.spb.alina.trailingzeroscalculator.service.CalculatorService;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(CalculatorEndpoint.class)
public class CalculatorEndpointTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private CalculatorService calculatorServiceMock;

  @Test
  public void should_countTrailingZeros_when_inputValueIsCorrectAndPositiveAndSmall() throws Exception {

    // Given
    long inputValue = 6;
    long expectedTrailingZerosCount = 1;

    given(calculatorServiceMock.countFactorialTrailingZeros(inputValue)).willReturn(expectedTrailingZerosCount);

    // When
    mockMvc.perform(get("/calculator").param("value", String.valueOf(inputValue)))

    // Then
           .andExpect(status().isOk())
           .andExpect(content().string(String.valueOf(expectedTrailingZerosCount)));

    verify(calculatorServiceMock, times(1)).countFactorialTrailingZeros(inputValue);
    verifyNoMoreInteractions(calculatorServiceMock);
  }

  @Test
  public void should_countTrailingZeros_when_inputValueIsCorrectAndPositiveAndLarge() throws Exception {

    // Given
    long inputValue = 1000;
    long expectedTrailingZerosCount = 249;

    given(calculatorServiceMock.countFactorialTrailingZeros(inputValue)).willReturn(expectedTrailingZerosCount);

    // When
    mockMvc.perform(get("/calculator").param("value", String.valueOf(inputValue)))

    // Then
           .andExpect(status().isOk())
           .andExpect(content().string(String.valueOf(expectedTrailingZerosCount)));

    verify(calculatorServiceMock, times(1)).countFactorialTrailingZeros(inputValue);
    verifyNoMoreInteractions(calculatorServiceMock);
  }

  @Test
  public void should_countTrailingZeros_when_inputValueIsZero() throws Exception {

    // Given
    long inputValue = 0;
    long expectedTrailingZerosCount = 0;

    given(calculatorServiceMock.countFactorialTrailingZeros(inputValue)).willReturn(expectedTrailingZerosCount);

    // When
    mockMvc.perform(get("/calculator").param("value", String.valueOf(inputValue)))

    // Then
           .andExpect(status().isOk())
           .andExpect(content().string(String.valueOf(expectedTrailingZerosCount)));

    verify(calculatorServiceMock, times(1)).countFactorialTrailingZeros(inputValue);
    verifyNoMoreInteractions(calculatorServiceMock);
  }

  @Test
  public void should_fail_when_inputValueIsNegative() throws Exception {

    // Given
    long inputValue = -18;

    given(calculatorServiceMock.countFactorialTrailingZeros(inputValue))
        .willThrow(new NegativeValueException());

    // When
    mockMvc.perform(get("/calculator").param("value", String.valueOf(inputValue)))

    // Then
           .andExpect(status().isBadRequest());

    verify(calculatorServiceMock, times(1)).countFactorialTrailingZeros(inputValue);
    verifyNoMoreInteractions(calculatorServiceMock);
  }

  @Test
  public void should_fail_when_inputValueIsNotAnInteger() throws Exception {

    // Given
    String inputValue = "10.1";

    // When
    mockMvc.perform(get("/calculator").param("value", inputValue))

    // Then
           .andExpect(status().isBadRequest())
           .andExpect(content().string("The input value should be a non-negative integer"));

    verifyZeroInteractions(calculatorServiceMock);
  }

  @Test
  public void should_fail_when_inputValueIsNotANumber() throws Exception {

    // Given
    String inputValue = "string";

    // When
    mockMvc.perform(get("/calculator").param("value", inputValue))

    // Then
           .andExpect(status().isBadRequest())
           .andExpect(content().string("The input value should be a non-negative integer"));

    verifyZeroInteractions(calculatorServiceMock);
  }
}