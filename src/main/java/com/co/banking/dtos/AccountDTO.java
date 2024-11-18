package com.co.banking.dtos;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO {
  @NotNull
  @Positive
  private Integer number;
  @NotEmpty
  private String type;
  @NotNull
  @Positive
  private Integer userId;
}
