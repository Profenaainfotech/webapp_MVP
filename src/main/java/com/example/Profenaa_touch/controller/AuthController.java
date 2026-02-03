package com.example.Profenaa_touch.controller;

import com.example.Profenaa_touch.Repository.UserRepository;
import com.example.Profenaa_touch.dto.AuthResponse;
import com.example.Profenaa_touch.dto.RequestOtpRequest;
import com.example.Profenaa_touch.dto.VerifyOtpRequest;
import com.example.Profenaa_touch.entity.OtpPurpose;
import com.example.Profenaa_touch.entity.User;
import com.example.Profenaa_touch.service.JwtService;
import com.example.Profenaa_touch.service.OtpService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/auth")
public class AuthController {

    private final OtpService otpService;
    private final JwtService jwtService;
    @Autowired
    private UserRepository userRepository;

    public AuthController(OtpService otpService, JwtService jwtService) {
        this.otpService = otpService;
        this.jwtService = jwtService;
    }

    // 🟢 REGISTER
    @PostMapping("/register/request-otp")
    public ResponseEntity<String> requestRegisterOtp(
            @RequestBody RequestOtpRequest request) {

        otpService.sendRegisterOtp(
                request.getName(),   // ⭐ name
                request.getEmail()
        );

        return ResponseEntity.ok("Registration OTP sent");
    }


    @PostMapping("/register/verify-otp")
    public ResponseEntity<String> verifyRegisterOtp(
            @RequestBody VerifyOtpRequest request) {

        otpService.verifyOtp(
                request.getEmail(),
                request.getOtp(),
                OtpPurpose.REGISTER
        );
        return ResponseEntity.ok("User registered successfully");
    }

    // 🔵 LOGIN
    @PostMapping("/login/request-otp")
    public ResponseEntity<String> requestLoginOtp(
            @RequestBody RequestOtpRequest request) {

        otpService.sendLoginOtp(request.getEmail());
        return ResponseEntity.ok("Login OTP sent");
    }

    @PostMapping("/login/verify-otp")
    public ResponseEntity<AuthResponse> verifyLoginOtp(
            @RequestBody VerifyOtpRequest request) {

        otpService.verifyOtp(
                request.getEmail(),
                request.getOtp(),
                OtpPurpose.LOGIN
        );


        User user = userRepository.findByEmail(request.getEmail()).orElseThrow();
        String token = jwtService.generateToken(
                user.getEmail(),
                user.getRole().name()
        );

        return ResponseEntity.ok(new AuthResponse(token));
    }
}
