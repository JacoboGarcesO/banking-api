package com.co.banking.dtos;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransferDTO {
  @NotNull
  private Integer accountId;
  @NotNull
  private Integer toAccountId;
  @NotNull
  @Min(0)
  private Double amount;
}
