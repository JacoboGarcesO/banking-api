package com.co.banking.models.constants;

public enum AccountType {
  SAVINGS, CHECKING;

  public static AccountType fromString(String type) {
    return switch (type.toUpperCase()) {
      case "SAVINGS" -> SAVINGS;
      case "CHECKING" -> CHECKING;
      default -> throw new IllegalArgumentException();
    };
  }
}
