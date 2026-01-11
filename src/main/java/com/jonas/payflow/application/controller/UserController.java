package com.jonas.payflow.application.controller;

import com.jonas.payflow.application.dto.CreateUserRequest;
import com.jonas.payflow.application.dto.UserResponse;
import com.jonas.payflow.application.service.UserService;
import com.jonas.payflow.domain.model.User;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @PostMapping
    public ResponseEntity<UserResponse> create(@Valid @RequestBody CreateUserRequest request){
        User user = userService.createUser(
                request.getName(),
                request.getEmail()
        );

        return ResponseEntity.ok(
                new UserResponse(
                        user.getId(),
                        user.getName(),
                        user.getEmail()
                )
        );
    }

}
