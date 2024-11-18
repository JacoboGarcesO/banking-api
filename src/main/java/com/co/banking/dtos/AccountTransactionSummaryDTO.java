package com.co.banking.dtos;

import lombok.*;
import lombok.experimental.SuperBuilder;

import java.util.List;

@EqualsAndHashCode(callSuper = true)
@Data
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public class AccountTransactionSummaryDTO extends AccountBalanceDTO {
  private List<TransactionSummaryDTO> transactions;
}
