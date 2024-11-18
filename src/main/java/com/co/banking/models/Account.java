package com.co.banking.models;

import com.co.banking.models.constants.AccountType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Table("accounts")
public class Account {
  @Id
  private Integer id;
  private Integer number;
  private AccountType type;
  @Column("user_id")
  private Integer userId;
}
