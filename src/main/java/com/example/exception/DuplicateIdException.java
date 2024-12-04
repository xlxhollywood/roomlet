package com.example.exception;

public class DuplicateIdException extends RuntimeException {
  public DuplicateIdException(String msg) {
      super(msg); // 상위 객체에게 메세지 넘김
  }
}
