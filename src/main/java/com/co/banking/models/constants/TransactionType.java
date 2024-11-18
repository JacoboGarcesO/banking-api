package com.co.banking.models.constants;

public enum TransactionType {
  WITHDRAW, DEPOSIT;

  public static TransactionType fromString(String type) {
    return switch (type.toUpperCase()) {
      case "WITHDRAW" -> WITHDRAW;
      case "DEPOSIT" -> DEPOSIT;
      default -> throw new IllegalArgumentException();
    };
  }
}
