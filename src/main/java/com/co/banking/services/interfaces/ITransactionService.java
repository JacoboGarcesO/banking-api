package com.co.banking.services.interfaces;

import com.co.banking.dtos.TransactionDTO;
import com.co.banking.models.Transaction;
import reactor.core.publisher.Mono;

public interface ITransactionService {
  Mono<Transaction> create(TransactionDTO transactionDTO);
}
