package com.co.banking.repositories;

import com.co.banking.models.User;
import org.springframework.data.repository.reactive.ReactiveCrudRepository;

public interface IUserRepository extends ReactiveCrudRepository<User, Integer> {
}
