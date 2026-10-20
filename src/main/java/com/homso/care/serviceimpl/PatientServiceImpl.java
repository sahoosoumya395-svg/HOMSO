package com.homso.care.serviceimpl;

import com.homso.care.dto.request.PatientRegistrationRequest;
import com.homso.care.dto.response.PatientRegistrationResponse;
import com.homso.care.entity.Patient;
import com.homso.care.entity.User;
import com.homso.care.enums.Role;
import com.homso.care.repository.PatientRepository;
import com.homso.care.repository.UserRepository;
import com.homso.care.service.PatientService;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Locale;
import java.util.UUID;

@Service
public class PatientServiceImpl implements PatientService {

    private final UserRepository userRepository;
    private final PatientRepository patientRepository;
    private final PasswordEncoder passwordEncoder;

    public PatientServiceImpl(
            UserRepository userRepository,
            PatientRepository patientRepository,
            PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.patientRepository = patientRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    @Transactional
    public PatientRegistrationResponse registerPatient(
            PatientRegistrationRequest request) {

        String email = request.getEmail().trim().toLowerCase(Locale.ROOT);

        if (userRepository.existsByEmail(email)) {
            throw new IllegalArgumentException(
                    "An account with this email already exists");
        }

        User user = new User();
        user.setFullName(request.getFullName().trim());
        user.setEmail(email);
        user.setPassword(passwordEncoder.encode(request.getPassword()));
        user.setPhone(request.getPhone());
        user.setRole(Role.PATIENT);
        user.setEnabled(true);

        User savedUser = userRepository.save(user);

        Patient patient = new Patient();
        patient.setUser(savedUser);
        patient.setMrnNo(generateUniqueMrn());
        patient.setDateOfBirth(request.getDateOfBirth());
        patient.setGender(request.getGender());
        patient.setBloodGroup(request.getBloodGroup());
        patient.setAddress(request.getAddress());

        Patient savedPatient = patientRepository.save(patient);

        return new PatientRegistrationResponse(
                savedPatient.getPatientId(),
                savedPatient.getMrnNo(),
                savedUser.getId(),
                savedUser.getFullName(),
                savedUser.getEmail(),
                savedUser.getPhone(),
                savedUser.getRole().name(),
                "Patient registered successfully"
        );
    }

    private String generateUniqueMrn() {
        String mrn;

        do {
            mrn = "HOM-PAT-" +
                    UUID.randomUUID()
                        .toString()
                        .replace("-", "")
                        .substring(0, 12)
                        .toUpperCase(Locale.ROOT);
        } while (patientRepository.existsByMrnNo(mrn));

        return mrn;
    }
}