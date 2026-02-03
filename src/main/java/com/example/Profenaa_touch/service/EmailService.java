package com.example.Profenaa_touch.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Service
public class EmailService {

    @Autowired
    private JavaMailSender mailSender;

    public void sendOtpEmail(String to, String otp) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(to);
            message.setSubject("Your OTP - Profenaa Touch");
            message.setText("Your OTP is: " + otp);

            mailSender.send(message);

            System.out.println("✅ OTP email sent to " + to);

        } catch (Exception e) {
            System.out.println("❌ EMAIL FAILED");
            e.printStackTrace();   // 🔥 THIS IS KEY
            throw e;
        }
    }
}



