package com.DailyRoutineHelper.web.controller;

import com.DailyRoutineHelper.web.doc.HealthCheckDoc;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HealthCheck implements HealthCheckDoc {

    @GetMapping("/Health")
    public ResponseEntity<String> healthCheck() {
        return ResponseEntity.ok("fuck yeah!!!");
    }

}
