package com.members.api.common;

import java.util.Arrays;
import java.util.List;

/**
 * GPM fault messages
 */
public class MemberFault {

  /* MemberExceptions */
  public static final String INVALID_ACTION = "Invalid ACTION";
  public static final String EXISTING_MEMBER = "Member already exists";
  public static final String COULD_NOT_MATCH_ID = "Id does not match";
  public static final String COULD_NOT_FIND_VENDOR_PRODUCT = "Could not find vendor product";
  public static final String NO_VENDOR_PRODUCT_SET_FOR_ORDER = "No vendor product was set for this order";
  public static final String COULD_NOT_FIND_MEMBER = "Could not find member";
  public static final String COULD_NOT_FIND_CHARGE = "Could not find charge";
  public static final String COULD_NOT_FIND_CHARGE_ASSOCIATED_WITH_ORDER = "Could not find charge associated with order";
  public static final String NO_CHARGES_FOUND = "No charges found";
  public static final String INVALID_ORDER_STATE = "The expected state of the order does not match the operation";
  public static final String PAYMENT_DUE_DATE_IS_BEFORE_CURRENT_TIME = "Payment due date is before current time";
  public static final String INVALID_ADHOC_FEETYPE = "Invalid AdHoc fee type";
  public static final String NO_RESERVATION_ITEM_FOUND = "Could not find item in current order";
  public static final String NO_PAYMENT_SCHEDULE_FOUND = "Could not find payment schedule for current order";
  public static final String COULD_NOT_FIND_BROKER_DETAILS_FOR_CURRENT_ORDER = "Could not find broker details for current order";
  public static final String CHARGE_NOT_IN_VALID_STATE_TO_APPLY_PROCESSOR_FEES = "Charge not in valid state to apply processor fees";
  public static final String REFUND_EXCEEDS_AVAILABLE_BALANCE = "Refund exceeds available balance";
  public static final String REFUND_AMOUNT_WITH_FEES_EXCEEDS_AVAILABLE_REFUND_BALANCE = "Refund amount with fees exceeds available refund balance";
  public static final String NO_REFUNDS_ABLE_TO_BE_GENERATED_FOR_CURRENCY = "No refunds able to be generated for currency";
  public static final String NO_CHARGES_WITH_AVAILABLE_BALANCE = "No charges with available balance";
  public static final String UNABLE_TO_GENERATE_REFUND_FOR_AMOUNT = "Unable to generate refund for amount";
  public static final String CHARGE_DETAILS_DOES_NOT_MATCH_CHARGE_AMOUNT = "Charge details does not match charge amount";
  public static final String COULD_NOT_FIND_PAYMENT_PROCESSOR = "Could not find payment processor";

  /* TechnicalExceptions */
  public static final String OPERATION_FAILED = "Operation Failed";
  public static final String UNABLE_TO_PROCESS = "Unable to process the request at this time: Operation Failed";
  public static final String COULD_NOT_MAP_RESPONSE = "Could not map response";
  public static final String RESERVATION_REQUEST_DESERIALIZATION_FAILURE = "Unable to convert existing ReservationOrder to object";
  public static final String UNABLE_TO_CONVERT_RESERVATION_ORDER_TO_STRING = "Unable to convert ReservationOrder to string";
  public static final String COULD_NOT_CONVERT_RESERVATION_ORDER_DTO = "Could not  between ReservationOrderDTO and domain object";


  public List<String> getMemberExceptions() {
    return Arrays.asList(INVALID_ACTION, EXISTING_MEMBER, COULD_NOT_MATCH_ID,
        NO_VENDOR_PRODUCT_SET_FOR_ORDER, COULD_NOT_FIND_MEMBER, COULD_NOT_FIND_CHARGE,
        COULD_NOT_FIND_CHARGE_ASSOCIATED_WITH_ORDER, NO_CHARGES_FOUND, INVALID_ORDER_STATE,
        PAYMENT_DUE_DATE_IS_BEFORE_CURRENT_TIME, INVALID_ADHOC_FEETYPE, NO_RESERVATION_ITEM_FOUND,
        NO_PAYMENT_SCHEDULE_FOUND, COULD_NOT_FIND_BROKER_DETAILS_FOR_CURRENT_ORDER,
        CHARGE_NOT_IN_VALID_STATE_TO_APPLY_PROCESSOR_FEES, REFUND_EXCEEDS_AVAILABLE_BALANCE,
        REFUND_AMOUNT_WITH_FEES_EXCEEDS_AVAILABLE_REFUND_BALANCE,
        NO_REFUNDS_ABLE_TO_BE_GENERATED_FOR_CURRENCY,
        NO_CHARGES_WITH_AVAILABLE_BALANCE, UNABLE_TO_GENERATE_REFUND_FOR_AMOUNT,
        CHARGE_DETAILS_DOES_NOT_MATCH_CHARGE_AMOUNT, COULD_NOT_FIND_PAYMENT_PROCESSOR);
  }

  public List<String> getTechnicalExceptions() {
    return Arrays.asList(OPERATION_FAILED, UNABLE_TO_PROCESS, COULD_NOT_MAP_RESPONSE,
        RESERVATION_REQUEST_DESERIALIZATION_FAILURE, UNABLE_TO_CONVERT_RESERVATION_ORDER_TO_STRING,
        COULD_NOT_CONVERT_RESERVATION_ORDER_DTO);
  }
}
