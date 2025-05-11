package com.webriz.subscriptionsservice;

import com.webriz.subscriptionsservice.exception.ResponseException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * Класс глобального перехватчика ошибок
 */
@ControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
  /**
   * Обработчик исключения из метода сервиса для генерации корректного http ответа об ошибке
   */
  @ExceptionHandler(ResponseException.class)
  private ResponseEntity<String> handleResponseException(ResponseException e) {
    log.warn("Response exception occurred: {}", e.getMessage());
    return new ResponseEntity<>(e.getMessage(), HttpStatus.NOT_FOUND);
  }

  @ExceptionHandler(RuntimeException.class)
  private ResponseEntity<String> handleRuntimeException(RuntimeException e) {
    log.error("Runtime exception occurred: {}", e.getMessage(), e);
    return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
  }

}
