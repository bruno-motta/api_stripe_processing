package com.github.payment.api.application.controllers;

import com.github.payment.api.application.dtos.request.LoginUserRequest;
import com.github.payment.api.application.dtos.response.LoginUserResponse;
import com.github.payment.api.application.ports.in.UserLoginUseCase;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/auth")
@RequiredArgsConstructor
public class AuthController {

    private final UserLoginUseCase userLoginUseCase;

    @PostMapping()
    public ResponseEntity<LoginUserResponse> login(@RequestBody @Valid LoginUserRequest request){
        LoginUserResponse response = userLoginUseCase.login(request);
        return ResponseEntity.ok(response);
    }
}
