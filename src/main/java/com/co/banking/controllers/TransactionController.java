package com.co.banking.controllers;

import com.co.banking.dtos.TransactionDTO;
import com.co.banking.models.Transaction;
import com.co.banking.services.interfaces.ITransactionService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/transactions")
public class TransactionController {
  private final ITransactionService transactionService;

  public TransactionController(ITransactionService transactionService) {
    this.transactionService = transactionService;
  }

  @PostMapping
  public Mono<ResponseEntity<Transaction>> create(@Valid @RequestBody TransactionDTO transactionDTO) {
    return transactionService.create(transactionDTO).map(ResponseEntity::ok);
  }
}
