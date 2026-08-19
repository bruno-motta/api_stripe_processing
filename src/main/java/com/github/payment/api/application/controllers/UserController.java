package com.github.payment.api.application.controllers;

import com.github.payment.api.application.dtos.request.RegisterUserRequest;
import com.github.payment.api.application.dtos.response.RegisterUserResponse;
import com.github.payment.api.application.ports.in.CreateUserUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users/register")
@RequiredArgsConstructor
public class UserController {

    private final CreateUserUseCase createUserUseCase;

    @PostMapping()
    public ResponseEntity<RegisterUserResponse> createUser(@RequestBody @Valid RegisterUserRequest registerUserRequest){
        RegisterUserResponse response = createUserUseCase.createUser(registerUserRequest);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }
}
