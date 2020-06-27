package com.members.api.service.exception;

public class MemberNotFoundException extends Exception {

  /**
   *
   */
  private static final long serialVersionUID = 1L;

  public MemberNotFoundException(String errorMessage) {
    super(errorMessage);
  }

  public MemberNotFoundException(String errorMessage, Throwable cause) {
    super(errorMessage, cause);
  }
}
