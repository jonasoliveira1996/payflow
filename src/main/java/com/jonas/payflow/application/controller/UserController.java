package com.jonas.payflow.application.controller;

import com.jonas.payflow.application.dto.CreateUserRequest;
import com.jonas.payflow.application.dto.UserResponse;
import com.jonas.payflow.application.service.UserService;
import com.jonas.payflow.domain.model.User;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Usuário")
@ApiResponses({
        @ApiResponse(responseCode = "200", description = "Usuário criado com sucsso"),
        @ApiResponse(responseCode = "400", description = "Erro de regra de negócio"),
        @ApiResponse(responseCode = "422", description = "Erro de validação")
})
@RestController
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    public UserController(UserService userService) {
        this.userService = userService;
    }

    @Operation(
            summary = "Criar usuário e conta",
            description = "Cria um usuário e conta de forma atômica"
    )
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
