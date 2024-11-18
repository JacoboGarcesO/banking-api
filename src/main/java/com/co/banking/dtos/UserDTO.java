package com.co.banking.dtos;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class UserDTO {
  @NotEmpty
  private String name;
  @Email
  @NotEmpty
  private String email;
  @NotEmpty
  private String address;
}
