package com.co.banking.services;

import com.co.banking.dtos.*;
import com.co.banking.models.Account;
import com.co.banking.models.Transaction;
import com.co.banking.models.constants.AccountType;
import com.co.banking.models.constants.TransactionType;
import com.co.banking.repositories.IAccountRepository;
import com.co.banking.repositories.ITransactionRepository;
import com.co.banking.services.interfaces.IAccountService;
import lombok.AllArgsConstructor;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

import java.util.stream.Collectors;

@AllArgsConstructor
@Service
public class AccountService implements IAccountService {
  private final IAccountRepository accountRepository;
  private final ITransactionRepository transactionRepository;

  public Mono<Account> create(AccountDTO account) {
    return accountRepository.save(
            Account.builder()
                    .number(account.getNumber())
                    .type(AccountType.fromString(account.getType()))
                    .userId(account.getUserId())
                    .build());
  }

  public Mono<AccountBalanceDTO> getAccountBalance(Integer accountId) {
    return accountRepository.findById(accountId)
            .flatMap(account ->
                    transactionRepository
                            .findByAccountId(accountId)
                            .map(transaction -> transaction.getType().equals(TransactionType.WITHDRAW) ? -transaction.getAmount() : transaction.getAmount()).reduce(Double::sum)
                            .map(balance -> AccountBalanceDTO.builder()
                                    .id(account.getId())
                                    .number(account.getNumber())
                                    .type(account.getType().toString())
                                    .balance(balance)
                                    .build()));
  }

  public Mono<AccountTransactionsDTO> getAccountTransactions(Integer accountId, String type) {
    return accountRepository
            .findById(accountId)
            .zipWith(
                    transactionRepository
                            .findByAccountId(accountId)
                            .filter(transaction -> transaction.getType().equals(TransactionType.fromString(type)))
                            .collectList()
            )
            .map(tuple -> AccountTransactionsDTO.builder()
                    .id(tuple.getT1().getId())
                    .number(tuple.getT1().getNumber())
                    .type(tuple.getT1().getType().toString())
                    .userId(tuple.getT1().getUserId())
                    .transactions(tuple.getT2().stream().collect(Collectors.toMap(Transaction::getId, Transaction::getAmount)))
                    .build());
  }

  public Mono<AccountTransactionSummaryDTO> getAccountTransactionSummary(Integer accountId) {
    return this.getAccountBalance(accountId)
            .zipWith(
                    transactionRepository
                            .findByAccountId(accountId)
                            .map(transaction -> TransactionSummaryDTO.builder()
                                    .id(transaction.getId())
                                    .type(transaction.getType().toString())
                                    .amount(transaction.getAmount())
                                    .build())
                            .collectList()
            )
            .map(tuple -> AccountTransactionSummaryDTO.builder()
                    .id(tuple.getT1().getId())
                    .number(tuple.getT1().getNumber())
                    .type(tuple.getT1().getType())
                    .balance(tuple.getT1().getBalance())
                    .transactions(tuple.getT2())
                    .build());
  }

  public Mono<TransactionDTO> transfer(TransferDTO transferDTO) {
    return this.getAccountBalance(transferDTO.getAccountId()).flatMap(account -> {
              if (account.getBalance() >= transferDTO.getAmount()) {
                return transactionRepository.save(
                      Transaction.builder()
                              .type(TransactionType.WITHDRAW)
                              .amount(transferDTO.getAmount())
                              .accountId(transferDTO.getAccountId())
                              .build());
              }

              return Mono.error(new IllegalArgumentException("Insufficient balance"));
            })
            .map(transaction -> TransactionDTO.builder()
                    .type(TransactionType.DEPOSIT.toString())
                    .amount(transaction.getAmount())
                    .accountId(transferDTO.getToAccountId())
                    .build());
  }

  @RabbitListener(queues = "${rabbit.queue-name}")
  public void receiveTransaction(TransactionDTO transaction) {
    transactionRepository.save(
            Transaction.builder()
                    .type(TransactionType.fromString(transaction.getType()))
                    .amount(transaction.getAmount())
                    .accountId(transaction.getAccountId())
                    .build())
            .doOnError(error -> {
              System.err.println("Error saving transaction: " + error.getMessage());
            })
            .subscribe();
  }
}
