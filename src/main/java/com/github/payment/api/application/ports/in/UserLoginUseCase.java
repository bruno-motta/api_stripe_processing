package com.github.payment.api.application.ports.in;

import com.github.payment.api.application.dtos.request.LoginUserRequest;
import com.github.payment.api.application.dtos.response.LoginUserResponse;

public interface UserLoginUseCase {

    LoginUserResponse login (LoginUserRequest request);

}
