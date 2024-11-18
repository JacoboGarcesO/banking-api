package com.co.banking.dtos;

import lombok.Data;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.NoArgsConstructor;

import java.util.Map;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountTransactionsDTO {
  private Integer id;
  private Integer number;
  private String type;
  private Integer userId;
  private Map<Integer, Double> transactions;
}
