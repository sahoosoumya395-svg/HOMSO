package com.homso.care.service;

import com.homso.care.dto.request.PatientRegistrationRequest;
import com.homso.care.dto.response.PatientRegistrationResponse;

public interface PatientService {

    PatientRegistrationResponse registerPatient(
            PatientRegistrationRequest request);
}