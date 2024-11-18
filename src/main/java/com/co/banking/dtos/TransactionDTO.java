package com.co.banking.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDTO {
  @NotBlank
  @NotNull
  private String type;
  @NotNull
  @PositiveOrZero()
  private Double amount;
  @NotNull
  @Positive()
  private Integer accountId;
}
