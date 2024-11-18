package com.co.banking.services;

import com.co.banking.dtos.TransactionDTO;
import com.co.banking.models.Transaction;
import com.co.banking.models.constants.TransactionType;
import com.co.banking.repositories.ITransactionRepository;
import com.co.banking.services.interfaces.ITransactionService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class TransactionService implements ITransactionService {
  private final ITransactionRepository transactionRepository;

  public Mono<Transaction> create(TransactionDTO transactionDTO) {
    return transactionRepository.save(
            Transaction.builder()
                    .type(TransactionType.fromString(transactionDTO.getType()))
                    .amount(transactionDTO.getAmount())
                    .accountId(transactionDTO.getAccountId())
                    .build());
  }
}
