package com.DailyRoutineHelper.web.doc;

import com.DailyRoutineHelper.web.dto.request.RegisterDto;
import com.DailyRoutineHelper.web.dto.request.LoginDto;
import com.DailyRoutineHelper.web.dto.response.JwtResponse;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(
        name = "UserController",
        description = "Контроллер для работы с пользователем и пользовательскими данными"
)
public interface AuthDoc {

    @Operation(description = "Регистрация нового пользователя в систему")
    ResponseEntity<String> register(RegisterDto addRecipeRequest);

    @Operation(description = "Вход в систему(login)")
    ResponseEntity<JwtResponse> login(LoginDto loginDto);

}
