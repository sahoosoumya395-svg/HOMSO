package com.homso.care.serviceimpl;

import com.homso.care.dto.request.LoginRequest;
import com.homso.care.dto.response.LoginResponse;
import com.homso.care.entity.Patient;
import com.homso.care.entity.User;
import com.homso.care.repository.PatientRepository;
import com.homso.care.repository.UserRepository;
import com.homso.care.service.AuthService;
import com.homso.care.util.JwtService;

import org.springframework.security.authentication
        .BadCredentialsException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;


@Service
public class AuthServiceImpl implements AuthService {

    private final UserRepository userRepository;
    private final PatientRepository patientRepository;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;

    public AuthServiceImpl(
            UserRepository userRepository,
            PatientRepository patientRepository,
            PasswordEncoder passwordEncoder,
            JwtService jwtService) {

        this.userRepository = userRepository;
        this.patientRepository = patientRepository;
        this.passwordEncoder = passwordEncoder;
        this.jwtService = jwtService;
    }

    @Override
    @Transactional(readOnly = true)
    public LoginResponse login(LoginRequest request) {

        String email = request.getEmail()
                .trim()
                .toLowerCase(Locale.ROOT);

        User user = userRepository.findByEmail(email)
                .orElseThrow(() -> new BadCredentialsException(
                        "Invalid email or password"));

        if (!user.isEnabled()
                || !passwordEncoder.matches(
                        request.getPassword(), user.getPassword())) {
            throw new BadCredentialsException(
                    "Invalid email or password");
        }

        String mrnNo = null;

        if (user.getRole().name().equals("PATIENT")) {

            Patient patient = patientRepository
                    .findByUserId(user.getId())
                    .orElseThrow(() -> new IllegalStateException(
                            "Patient record not found"));

            mrnNo = patient.getMrnNo();
        }

        String token = jwtService.generateToken(user);

        return new LoginResponse(
                token,
                user.getId(),
                user.getFullName(),
                user.getEmail(),
                user.getRole().name(),
                mrnNo,
                "Login successful"
        );
    }
}