package com.github.payment.api.application.dtos.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public record LoginUserRequest(

        @Email
        @NotBlank(message = "O E-MAIL não é permitido ficar em branco.")
        @Size(max = 200, message = "Máximo de caracteres permitido.")
        String email,

        @NotBlank(message = "A senha é obrigatória e não pode conter apenas espaços.")
        @Size(min = 8, message = "A senha deve ter no mínimo 8 caracteres.")
        String password

) {
}
