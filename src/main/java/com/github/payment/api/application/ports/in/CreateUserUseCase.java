package com.github.payment.api.application.ports.in;

import com.github.payment.api.application.dtos.request.RegisterUserRequest;
import com.github.payment.api.application.dtos.response.RegisterUserResponse;

public interface CreateUserUseCase {

    RegisterUserResponse createUser(RegisterUserRequest userRequest);
}
