package com.github.payment.api.application.service;

import com.github.payment.api.application.dtos.request.RegisterUserRequest;
import com.github.payment.api.application.dtos.response.RegisterUserResponse;
import com.github.payment.api.application.mapper.UserMapper;
import com.github.payment.api.application.ports.in.CreateUserUseCase;
import com.github.payment.api.application.ports.out.UserRepository;
import com.github.payment.api.domain.model.User;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserService implements CreateUserUseCase {

    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public RegisterUserResponse createUser(RegisterUserRequest userRequest){

        if(userRepository.existsByEmail(userRequest.email())){
            throw new IllegalArgumentException("Email informado existente, insira um e-mail válido!");
        }

        String passwordHash = passwordEncoder.encode(userRequest.password());

        User user = User.create(
                userRequest.name(),
                userRequest.email(),
                passwordHash
        );

        User savedUser = userRepository.save(user);

        return UserMapper.toResponse(savedUser);

    }

}
