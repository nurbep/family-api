package com.members.api.service.exception;

import java.time.ZonedDateTime;
import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class MemberError {

  private ZonedDateTime timestamp;
  private String exception;
  private int faultcode;
  private String message;
  private String path;
}
