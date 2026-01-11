package com.jonas.payflow.application.service;

import com.jonas.payflow.domain.model.User;
import com.jonas.payflow.domain.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final AccountService accountService;

    public UserService(UserRepository userRepository,
                       AccountService accountService) {
        this.userRepository = userRepository;
        this.accountService = accountService;
    }

    @Transactional
    public User createUser(String name, String email) {
        User user = new User();
        user.setName(name);
        user.setEmail(email);

        User savedUser = userRepository.save(user);

        accountService.createAccountFor(savedUser);

        return savedUser;
    }


}
