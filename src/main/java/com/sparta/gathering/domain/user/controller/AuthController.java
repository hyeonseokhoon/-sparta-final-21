
package com.sparta.gathering.domain.user.controller;

import com.sparta.gathering.common.config.JwtTokenProvider;
import com.sparta.gathering.domain.user.dto.request.LoginRequest;
import com.sparta.gathering.domain.user.entity.User;

import com.sparta.gathering.domain.user.service.RefreshTokenService;
import com.sparta.gathering.domain.user.service.UserService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@Tag(name = "Auth", description = "인증 API")
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;
    private final JwtTokenProvider jwtTokenProvider;
    private final RefreshTokenService refreshTokenService; // 보류

    @Autowired
    public AuthController(UserService userService, JwtTokenProvider jwtTokenProvider, RefreshTokenService refreshTokenService) {
        this.userService = userService;
        this.jwtTokenProvider = jwtTokenProvider;
        this.refreshTokenService = refreshTokenService;
    }

    // 일반 로그인
    @Operation(summary = "로그인", description = "로그인을 진행합니다.")
    @PostMapping("/login")
    public ResponseEntity<Void> login(@Valid @RequestBody LoginRequest loginRequest) {
        User user = userService.authenticateUser(loginRequest.getEmail(), loginRequest.getPassword());
        String token = jwtTokenProvider.createToken(user.getId(), user.getEmail(), user.getNickName(), user.getUserRole());
        return ResponseEntity.ok()
                .header("Authorization", token)
                .build();
    }

}

