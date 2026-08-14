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
    private OffsetDateTime updatedAt; //TODO: VERIFICAR USABILIDADE - RESPONSE/SERVICE
    private boolean active;

    private final static OffsetDateTime now = OffsetDateTime.now();

    private User(UUID id, String name, String email, String passwordHash, RoleUser role, OffsetDateTime createdAt, boolean active) {
        this.id = id;
        this.name = name;
        this.email = email;
        this.passwordHash = passwordHash;
        this.role = role;
        this.createdAt = createdAt;
        this.active = active;
    }

    private User(){};

    public static User create(String name, String email, String passwordHash){
        validateName(name);
        validateEmail(email);

        User user = new User();
        user.id = UUID.randomUUID();
        user.name = name;
        user.email = email;
        user.passwordHash = passwordHash;
        user.role = RoleUser.ROLE_USER;
        user.createdAt = now;
        user.updatedAt = now;
        user.active = true;

        return user;

    }

    public static User reconstitute(UUID id, String name, String email, String passwordHash, RoleUser role, OffsetDateTime createdAt, OffsetDateTime updatedAt, boolean active){

        User user = new User();
        user.id = id;
        user.name = name;
        user.email = email;
        user.passwordHash = passwordHash;
        user.role = role;
        user.createdAt = createdAt;
        user.updatedAt = updatedAt;
        user.active = active;

        return user;
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

        if(!email.contains("@")){
            throw new IllegalArgumentException("Informe um e-mail válido");
        }
    }

}
