package com.co.banking.repositories;

import com.co.banking.models.Account;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;
import reactor.core.publisher.Flux;

public interface IAccountRepository extends ReactiveCrudRepository<Account, Integer> {
  Flux<Account> findByUserId(Integer userId);
}
