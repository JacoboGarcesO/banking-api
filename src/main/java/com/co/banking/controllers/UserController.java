package com.co.banking.controllers;

import com.co.banking.dtos.UserAccountsSummaryDTO;
import com.co.banking.dtos.UserDTO;
import com.co.banking.models.User;
import com.co.banking.services.interfaces.IUserService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

@RestController
@RequestMapping("api/users")
public class UserController {
  private final IUserService userService;

  public UserController(IUserService userService) {
    this.userService = userService;
  }

  @PostMapping
  public Mono<ResponseEntity<User>> createUser(@Valid @RequestBody UserDTO user) {
    return userService.create(user).map(ResponseEntity::ok);
  }

  @GetMapping("/{userId}/accounts")
  public Mono<ResponseEntity<UserAccountsSummaryDTO>> getUserAccountsSummary(@PathVariable Integer userId) {
    return userService.getUserAccountsSummary(userId).map(ResponseEntity::ok);
  }
}
