package com.example.Profenaa_touch.Repository;

import com.example.Profenaa_touch.entity.EmailOtp;
import com.example.Profenaa_touch.entity.OtpPurpose;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface EmailOtpRepository extends JpaRepository<EmailOtp, Long> {

    Optional<EmailOtp>
    findTopByEmailAndOtpAndPurposeAndUsedFalseOrderByExpiryTimeDesc(
            String email,
            String otp,
            OtpPurpose purpose
    );
}
