package com.DailyRoutineHelper.exception.response;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
@AllArgsConstructor
@Schema(name = "ErrorResponse", description = "Тело ответа с ошибкой")
public class ErrorResponse {

    private HttpStatus status;

    private String message;

    public int getStatus() {
        return status.value();
    }

}