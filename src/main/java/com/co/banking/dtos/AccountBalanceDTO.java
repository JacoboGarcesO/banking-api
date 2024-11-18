package com.co.banking.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class AccountBalanceDTO {
  protected Integer id;
  protected Integer number;
  protected String type;
  protected Double balance;
}
