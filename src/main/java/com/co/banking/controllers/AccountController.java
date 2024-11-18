package com.co.banking.controllers;

import com.co.banking.dtos.*;
import com.co.banking.models.Account;
import com.co.banking.services.interfaces.IAccountService;
import jakarta.validation.Valid;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/accounts")
public class AccountController {
  private final IAccountService accountService;
  private final RabbitTemplate rabbitTemplate;

  @Value("${rabbit.exchange-name}")
  private String exchangeName;

  @Value("${rabbit.routing-key}")
  private String routingKey;

  public AccountController(IAccountService accountService, RabbitTemplate rabbitTemplate) {
    this.accountService = accountService;
    this.rabbitTemplate = rabbitTemplate;
  }

  @PostMapping
  public Mono<ResponseEntity<Account>> createAccount(@Valid @RequestBody AccountDTO account) {
    return accountService.create(account).map(ResponseEntity::ok);
  }

  @GetMapping("/{accountId}")
  public Mono<ResponseEntity<AccountBalanceDTO>> getAccountBalance(@PathVariable Integer accountId) {
    return accountService.getAccountBalance(accountId).map(ResponseEntity::ok);
  }

  @GetMapping("/{accountId}/{type}")
  public Mono<ResponseEntity<AccountTransactionsDTO>> getAccountTransactions(@PathVariable Integer accountId, @PathVariable String type) {
    return accountService.getAccountTransactions(accountId, type).map(ResponseEntity::ok);
  }

  @GetMapping("/{accountId}/summary")
  public Mono<ResponseEntity<AccountTransactionSummaryDTO>> getAccountTransactionSummary(@PathVariable Integer accountId) {
    return accountService.getAccountTransactionSummary(accountId).map(ResponseEntity::ok);
  }

  @PostMapping("/transfer")
  public Mono<ResponseEntity<TransactionDTO>> transfer(@Valid @RequestBody TransferDTO transfer) {
    return accountService.transfer(transfer).map(transaction -> {
      rabbitTemplate.convertAndSend(exchangeName, routingKey, transaction);
      return ResponseEntity.ok(transaction);
    });
  }
}
