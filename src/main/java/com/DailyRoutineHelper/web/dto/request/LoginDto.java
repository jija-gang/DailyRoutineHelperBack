package com.DailyRoutineHelper.web.dto.request;

import io.swagger.v3.oas.annotations.media.Schema;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;

@Builder
@Schema(description = "Запрос на регистрацию пользователя со всеми нужными данными")
public record LoginDto(
        @NotBlank(message = "Логин пользователя обязателен для входа в систему")
        @Size(min = 3, max = 255, message = "имя пользователя должно быть от 3 до 255 символов")
        @Schema(description = "логин", example = "login@gmail.ru")
        String login,

        @NotBlank(message = "Пароль пользователя обязателен для входа в систему")
        @Size(min = 3, max = 255, message = "имя пользователя должно быть от 3 до 255 символов")
        @Schema(description = "пароль", example = "securepassword123")
        String password
) {}
