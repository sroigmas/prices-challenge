package com.github.sroigmas.priceschallenge.application.exception;

public class ApplicationNotFoundException extends RuntimeException {

  public ApplicationNotFoundException(String message) {
    super(message);
  }
}
