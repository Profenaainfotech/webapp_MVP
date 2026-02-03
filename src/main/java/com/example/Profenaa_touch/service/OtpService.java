package com.example.Profenaa_touch.service;

import com.example.Profenaa_touch.Repository.EmailOtpRepository;
import com.example.Profenaa_touch.entity.EmailOtp;
import com.example.Profenaa_touch.entity.OtpPurpose;
import com.example.Profenaa_touch.util.OtpUtil;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class OtpService {

    private final EmailOtpRepository otpRepository;
    private final EmailService emailService;
    private final UserService userService;

    public OtpService(
            EmailOtpRepository otpRepository,
            EmailService emailService,
            UserService userService
    ) {
        this.otpRepository = otpRepository;
        this.emailService = emailService;
        this.userService = userService;
    }

    // 🔐 REGISTER OTP
    public void sendRegisterOtp(String name, String email) {

        if (userService.exists(email)) {
            throw new RuntimeException("User already registered");
        }

        saveAndSend(name, email, OtpPurpose.REGISTER);
    }

    // 🔐 LOGIN OTP
    public void sendLoginOtp(String email) {

        if (!userService.exists(email)) {
            throw new RuntimeException("Registration required");
        }

        saveAndSend(null, email, OtpPurpose.LOGIN); // 👈 name not needed
    }

    // 🔑 COMMON METHOD
    private void saveAndSend(String name, String email, OtpPurpose purpose) {

        String otp = OtpUtil.generateOtp();

        EmailOtp emailOtp = new EmailOtp();
        emailOtp.setName(name);          // ✅ safe (null for login)
        emailOtp.setEmail(email);
        emailOtp.setOtp(otp);
        emailOtp.setPurpose(purpose);
        emailOtp.setExpiryTime(LocalDateTime.now().plusMinutes(5));
        emailOtp.setUsed(false);

        otpRepository.save(emailOtp);
        emailService.sendOtpEmail(email, otp);
    }

    // 🔍 VERIFY OTP
    public void verifyOtp(String email, String otp, OtpPurpose purpose) {

        EmailOtp storedOtp = otpRepository
                .findTopByEmailAndOtpAndPurposeAndUsedFalseOrderByExpiryTimeDesc(
                        email, otp, purpose
                )
                .orElseThrow(() -> new RuntimeException("Invalid OTP"));

        if (storedOtp.getExpiryTime().isBefore(LocalDateTime.now())) {
            throw new RuntimeException("OTP expired");
        }

        storedOtp.setUsed(true);
        otpRepository.save(storedOtp);

        // ✅ REGISTER USER ONLY AFTER REGISTER OTP
        if (purpose == OtpPurpose.REGISTER) {
            userService.register(
                    storedOtp.getName(),
                    storedOtp.getEmail()
            );
        }
    }
}
