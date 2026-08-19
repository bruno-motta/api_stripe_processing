package com.github.payment.api.application.service;

import com.github.payment.api.application.dtos.request.LoginUserRequest;
import com.github.payment.api.application.dtos.response.LoginUserResponse;
import com.github.payment.api.application.ports.in.UserLoginUseCase;
import com.github.payment.api.infrastructure.persistence.repositories.UserEntityRepository;
import com.github.payment.api.infrastructure.security.JwtService;
import com.github.payment.api.infrastructure.security.UserDetailsImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.AuthenticationManager;

import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthUserService implements UserLoginUseCase {

    private final UserEntityRepository userEntityRepository;
    private final JwtService jwtService;
    private final AuthenticationManager authenticationManager;

    public LoginUserResponse login(LoginUserRequest userRequest){

         UsernamePasswordAuthenticationToken authToken = new UsernamePasswordAuthenticationToken(
                 userRequest.email(),
                 userRequest.password()
         );

        Authentication authentication = this.authenticationManager.authenticate(authToken);

        UserDetails userDetails = (UserDetails) authentication.getPrincipal();

        String token = this.jwtService.generateToken((UserDetailsImpl) userDetails);

        return new LoginUserResponse(token);
    }




}
