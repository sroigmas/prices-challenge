package com.github.sroigmas.priceschallenge.infrastructure.rest;

import com.github.sroigmas.priceschallenge.application.exception.ApplicationNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class CommonRestExceptionHandler {

  @ExceptionHandler(MethodArgumentTypeMismatchException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleMethodArgumentTypeMismatchExceptions(
      MethodArgumentTypeMismatchException e) {
    String message = e.getName() + " should be of type " + e.getRequiredType().getName();
    return new ErrorResponse(HttpStatus.BAD_REQUEST, message);
  }

  @ExceptionHandler(MissingServletRequestParameterException.class)
  @ResponseStatus(HttpStatus.BAD_REQUEST)
  public ErrorResponse handleMissingServletRequestParameterExceptions(
      MissingServletRequestParameterException e) {
    return new ErrorResponse(HttpStatus.BAD_REQUEST, e.getMessage());
  }

  @ExceptionHandler(ApplicationNotFoundException.class)
  @ResponseStatus(HttpStatus.NOT_FOUND)
  public ErrorResponse handleApplicationNotFoundExceptions(
      ApplicationNotFoundException e) {
    return new ErrorResponse(HttpStatus.NOT_FOUND, e.getMessage());
  }
}
