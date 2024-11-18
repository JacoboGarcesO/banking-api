package com.co.banking.services;

import com.co.banking.dtos.AccountTransactionSummaryDTO;
import com.co.banking.dtos.UserAccountsSummaryDTO;
import com.co.banking.dtos.UserDTO;
import com.co.banking.models.User;
import com.co.banking.repositories.IAccountRepository;
import com.co.banking.repositories.IUserRepository;
import com.co.banking.services.interfaces.IAccountService;
import com.co.banking.services.interfaces.IUserService;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;

@AllArgsConstructor
@Service
public class UserService implements IUserService {
  private final IUserRepository userRepository;
  private final IAccountRepository accountRepository;
  private final IAccountService accountService;

  public Mono<User> create(UserDTO user) {
    return userRepository.save(
      User.builder()
        .name(user.getName())
        .email(user.getEmail())
        .address(user.getAddress())
        .build());
  }

  public Mono<UserAccountsSummaryDTO> getUserAccountsSummary(Integer userId) {
    return userRepository
      .findById(userId)
      .zipWith(
        accountRepository
          .findByUserId(userId)
          .flatMap(account -> accountService.getAccountTransactionSummary(account.getId()))
          .collectList()
      )
      .map(tuple -> {
        Double totalBalance = tuple.getT2().stream().mapToDouble(AccountTransactionSummaryDTO::getBalance).sum();
        return UserAccountsSummaryDTO.builder()
          .name(tuple.getT1().getName())
          .email(tuple.getT1().getEmail())
          .address(tuple.getT1().getAddress())
          .accounts(tuple.getT2())
          .totalBalance(totalBalance)
          .build();
      });
  }
}
