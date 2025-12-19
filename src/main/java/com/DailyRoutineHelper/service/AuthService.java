package com.DailyRoutineHelper.service;

import com.DailyRoutineHelper.web.dto.request.RegisterDto;
import com.DailyRoutineHelper.web.dto.request.LoginDto;
import com.DailyRoutineHelper.web.dto.response.JwtResponse;

public interface AuthService {
    void registerUser(RegisterDto registerDto);

    JwtResponse generateToken(LoginDto loginDto);
}
