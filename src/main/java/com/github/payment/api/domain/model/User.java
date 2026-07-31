package com.github.payment.api.domain.model;

import com.github.payment.api.domain.enuns.RoleUser;
import lombok.Getter;

import java.time.OffsetDateTime;
import java.util.UUID;

@Getter
public class User {
    
    private UUID id;
    private String name;
    private String email;
    private String passwordHash;      
    private RoleUser role;
    private OffsetDateTime createdAt;
    private OffsetDateTime updatedAt;
    private boolean active;

    private User(UUID id, String name, String email, String passwordHash, RoleUser role, OffsetDateTime createdAt, boolean active) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
        this.createdAt = createdAt;
        this.active = active;
    }

    private final static OffsetDateTime now = OffsetDateTime.now();

    public static User create(String name, String email, String passwordHash){
        validateName(name);
        validateEmail(email);

        return new User(
                UUID.randomUUID(),
                name,
                email,
                passwordHash,
                RoleUser.USER,
                OffsetDateTime.now(),
                true

        );
    }

    public void deactivate(){
        this.active = false;
        this.updatedAt = OffsetDateTime.now();
    }

    public void activate(){
        this.active = true;
        this.updatedAt = OffsetDateTime.now();
    }

    public void changeRole(RoleUser role){
        if(role == null){
            throw new IllegalArgumentException("Role não pode ser nulo.");
        }
        this.role = role;
        this.updatedAt = OffsetDateTime.now();
    }

    private static void validateName(String name){
        if(name == null || name.isBlank()){
            throw new IllegalArgumentException("O nome precisa ser informado");
        }
    }

    private static void validateEmail(String email){
        if(email == null || email.isBlank()){
            throw new IllegalArgumentException("O E-mail precisa ser informado");
        }

        if(email.contains("@")){
            throw new IllegalArgumentException("Informe um e-mail válido");
        }
    }















}
