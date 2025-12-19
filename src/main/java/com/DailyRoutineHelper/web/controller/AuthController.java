package com.DailyRoutineHelper.web.controller;

import com.DailyRoutineHelper.web.dto.request.RegisterDto;
import com.DailyRoutineHelper.service.AuthService;
import com.DailyRoutineHelper.web.doc.AuthDoc;
import com.DailyRoutineHelper.web.dto.request.LoginDto;
import com.DailyRoutineHelper.web.dto.response.JwtResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class AuthController implements AuthDoc {

    private final AuthService authService;

    @Override
    @PostMapping("/register")
    public ResponseEntity<String> register(@Valid @RequestBody RegisterDto registerDto) {
        authService.registerUser(registerDto);
        return ResponseEntity.ok("registered");
    }

    @Override
    @PostMapping("/login")
    public ResponseEntity<JwtResponse> login(@Valid @RequestBody LoginDto loginDto) {
        var jwtResponse = authService.generateToken(loginDto);
        return ResponseEntity.ok(jwtResponse);
    }

}
