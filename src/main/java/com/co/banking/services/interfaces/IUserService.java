package com.co.banking.services.interfaces;

import com.co.banking.dtos.UserAccountsSummaryDTO;
import com.co.banking.dtos.UserDTO;
import com.co.banking.models.User;
import reactor.core.publisher.Mono;

public interface IUserService {
  Mono<User> create(UserDTO user);
  Mono<UserAccountsSummaryDTO> getUserAccountsSummary(Integer userId);
}
