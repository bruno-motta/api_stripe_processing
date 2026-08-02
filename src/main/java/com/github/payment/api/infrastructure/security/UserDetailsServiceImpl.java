package com.github.payment.api.infrastructure.security;

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
    //TODO: CASTING?
    @Override
    public UserDetails loadUserByUsername(String usernameEmail) throws UsernameNotFoundException {
        return (UserDetails) userEntityRepository.findByEmail(usernameEmail)
                .orElseThrow(() -> new UsernameNotFoundException("Usuário não encontrado" + usernameEmail)
        );
    }
}
