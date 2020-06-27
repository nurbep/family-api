package com.members.api.web;

import com.members.api.common.MemberFault;
import com.members.api.service.exception.MemberError;
import com.members.api.service.exception.MemberNotFoundException;
import com.members.api.service.exception.TechnicalException;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.Optional;
import javax.servlet.http.HttpServletRequest;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class MemberControllerAdvice {

  public static final String PAYMENT_FAULT = "PaymentFault";
  public static final String UTC = "UTC";
  public static final int MEMBER_FAULT_CODE = 4000;
  public static final int TECHNICAL_EXCEPTION_FAULT_CODE = 5000;
  public static final int PAYMENT_SERVICE_FAULT_CODE = 1000;
  public static final int UNKNOWN_FAULT_CODE = 999;

  @ExceptionHandler(TechnicalException.class)
  public ResponseEntity<MemberError> handleTechnicalException(HttpServletRequest request,
      final Exception ex) {
    return error(ex, HttpStatus.INTERNAL_SERVER_ERROR, request);
  }

  @ExceptionHandler(MemberNotFoundException.class)
  public ResponseEntity<MemberError> handleBusinessException(HttpServletRequest request,
      final Exception ex) {
    return error(ex, HttpStatus.BAD_REQUEST, request);
  }

  private ResponseEntity<MemberError> error(final Exception exception, final HttpStatus httpStatus,
      HttpServletRequest request) {
    final String message;
    String temporaryDeferredMessage;
    try {
      temporaryDeferredMessage = Optional.of(exception.getMessage())
          .orElse(exception.getClass().getSimpleName());
    } catch (Exception ex) {
      temporaryDeferredMessage = exception.getClass().getSimpleName();
    }
    message = temporaryDeferredMessage;
    MemberFault gpmFault = new MemberFault();

    int faultCode = UNKNOWN_FAULT_CODE;
    Optional<String> memberException = gpmFault.getMemberExceptions()
        .stream().filter(message::equalsIgnoreCase).findFirst();
    Optional<String> technicalException = gpmFault.getTechnicalExceptions()
        .stream().filter(message::equalsIgnoreCase).findFirst();
    if (memberException.isPresent()) {
      faultCode = MEMBER_FAULT_CODE + gpmFault.getMemberExceptions().indexOf(memberException.get());
    } else if (technicalException.isPresent()) {
      faultCode = TECHNICAL_EXCEPTION_FAULT_CODE + gpmFault.getTechnicalExceptions()
          .indexOf(technicalException.get());
    } else if (message.contains(PAYMENT_FAULT)) {
      faultCode = PAYMENT_SERVICE_FAULT_CODE;
    }

    MemberError error = MemberError.builder()
        .timestamp(ZonedDateTime.now(ZoneId.of(UTC)))
        .faultcode(faultCode)
        .exception(exception.getClass().getSimpleName())
        .message(message)
        .path(request.getRequestURI())
        .build();

    HttpHeaders headers = new HttpHeaders();
    headers.setContentType(MediaType.APPLICATION_JSON);
    return new ResponseEntity<>(error, headers, httpStatus);
  }
}