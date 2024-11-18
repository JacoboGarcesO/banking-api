package com.co.banking.models;

import com.co.banking.models.constants.TransactionType;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table("transactions")
public class Transaction {
  @Id
  private Integer id;
  private TransactionType type;
  private Double amount;
  @Column("account_id")
  private Integer accountId;
}
