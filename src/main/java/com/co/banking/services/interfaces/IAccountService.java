package com.co.banking.services.interfaces;

import com.co.banking.dtos.*;
import com.co.banking.models.Account;
import reactor.core.publisher.Mono;

public interface IAccountService {
  Mono<Account> create(AccountDTO account);
  Mono<AccountBalanceDTO> getAccountBalance(Integer accountId);
  Mono<AccountTransactionsDTO> getAccountTransactions(Integer accountId, String type);
  Mono<AccountTransactionSummaryDTO> getAccountTransactionSummary(Integer accountId);
  Mono<TransactionDTO> transfer(TransferDTO transferDTO);
}
