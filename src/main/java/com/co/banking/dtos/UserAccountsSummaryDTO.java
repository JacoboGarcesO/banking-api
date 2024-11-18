package com.co.banking.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserAccountsSummaryDTO {
  private String name;
  private String email;
  private String address;
  private Double totalBalance;
  private List<AccountTransactionSummaryDTO> accounts;
}
