package com.DailyRoutineHelper.web.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Schema(description = "Запрос на регистрацию пользователя со всеми нужными данными")
@AllArgsConstructor
@NoArgsConstructor
public class RegisterDto {

    @NotBlank(message = "Логин пользователя обязателен для регистрации в системе")
    @Size(min = 3, max = 255, message = "имя пользователя должно быть от 3 до 255 символов")
    @Schema(description = "логин пользователя", example = "user")
    private String username;

    @NotBlank(message = "Email пользователя обязателен для регистрации в системе")
    @Email
    @Schema(description = "email пользователя", example = "user@gmail.com")
    private String email;

    @NotBlank(message = "пароль обязателен для регистрации в системе")
    @Size(min = 8, max = 20, message = "пароль должен быть от 8 до 20 символов")
    @Schema(description = "пароль", example = "secretpassword123")
    private String password;

    @NotBlank(message = "Имя")
    @Size(min = 1, max = 50)
    @Schema(description = "Имя", example = "Имяпользователя")
    private String firstName;

    @NotBlank(message = "Фамилия")
    @Size(min = 1, max = 50)
    @Schema(description = "Фамилия", example = "Фамилияпользователя")
    private String lastName;

}
