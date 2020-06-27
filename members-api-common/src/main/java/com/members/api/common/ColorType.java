package com.members.api.common;

public enum ColorType {
  BLACK("black"),
  BROWN("brown"),
  GREW("grew"),
  WHITE("white");

  private String value;

  ColorType(String value) {
    this.value = value;
  }

  public String getValue() {
    return this.value;
  }
}
