package com.homso.care.controller;

import com.homso.care.dto.request.PatientRegistrationRequest;
import com.homso.care.dto.response.PatientRegistrationResponse;
import com.homso.care.service.PatientService;

import jakarta.validation.Valid;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/patients")
public class PatientController {

    private final PatientService patientService;

    public PatientController(PatientService patientService) {
        this.patientService = patientService;
    }

    @PostMapping("/register")
    public ResponseEntity<PatientRegistrationResponse> registerPatient(
            @Valid @RequestBody PatientRegistrationRequest request) {

        PatientRegistrationResponse response =
                patientService.registerPatient(request);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(response);
    }
}