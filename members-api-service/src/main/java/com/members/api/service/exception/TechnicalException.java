package com.members.api.service.exception;

import lombok.extern.slf4j.Slf4j;

/**
 * Base Exception for technical issues.
 *
 * @author greg.guevara
 */
@Slf4j
public class TechnicalException extends Exception {

  public TechnicalException(String errorMessage) {
    super(errorMessage);
  }

  public TechnicalException(String errorMessage, Throwable cause) {
    super(errorMessage, cause);
    /*throwing a technical exception always prints the full stack trace to the log when available*/
    if (errorMessage != null && cause != null && cause.getStackTrace() != null) {
      log.error(errorMessage, cause);
    } else {
      log.error("failed to supply error data");
    }
  }
}
