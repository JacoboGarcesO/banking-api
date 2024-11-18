package com.co.banking.repositories;

import com.co.banking.models.Transaction;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface ITransactionRepository extends ReactiveCrudRepository<Transaction, Integer> {
  Flux<Transaction> findByAccountId(Integer accountId);
}
