package com.DailyRoutineHelper.web.doc;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.ResponseEntity;

@Tag(
        name = "HealthCheck",
        description = "API для проверки доступности сервиса"
)
public interface HealthCheckDoc {
    
    @Operation(description = "Метод для проверки доступности сервиса")
    ResponseEntity<String> healthCheck();

}
