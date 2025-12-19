package com.DailyRoutineHelper.service.impl;

import com.DailyRoutineHelper.web.dto.request.RegisterDto;
import com.DailyRoutineHelper.data.enums.UserRoles;
import com.DailyRoutineHelper.data.model.User;
import com.DailyRoutineHelper.exception.UserAlreadyExistsException;
import com.DailyRoutineHelper.service.AuthService;
import com.DailyRoutineHelper.storage.mapper.UserMapper;
import com.DailyRoutineHelper.storage.repository.UserRepository;
import com.DailyRoutineHelper.utils.JwtTokenUtil;
import com.DailyRoutineHelper.web.dto.request.LoginDto;
import com.DailyRoutineHelper.web.dto.response.JwtResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
@RequiredArgsConstructor
@Slf4j
public class AuthServiceImpl implements AuthService {

    private final UserMapper userMapper;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtTokenUtil jwtTokenUtil;

    @Override
    public void registerUser(RegisterDto registerDto) {
        validateRegistration(registerDto);

        User userEntity = createUserEntity(registerDto);
        userRepository.save(userEntity);

        log.info("User registered: {}", userEntity.getUsername());
    }

    private void validateRegistration(RegisterDto registerDto) {
        if (userRepository.existsByUsername(registerDto.getUsername())) {
            throw new UserAlreadyExistsException("username");
        }

        if (userRepository.existsByEmail(registerDto.getEmail())) {
            throw new UserAlreadyExistsException("email");
        }
    }

    private User createUserEntity(RegisterDto registerDto) {
        User userEntity = userMapper.toEntity(registerDto);
        userEntity.setPassword(passwordEncoder.encode(registerDto.getPassword()));
        userEntity.setCreatedAt(LocalDateTime.now());
        userEntity.setUserRole(UserRoles.USER);
        return userEntity;
    }

    public JwtResponse generateToken(LoginDto loginDto) {
        User user = userRepository.findByUsername(loginDto.login())
                .orElseThrow(() -> new RuntimeException("User not found"));

        if (!passwordEncoder.matches(loginDto.password(), user.getPassword())) {
            throw new RuntimeException("Invalid password");
        }

        String token = jwtTokenUtil.generateToken(
                user.getId(),
                "user.getRole()"
        );

        log.info("User logged in: {}", user.getUsername());

        return new JwtResponse(token);
    }

}
