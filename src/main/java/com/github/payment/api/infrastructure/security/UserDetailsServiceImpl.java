package com.github.payment.api.infrastructure.security;

import com.github.payment.api.infrastructure.persistence.entity.UserEntity;
import com.github.payment.api.infrastructure.persistence.repositories.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserEntityRepository userEntityRepository;


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserEntity userEntity = userEntityRepository.findByEmail(username).orElseThrow(
                () -> new UsernameNotFoundException("Usuario não encontrado")
        );

        return new UserDetailsImpl(userEntity);
    }
}
